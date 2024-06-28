package com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import com.ytdd9527.networks.expansion.util.DisplayGroupGenerators;
import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.utils.StackUtils;
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

public class AdvancedPointTransfer extends AdvancedDirectional implements RecipeDisplayItem {


    private static final ItemStack AIR = new CustomItemStack(Material.AIR);


    private static final int[] BACKGROUND_SLOTS = new int[]{
            0,
            10,
            18,
            27,28,29,
            36,37,38
    };
    private static final int[] TEMPLATE_BACKGROUND = new int[]{
            3,
            12,
            21,
            30,
            39,
            48
    };
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
    private static final int MINUS_SLOT = 45;
    private static final int SHOW_SLOT = 46;
    private static final int ADD_SLOT = 47;
    private static final int TRANSPORT_MODE_SLOT = 36;

    public static final CustomItemStack TEMPLATE_BACKGROUND_STACK = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "指定需要推送的物品"
    );
    private static final String PUSH_KEY = "push-ticker";
    private static final String GRAB_KEY = "grab-ticker";

    private static final String KEY_UUID = "display-uuid";
    private static final int TRANSPORT_LIMIT = 64;
    private int maxDistance;
    private int pushItemTick;
    private int grabItemTick;
    private int requiredPower;

    private int freeAmount;
    private int totalAmount;

    private boolean useSpecialModel;
    private Function<Location, DisplayGroup> displayGroupGenerator;

    public AdvancedPointTransfer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String configKey) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAIN_DISPATCHER, TRANSPORT_LIMIT);
        for (int slot : TEMPLATE_SLOTS) {
            this.getSlotsToDrop().add(slot);
        }
        loadConfigurations(configKey);
    }

    private void loadConfigurations(String configKey) {
        int defaultMaxDistance = 32;
        int defaultPushItemTick = 6;
        int defaultGrabItemTick = 12;
        int defaultRequiredPower = 5000;
        boolean defaultUseSpecialModel = false;

        FileConfiguration config = Networks.getInstance().getConfig();

        // 读取配置值
        this.maxDistance = config.getInt("items." + configKey + ".max-distance", defaultMaxDistance);
        this.pushItemTick = config.getInt("items." + configKey + ".pushitem-tick", defaultPushItemTick);
        this.grabItemTick = config.getInt("items." + configKey + ".grabitem-tick", defaultGrabItemTick);
        this.requiredPower = config.getInt("items." + configKey + ".required-power", defaultRequiredPower);
        this.useSpecialModel = config.getBoolean("items." + configKey + ".use-special-model.enable", defaultUseSpecialModel);


        Map<String, Function<Location, DisplayGroup>> generatorMap = new HashMap<>();
        generatorMap.put("cloche", DisplayGroupGenerators::generateCloche);
        generatorMap.put("cell", DisplayGroupGenerators::generateCell);

        this.displayGroupGenerator = null;

        if (this.useSpecialModel) {
            String generatorKey = config.getString("items." + configKey + ".use-special-model.type");
            this.displayGroupGenerator = generatorMap.get(generatorKey);
            if (this.displayGroupGenerator == null) {
                Networks.getInstance().getLogger().warning("未知类型 '" + generatorKey + "', 模型已禁用。");
                this.useSpecialModel = false;
            }
        }
    }
    private void performPushItemOperationAsync(@Nonnull NetworkRoot root, @Nonnull Block block, @Nullable BlockMenu blockMenu) {
        if (blockMenu != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    tryPushItem(root, block, blockMenu);
                }
            }.runTaskAsynchronously(Networks.getInstance());
        }
    }
    private void performGrabItemOperationAsync(@Nonnull NetworkRoot root, @Nullable BlockMenu blockMenu) {
        if (blockMenu != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    tryGrabItem(root, blockMenu);
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
        int currentPushTick = getTickCounter(block, PUSH_KEY);
        int currentGrabTick = getTickCounter(block, GRAB_KEY);

        if (networkRoot.getRootPower() > this.requiredPower) {
            if (currentPushTick == 0) {
                networkRoot.removeRootPower(this.requiredPower);
                performPushItemOperationAsync(networkRoot, block, blockMenu);
            }
            if (currentGrabTick == 0) {
                networkRoot.removeRootPower(this.requiredPower);
                performGrabItemOperationAsync(networkRoot, blockMenu);
            }
            currentPushTick = (currentPushTick + 1) % pushItemTick;
            currentGrabTick = (currentGrabTick + 1) % grabItemTick;

            updateTickCounter(block, PUSH_KEY, currentPushTick);
            updateTickCounter(block, GRAB_KEY, currentGrabTick);
        }
    }
    private int getTickCounter(Block block, String key) {
        String value = BlockStorage.getLocationInfo(block.getLocation(), key);
        try {
            return (value != null) ? Integer.parseInt(value) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private void updateTickCounter(Block block, String key, int value) {
        BlockStorage.addBlockInfo(block.getLocation(), key, Integer.toString(value));
    }

    //! 不能直接用高级链推的替换！
    private void tryPushItem(@Nonnull NetworkRoot root, @Nonnull Block block, @Nonnull BlockMenu blockMenu) {
        final BlockFace direction = this.getCurrentDirection(blockMenu);

        Block targetBlock = blockMenu.getBlock().getRelative(direction);

        for (int i = 0; i <= maxDistance; i++) {

            final BlockMenu targetMenu = StorageCacheUtils.getMenu(targetBlock.getLocation());

            if (targetMenu == null) {
                return;
            }
            int currentLimit = getCurrentNumber(blockMenu.getBlock());
            String currentTransportMode = getCurrentTransportMode(block);

            for (int itemSlot : this.getItemSlots()) {
                final ItemStack testItem = blockMenu.getItemInSlot(itemSlot);

                if (testItem == null || testItem.getType() == Material.AIR) {
                    continue;
                }

                final ItemStack clone = testItem.clone();
                clone.setAmount(1);

                int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.INSERT, clone);

                int freeAmount = 0;
                int retrievedAmount = 0;
                // 读取模式
                switch (currentTransportMode) {
                    // 无限制模式
                    case TRANSPORT_MODE_NONE -> {
                        // 计算总共需要推送的数量
                        for (int slot : slots) {
                            final ItemStack itemStack = targetMenu.getItemInSlot(slot);
                            if (itemStack == null || itemStack.getType() == Material.AIR) {
                                freeAmount += clone.getMaxStackSize();
                            } else {
                                if (StackUtils.itemsMatch(itemStack, clone)) {
                                    freeAmount += itemStack.getMaxStackSize() - itemStack.getAmount();
                                }
                            }

                            if (freeAmount > currentLimit) {
                                freeAmount = currentLimit;
                                break;
                            }
                        }

                        // 直接推送物品
                        final ItemRequest itemRequest = new ItemRequest(clone, freeAmount);
                        ItemStack retrieved = root.getItemStack(itemRequest);
                        if (retrieved != null) {
                            targetMenu.pushItem(retrieved, slots);
                        }
                    }
                    // 仅空模式
                    case TRANSPORT_MODE_NULL_ONLY -> {
                        for (int slot : slots) {
                            // 读取每个槽的物品
                            final ItemStack itemStack = targetMenu.getItemInSlot(slot);

                            // 仅空槽会被运输
                            if (itemStack == null || itemStack.getType() == Material.AIR) {
                                // 计算需要推送的数量
                                int amount = clone.getMaxStackSize();
                                if (retrievedAmount + amount > currentLimit) {
                                    amount = currentLimit - retrievedAmount;
                                }

                                // 推送物品
                                final ItemRequest itemRequest = new ItemRequest(clone, amount);
                                ItemStack retrieved = root.getItemStack(itemRequest);

                                // 只推送到指定的格
                                if (retrieved != null) {
                                    targetMenu.pushItem(retrieved, slot);
                                    // 增加数量
                                    retrievedAmount += retrieved.getAmount();
                                }
                            }
                            if (retrievedAmount >= currentLimit) {
                                break;
                            }
                        }
                    }

                    // 仅非空模式
                    case TRANSPORT_MODE_NONNULL_ONLY -> {
                        for (int slot : slots) {
                            // 读取每个槽的物品
                            final ItemStack itemStack = targetMenu.getItemInSlot(slot);

                            // 仅非空模式本质上就是只运输到有相同物品的格子
                            if (StackUtils.itemsMatch(clone, itemStack)) {

                                // 计算需要推送的数量
                                int amount = itemStack.getMaxStackSize() - itemStack.getAmount();
                                if (retrievedAmount + amount > getCurrentNumber(blockMenu.getBlock())) {
                                    amount = currentLimit - retrievedAmount;
                                }

                                // 推送物品
                                final ItemRequest itemRequest = new ItemRequest(clone, amount);
                                ItemStack retrieved = root.getItemStack(itemRequest);

                                // 只推送到指定的格
                                if (retrieved != null) {
                                    targetMenu.pushItem(retrieved, slot);
                                    // 增加数量
                                    retrievedAmount += retrieved.getAmount();
                                }
                            }
                            if (retrievedAmount >= currentLimit) {
                                break;
                            }
                        }
                    }
                }
                
            }
            targetBlock = targetBlock.getRelative(direction);
        }
    }
    private void tryGrabItem(@Nonnull NetworkRoot root, @Nonnull BlockMenu blockMenu) {
        if (blockMenu == null) {
            return;
        }

        BlockFace direction = this.getCurrentDirection(blockMenu);
        Block currentBlock = blockMenu.getBlock().getRelative(direction);

        for (int i = 0; i < maxDistance && currentBlock.getType() != Material.AIR; i++) {
            BlockMenu targetMenu = StorageCacheUtils.getMenu(currentBlock.getLocation());

            if (targetMenu == null) {
                break;
            }
            int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.WITHDRAW, null);
            this.totalAmount = 0;
            for (int slot : slots) {
                ItemStack itemStack = targetMenu.getItemInSlot(slot);

                if (itemStack != null) {

                    if (isItemTransferable(itemStack)) {
                        if (this.totalAmount >= getCurrentNumber(blockMenu.getBlock())) {
                            break;
                        }
                        int before = itemStack.getAmount();
                        if (this.totalAmount + before > getCurrentNumber(blockMenu.getBlock())) {
                            ItemStack clone = itemStack.clone();
                            clone.setAmount(getCurrentNumber(blockMenu.getBlock()) - this.totalAmount);
                            root.addItemStack(clone);
                            if (clone.getAmount() < getCurrentNumber(blockMenu.getBlock()) - this.totalAmount) {
                                itemStack.setAmount(before-(getCurrentNumber(blockMenu.getBlock())-this.totalAmount-clone.getAmount()));
                                targetMenu.replaceExistingItem(slot, itemStack);
                            }
                        } else {
                            root.addItemStack(itemStack);

                            if (itemStack.getAmount() < before) {
                                this.totalAmount += before - itemStack.getAmount();
                                //抓取成功显示粒子
                                //showParticle(blockMenu.getBlock().getLocation(), direction);
                                targetMenu.replaceExistingItem(slot, itemStack);
                            }
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
                "&f-&7 简而言之，网链调度器不会频繁操作，从而保持服务器流畅"
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

    @Override
    protected int getMinusSlot() {
        return MINUS_SLOT;
    }

    @Override
    protected int getShowSlot() {
        return SHOW_SLOT;
    }

    @Override
    protected int getAddSlot() {
        return ADD_SLOT;
    }

    @Override
    protected int getTransportModeSlot() {
        return TRANSPORT_MODE_SLOT;
    }
}
