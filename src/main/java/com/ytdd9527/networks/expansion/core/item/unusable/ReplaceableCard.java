package com.ytdd9527.networks.expansion.core.item.unusable;

import com.ytdd9527.networks.expansion.core.interfaces.RecipeItem;
import com.ytdd9527.networks.expansion.core.interfaces.UnCopiableItem;
import com.ytdd9527.networks.expansion.util.RecipeUtil;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

/**
 * targetMaterial = this.getItem() + sourceMaterial
 * example:
 *      WATER_BUCKET = WATER_CARD + BUCKET
 *      DIAMOND_PICKAXE = DIAMOND_PICKAXE_CARD + NULL
 * @author Final_ROOT
 * @since 2.4
 */
public class ReplaceableCard extends UnusableSlimefunItem implements RecipeItem, UnCopiableItem {
    private static final Map<Material, ReplaceableCard> MATERIAL_SLIMEFUN_ITEM_MAP = new EnumMap<>(Material.class);

    private final Material targetMaterial;
    @Nullable
    private final Material extraSourceMaterial;

    public ReplaceableCard(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nonnull Material targetMaterial, @Nullable Material extraSourceMaterial) {
        super(itemGroup, item, recipeType, recipe);
        if(MATERIAL_SLIMEFUN_ITEM_MAP.containsKey(targetMaterial)) {
            throw new IllegalArgumentException("duplicated material while registering " + this.getId());
        }
        MATERIAL_SLIMEFUN_ITEM_MAP.put(targetMaterial, this);
        this.targetMaterial = targetMaterial;
        this.extraSourceMaterial = extraSourceMaterial;
    }

    @Nullable
    public static ReplaceableCard getByMaterial(@Nonnull Material material) {
        return MATERIAL_SLIMEFUN_ITEM_MAP.get(material);
    }

    public Material getTargetMaterial() {
        return targetMaterial;
    }

    @Nullable
    public Material getExtraSourceMaterial() {
        return extraSourceMaterial;
    }

    @Override
    public void registerDefaultRecipes() {


        ItemStack[] inputItemStacks = new ItemStack[] {new ItemStack(this.targetMaterial)};

        ItemStack[] outputItemStacks = new ItemStack[this.extraSourceMaterial == null ? 1 : 2];
        outputItemStacks[0] = this.getItem();
        if(outputItemStacks.length == 2 ) {
            outputItemStacks[1] = new ItemStack(this.extraSourceMaterial);
        }
        this.registerRecipe(inputItemStacks, outputItemStacks);
    }
}
