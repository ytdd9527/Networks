package com.ytdd9527.networks.expansion.core.utils;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;

import dev.sefiraat.sefilib.entity.display.builders.ItemDisplayBuilder;
import com.ytdd9527.networks.expansion.setup.ItemsModel;

import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Location;


import org.bukkit.Material;
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

    public static DisplayGroup generateBridge1(@Nonnull Location location) {
        final DisplayGroup displayGroup = new DisplayGroup(location, 1f, 0.5f);

        displayGroup.addDisplay(
                "bridge_1",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0, 0.65, 0))
                        .setItemStack(new ItemStack(BRIDGE_STACK))
                        .setTransformation(Transformations.BRIDGE_1.getTransformation())
                        .build(displayGroup)
        );
        displayGroup.addDisplay(
                "bridge_3",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0, 0.9, 0.3))
                        .setItemStack(new ItemStack(BRIDGE_CORNER_RIM_STACK))
                        .setTransformation(Transformations.BRIDGE_3.getTransformation())
                        .build(displayGroup)
        );
        return displayGroup;

    }
    public static DisplayGroup generateBridge2(@Nonnull Location location) {
        final DisplayGroup displayGroup = new DisplayGroup(location, 1f, 0.5f);
        displayGroup.addDisplay(
                "bridge_2",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0, 1, 0))
                        .setItemStack(new ItemStack(BRIDGE_STACK))
                        .setTransformation(Transformations.BRIDGE_2.getTransformation())
                        .build(displayGroup)
        );
        return displayGroup;
    }
    public static DisplayGroup generateBridge3(@Nonnull Location location) {
        final DisplayGroup displayGroup = new DisplayGroup(location, 1f, 0.5f);
        displayGroup.addDisplay(
                "bridge_3",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0, 1, 0.4))
                        .setItemStack(new ItemStack(BRIDGE_STACK))
                        .setTransformation(Transformations.BRIDGE_3.getTransformation())
                        .build(displayGroup)
        );
        return displayGroup;
    }
    public static DisplayGroup generateBridge4(@Nonnull Location location) {
        final DisplayGroup displayGroup = new DisplayGroup(location, 1f, 0.5f);
        displayGroup.addDisplay(
                "bridge_4",
                new ItemDisplayBuilder()
                        .setGroupParentOffset(new Vector(0, 1, 0.25))
                        .setItemStack(new ItemStack(BRIDGE_CORNER_RIM_STACK))
                        .setTransformation(Transformations.BRIDGE_3.getTransformation())
                        .build(displayGroup)
        );
        return displayGroup;
    }
    public static final CustomItemStack BRIDGE_STACK = new CustomItemStack(
            Skulls.BRIDGE1.getPlayerHead(),""
    );
    public static final CustomItemStack BRIDGE_CORNER_RIM_STACK = new CustomItemStack(
            Skulls.BRIDGE2.getPlayerHead(),""
    );
}
