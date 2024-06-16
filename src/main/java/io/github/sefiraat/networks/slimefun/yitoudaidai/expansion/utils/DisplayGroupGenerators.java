package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import dev.sefiraat.sefilib.entity.display.builders.BlockDisplayBuilder;
import dev.sefiraat.sefilib.entity.display.builders.ItemDisplayBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public final class DisplayGroupGenerators {

    private DisplayGroupGenerators() {
        throw new IllegalStateException("Utility class");
    }


    public static DisplayGroup generateCloche(@Nonnull Location location) {
        final DisplayGroup displayGroup = new DisplayGroup(location, 1.5f, 0.5f);

        List<Vector> WhiteConcrete = Arrays.asList(
                new Vector(0.25, 0.8, -0.45),
                new Vector(0.25, 0.8, -0.4),

                new Vector(-0.3, 0.8, -0.45),
                new Vector(-0.3, 0.8, -0.4),

                new Vector(0.25, 0.25, -0.45),
                new Vector(0.25, 0.25, -0.4),

                new Vector(-0.3, 0.25, -0.45),
                new Vector(-0.3, 0.25, -0.4)
        );
        for (int i = 1; i <= WhiteConcrete.size(); i++) {

            String name = "WhiteConcrete" + i;
            Vector offset = WhiteConcrete.get(i - 1);

            displayGroup.addDisplay(
                    name,
                    new ItemDisplayBuilder()
                            .setGroupParentOffset(offset)
                            .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                            .setTransformation(Transformations.CONCRETE.getTransformation())
                            .build(displayGroup)
            );
        }

        List<Vector> LightGrayConcrete = Arrays.asList(
                new Vector(0.20, 0.8, -0.45),
                new Vector(0.20, 0.8, -0.4),

                new Vector(0.25, 0.75, -0.45),
                new Vector(0.25, 0.75, -0.4),

                new Vector(-0.25, 0.8, -0.45),
                new Vector(-0.25, 0.8, -0.4),

                new Vector(-0.3, 0.75, -0.45),
                new Vector(-0.3, 0.75, -0.4),

                new Vector(0.2, 0.25, -0.45),
                new Vector(0.2, 0.25, -0.4),

                new Vector(0.25, 0.3, -0.45),
                new Vector(0.25, 0.3, -0.4),

                new Vector(-0.25, 0.25, -0.45),
                new Vector(-0.25, 0.25, -0.4),

                new Vector(-0.3, 0.3, -0.45),
                new Vector(-0.3, 0.3, -0.4)
        );
        for (int i = 1; i <= LightGrayConcrete.size(); i++) {
            String name = "LightGrayConcrete" + i;
            Vector offset = LightGrayConcrete.get(i - 1);

            displayGroup.addDisplay(
                    name,
                    new ItemDisplayBuilder()
                            .setGroupParentOffset(offset)
                            .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                            .setTransformation(Transformations.CONCRETE.getTransformation())
                            .build(displayGroup)
            );
        }

        List<Vector> GrayConcrete = Arrays.asList(
                //上
                new Vector(0.15, 0.8, -0.45),
                new Vector(0.15, 0.8, -0.4),
                new Vector(0.10, 0.8, -0.45),
                new Vector(0.10, 0.8, -0.4),
                new Vector(0.05, 0.8, -0.45),
                new Vector(0.05, 0.8, -0.4),
                new Vector(0, 0.8, -0.45),
                new Vector(0, 0.8, -0.4),
                new Vector(-0.05, 0.8, -0.45),
                new Vector(-0.05, 0.8, -0.4),
                new Vector(-0.10, 0.8, -0.45),
                new Vector(-0.10, 0.8, -0.4),
                new Vector(-0.15, 0.8, -0.45),
                new Vector(-0.15, 0.8, -0.4),
                new Vector(-0.2, 0.8, -0.45),
                new Vector(-0.2, 0.8, -0.4),
                //右边
                new Vector(0.25, 0.70, -0.45),
                new Vector(0.25, 0.70, -0.4),
                new Vector(0.25, 0.65, -0.45),
                new Vector(0.25, 0.65, -0.4),
                new Vector(0.25, 0.60, -0.45),
                new Vector(0.25, 0.60, -0.4),
                new Vector(0.25, 0.55, -0.45),
                new Vector(0.25, 0.55, -0.4),
                new Vector(0.25, 0.50, -0.45),
                new Vector(0.25, 0.50, -0.4),
                new Vector(0.25, 0.45, -0.45),
                new Vector(0.25, 0.45, -0.4),
                new Vector(0.25, 0.40, -0.45),
                new Vector(0.25, 0.40, -0.4),
                new Vector(0.25, 0.35, -0.45),
                new Vector(0.25, 0.35, -0.4),
                //左边
                new Vector(-0.3, 0.70, -0.45),
                new Vector(-0.3, 0.70, -0.4),
                new Vector(-0.3, 0.65, -0.45),
                new Vector(-0.3, 0.65, -0.4),
                new Vector(-0.3, 0.60, -0.45),
                new Vector(-0.3, 0.60, -0.4),
                new Vector(-0.3, 0.55, -0.45),
                new Vector(-0.3, 0.55, -0.4),
                new Vector(-0.3, 0.50, -0.45),
                new Vector(-0.3, 0.50, -0.4),
                new Vector(-0.3, 0.45, -0.45),
                new Vector(-0.3, 0.45, -0.4),
                new Vector(-0.3, 0.40, -0.45),
                new Vector(-0.3, 0.40, -0.4),
                new Vector(-0.3, 0.35, -0.45),
                new Vector(-0.3, 0.35, -0.4),
                //下
                new Vector(0.15, 0.25, -0.45),
                new Vector(0.15, 0.25, -0.4),
                new Vector(0.1, 0.25, -0.45),
                new Vector(0.1, 0.25, -0.4),
                new Vector(0.05, 0.25, -0.45),
                new Vector(0.05, 0.25, -0.4),
                new Vector(0, 0.25, -0.45),
                new Vector(0, 0.25, -0.4),
                new Vector(-0.05, 0.25, -0.45),
                new Vector(-0.05, 0.25, -0.4),
                new Vector(-0.1, 0.25, -0.45),
                new Vector(-0.1, 0.25, -0.4),
                new Vector(-0.15, 0.25, -0.45),
                new Vector(-0.15, 0.25, -0.4),
                new Vector(-0.2, 0.25, -0.45),
                new Vector(-0.2, 0.25, -0.4)
        );
        for (int i = 1; i <= GrayConcrete.size(); i++) {
            String name = "1GrayConcrete" + i;
            Vector offset = GrayConcrete.get(i - 1);

            displayGroup.addDisplay(
                    name,
                    new ItemDisplayBuilder()
                            .setGroupParentOffset(offset)
                            .setItemStack(new ItemStack(Material.GRAY_CONCRETE))
                            .setTransformation(Transformations.CONCRETE.getTransformation())
                            .build(displayGroup)
            );
        }
        return displayGroup;
    }

    public static DisplayGroup generateClochee(@Nonnull Location location) {
        final DisplayGroup displayGroup = new DisplayGroup(location, 1.5f, 0.5f);
        List<Vector> offsets = Arrays.asList(
                new Vector(0.25, 0.8, -0.4),
                new Vector(-0.3, 0.8, -0.4),
                new Vector(0.25, 0.25, -0.4),
                new Vector(-0.3, 0.25, -0.4)
        );
        ItemStack whiteConcreteItemStack = new ItemStack(Material.WHITE_CONCRETE);
        Transformation whiteConcreteTransformation = Transformations.WHITE_CONCRETE_1.getTransformation();

        displayGroup.addDisplay(
                "whiteConcrete1",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.8, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );

        displayGroup.addDisplay(
                "whiteConcrete2",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.3, 0.8, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );

        displayGroup.addDisplay(
                "whiteConcrete3",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );

        displayGroup.addDisplay(
                "whiteConcrete4",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.3, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_2",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.75, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_3",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.70, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_4",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.65, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_5",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.60, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_5",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.55, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_7",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.50, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_8",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.45, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_9",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.40, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_10",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.35, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_11",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.30, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.25, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );


        displayGroup.addDisplay(
                "cloche_12_1",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.3, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_2",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.25, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_3",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.20, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_4",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.15, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_5",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.10, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_6",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(-0.05, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_7",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.00, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_8",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.05, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_9",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.1, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_10",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.15, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.WHITE_CONCRETE))
                        .setTransformation(Transformations.WHITE_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "cloche_12_11",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0.2, 0.25, -0.4))
                        .setItemStack(new ItemStack(Material.LIGHT_GRAY_CONCRETE))
                        .setTransformation(Transformations.LIGHT_GRAY_CONCRETE_1.getTransformation())
                        .build(displayGroup)
        );
        return displayGroup;
    }

}
