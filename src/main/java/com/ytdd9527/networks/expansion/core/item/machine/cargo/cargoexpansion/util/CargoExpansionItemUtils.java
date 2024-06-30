package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.util;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.inventory.meta.Damageable;

public class CargoExpansionItemUtils {
    public static boolean isItemSimilar(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }

        var sfSimilar = SlimefunUtils.isItemSimilar(item2, item1, true, false);
        if (!item1.hasItemMeta() || !sfSimilar) {
            return sfSimilar;
        }

        if (!item2.hasItemMeta()) {
            return false;
        }

        var meta = item1.getItemMeta();
        var otherMeta = item2.getItemMeta();
        if (meta instanceof BundleMeta bundle) {
            if (!(otherMeta instanceof BundleMeta otherBundle)) {
                return false;
            }

            if (!bundle.hasItems() || !otherBundle.hasItems()) {
                return bundle.hasItems() == otherBundle.hasItems();
            }

            var content = bundle.getItems();
            var otherContent = otherBundle.getItems();
            for (var i = 0; i < content.size(); i++) {
                var itemInSlot = content.get(i);
                var itemInOther = otherContent.get(i);
                if (!isItemSimilar(itemInSlot, itemInOther)) {
                    return false;
                }
            }
        }

        if (meta instanceof Damageable damageable) {
            return otherMeta instanceof Damageable otherDamageable && damageable.getDamage() == otherDamageable.getDamage();
        }

        return true;
    }
}
