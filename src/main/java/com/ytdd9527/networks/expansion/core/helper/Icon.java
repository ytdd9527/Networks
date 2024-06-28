package com.ytdd9527.networks.expansion.core.helper;


import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


/**
 * @author Final_ROOT
 */
public class Icon {
    public static final ItemStack BORDER_ICON = ChestMenuUtils.getBackground();
    public static final ItemStack ERROR_ICON = new CustomItemStack(Material.BARRIER);
    public static final ItemStack RECIPE_ICON = new CustomItemStack(Material.PAPER);
}
