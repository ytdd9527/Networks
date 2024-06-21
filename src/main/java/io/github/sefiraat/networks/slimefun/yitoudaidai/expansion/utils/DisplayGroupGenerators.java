    package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;

    import dev.sefiraat.sefilib.entity.display.DisplayGroup;

    import dev.sefiraat.sefilib.entity.display.builders.ItemDisplayBuilder;
    import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.model.ItemStacksModel;

    import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

    import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
    import org.bukkit.Location;


    import org.bukkit.inventory.ItemStack;

    import org.bukkit.util.Vector;

    import javax.annotation.Nonnull;


    public final class DisplayGroupGenerators {

        private DisplayGroupGenerators() {
            throw new IllegalStateException("Utility class");
        }


        public static DisplayGroup generateCloche(@Nonnull Location location) {
            final DisplayGroup displayGroup = new DisplayGroup(location, 1.1f, 0.5f);
            displayGroup.addDisplay(
                    "purge",
                    new ItemDisplayBuilder()
                            .setGroupParentOffset(new Vector(0, 1, 0))
                            .setItemStack(new ItemStack(ItemStacksModel.NEA_MODEL_PURGER))
                            .setTransformation(Transformations.TWO.getTransformation())
                            .build(displayGroup)
            );
            return displayGroup;
        }
        public static DisplayGroup generateCell(@Nonnull Location location) {
            final DisplayGroup displayGroup = new DisplayGroup(location, 1.1f, 0.5f);
            displayGroup.addDisplay(
                    "cell",
                    new ItemDisplayBuilder()
                            .setGroupParentOffset(new Vector(0, 1, 0))
                            .setItemStack(new ItemStack(ItemStacksModel.NE_MODEL_CELL))
                            .setTransformation(Transformations.TWO.getTransformation())
                            .build(displayGroup)
            );
            return displayGroup;
        }
        public static DisplayGroup generatePowerNode(@Nonnull Location location) {
            final DisplayGroup displayGroup = new DisplayGroup(location, 2f, 0.5f);
            displayGroup.addDisplay(
                    "powernode",
                    new ItemDisplayBuilder()
                            .setGroupParentOffset(new Vector(0, 0, 0))
                            .setItemStack(new ItemStack(ItemStacksModel.NE_MODEL_CAPACITOR_5))
                            .setTransformation(Transformations.NE_MODEL_CAPACITOR_5.getTransformation())
                            .build(displayGroup)
            );
            return displayGroup;
        }


    }
