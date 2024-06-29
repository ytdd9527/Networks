package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import javax.annotation.Nullable;
import java.util.*;

public class SourcedItemContainer {

    private final ItemStack sample;
    private final ItemStackWrapper wrapper;
    private final List<SourcedItemStack> items;
    private final Set<Location> blockedLocation;
    private int amount;

    public SourcedItemContainer(ItemStack itemSample) {
        sample = new ItemStack(itemSample);
        sample.setAmount(1);
        items = new LinkedList<>();
        blockedLocation = new HashSet<>(4096);
        wrapper = ItemStackWrapper.wrap(itemSample);
        amount = 0;
    }

    public boolean addItem(SourcedItemStack item) {
        if (SlimefunUtils.isItemSimilar(wrapper, item, true, false)) {
            // Check enchantments
            Map<Enchantment, Integer> ench, ench2;
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) sample.getItemMeta();
                EnchantmentStorageMeta meta2 = (EnchantmentStorageMeta) item.getItemMeta();
                ench = meta == null ? Collections.emptyMap() : meta.getStoredEnchants();
                ench2 = meta2 == null ? Collections.emptyMap() : meta2.getStoredEnchants();
            } else {
                ench = sample.getEnchantments();
                ench2 = item.getEnchantments();
            }
            if (ench.size() != ench2.size()) {
                return false;
            }
            for (Map.Entry<Enchantment, Integer> each : ench.entrySet()) {
                if (!each.getValue().equals(ench2.getOrDefault(each.getKey(), -1))) {
                    return false;
                }
            }

            amount += item.getAmount();
            items.add(item);
            blockedLocation.add(item.getSourceLocation());
            return true;
        }
        return false;
    }

    public boolean isLocationBlocked(Location l) {
        return blockedLocation.contains(l);
    }

    public ItemStack getSample() {
        return sample.clone();
    }

    public ItemStackWrapper getWrapper() {
        return wrapper;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * Remove specific amount from container
     * @param amountRemove: the amount will be removed
     * @return the amount removed (usually is same as input, but return the amount if stored amount is less than remove)
     */
    public int removeAmount(int amountRemove) {
        if (this.amount < amountRemove) {
            int re = this.amount;
            this.amount = 0;
            return re;
        } else {
            this.amount -= amountRemove;
            return amountRemove;
        }
    }

    /**
     * Push back all rest items
     * @return The item cannot be push back, the amount may more than stack max size
     */
    @Nullable
    public ItemStack pushBack() {
        ItemStack re = getSample();
        for(SourcedItemStack each : items) {
            if (amount > 0) {
                // Check and update amount
                if (amount < each.getAmount()) {
                    each.setAmount(amount);
                    amount = 0;
                } else {
                    amount -= each.getAmount();
                }

                ItemStack rest = each.pushBack();
                if (rest != null) {
                    amount += rest.getAmount();
                }
            }
        }

        if (amount == 0) {
            return null;
        }

        re.setAmount(amount);
        return re;
    }
}
