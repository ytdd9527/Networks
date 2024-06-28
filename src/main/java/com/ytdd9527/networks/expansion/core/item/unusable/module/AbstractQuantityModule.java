package com.ytdd9527.networks.expansion.core.item.unusable.module;

import com.ytdd9527.networks.expansion.core.item.unusable.UnusableSlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Final_ROOT
 * @since 2.4
 */
public abstract class AbstractQuantityModule extends UnusableSlimefunItem {
    public AbstractQuantityModule(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public abstract int getEffect(int itemAmount);
}
