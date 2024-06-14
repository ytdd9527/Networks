package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;
import dev.sefiraat.sefilib.misc.RotationFace;
import io.papermc.lib.PaperLib;
import dev.sefiraat.sefilib.misc.TransformationBuilder;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;

import javax.annotation.Nonnull;

public enum Transformations {
    CONCRETE(new TransformationBuilder()
            .scale(0.05f, 0.05f, 0.05f)
            .build()
    ),

    WHITE_CONCRETE_1(new TransformationBuilder()
            .scale(0.05f, 0.05f, 0.05f)
            .build()
    ),
    LIGHT_GRAY_CONCRETE_1(new TransformationBuilder()
            .scale(0.1f, 0.1f, 0.1f)
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
