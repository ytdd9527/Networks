package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.MagicWorkbenchBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedMagicWorkbenchRecipes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class MagicWorkbenchEncoder extends AbstractEncoder {

    public MagicWorkbenchEncoder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void blueprintSetter(ItemStack itemStack, ItemStack[] inputs, ItemStack crafted) {
        MagicWorkbenchBlueprint.setBlueprint(itemStack, inputs, crafted);
    }

    public boolean isVaildBlueprint(ItemStack blueprint) {
        return SlimefunItem.getByItem(blueprint) instanceof MagicWorkbenchBlueprint;
    }
    public Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries() {
        return SupportedMagicWorkbenchRecipes.getRecipes().entrySet();
    };
    public boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe) {
        return SupportedMagicWorkbenchRecipes.testRecipe(inputs, recipe);
    };
}
