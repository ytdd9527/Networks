package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface CargoInventory {

    /**
     * Insert item into inventory.
     * @param container: the item container.
     * @param oneStackMode: if one stack mode enabled.
     * @return the possible material types. Material.AIR means any.
     */
    @Nonnull
    Set<Material> pushItem(SourcedItemContainer container, boolean oneStackMode);

    @Nullable
    ItemStack pushBack(ItemStack item, int preferSlot);

    @Nonnull
    List<SourcedItemStack> getMatched(Predicate<ItemStack> filter);

    @Nonnull
    Location getLocation();

    /**
     * Call when all cargo operation done
     */
    default void update(){}

}
