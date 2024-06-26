package com.ytdd9527.networks.expansion.core.item.machine.grid;

import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.GridItemRequest;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.SupportedRecipes;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.grid.GridCache;
import io.github.sefiraat.networks.slimefun.network.grid.GridCache.DisplayMode;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class NetworkCraftingGridNewStyle extends AbstractGridNewStyle {

    private static final int[] BACKGROUND_SLOTS = {
        5, 14, 23, 32, 33, 40, 41, 42, 43, 44, 45, 48
    };

    private static final int[] DISPLAY_SLOTS = {
        0, 1, 2, 3, 4,
        9, 10, 11, 12, 13,
        18, 19, 20, 21, 22,
        27, 28, 29, 30, 31,
    };

    private static final int[] CRAFT_ITEMS = {
        6, 7, 8, 15, 16, 17, 24, 25, 26
    };

    private static final int[] INPUT_SLOTS = {
        49, 50, 51, 52, 53
    };

    private static final int CHANGE_SORT = 36;
    private static final int FILTER = 47;
    private static final int CLICK_SEARCH_SLOT = 45;
    private static final int AUTO_FILTER_SLOT = 46;
    private static final int PAGE_PREVIOUS = 37;
    private static final int PAGE_NEXT = 38;
    private static final int TOGGLE_MODE_SLOT = 39;
    private static final int ORANGE_BACKGROUND = 48;
    private static final int CRAFT_BUTTON_SLOT = 34;
    private static final int CRAFT_OUTPUT_SLOT = 35;

    private static final CustomItemStack CRAFT_BUTTON_STACK = new CustomItemStack(
        Material.CRAFTING_TABLE,
        Theme.CLICK_INFO.getColor() + "合成",
        Theme.CLICK_INFO + "左键点击: " + Theme.PASSIVE + "返回物品到网络并合成"
    );
    
    private static final Map<Location, GridCache> CACHE_MAP = new HashMap<>();

    public NetworkCraftingGridNewStyle(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        for(int slot: getInputSlots()){
            this.getSlotsToDrop().add(slot);
        }
        for(int slot: getCraftSlots()){
            this.getSlotsToDrop().add(slot);
        }
        this.getSlotsToDrop().add(getAutoFilterSlot());
        this.getSlotsToDrop().add(getCraftOutputSlot());
    }

    @Override
    @Nonnull
    protected BlockMenuPreset getPreset() {
        return new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(getBackgroundSlots());
                setSize(54);
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

                menu.replaceExistingItem(CRAFT_BUTTON_SLOT, CRAFT_BUTTON_STACK);
                menu.addMenuClickHandler(CRAFT_BUTTON_SLOT, (player, slot, item, action) -> {
                    tryCraft(menu, player);
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
            };
        };
    };
    

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

    public int[] getCraftSlots() {
        return CRAFT_ITEMS;
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

    public int getCraftOutputSlot() {
        return CRAFT_OUTPUT_SLOT;
    }

    public int getToggleModeSlot() {
        return TOGGLE_MODE_SLOT;
    }

    @Override
    protected int getFilterSlot() {
        return FILTER;
    }

    @SuppressWarnings("deprecation")
    private void tryCraft(@Nonnull BlockMenu menu, @Nonnull Player player) {
        // Get node and, if it doesn't exist - escape
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(menu.getLocation());
        if (definition.getNode() == null) {
            return;
        }

        // Return items to network
        final ItemStack itemCache = menu.getItemInSlot(CRAFT_OUTPUT_SLOT);

        if (itemCache != null && itemCache.getType() != Material.AIR) {
            definition.getNode().getRoot().addItemStack(itemCache);
        }

        // Get the recipe input
        final ItemStack[] inputs = new ItemStack[CRAFT_ITEMS.length];
        int i = 0;
        for (int recipeSlot : CRAFT_ITEMS) {
            ItemStack stack = menu.getItemInSlot(recipeSlot);
            inputs[i] = stack;
            i++;
        }

        ItemStack crafted = null;

        // Go through each slimefun recipe, test and set the ItemStack if found
        for (Map.Entry<ItemStack[], ItemStack> entry : SupportedRecipes.getRecipes().entrySet()) {
            if (SupportedRecipes.testRecipe(inputs, entry.getKey())) {
                crafted = entry.getValue().clone();
                break;
            }
        }

        // If no slimefun recipe found, try a vanilla one
        if (crafted == null) {
            crafted = Bukkit.craftItem(inputs, player.getWorld(), player);
        }

        // If no item crafted OR result doesn't fit, escape
        if (crafted.getType() == Material.AIR || !menu.fits(crafted, CRAFT_OUTPUT_SLOT)) {
            return;
        }

        // Push item
        menu.pushItem(crafted, CRAFT_OUTPUT_SLOT);

        // Let's clear down all the items
        for (int recipeSlot : CRAFT_ITEMS) {
            final ItemStack itemInSlot = menu.getItemInSlot(recipeSlot);
            if (itemInSlot != null) {
                // Grab a clone for potential retrieval
                final ItemStack itemInSlotClone = itemInSlot.clone();
                itemInSlotClone.setAmount(1);
                ItemUtils.consumeItem(menu.getItemInSlot(recipeSlot), 1, true);
                // We have consumed a slot item and now the slot it empty - try to refill
                if (menu.getItemInSlot(recipeSlot) == null) {
                    // Process item request
                    final GridItemRequest request = new GridItemRequest(itemInSlotClone, 1, player);
                    final ItemStack requestingStack = definition.getNode().getRoot().getItemStack(request);
                    if (requestingStack != null) {
                        menu.replaceExistingItem(recipeSlot, requestingStack);
                    }
                }
            }
        }
    }
}
