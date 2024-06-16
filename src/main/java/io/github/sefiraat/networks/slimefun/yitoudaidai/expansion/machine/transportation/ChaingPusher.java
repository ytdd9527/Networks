package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.transportation;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.utils.StackUtils;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ChaingPusher extends NetworkDirectional implements RecipeDisplayItem {

    // 定义推送的最大距离
    private static final int MAX_DISTANCE = 32;
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
    private static final String TICK_COUNTER_KEY = "chain_pusher_tick_counter";

    public ChaingPusher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAING_PUSHER);
        for (int slot : TEMPLATE_SLOTS) {
            this.getSlotsToDrop().add(slot);
        }
    }
    /**
     * 异步执行推送操作。
     * @param blockMenu 可能为null的BlockMenu对象
     */
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

    /**
     * 每个Tick调用的方法，用于执行推送操作。
     * @param blockMenu 可能为null的BlockMenu对象
     * @param block 当前Block对象
     */
    @Override
    protected void onTick(@Nullable BlockMenu blockMenu, @Nonnull Block block) {
        super.onTick(blockMenu, block);

        // 初始化Tick计数器
        int tickCounter = getTickCounter(block);
        tickCounter = (tickCounter + 1) % 10;

        // 每10个Tick执行一次推送操作
        if (tickCounter == 0) {
            performPushItemOperationAsync(blockMenu);
        }

        // 更新Tick计数器
        updateTickCounter(block, tickCounter);
    }

    /**
     * 获取指定方块的Tick计数器的值。
     * @param block 当前处理的方块对象
     * @return Tick计数器的整数值
     */
    private int getTickCounter(Block block) {
        // 从BlockStorage中获取与TICK_COUNTER_KEY关联的值
        String tickCounterValue = BlockStorage.getLocationInfo(block.getLocation(), TICK_COUNTER_KEY);
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
        // 将更新后的Tick计数器值存储到BlockStorage中
        BlockStorage.addBlockInfo(block.getLocation(), TICK_COUNTER_KEY, Integer.toString(tickCounter));
    }

    private void tryPushItem(@Nonnull BlockMenu blockMenu) {
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }

        final BlockFace direction = this.getCurrentDirection(blockMenu);

        Block targetBlock = blockMenu.getBlock().getRelative(direction);

        for (int i = 0; i <= MAX_DISTANCE; i++) {
            targetBlock = targetBlock.getRelative(direction);

            // 获取目标方块的BlockMenu
            final BlockMenu targetMenu = StorageCacheUtils.getMenu(targetBlock.getLocation());

            if (targetMenu == null) {
                return; // 如果没有找到BlockMenu，直接返回，结束整个方法
            }

            for (int itemSlot : this.getItemSlots()) {
                final ItemStack testItem = blockMenu.getItemInSlot(itemSlot);

                if (testItem == null || testItem.getType() == Material.AIR) {
                    continue; // 如果物品为空，继续下一个槽位
                }

                final ItemStack clone = testItem.clone();
                clone.setAmount(1);
                final ItemRequest itemRequest = new ItemRequest(clone, clone.getMaxStackSize());

                // 获取目标机器可以插入物品的所有槽位
                int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.INSERT, clone);

                for (int slot : slots) {
                    final ItemStack itemStack = targetMenu.getItemInSlot(slot);

                    if (itemStack != null && itemStack.getType() != Material.AIR) {
                        final int space = itemStack.getMaxStackSize() - itemStack.getAmount();
                        if (space > 0 && StackUtils.itemsMatch(itemRequest, itemStack, true)) {
                            itemRequest.setAmount(space);
                        } else {
                            continue; // 如果槽位已满或物品不匹配，继续下一个槽位
                        }
                    }

                    ItemStack retrieved = definition.getNode().getRoot().getItemStack(itemRequest);
                    if (retrieved != null) {
                        targetMenu.pushItem(retrieved, slots);
                        //showParticle(blockMenu.getBlock().getLocation(), direction);
                        //显示粒子
                    }

                    break; // 推送成功后退出当前槽位循环
                }
            }
        }
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
                "&a⇩频率机制⇩",
                "",
                "&7这个链式推送器有一个简单的频率控制&f：",
                "",
                "&e执行频率&f:",
                "&f-&7 每隔5-6秒自动执行一次推送",
                "&f-&7 目的: 这样做可以平稳地运行，减少服务器负载",
                "",
                "&f-&7 简而言之，链式推送器不会频繁操作，从而保持服务器流畅"
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩推送逻辑⇩",
                "&7以下是链式推送器的操作说明：",
                "",
                "&e最大推送距离&7: &f32格",
                "&e推送对象: &f机器方块的输出槽位中的物品",
                "",
                "&e运行流程&f:",
                "&f-&7 打开界面设置你所需的方向",
                "&f-&7 链式推送器从当前方块开始，沿着设定方向搜索",
                "",
                "&e推送条件&f:",
                "&f-&7 遇到可输入槽位，且物品不是空气时",
                "&f-&7 将物品从网络添加到机器的输入槽中(确保网络有足够的物品不然不进行推送)",
                "&f-&7 确保物品在机器按计划和有序地推送，避免混乱",
                "",
                "&e停止条件&f:",
                "&f-&7 达到最大推送距离(32格)",
                "&f-&7 遇到的方块为空，或者",
                "&f-&7 没有更多可推送的物品",
                "&f-&7 推送器将停止操作",
                "",
                "&e效率与秩序&f:",
                "&f-&7 此机制确保物品流动的高效性和有序性。"
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a ⇩链式推送器使用指南⇩",
                "",
                "&7链式推送器效率最大化建议：",
                "",
                "&f-&7 充分利用链式推送器范围: 每个推送器可以覆盖长达64格的距离",
                "&f-&7 确保您的布局设计能够覆盖多个机器，以实现最大效率",
                "",
                "&f-&7 避免单个机器配置: 不要仅在一个机器上使用链式推送器",
                "&f-&7 这样做会限制您的自动化系统的潜力和扩展性",
                "",
                "&f-&7请遵循这些建议，您将能够最大化每个链式推送器的工作效能，",
                "&f-&7同时保持也可以服务器流畅运行"
        ));
        return displayRecipes ;
    }
}
