package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class SourcedItemStack extends ItemStack {

    private final CargoInventory sourceInv;
    private final int sourceSlot;

    public SourcedItemStack(ItemStack item, CargoInventory sourceInv, int sourceSlot) {
        super(item);
        this.sourceInv = sourceInv;
        this.sourceSlot = sourceSlot;
    }

    /**
     * Push this ItemStack back to the source
     * @return ItemStack if cannot insert into the source Inventory
     */
    public ItemStack pushBack() {
        return sourceInv.pushBack(this, sourceSlot);
    }

    public Location getSourceLocation(){
        return sourceInv.getLocation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SourcedItemStack that = (SourcedItemStack) o;
        return sourceSlot == that.sourceSlot && sourceInv.equals(that.sourceInv);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + sourceInv.hashCode() + sourceSlot;
    }
}
