package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage;

import com.ytdd9527.networks.expansion.core.item.AbstractMySlimefunItem;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data.DataStorage;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StorageUnitUpgradeTable extends AbstractMySlimefunItem {

    private static Map<ItemStack[], ItemStack> recipes = new HashMap<>();
    private final int[] border = {0,8,9,17,18,26};
    private final int[] innerBorder = {1,5,6,7,10,14,16,19,23,24,25};
    private final int[] inputSlots = {2,3,4,11,12,13,20,21,22};
    private final int outputSlot = 15;
    private final int actionBtnSlot = 17;
    private final ItemStack actionBtn = new CustomItemStack(Material.REDSTONE_TORCH, "&6点击升级", "");
    private final ItemStack noOutput = new CustomItemStack(Material.BARRIER, "&c无输出", "");

    public StorageUnitUpgradeTable(
            ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                for (int slot : border) {
                    addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
                }
                ItemStack innerBorderItem = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "", "");
                for (int slot : innerBorder) {
                    addItem(slot, innerBorderItem, ChestMenuUtils.getEmptyClickHandler());
                }
                addItem(outputSlot, noOutput, new AdvancedMenuClickHandler() {
                    @Override
                    public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                        ItemStack itemInSlot = e.getInventory().getItem(slot);
                        return (cursor == null || cursor.getType() == Material.AIR) && (itemInSlot==null || itemInSlot.getType()!=Material.BARRIER);
                    }

                    @Override
                    public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                        return false;
                    }
                });
            }

            @Override
            public void newInstance(@NotNull BlockMenu menu, @NotNull Block b) {
                menu.addMenuClickHandler(actionBtnSlot, ((p, slot, item, action) -> {
                    craft(menu);
                    return false;
                }));
                menu.replaceExistingItem(actionBtnSlot, actionBtn);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || canUse(p,false);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }
        };
    }

    public static void addRecipe(ItemStack[] recipe, ItemStack out) {
        recipes.put(recipe, out);
    }

    private void craft(BlockMenu menu) {
        boolean flag = false;
        for (Map.Entry<ItemStack[], ItemStack> each : recipes.entrySet()) {
            if (match(menu, each.getKey())) {
                int id = CargoStorageUnit.getBoundId(menu.getItemInSlot(inputSlots[4]));
                ItemStack out = each.getValue().clone();
                if (id != -1) {
                    if (DataStorage.isContainerLoaded(id)) {
                        DataStorage.getCachedStorageData(id).ifPresent(this::upgrade);
                    } else {
                        // Schedule to the same thread to ensure that this task is running after data request
                        DataStorage.requestStorageData(id);
                        Networks.getQueryQueue().scheduleQuery(() -> {
                            Optional<StorageUnitData> data = DataStorage.getCachedStorageData(id);
                            data.ifPresent(this::upgrade);
                            return true;
                        });
                    }
                    out = CargoStorageUnit.bindId(out,id);
                }
                menu.replaceExistingItem(outputSlot, out);
                flag = true;
            }
        }

        if (flag) {
            for (int slot : inputSlots) {
                menu.consumeItem(slot);
            }
        } else {
            menu.replaceExistingItem(outputSlot, noOutput);
        }
    }

    private boolean match(BlockMenu menu, ItemStack[] recipe) {
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                SlimefunItem sfItem = SlimefunItem.getByItem(menu.getItemInSlot(inputSlots[i]));
                if (sfItem != null && SlimefunUtils.isItemSimilar(recipe[i], sfItem.getItem(), true, false)) {
                    continue;
                }
                return false;
            }
            if (!SlimefunUtils.isItemSimilar(recipe[i], menu.getItemInSlot(inputSlots[i]), true, false)) {
                return false;
            }
        }
        return true;
    }

    private void upgrade(StorageUnitData d) {
        StorageUnitType next = d.getSizeType().next();
        if (next != null) {
            d.setSizeType(next);
        }
    }
}
