package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.util.CargoExpansionItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import org.bukkit.inventory.ItemStack;

public class ItemContainer {

    private final int id;
    private final ItemStack sample;
    private final ItemStackWrapper wrapper;
    private int amount;

    public ItemContainer(int id, ItemStack item, int amount) {
        this.id = id;
        this.sample = item.clone();
        sample.setAmount(1);
        this.wrapper = ItemStackWrapper.wrap(sample);
        this.amount = amount;
    }

    public ItemStack getSample() {
        return sample.clone();
    }

    public ItemStackWrapper getWrapper() {
        return wrapper;
    }

    public boolean isSimilar(ItemStack other) {
        return CargoExpansionItemUtils.isItemSimilar(wrapper, other);
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     * Remove specific amount from container
     * @param amount: amount will be removed
     * @return amount that actual removed
     */
    public int removeAmount(int amount) {
        if (this.amount > amount) {
            this.amount -= amount;
            return amount;
        } else {
            int re = this.amount;
            this.amount = 0;
            return re;
        }
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "ItemContainer{" +
                "id=" + id +
                ", sample=" + sample +
                ", wrapper=" + wrapper +
                ", amount=" + amount +
                '}';
    }
}
