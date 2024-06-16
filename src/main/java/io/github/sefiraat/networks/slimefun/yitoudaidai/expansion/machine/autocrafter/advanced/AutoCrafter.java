package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.autocrafter.advanced;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkObject;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;

import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.*;

public class AutoCrafter extends NetworkObject {
    private static final int[] BACKGROUND_SLOTS = {18,19,20,21,23,24,25,45,46,47,48,50,51,52,53};
    private static final int[] TEST_ITEM_SLOT = {
            0, 1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17};
    private static final int[] TEST_ITEM_BACKDROP = {22};

    private static final int[] OUTPUT_ITEM_SLOT = {
            27,28,29,30,31,32,33,34,35
    };
    private static final int[] OUTPUT_SLOT = {
            36,37,38,39,40,41,42,43,44
    };
    private static final int[] OUTPUT_ITEM_BACKDROP = {49};
    private static final Set<Location> locked = new HashSet<>(1024);
    private final int lockModeSlot = 26;
    private static final CustomItemStack TEST_BACKDROP_STACK = new CustomItemStack(
            Material.GREEN_STAINED_GLASS_PANE,
            Theme.SUCCESS + "指定输出物品"
    );

    private static final CustomItemStack OUTPUT_BACKDROP_STACK = new CustomItemStack(
            Material.ORANGE_STAINED_GLASS_PANE,
            Theme.SUCCESS + "输出栏"
    );

    private final ItemSetting<Integer> tickRate;

    public AutoCrafter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.EXPORT);
        this.tickRate = new IntRangeSetting(this, "tick_rate", 1, 1, 10);
        addItemSetting(this.tickRate);


        for (int testitemslot : TEST_ITEM_SLOT) {
            this.getSlotsToDrop().add(testitemslot);
        }
        for (int outputitemslot : OUTPUT_ITEM_SLOT) {
            this.getSlotsToDrop().add(outputitemslot);
        }
        for (int outputslot : OUTPUT_SLOT) {
            this.getSlotsToDrop().add(outputslot);
        }

        addItemHandler(
                new BlockTicker() {

                    private int tick = 1;

                    @Override
                    public boolean isSynchronized() {
                        return false;
                    }

                    @Override
                    public void tick(Block block, SlimefunItem item, SlimefunBlockData data) {
                        if (tick <= 1) {
                            final BlockMenu blockMenu = data.getBlockMenu();
                            addToRegistry(block);
                            tryFetchItem(blockMenu);

                        }
                    }

                    @Override
                    public void uniqueTick() {
                        tick = tick <= 1 ? tickRate.getValue() : tick - 1;
                    }
                },
                new BlockBreakHandler(true, true) {
                    @Override
                    public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                        BlockMenu blockMenu = StorageCacheUtils.getMenu(e.getBlock().getLocation());

                        for (int testitemslot : TEST_ITEM_SLOT) {
                            blockMenu.dropItems(blockMenu.getLocation(), testitemslot);
                        }
                        for (int outputitemslot : OUTPUT_ITEM_SLOT) {
                            blockMenu.dropItems(blockMenu.getLocation(), outputitemslot);
                        }

                    }
                }
        );
    }

    private void tryFetchItem(@Nonnull BlockMenu blockMenu) {
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
        if (definition.getNode() == null) {
            return;
        }
        NetworkRoot networkRoot = definition.getNode().getRoot();

        List<ItemRequest> itemRequests = new ArrayList<>();
        List<ItemStack> retrievedItems = new ArrayList<>();
        int totalFreeStackSpaces = 0; // 计算所有输出槽位总共还能容纳多少个物品堆叠





        // 计算所有输出槽位的总空闲空间
        for (int outputSlot : OUTPUT_ITEM_SLOT) {
            ItemStack currentStack = blockMenu.getItemInSlot(outputSlot);
            if (currentStack == null || currentStack.getType() == Material.AIR) {
                totalFreeStackSpaces += 64; // 假设最大堆叠数为64
            } else {
                totalFreeStackSpaces += (64 - currentStack.getAmount()); // 假设最大堆叠数为64
            }
        }

        // 收集所有测试槽位的物品请求
        for (int testItemSlot : TEST_ITEM_SLOT) {
            ItemStack testItem = blockMenu.getItemInSlot(testItemSlot);
            if (testItem == null || testItem.getType() == Material.AIR) {
                continue;
            }
            int itemStackSpaces = testItem.getAmount(); // 当前测试槽位物品占用的堆叠空间
            if (totalFreeStackSpaces >= itemStackSpaces) {
                itemRequests.add(new ItemRequest(testItem, itemStackSpaces));
                totalFreeStackSpaces -= itemStackSpaces; // 更新剩余空闲堆叠空间
            } else {
                // 如果没有足够的空闲空间，停止添加更多请求
                break;
            }
        }

        // 如果有物品请求，尝试批量获取物品
        if (!itemRequests.isEmpty()) {
            retrievedItems = networkRoot.getItemStacks(itemRequests);
        }

        // 分配检索到的物品到输出槽位，同时检查槽位是否已满
        for (ItemStack retrieved : retrievedItems) {
            if (retrieved == null) {
                continue;
            }

            boolean placed = false;
            for (int outputSlot : OUTPUT_ITEM_SLOT) {
                // 检查输出槽位是否为空或者是否有足够的空间
                if (canPlaceItem(blockMenu, outputSlot, retrieved)) {
                    // 将物品放入槽位
                    blockMenu.pushItem(retrieved, outputSlot);
                    placed = true;
                    break; // 跳出循环，因为请求的物品已经被放置
                }
            }

            // 如果当前检索到的物品没有被放置，则停止请求更多的物品
            if (!placed) {
                break;
            }
        }


    }
    // 辅助方法，用于检查是否可以将物品放入指定的槽位
    private boolean canPlaceItem(@Nonnull BlockMenu blockMenu, int slot, @Nonnull ItemStack itemStack) {
        ItemStack current = blockMenu.getItemInSlot(slot);
        if (current == null || current.getType() == Material.AIR) {
            // 槽位为空，可以放置物品
            return true;
        } else if (StackUtils.itemsMatch(itemStack, current) && current.getAmount() < current.getMaxStackSize()) {
            // 槽位中已有相同类型的物品，并且没有达到堆叠上限
            return true;
        }
        // 不能放置物品
        return false;
    }



    private void addBackItems(@Nonnull BlockMenu blockMenu, Map<Material, Integer> items) {
        // 将输入物品加回输入槽位
        for (Map.Entry<Material, Integer> entry : items.entrySet()) {
            for (int testItemSlot : TEST_ITEM_SLOT) {
                ItemStack testItem = blockMenu.getItemInSlot(testItemSlot);
                if (testItem == null || testItem.getType() == Material.AIR) {
                    blockMenu.pushItem(new ItemStack(entry.getKey(), entry.getValue()), testItemSlot);
                    break;
                }
            }
        }
    }
    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                for (int slot : BACKGROUND_SLOTS) {
                    addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
                }

                drawBackground(BACKGROUND_SLOTS);
                drawBackground(TEST_BACKDROP_STACK, TEST_ITEM_BACKDROP);
                drawBackground(OUTPUT_BACKDROP_STACK, OUTPUT_ITEM_BACKDROP);
                addItem(lockModeSlot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());

            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return NetworkSlimefunItems.NETWORK_GRID.canUse(player, false)
                        && Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return OUTPUT_ITEM_SLOT;
                }
                return new int[0];
            }
        };
    }

}