package com.ytdd9527.networks.expansion.core.item.machine.cargo;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LineTransferDispatcher extends NetworkDirectional {


    private static final ItemStack AIR = new CustomItemStack(Material.AIR);


    private static final int[] BACKGROUND_SLOTS = new int[]{
            0,
            10,
            18,
            27,28,29,
            36,37,38,
            45,46,47
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
    public static final CustomItemStack TEMPLATE_BACKGROUND_STACK = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "指定需要推送的物品"
    );

    public LineTransferDispatcher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAIN_DISPATCHER);
        for (int slot : TEMPLATE_SLOTS) {
            this.getSlotsToDrop().add(slot);
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

        performPushItemOperationAsync(blockMenu);
        performGrabItemOperationAsync(blockMenu);
    }

    private void tryPushItem(@Nonnull BlockMenu blockMenu) {
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
        if (definition == null || definition.getNode() == null) {
            return;
        }

        final BlockFace direction = this.getCurrentDirection(blockMenu);
        Block targetBlock = blockMenu.getBlock().getRelative(direction);

        final BlockMenu targetMenu = StorageCacheUtils.getMenu(targetBlock.getLocation());
        if (targetMenu == null) {
            return;
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
        
        BlockMenu targetMenu = StorageCacheUtils.getMenu(currentBlock.getLocation());

        if (targetMenu == null) {
            return;
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