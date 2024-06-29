package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.basic;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.ExpansionWorkbenchBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedExpansionWorkbenchRecipes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class AutoExpansionWorkbenchCrafter extends AbstractAutoCrafter {
    public AutoExpansionWorkbenchCrafter(
            ItemGroup itemGroup,
            SlimefunItemStack item,
            RecipeType recipeType,
            ItemStack[] recipe,
            int chargePerCraft,
            boolean withholding
    ) {
        super(itemGroup, item, recipeType, recipe, chargePerCraft, withholding);
    }

    public Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries() {
        return SupportedExpansionWorkbenchRecipes.getRecipes().entrySet();
    }

    public boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe) {
        return SupportedExpansionWorkbenchRecipes.testRecipe(inputs, recipe);
    }

    public boolean isVaildBlueprint(SlimefunItem item) {
        return item instanceof ExpansionWorkbenchBlueprint;
    }
}
