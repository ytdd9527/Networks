package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.ArmorForgeBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedArmorForgeRecipes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class ArmorForgeEncoder extends AbstractEncoder {

    public ArmorForgeEncoder(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public void blueprintSetter(ItemStack itemStack, ItemStack[] inputs, ItemStack crafted) {
        ArmorForgeBlueprint.setBlueprint(itemStack, inputs, crafted);
    }

    public boolean isVaildBlueprint(ItemStack blueprint) {
        return SlimefunItem.getByItem(blueprint) instanceof ArmorForgeBlueprint;
    }
    public Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries() {
        return SupportedArmorForgeRecipes.getRecipes().entrySet();
    };
    public boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe) {
        return SupportedArmorForgeRecipes.testRecipe(inputs, recipe);
    };
}
