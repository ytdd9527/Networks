package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public enum TransportMode {

    ACCEPT(new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&a正在传输", "")),
    REJECT(new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&c暂停传输", "")),
    IN_ONLY(new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "&b仅输入", "")),
    OUT_ONLY(new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, "&e仅输出", ""));

    private final Map<TransportMode, ItemStack> displayItems = new HashMap<>();

    TransportMode(ItemStack displayItem) {
        displayItems.put(this, displayItem);
    }

    public ItemStack getDisplayItem() {
        return displayItems.get(this);
    }

    public boolean canInput() {
        switch (this) {
            case ACCEPT:
            case IN_ONLY:
                return true;
            default:
                return false;
        }
    }

    public boolean canOutput() {
        switch (this) {
            case ACCEPT:
            case OUT_ONLY:
                return true;
            default:
                return false;
        }
    }

    public TransportMode next() {
        int index = this.ordinal()+1;
        if (index == values().length) {
            return ACCEPT;
        }
        return values()[index];
    }

    public TransportMode previous() {
        int index = this.ordinal()-1;
        if (index < 0) {
            return OUT_ONLY;
        }
        return values()[index];
    }

}
