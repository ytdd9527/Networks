package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.SmelteryBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedSmelteryRecipes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class SmelteryEncoder extends AbstractEncoder {

    public SmelteryEncoder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void blueprintSetter(ItemStack itemStack, ItemStack[] inputs, ItemStack crafted) {
        SmelteryBlueprint.setBlueprint(itemStack, inputs, crafted);
    }

    public boolean isVaildBlueprint(ItemStack blueprint) {
        return SlimefunItem.getByItem(blueprint) instanceof SmelteryBlueprint;
    }
    public Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries() {
        return SupportedSmelteryRecipes.getRecipes().entrySet();
    };
    public boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe) {
        return SupportedSmelteryRecipes.testRecipe(inputs, recipe);
    };
}
