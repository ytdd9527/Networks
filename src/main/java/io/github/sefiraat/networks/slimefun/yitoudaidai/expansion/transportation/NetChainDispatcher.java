package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.mooy1.infinityexpansion.InfinityExpansion;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.managers.ConfigManager;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class NetChainDispatcher extends NetworkDirectional implements RecipeDisplayItem {


    private static final ItemStack AIR = new CustomItemStack(Material.AIR);


    private static final int[] BACKGROUND_SLOTS = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 17, 18, 20, 22, 23, 27, 28, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };

    private static final int[] TEMPLATE_BACKGROUND = new int[]{16};
    private static final int[] TEMPLATE_SLOTS = new int[]{24, 25, 26};
    private static final int NORTH_SLOT = 11;
    private static final int SOUTH_SLOT = 29;
    private static final int EAST_SLOT = 21;
    private static final int WEST_SLOT = 19;
    private static final int UP_SLOT = 14;
    private static final int DOWN_SLOT = 32;
    public static final CustomItemStack TEMPLATE_BACKGROUND_STACK = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "指定需要推送的物品"
    );
    private static final String CHAIN_TICK_KEY = "DispTick";

    private static final int MAX_DISTANCE_LIMIT = 64;
    private int maxDistance;
    private int pushItemTick;
    private int grabItemTick;
    private int requiredPower;
    public NetChainDispatcher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String itemId) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAING_PUSHER);
        for (int slot : TEMPLATE_SLOTS) {
            this.getSlotsToDrop().add(slot);
        }
        loadConfigurations(itemId);
    }

    private void loadConfigurations(String itemId) {
        int defaultMaxDistance = 32;
        int defaultPushItemTick = 5;
        int defaultGrabItemTick = 10;
        int defaultRequiredPower = 5000;

        FileConfiguration config = Networks.getInstance().getConfig();
        this.maxDistance = Math.min(config.getInt("items." + itemId + ".max-distance", defaultMaxDistance), MAX_DISTANCE_LIMIT);
        this.pushItemTick = config.getInt("items." + itemId + ".pushitem-tick", defaultPushItemTick);
        this.grabItemTick = config.getInt("items." + itemId + ".grabitem-tick", defaultGrabItemTick);
        this.requiredPower = config.getInt("items." + itemId + ".required-power", defaultRequiredPower);

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

    /**
     * 每个Tick调用的方法，用于执行推送操作。
     * @param blockMenu 可能为null的BlockMenu对象
     * @param block 当前Block对象
     */
    @Override
    protected void onTick(@Nullable BlockMenu blockMenu, @Nonnull Block block) {
        super.onTick(blockMenu, block);
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
        if (definition == null || definition.getNode() == null) {
            return;
        }

        // 获取与 blockMenu 关联的 NetworkRoot 实例
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
        tryPushItemtick = (tryPushItemtick + 1) % this.pushItemTick;

        if (tryGrabItemtick == 0) {
            if (networkRoot.getRootPower() >= this.requiredPower) {
                networkRoot.removeRootPower(this.requiredPower);
                performGrabItemOperationAsync(blockMenu);
            } else {
            }
        }
        tryGrabItemtick = (tryGrabItemtick + 1) % this.grabItemTick;

        updateTickCounter(block, tryPushItemtick);
        updateTickCounter(block, tryGrabItemtick);
    }
    /**
     * 获取指定方块的Tick计数器的值。
     * @param block 当前处理的方块对象
     * @return Tick计数器的整数值
     */
    private int getTickCounter(Block block) {
        String tickCounterValue = BlockStorage.getLocationInfo(block.getLocation(), CHAIN_TICK_KEY);
        try {
            // 如果存在值，则尝试将其解析为整数
            return (tickCounterValue != null) ? Integer.parseInt(tickCounterValue) : 0;
        } catch (NumberFormatException e) {
            // 如果解析失败，则返回0
            return 0;
        }
    }

    /**
     * 更新指定方块的Tick计数器。
     * @param block 当前处理的方块对象
     * @param tickCounter 更新后的Tick计数器的值
     */
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

        for (int i = 0; i < this.maxDistance; i++) {
            final BlockMenu targetMenu = StorageCacheUtils.getMenu(targetBlock.getLocation());
            if (targetMenu == null) {
                break; // 如果没有找到BlockMenu，结束循环
            }

            for (int itemSlot : this.getItemSlots()) {
                final ItemStack testItem = blockMenu.getItemInSlot(itemSlot);
                if (testItem == null || testItem.getType() == Material.AIR || testItem.getAmount() == 0) {
                    continue; // 如果物品为空或数量为0，跳过
                }

                final ItemStack clone = testItem.clone();
                clone.setAmount(1);
                final ItemRequest itemRequest = new ItemRequest(clone, clone.getMaxStackSize());

                // 获取目标机器可以插入物品的所有槽位
                int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.INSERT, clone);

                // 仅推送输入槽上存在的物品
                boolean isItemAvailable = false;
                for (int slot : slots) {
                    final ItemStack targetItemStack = targetMenu.getItemInSlot(slot);
                    if (targetItemStack != null && targetItemStack.getType() == clone.getType()) {
                        isItemAvailable = true;
                        break; // 找到目标槽位上有相同类型的物品
                    }
                }

                if (!isItemAvailable) {
                    continue; // 如果目标机器上没有相同类型的物品，跳过推送
                }

                // 推送逻辑
                for (int slot : slots) {
                    final ItemStack itemStack = targetMenu.getItemInSlot(slot);
                    if (itemStack != null && itemStack.getType() == clone.getType() && itemStack.getAmount() < itemStack.getMaxStackSize()) {
                        int space = itemStack.getMaxStackSize() - itemStack.getAmount();
                        if (space > 0) {
                            itemRequest.setAmount(space); // 更新请求数量为可用空间
                            ItemStack retrieved = definition.getNode().getRoot().getItemStack(itemRequest);
                            if (retrieved != null && retrieved.getAmount() > 0) {
                                targetMenu.pushItem(retrieved, slot);
                                // 显示粒子效果（如果需要）
                                // showParticle(blockMenu.getBlock().getLocation(), direction);
                            }
                        }
                        break; // 推送成功后退出当前槽位循环
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

        for (int i = 0; i < this.maxDistance && currentBlock.getType() != Material.AIR; i++) {
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
    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes  = new ArrayList<>(4);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩运行频率⇩",
                "",
                "&e执行频率&f:",
                "&f-&7[&a推送频率&7]&f:&7 每次 " + this.pushItemTick + " Tick 推送一次",
                "&f-&7[&a抓取频率&7]&f:&7 每次 " + this.grabItemTick + " Tick 抓取一次",
                "",
                "&f-&7[&a1 Tick = 0.5s]"
        ));
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩电力消耗⇩",
                "",
                "&e网络电力消耗&f:",
                "&f-&7[&a推送 ⚡&7]&f:&7 " + this.requiredPower + " J 每次推送",
                "&f-&7[&a抓取 ⚡&7]&f:&7 " + this.requiredPower + " J 每次抓取",
                "",
                "&f-&7 简而言之，链式推送器不会频繁操作，从而保持服务器流畅"
        ));
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩功能⇩",
                "",
                "&e最大距离&7: &f" + this.maxDistance + "格",
                "&f-&7 可以同时推送物品且抓取物品",
                "&f-&7 推送物品需要全部改机器的输入槽上有指定的物品推送",
                "",
                "&e运行流程&f:",
                "&f-&7 打开界面设置你所需的方向",
                "&f-&7 网链调度器当前方块开始，沿着设定方向搜索",
                "",
                "&e推送条件&f:",
                "&f-&7[&a推送物品&7]&f:&7指定需要推送的物品到机器输入槽上[确保槽位上是否有指定需要推送的物品]",
                "&f-&7[&a停止条件①&7]&f:&7如果机器输入槽上没有指定需要推送的物品则不进行推送",
                "&f-&7[&a停止条件②&7]&f:&7达到最大推送距离[&f" + this.maxDistance + "格]",
                "",
                "&e抓取逻辑&f:",
                "&f-&7[&a抓取物品&7]&f:&7将输出槽上的物品全部抓取网络中",
                "&f-&7[&a停止条件&7]&f:&7达到最大抓取距离[&f" + this.maxDistance + "格]",
                "&f-&7 遇到的方块为空，或者",
                "&f-&7 没有更多可抓取的物品,或没有足够网络空间",
                "&f-&7 抓取将停止操作"
        ));
        return displayRecipes;
    }
}

