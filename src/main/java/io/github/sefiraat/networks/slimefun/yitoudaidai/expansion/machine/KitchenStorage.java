package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class KitchenStorage extends KitchenObject {
    public KitchenStorage(ItemGroup itemGroup,
                          SlimefunItemStack item,
                          RecipeType recipeType,
                          ItemStack[] recipe,
                          Function<Location, DisplayGroup> displayGroupFunction
    ) {
        super(itemGroup, item, recipeType, recipe, displayGroupFunction);
    }
}
