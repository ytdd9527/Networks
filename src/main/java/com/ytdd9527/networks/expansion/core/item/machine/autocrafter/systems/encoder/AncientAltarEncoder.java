package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.AncientAltarBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedAncientAltarRecipes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class AncientAltarEncoder extends AbstractEncoder {

    public AncientAltarEncoder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void blueprintSetter(ItemStack itemStack, ItemStack[] inputs, ItemStack crafted) {
        AncientAltarBlueprint.setBlueprint(itemStack, inputs, crafted);
    }

    public boolean isVaildBlueprint(ItemStack blueprint) {
        return SlimefunItem.getByItem(blueprint) instanceof AncientAltarBlueprint;
    }
    public Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries() {
        return SupportedAncientAltarRecipes.getRecipes().entrySet();
    };
    public boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe) {
        return SupportedAncientAltarRecipes.testRecipe(inputs, recipe);
    };
}
