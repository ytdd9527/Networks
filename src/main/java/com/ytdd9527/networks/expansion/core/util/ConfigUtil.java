package com.ytdd9527.networks.expansion.core.util;

import com.ytdd9527.networks.expansion.core.group.MainItemGroup;
import com.ytdd9527.networks.expansion.core.group.SubFlexItemGroup;
import io.github.sefiraat.networks.Networks;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

/**
 * @author Final_ROOT
 * @since 2.0
 */
public class ConfigUtil {

    public static MainItemGroup getMainItemGroup(@Nonnull String key, @Nonnull Material defaultMaterial, @Nonnull String defaultName) {
        CustomItemStack customItemStack = new CustomItemStack(defaultMaterial, defaultName);
        NamespacedKey namespacedKey = new NamespacedKey(Networks.getInstance(), key);
        return new MainItemGroup(namespacedKey, customItemStack, 0);
    }
    public static SubFlexItemGroup getSubFlexItemGroup(@Nonnull String key, @Nonnull Material defaultMaterial, @Nonnull String defaultName) {
        CustomItemStack customItemStack = new CustomItemStack(defaultMaterial, defaultName);
        NamespacedKey namespacedKey = new NamespacedKey(Networks.getInstance(), key);
        return new SubFlexItemGroup(namespacedKey, customItemStack, 0);
    }

}
