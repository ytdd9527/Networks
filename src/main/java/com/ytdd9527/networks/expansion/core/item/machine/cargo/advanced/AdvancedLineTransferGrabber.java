package com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import com.ytdd9527.networks.expansion.util.DisplayGroupGenerators;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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

public class AdvancedLineTransferGrabber extends AdvancedDirectional implements RecipeDisplayItem {

    private static final String TICK_COUNTER_KEY = "chain_grabber_plus_tick_counter";
    private static final String KEY_UUID = "display-uuid";
    private boolean useSpecialModel;
    private Function<Location, DisplayGroup> displayGroupGenerator;
    private static final ItemStack AIR = new CustomItemStack(Material.AIR);
    private static final int TRANSPORT_LIMIT = 576;

    private static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 16, 17, 18, 19, 21, 23, 24, 25, 26, 27, 28, 29, 21, 31, 32, 34, 35, 39, 40, 41, 42, 43, 44
    };
    // 抓取不需要设置运输模式
    private static final int TRANSPORT_MODE_SLOT = -1;
    private static final int MINUS_SLOT = 36;
    private static final int SHOW_SLOT = 37;
    private static final int ADD_SLOT = 38;

    private int grabItemTick;
    private int maxDistance;

    private int totalAmount;

    public AdvancedLineTransferGrabber(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String configKey) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAIN_GRABBER, TRANSPORT_LIMIT);
        loadConfigurations(configKey);
    }

    private void loadConfigurations(String configKey) {
        FileConfiguration config = Networks.getInstance().getConfig();

        int defaultMaxDistance = 32;
        int defaultGrabItemTick = 1;
        boolean defaultUseSpecialModel = false;

        this.maxDistance = config.getInt("items." + configKey + ".max-distance", defaultMaxDistance);
        this.grabItemTick = config.getInt("items." + configKey + ".grabitem-tick", defaultGrabItemTick);
        this.useSpecialModel = config.getBoolean("items." + configKey + ".use-special-model.enable", defaultUseSpecialModel);


        Map<String, Function<Location, DisplayGroup>> generatorMap = new HashMap<>();
        generatorMap.put("cloche", DisplayGroupGenerators::generateCloche);

        this.displayGroupGenerator = null;

        if (this.useSpecialModel) {
            String generatorKey = config.getString("items." + configKey + ".use-special-model.type");
            this.displayGroupGenerator = generatorMap.get(generatorKey);
            if (this.displayGroupGenerator == null) {
                Networks.getInstance().getLogger().warning("未知的展示组类型 '" + generatorKey + "', 特殊模型已禁用。");
                this.useSpecialModel = false;
            }
        }

    }
    private void performGrabbingOperationAsync(@Nullable BlockMenu blockMenu) {
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

        int tickCounter = getTickCounter(block);
        tickCounter = (tickCounter + 1) % grabItemTick;

        if (tickCounter == 0) {
            performGrabbingOperationAsync(blockMenu);
        }
        updateTickCounter(block, tickCounter);
    }
    private int getTickCounter(Block block) {

        String tickCounterValue = BlockStorage.getLocationInfo(block.getLocation(), TICK_COUNTER_KEY);
        try {

            return (tickCounterValue != null) ? Integer.parseInt(tickCounterValue) : 0;
        } catch (NumberFormatException e) {

            return 0;
        }
    }
    private void updateTickCounter(Block block, int tickCounter) {

        BlockStorage.addBlockInfo(block.getLocation(), TICK_COUNTER_KEY, Integer.toString(tickCounter));
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
                            definition.getNode().getRoot().addItemStack(clone);
                            if (clone.getAmount() < getCurrentNumber(blockMenu.getBlock()) - this.totalAmount) {
                                itemStack.setAmount(before-(getCurrentNumber(blockMenu.getBlock())-this.totalAmount-clone.getAmount()));
                                targetMenu.replaceExistingItem(slot, itemStack);
                            }
                        } else {
                            definition.getNode().getRoot().addItemStack(itemStack);

                            if (itemStack.getAmount() < before) {
                                totalAmount += before - itemStack.getAmount();
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
    private boolean isItemTransferable(@Nonnull ItemStack itemStack) {
        return itemStack != null && itemStack.getType() != Material.AIR;
    }
    @Override
    protected Particle.DustOptions getDustOptions() {
        return new Particle.DustOptions(Color.LIME, 5);
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

    @Override
    protected int[] getBackgroundSlots() {
        return BACKGROUND_SLOTS;
    }

    protected int getMinusSlot() {
        return MINUS_SLOT;
    }

    protected int getShowSlot() {
        return SHOW_SLOT;
    }

    protected int getAddSlot() {
        return ADD_SLOT;
    }


    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes  = new ArrayList<>(6);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩运行频率⇩",
                "",
                "&e执行频率&f:",
                "&f-&7[&a抓取频率&7]&f:&7 每 &6" + grabItemTick + " SfTick &7抓取一次",
                "&f-&7[&a1 SfTick=0.5s]",
                "",
                "&f-&7 简而言之，链式推送器不会频繁操作，从而保持服务器流畅"
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩功能⇩",
                "",
                "&e最大距离&7: &6"+maxDistance+"格",
                "",
                "&e运行流程&f:",
                "&f-&7 打开界面设置你所需的方向",
                "&f-&7 网络链式抓取器当前方块开始，沿着设定方向搜索",
                "",
                "&e抓取逻辑&f:",
                "&f-&7[&a抓取物品&7]&f:&7将输出槽上的物品全部抓取网络中",
                "&f-&7[&a停止条件&7]&f:&7达到最大抓取距离[&6"+maxDistance+"格]",
                "&f-&7 遇到的方块为空，或者",
                "&f-&7 没有更多可抓取的物品,或没有足够网络空间",
                "&f-&7 抓取将停止操作"
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩使用指南⇩",
                "",
                "&7网络链式抓取器效率最大化建议：",
                "",
                "&f-&7 如果你使用网络链式抓取器就没必要给机器继续使用抓取器了",
                "&f-&7 不要双管齐下多此一举",
                "",
                "&f-&7 充分利用网络链式抓取器范围: 每次抓取物品可以覆盖长达&7[&6"+maxDistance+"格&7]的距离",
                "&f-&7 确保您的布局设计能够覆盖多个机器，以实现最大效率",
                "",
                "&f-&7 避免单个机器配置: 不要仅在一个机器上使用链式抓取器",
                "&f-&7 这样做会限制您的自动化系统的潜力和扩展性",
                "",
                "&f-&7请遵循这些建议，您将能够最大化每个链式抓取器的工作效能，",
                "&f-&7同时保持也可以服务器流畅运行"
        ));
        return displayRecipes ;
    }

    @Override
    protected int getTransportModeSlot() {
        return TRANSPORT_MODE_SLOT;
    }
}
