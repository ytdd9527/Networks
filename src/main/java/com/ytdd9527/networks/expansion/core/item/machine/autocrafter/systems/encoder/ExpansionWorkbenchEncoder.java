package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.ExpansionWorkbenchBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedExpansionWorkbenchRecipes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class ExpansionWorkbenchEncoder extends AbstractEncoder {

    public ExpansionWorkbenchEncoder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void blueprintSetter(ItemStack itemStack, ItemStack[] inputs, ItemStack crafted) {
        ExpansionWorkbenchBlueprint.setBlueprint(itemStack, inputs, crafted);
    }

    public boolean isVaildBlueprint(ItemStack blueprint) {
        return SlimefunItem.getByItem(blueprint) instanceof ExpansionWorkbenchBlueprint;
    }
    public Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries() {
        return SupportedExpansionWorkbenchRecipes.getRecipes().entrySet();
    };
    public boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe) {
        return SupportedExpansionWorkbenchRecipes.testRecipe(inputs, recipe);
    };
}