//private void tryPushItem(@Nonnull BlockMenu blockMenu) {
//    final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
//    if (definition == null || definition.getNode() == null) {
//        return;
//    }
//
//    final BlockFace direction = this.getCurrentDirection(blockMenu);
//    Block targetBlock = blockMenu.getBlock().getRelative(direction);
//
//    for (int i = 0; i < MAX_DISTANCE; i++) {
//        targetBlock = targetBlock.getRelative(direction);
//        final BlockMenu targetMenu = StorageCacheUtils.getMenu(targetBlock.getLocation());
//        if (targetMenu == null) {
//            break; // 如果没有找到BlockMenu，结束循环
//        }
//
//        for (int itemSlot : this.getItemSlots()) {
//            final ItemStack testItem = blockMenu.getItemInSlot(itemSlot);
//            if (testItem == null || testItem.getType() == Material.AIR) {
//                continue; // 空槽位，跳过
//            }
//
//            final ItemStack clone = testItem.clone();
//            clone.setAmount(1);
//
//            // 获取目标机器可以插入物品的所有槽位
//            int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.INSERT, clone);
//
//            boolean isItemMatched = false;
//            for (int slot : slots) {
//                final ItemStack targetItemStack = targetMenu.getItemInSlot(slot);
//                // 检查目标槽位是否有相同类型的物品，并且没有达到最大堆叠数量
//                if (targetItemStack != null && targetItemStack.getType() == clone.getType() && targetItemStack.getAmount() < targetItemStack.getMaxStackSize()) {
//                    isItemMatched = true;
//                    break;
//                }
//            }
//
//            if (!isItemMatched) {
//                continue; // 如果没有匹配的物品，跳过当前的testItem
//            }
//
//            // 如果找到匹配的物品，执行推送逻辑
//            for (int slot : slots) {
//                final ItemStack itemStack = targetMenu.getItemInSlot(slot);
//                if (itemStack != null && itemStack.getType() == clone.getType()) {
//                    final int space = itemStack.getMaxStackSize() - itemStack.getAmount();
//                    if (space > 0) {
//                        ItemStack toPush = clone.clone();
//                        toPush.setAmount(space); // 推送剩余空间数量的物品
//                        targetMenu.pushItem(toPush, slot);
//                        // 显示粒子效果（如果需要）
//                        // showParticle(blockMenu.getBlock().getLocation(), direction);
//                    }
//                    break; // 推送成功后退出当前槽位循环
//                }
//            }
//        }
//    }
//}