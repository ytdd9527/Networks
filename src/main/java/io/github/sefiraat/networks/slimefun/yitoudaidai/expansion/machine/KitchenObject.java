package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import dev.sefiraat.sefilib.slimefun.blocks.DisplayGroupBlock;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.function.Function;

public abstract class KitchenObject extends DisplayGroupBlock {

    private static final String WIKI_PAGE = "machines/cooking-work-stations";

    public static final Set<Material> BUCKET_ITEMS = Set.of(
        Material.BUCKET,
        Material.AXOLOTL_BUCKET,
        Material.COD_BUCKET,
        Material.LAVA_BUCKET,
        Material.MILK_BUCKET,
        Material.POWDER_SNOW_BUCKET,
        Material.PUFFERFISH_BUCKET,
        Material.SALMON_BUCKET,
        Material.TADPOLE_BUCKET,
        Material.TROPICAL_FISH_BUCKET,
        Material.WATER_BUCKET
    );

    protected KitchenObject(ItemGroup itemGroup,
                            SlimefunItemStack item,
                            RecipeType recipeType,
                            ItemStack[] recipe,
                            Function<Location, DisplayGroup> displayGroupFunction
    ) {
        super(itemGroup, item, recipeType, recipe, displayGroupFunction);
    }

    @Override
    public void preRegister() {

        addItemHandler(
            new BlockUseHandler() {
                @Override
                public void onRightClick(PlayerRightClickEvent e) {
                    KitchenObject.this.onRightClick(e);
                }
            }
        );
    }

    @Override
    public void postRegister() {
        addWikiPage(WIKI_PAGE);
    }

    protected void onRightClick(@Nonnull PlayerRightClickEvent event) {
        if (event.getPlayer().isSneaking()) {
            ItemStack mainHand = event.getItem();
            ItemStack offHand = event.getPlayer().getInventory().getItemInOffHand();
            Block block = event.getClickedBlock().orElse(null);

            if (block == null) {
                return;
            }

            if (mainHand != null && !mainHand.getType().isAir()) {
                trySetTop(block.getLocation(), mainHand);
            }

            if (offHand != null && !offHand.getType().isAir()) {
                trySetBase(block.getLocation(), offHand);
            }
        }
    }

    public void trySetTop(@Nonnull Location location, @Nonnull ItemStack itemStack) {
        trySetObject(location, itemStack, "counter_top");
    }

    public void trySetBase(@Nonnull Location location, @Nonnull ItemStack itemStack) {
        trySetObject(location, itemStack, "counter_base");
    }

    @ParametersAreNonnullByDefault
    private void trySetObject(Location location, ItemStack itemStack, String name) {
        if (itemStack.getType().isBlock() && itemStack.getType().isOccluding()) {
            DisplayGroup displayGroup = getDisplayGroup(location);
            if (displayGroup == null) {
                return;
            }
            Display display = displayGroup.getDisplays().get(name);
            if (display instanceof ItemDisplay itemDisplay) {
                itemDisplay.setItemStack(itemStack.clone());
            }
        }
    }

    public static boolean isBucket(@Nonnull ItemStack itemStack) {
        return isBucket(itemStack.getType());
    }

    public static boolean isBucket(@Nonnull Material material) {
        return BUCKET_ITEMS.contains(material);
    }

    @Nonnull
    @Override
    protected Material getBaseMaterial() {
        return Material.BARRIER;
    }
}
