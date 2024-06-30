package com.ytdd9527.networks.expansion.core.item;

import com.ytdd9527.networks.expansion.core.interfaces.RecipeItem;
import com.ytdd9527.networks.libs.slimefun.util.dto.MachineRecipeFactory;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * We may add something soon
 * @author Final_ROOT
 * @since 2.0
 */
public abstract class AbstractMySlimefunItem extends SlimefunItem {
    public AbstractMySlimefunItem(@Nonnull ItemGroup itemGroup, @Nonnull SlimefunItemStack item, @Nonnull RecipeType recipeType, @Nonnull ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public AbstractMySlimefunItem(@Nonnull ItemGroup itemGroup, @Nonnull SlimefunItemStack item, @Nonnull RecipeType recipeType, @Nonnull ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    protected AbstractMySlimefunItem(@Nonnull ItemGroup itemGroup, @Nonnull ItemStack item, @Nonnull String id, @Nonnull RecipeType recipeType, @Nonnull ItemStack[] recipe) {
        super(itemGroup, item, id, recipeType, recipe);
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        super.register(addon);
        if (this instanceof RecipeItem recipeItem) {
            int delay = recipeItem.getRegisterRecipeDelay();
            if(delay > 0) {
                this.getAddon().getJavaPlugin().getServer().getScheduler().runTaskLater((Plugin)addon, () -> {
                    (recipeItem).registerDefaultRecipes();
                    MachineRecipeFactory.getInstance().initAdvancedRecipeMap(this.getId());
                }, delay);
            } else {
                (recipeItem).registerDefaultRecipes();
                MachineRecipeFactory.getInstance().initAdvancedRecipeMap(this.getId());
            }
        }
    }

    @Nonnull
    public AbstractMySlimefunItem registerThis() {
        this.register(JavaPlugin.getPlugin(Networks.class));
        return this;
    }
}
