package com.ytdd9527.networks.expansion.core.item.machine.cargo;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import com.ytdd9527.networks.expansion.util.DisplayGroupGenerators;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class LineTransfer extends NetworkDirectional implements RecipeDisplayItem {


    private static final ItemStack AIR = new CustomItemStack(Material.AIR);


    private static final int[] BACKGROUND_SLOTS = new int[]{
            0,
            10,
            18,
            27,28,29,
            36,37,38,
            45,46,47
    };
    private static final int[] TEMPLATE_BACKGROUND = new int[]{3,
            12,
            21,
            30,
            39,
            48};
    private static final int[] TEMPLATE_SLOTS = new int[]{
            4,5,6,7,8,
            13,14,15,16,17,
            22,23,24,25,26,
            31,32,33,34,35,
            40,41,42,43,44,
            49,50,51,52,53
    };
    private static final int NORTH_SLOT = 1;
    private static final int SOUTH_SLOT = 19;
    private static final int EAST_SLOT = 11;
    private static final int WEST_SLOT = 9;
    private static final int UP_SLOT = 2;
    private static final int DOWN_SLOT = 20;
    public static final CustomItemStack TEMPLATE_BACKGROUND_STACK = new CustomItemStack(
            Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "指定需要推送的物品"
    );
    private static final String CHAIN_TICK_KEY = "chain_DispTick";

    private static final String KEY_UUID = "display-uuid";
    private static final int MAX_DISTANCE_LIMIT = 100;
    private int maxDistance;
    private int pushItemTick;
    private int grabItemTick;
    private int requiredPower;

    private boolean useSpecialModel;
    private Function<Location, DisplayGroup> displayGroupGenerator;

    public LineTransfer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String itemId) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAIN_DISPATCHER);
        for (int slot : TEMPLATE_SLOTS) {
            this.getSlotsToDrop().add(slot);
        }
        loadConfigurations(itemId);
    }

    private void loadConfigurations(String itemId) {
        int defaultMaxDistance = 32;
        int defaultPushItemTick = 1;
        int defaultGrabItemTick = 1;
        int defaultRequiredPower = 5000;
        boolean defaultUseSpecialModel = false;

        FileConfiguration config = Networks.getInstance().getConfig();

        this.maxDistance = Math.min(config.getInt("items." + itemId + ".max-distance", defaultMaxDistance), MAX_DISTANCE_LIMIT);
        this.pushItemTick = config.getInt("items." + itemId + ".pushitem-tick", defaultPushItemTick);
        this.grabItemTick = config.getInt("items." + itemId + ".grabitem-tick", defaultGrabItemTick);
        this.requiredPower = config.getInt("items." + itemId + ".required-power", defaultRequiredPower);
        this.useSpecialModel = config.getBoolean("items." + itemId + ".use-special-model.enable", defaultUseSpecialModel);


        Map<String, Function<Location, DisplayGroup>> generatorMap = new HashMap<>();
        generatorMap.put("cloche", DisplayGroupGenerators::generateCloche);
        generatorMap.put("cell", DisplayGroupGenerators::generateCell);

        this.displayGroupGenerator = null;

        if (this.useSpecialModel) {
            String generatorKey = config.getString("items." + itemId + ".use-special-model.type");
            this.displayGroupGenerator = generatorMap.get(generatorKey);
            if (this.displayGroupGenerator == null) {
                Networks.getInstance().getLogger().warning("未知类型 '" + generatorKey + "', 模型已禁用。");
                this.useSpecialModel = false;
            }
        }
    }
    private void performPushItemOperationAsync(@Nullable BlockMenu blockMenu) {
        if (blockMenu != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    tryPushItem(blockMenu);
                }
            }.runTaskAsynchronously(Networks.getInstance());
        }
    }
    private void performGrabItemOperationAsync(@Nullable BlockMenu blockMenu) {
        if (blockMenu != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    tryGrabItem(blockMenu);
                }
            }.runTaskAsynchronously(Networks.getInstance());
        }
    }
    @Override
    protected void onTick(@Nullable BlockMenu blockMenu, @Nonnull Block block) {
        super.onTick(blockMenu, block);
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
        if (definition == null || definition.getNode() == null) {
            return;
        }
        NetworkRoot networkRoot = definition.getNode().getRoot();
        int tryPushItemtick = getTickCounter(block);
        int tryGrabItemtick = getTickCounter(block);
        if (tryPushItemtick == 0) {
            if (networkRoot.getRootPower() >= this.requiredPower) {
                networkRoot.removeRootPower(this.requiredPower);
                performPushItemOperationAsync(blockMenu);
            } else {
            }
        }
        tryPushItemtick = (tryPushItemtick + 1) % pushItemTick;

        if (tryGrabItemtick == 0) {
            if (networkRoot.getRootPower() >= this.requiredPower) {
                networkRoot.removeRootPower(this.requiredPower);
                performGrabItemOperationAsync(blockMenu);
            } else {
            }
        }
        tryGrabItemtick = (tryGrabItemtick + 1) % grabItemTick;

        updateTickCounter(block, tryPushItemtick);
        updateTickCounter(block, tryGrabItemtick);
    }
    private int getTickCounter(Block block) {
        String tickCounterValue = BlockStorage.getLocationInfo(block.getLocation(), CHAIN_TICK_KEY);
        try {
            return (tickCounterValue != null) ? Integer.parseInt(tickCounterValue) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private void updateTickCounter(Block block, int tickCounter) {
        BlockStorage.addBlockInfo(block.getLocation(), CHAIN_TICK_KEY, Integer.toString(tickCounter));
    }
    private void tryPushItem(@Nonnull BlockMenu blockMenu) {
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
        if (definition == null || definition.getNode() == null) {
            return;
        }

        final BlockFace direction = this.getCurrentDirection(blockMenu);
        Block targetBlock = blockMenu.getBlock().getRelative(direction);

        for (int i = 0; i < maxDistance; i++) {

            final BlockMenu targetMenu = StorageCacheUtils.getMenu(targetBlock.getLocation());

            if (targetMenu == null) {
                break;
            }

            for (int itemSlot : this.getItemSlots()) {

                final ItemStack testItem = blockMenu.getItemInSlot(itemSlot);

                if (testItem == null || testItem.getType() == Material.AIR || testItem.getAmount() == 0) {
                    continue;
                }
                final ItemStack clone = testItem.clone();

                clone.setAmount(1);

                final ItemRequest itemRequest = new ItemRequest(clone, clone.getMaxStackSize());

                int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.INSERT, clone);

                boolean isItemAvailable = false;

                for (int slot : slots) {
                    final ItemStack targetItemStack = targetMenu.getItemInSlot(slot);
                    if (targetItemStack != null && targetItemStack.getType() == clone.getType()) {
                        isItemAvailable = true;
                        break;
                    }
                }

                if (!isItemAvailable) {
                    continue;
                }
                for (int slot : slots) {
                    final ItemStack itemStack = targetMenu.getItemInSlot(slot);
                    if (itemStack != null && itemStack.getType() == clone.getType() && itemStack.getAmount() < itemStack.getMaxStackSize()) {
                        int space = itemStack.getMaxStackSize() - itemStack.getAmount();
                        if (space > 0) {
                            itemRequest.setAmount(space);
                            ItemStack retrieved = definition.getNode().getRoot().getItemStack(itemRequest);
                            if (retrieved != null && retrieved.getAmount() > 0) {
                                targetMenu.pushItem(retrieved, slot);
                                // 显示粒子效果（如果需要）
                                // showParticle(blockMenu.getBlock().getLocation(), direction);
                            }
                        }
                        break;
                    }
                }
            }
            targetBlock = targetBlock.getRelative(direction);
        }
    }
    private void tryGrabItem(@Nonnull BlockMenu blockMenu) {
        if (blockMenu == null) {
            return;
        }

        NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }

        BlockFace direction = this.getCurrentDirection(blockMenu);
        Block currentBlock = blockMenu.getBlock().getRelative(direction);

        for (int i = 0; i < maxDistance; i++) {
            if (currentBlock == null) {
                break;
            }
            if (currentBlock.getType() == Material.AIR) {
                break;
            }

            BlockMenu targetMenu = StorageCacheUtils.getMenu(currentBlock.getLocation());
            if (targetMenu == null) {
                break;
            }
            int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.WITHDRAW, null);
            for (int slot : slots) {
                ItemStack itemStack = targetMenu.getItemInSlot(slot);

                if (itemStack != null) {

                    if (isItemTransferable(itemStack)) {
                        int before = itemStack.getAmount();

                        definition.getNode().getRoot().addItemStack(itemStack);

                        if (itemStack.getAmount() < before) {
                            //抓取成功显示粒子
                            //showParticle(blockMenu.getBlock().getLocation(), direction);
                            targetMenu.replaceExistingItem(slot, itemStack);
                        }
                    }
                }
            }
            currentBlock = currentBlock.getRelative(direction);
        }
    }

    /**
     * 检查物品是否可传输。
     * @param itemStack 要检查的物品堆栈
     * @return 如果物品可传输返回true，否则返回false
     */
    private boolean isItemTransferable(@Nonnull ItemStack itemStack) {
        return itemStack != null && itemStack.getType() != Material.AIR;
    }
    @Nonnull
    @Override
    protected int[] getBackgroundSlots() {
        return BACKGROUND_SLOTS;
    }

    @Nullable
    @Override
    protected int[] getOtherBackgroundSlots() {
        return TEMPLATE_BACKGROUND;
    }

    @Nullable
    @Override
    protected CustomItemStack getOtherBackgroundStack() {
        return TEMPLATE_BACKGROUND_STACK;
    }

    @Override
    public int getNorthSlot() {
        return NORTH_SLOT;
    }
    @Override
    public int getSouthSlot() {
        return SOUTH_SLOT;
    }
    @Override
    public int getEastSlot() {
        return EAST_SLOT;
    }
    @Override
    public int getWestSlot() {
        return WEST_SLOT;
    }
    @Override
    public int getUpSlot() {
        return UP_SLOT;
    }
    @Override
    public int getDownSlot() {
        return DOWN_SLOT;
    }
    @Override
    public int[] getItemSlots() {
        return TEMPLATE_SLOTS;
    }
    @Override
    public void preRegister() {
        if (useSpecialModel) {
            addItemHandler(new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                    e.getBlock().setType(Material.BARRIER);
                    setupDisplay(e.getBlock().getLocation());
                }
            });
        }

        // 添加破坏处理器，不管 useSpecialModel 的值如何，破坏时的逻辑都应该执行
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Location location = e.getBlock().getLocation();
                removeDisplay(location);
                e.getBlock().setType(Material.AIR);
            }
        });
    }
    private void setupDisplay(@Nonnull Location location) {
        if (this.displayGroupGenerator != null) {
            DisplayGroup displayGroup = this.displayGroupGenerator.apply(location.clone().add(0.5, 0, 0.5));
            StorageCacheUtils.setData(location, KEY_UUID, displayGroup.getParentUUID().toString());
        }
    }
    private void removeDisplay(@Nonnull Location location) {
        DisplayGroup group = getDisplayGroup(location);
        if (group != null) {
            group.remove();
        }
    }
    @Nullable
    private UUID getDisplayGroupUUID(@Nonnull Location location) {
        String uuid = StorageCacheUtils.getData(location, KEY_UUID);
        if (uuid == null) {
            return null;
        }
        return UUID.fromString(uuid);
    }
    @Nullable
    private DisplayGroup getDisplayGroup(@Nonnull Location location) {
        UUID uuid = getDisplayGroupUUID(location);
        if (uuid == null) {
            return null;
        }
        return DisplayGroup.fromUUID(uuid);
    }
    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes  = new ArrayList<>(4);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩运行频率⇩",
                "",
                "&e执行频率&f:",
                "&f-&7[&a推送频率&7]&f:&7 每 &6" + pushItemTick + " SfTick &7推送一次",
                "&f-&7[&a抓取频率&7]&f:&7 每 &6" + grabItemTick + " SfTick &7抓取一次",
                "&f-&7[&a1 SfTick=0.5s]",
                "",
                "&f-&7 简而言之，链式推送器不会频繁操作，从而保持服务器流畅"
        ));
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩电力消耗⇩",
                "",
                "&e网络电力消耗&f:",
                "&f-&7[&a推送 ⚡&7]&f: &6" + requiredPower + " J&7 每次推送",
                "&f-&7[&a抓取 ⚡&7]&f: &6" + requiredPower + " J&7 每次抓取"
        ));
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩功能⇩",
                "",
                "&e最大距离&7: &6" + maxDistance + "格",
                "&f-&7 可以同时推送物品且抓取物品",
                "&f-&7 推送物品需要全部该机器的输入槽上有指定的物品推送",
                "",
                "&e运行流程&f:",
                "&f-&7 打开界面设置你所需的方向",
                "&f-&7 网链调度器当前方块开始，沿着设定方向搜索",
                "",
                "&e推送条件&f:",
                "&f-&7[&a推送物品&7]&f:&7指定需要推送的物品到机器输入槽上[确保槽位上是否有指定需要推送的物品]",
                "&f-&7[&a停止条件①&7]&f:&7如果机器输入槽上没有指定需要推送的物品则不进行推送",
                "&f-&7[&a停止条件②&7]&f:&7达到最大推送距离[&6" + maxDistance + "格&7]",
                "",
                "&e抓取逻辑&f:",
                "&f-&7[&a抓取物品&7]&f:&7将输出槽上的物品全部抓取网络中",
                "&f-&7[&a停止条件&7]&f:&7达到最大抓取距离[&6" + maxDistance + "格&7]",
                "&f-&7 遇到的方块为空，或者",
                "&f-&7 没有更多可抓取的物品,或没有足够网络空间",
                "&f-&7 抓取将停止操作"
        ));
        return displayRecipes;
    }
}