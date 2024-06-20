package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;


import io.papermc.lib.PaperLib;
import dev.sefiraat.sefilib.misc.TransformationBuilder;
import org.bukkit.util.Transformation;

import org.joml.Quaternionf;

import javax.annotation.Nonnull;

public enum Transformations {
    TWO(new TransformationBuilder()
            .scale(1.95f, 2f, 2f)
            .build()
    ),
    NE_MODEL_CAPACITOR_5(new TransformationBuilder()
            .scale(1.5f, -4f, 1.5f)
            .build()
    ),
    CLOCHE_BASE(new TransformationBuilder()
            .scale(0.5f, 1f, 0.5f)
            .build()
    ),
    CLOCHE_GLASS(new TransformationBuilder()
            .scale(0.5f, 0.5f, 0.5f)
            .build()
    ),
    CLOCHE_DIRT(new TransformationBuilder()
            .scale(0.4f, 0.4f, 0.4f)
            .build()
    ),
    ;



    private final Transformation transformation;

    Transformations(@Nonnull Transformation transformation) {
        this.transformation = transformation;
    }

    public Transformation getTransformation() {
        return getTransformation(true);
    }

    public Transformation getTransformation(boolean itemDisplay) {
        // In 1.20+ the y axis of item displays are rotated by 180°
        // This corrects the visuals by rotating again
        if (itemDisplay && PaperLib.getMinecraftVersion() >= 20) {
            return new Transformation(transformation.getTranslation(),
                    transformation.getLeftRotation(),
                    transformation.getScale(),
                    new Quaternionf(transformation.getRightRotation()).rotateY((float) Math.PI));
        }
        return transformation;
    }

}
