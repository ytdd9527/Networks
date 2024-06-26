package com.ytdd9527.networks.expansion.core.item.machine.grid;

import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.SupportedRecipes;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.grid.GridCache;
import io.github.sefiraat.networks.slimefun.network.grid.GridCache.DisplayMode;
import io.github.sefiraat.networks.slimefun.tools.CraftingBlueprint;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class NetworkEncodingGridNewStyle extends AbstractGridNewStyle {

    private static final int[] BACKGROUND_SLOTS = {
        5, 14, 23, 40, 41, 42, 43, 44, 45, 48
    };

    private static final int[] DISPLAY_SLOTS = {
        0, 1, 2, 3, 4,
        9, 10, 11, 12, 13,
        18, 19, 20, 21, 22,
        27, 28, 29, 30, 31
    };

    private static final int[] INPUT_SLOTS = {
        49, 50, 51, 52, 53
    };

    private static final int[] RECIPE_SLOTS = {
        6, 7, 8,
        15, 16, 17,
        24, 25, 26
    };

    private static final int BLUEPRINT_BACK = 32;

    private static final int CHANGE_SORT = 36;
    private static final int CLICK_SEARCH_SLOT = 45;
    private static final int FILTER = 47;
    private static final int AUTO_FILTER_SLOT = 46;
    private static final int PAGE_PREVIOUS = 37;
    private static final int PAGE_NEXT = 38;
    private static final int TOGGLE_MODE_SLOT = 39;
    private static final int ORANGE_BACKGROUND = 48;

    private static final int BLANK_BLUEPRINT_SLOT = 33;
    private static final int CHARGE_COST = 2000;
    private static final int ENCODE_SLOT = 34;
    private static final int OUTPUT_SLOT = 35;

    public static final CustomItemStack BLUEPRINT_BACK_STACK = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "空白蓝图 →"
    );

    public static final CustomItemStack ENCODE_STACK = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "点击此处进行编码"
    );

    private static final Map<Location, GridCache> CACHE_MAP = new HashMap<>();

    public NetworkEncodingGridNewStyle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        for(int slot: getInputSlots()){
            this.getSlotsToDrop().add(slot);
        }
        this.getSlotsToDrop().add(getAutoFilterSlot());
        for(int slot: getRecipeSlots()){
            this.getSlotsToDrop().add(slot);
        }
        this.getSlotsToDrop().add(getOutputSlot());
        this.getSlotsToDrop().add(getBlankBlueprintSlot());
    }

    @Override
    @Nonnull
    protected BlockMenuPreset getPreset() {
        return new BlockMenuPreset(this.getId(), this.getItemName()) {

            @SuppressWarnings("deprecation")
            @Override
            public void init() {
                drawBackground(getBackgroundSlots());
                setSize(54);
                addItem(ENCODE_SLOT, ENCODE_STACK, (player, i, itemStack, clickAction) -> false);
                addItem(BLUEPRINT_BACK, BLUEPRINT_BACK_STACK, (player, i, itemStack, clickAction) -> false);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return NetworkSlimefunItems.NETWORK_GRID.canUse(player, false)
                    && Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return getInputSlots();
                }
                return new int[0];
            }

            @SuppressWarnings("deprecation")
            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
                getCacheMap().put(menu.getLocation(), new GridCache(0, 0, GridCache.SortOrder.ALPHABETICAL));

                menu.replaceExistingItem(getPagePrevious(), getPagePreviousStack());
                menu.addMenuClickHandler(getPagePrevious(), (p, slot, item, action) -> {
                    GridCache gridCache = getCacheMap().get(menu.getLocation());
                    gridCache.setPage(gridCache.getPage() <= 0 ? 0 : gridCache.getPage() - 1);
                    getCacheMap().put(menu.getLocation(), gridCache);
                    return false;
                });

                menu.replaceExistingItem(getPageNext(), getPageNextStack());
                menu.addMenuClickHandler(getPageNext(), (p, slot, item, action) -> {
                    GridCache gridCache = getCacheMap().get(menu.getLocation());
                    gridCache.setPage(gridCache.getPage() >= gridCache.getMaxPages() ? gridCache.getMaxPages() : gridCache.getPage() + 1);
                    getCacheMap().put(menu.getLocation(), gridCache);
                    return false;
                });

                menu.replaceExistingItem(getChangeSort(), getChangeSortStack());
                menu.addMenuClickHandler(getChangeSort(), (p, slot, item, action) -> {
                    GridCache gridCache = getCacheMap().get(menu.getLocation());
                    if (gridCache.getSortOrder() == GridCache.SortOrder.ALPHABETICAL) {
                        gridCache.setSortOrder(GridCache.SortOrder.NUMBER);
                    } else {
                        gridCache.setSortOrder(GridCache.SortOrder.ALPHABETICAL);
                    }
                    getCacheMap().put(menu.getLocation(), gridCache);
                    return false;
                });

                menu.replaceExistingItem(getFilterSlot(), getFilterStack());
                menu.addMenuClickHandler(getFilterSlot(), (p, slot, item, action) -> {
                    GridCache gridCache = getCacheMap().get(menu.getLocation());
                    return setFilter(p, menu, gridCache, action);
                });

                menu.replaceExistingItem(getOrangeBackgroud(), getAutoFilterStack());

                for (int displaySlot : getDisplaySlots()) {
                    menu.replaceExistingItem(displaySlot, null);
                    menu.addMenuClickHandler(displaySlot, (p, slot, item, action) -> false);
                }

                menu.addMenuClickHandler(ENCODE_SLOT, (player, i, itemStack, clickAction) -> {
                    tryEncode(player, menu);
                    return false;
                });

                menu.replaceExistingItem(getClickSearchSlot(), getClickSearchStack());
                menu.addMenuClickHandler(getClickSearchSlot(), (p, slot, item, action) -> {
                    GridCache gridCache = getCacheMap().get(menu.getLocation());
                    return autoSetFilter(p, menu, gridCache, action);
                });

                menu.replaceExistingItem(getToggleModeSlot(), getModeStack(DisplayMode.DISPLAY));
                menu.addMenuClickHandler(getToggleModeSlot(), (p, slot, item, action) -> {
                    GridCache gridCache = getCacheMap().get(menu.getLocation());
                    gridCache.toggleDisplayMode();
                    menu.replaceExistingItem(getToggleModeSlot(), getModeStack(gridCache));
                    return false;
                });

            }
        };
    }

    @Nonnull
    public Map<Location, GridCache> getCacheMap() {
        return CACHE_MAP;
    }

    public int[] getBackgroundSlots() {
        return BACKGROUND_SLOTS;
    }

    public int[] getDisplaySlots() {
        return DISPLAY_SLOTS;
    }

    public int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    public int[] getRecipeSlots() {
        return RECIPE_SLOTS;
    }

    public int getChangeSort() {
        return CHANGE_SORT;
    }

    public int getClickSearchSlot() {
        return CLICK_SEARCH_SLOT;
    }

    public int getPagePrevious() {
        return PAGE_PREVIOUS;
    }

    public int getPageNext() {
        return PAGE_NEXT;
    }
    public int getOrangeBackgroud() {
        return ORANGE_BACKGROUND;
    }

    public int getAutoFilterSlot() {
        return AUTO_FILTER_SLOT;
    }

    public int getOutputSlot() {
        return OUTPUT_SLOT;
    }

    public int getBlankBlueprintSlot() {
        return BLANK_BLUEPRINT_SLOT;
    }

    public int getToggleModeSlot() {
        return TOGGLE_MODE_SLOT;
    }

    @Override
    protected int getFilterSlot() {
        return FILTER;
    }

    @SuppressWarnings("deprecation")
    public void tryEncode(@Nonnull Player player, @Nonnull BlockMenu blockMenu) {
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }

        final NetworkRoot root = definition.getNode().getRoot();
        final long networkCharge = root.getRootPower();

        if (networkCharge < CHARGE_COST) {
            player.sendMessage(Theme.WARNING + "网络中的电力不足，无法完成该任务");
            return;
        }

        ItemStack blueprint = blockMenu.getItemInSlot(BLANK_BLUEPRINT_SLOT);

        if (!(SlimefunItem.getByItem(blueprint) instanceof CraftingBlueprint)) {
            player.sendMessage(Theme.WARNING + "你需要提供一个空的合成蓝图");
            return;
        }

        // Get the recipe input
        final ItemStack[] inputs = new ItemStack[RECIPE_SLOTS.length];
        int i = 0;
        for (int recipeSlot : RECIPE_SLOTS) {
            ItemStack stackInSlot = blockMenu.getItemInSlot(recipeSlot);
            if (stackInSlot != null) {
                inputs[i] = new ItemStack(stackInSlot);
                inputs[i].setAmount(1);
            }
            i++;
        }

        ItemStack crafted = null;

        // Go through each slimefun recipe, test and set the ItemStack if found
        for (Map.Entry<ItemStack[], ItemStack> entry : SupportedRecipes.getRecipes().entrySet()) {
            if (SupportedRecipes.testRecipe(inputs, entry.getKey())) {
                crafted = new ItemStack(entry.getValue().clone());
                break;
            }
        }

        // If no slimefun recipe found, try a vanilla one
        if (crafted == null) {
            crafted = Bukkit.craftItem(inputs.clone(), player.getWorld(), player);
        }

        // If no item crafted OR result doesn't fit, escape
        if (crafted.getType() == Material.AIR) {
            player.sendMessage(Theme.WARNING + "这似乎不是一个有效的配方");
            return;
        }

        final ItemStack blueprintClone = StackUtils.getAsQuantity(blueprint, 1);

        CraftingBlueprint.setBlueprint(blueprintClone, inputs, crafted);

        if (blockMenu.fits(blueprintClone, OUTPUT_SLOT)) {
            blueprint.setAmount(blueprint.getAmount() - 1);
            for (int recipeSlot : RECIPE_SLOTS) {
                ItemStack slotItem = blockMenu.getItemInSlot(recipeSlot);
                if (slotItem != null) {
                    slotItem.setAmount(slotItem.getAmount() - 1);
                }
            }
            blockMenu.pushItem(blueprintClone, OUTPUT_SLOT);
        } else {
            player.sendMessage(Theme.WARNING + "需要清空输出烂");
        }

        root.removeRootPower(CHARGE_COST);
    }
}
