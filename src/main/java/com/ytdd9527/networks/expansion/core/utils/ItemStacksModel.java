package com.ytdd9527.networks.expansion.core.utils;

import com.ytdd9527.networks.expansion.core.utils.Skulls;
import io.github.sefiraat.networks.slimefun.NetworksItemGroups;
import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.sefiraat.networks.slimefun.tools.NetworkRake;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import jdk.jshell.Snippet;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.text.MessageFormat;


@UtilityClass
public class ItemStacksModel {


    public static final SlimefunItemStack NE_MODEL_COORDINATE_TRANSMITTER;
    public static final SlimefunItemStack NE_MODEL_COORDINATE_RECEIVER;
    public static final SlimefunItemStack NE_MODEL_CHAING_PUSHER;
    public static final SlimefunItemStack NE_MODEL_CHAING_PUSHER_PLUS;
    public static final SlimefunItemStack NE_MODEL_CHAING_GRABBER;
    public static final SlimefunItemStack NE_MODEL_CHAING_GRABBER_PLUS;
    public static final SlimefunItemStack NEA_MODEL_IMPORT;
    public static final SlimefunItemStack NEA_MODEL_EXPORT;
    public static final SlimefunItemStack NEA_MODEL_PURGER;
    public static final SlimefunItemStack NE_MODEL_CELL;
    public static final SlimefunItemStack NE_MODEL_CAPACITOR_5;


    static {

        //写好的模型
        NEA_MODEL_PURGER = Theme.model("NEA_PURGER",Skulls.TRASH_CAN.getPlayerHead(), Theme.MACHINE,"网络高级清除器","需要网络扳手器 才能拆除");
        NE_MODEL_CELL = Theme.model("NE_CELL", Skulls.NE_MODEL_CELL.getPlayerHead(), Theme.MACHINE,"网络单元","需要网络扳手器 才能拆除");
        NE_MODEL_CAPACITOR_5 = Theme.model("NE_CAPACITOR_5", Skulls.NE_MODEL_CAPACITOR_5.getPlayerHead(), Theme.MACHINE,"网络电容(5)","需要网络扳手器 才能拆除");

        //没写的
        NE_MODEL_COORDINATE_TRANSMITTER = Theme.model("NE_COORDINATE_TRANSMITTER",Enchanted(Material.NOTE_BLOCK),Theme.MACHINE,"网络坐标传输器");
        NE_MODEL_COORDINATE_RECEIVER = Theme.model("NE_COORDINATE_RECEIVER",Enchanted(Material.JUKEBOX),Theme.MACHINE,"网络坐标接收器");
        NE_MODEL_CHAING_PUSHER = Theme.model("NE_CHAING_PUSHER",new ItemStack(Material.OBSERVER),Theme.MACHINE,"网络链式推送器");
        NE_MODEL_CHAING_PUSHER_PLUS = Theme.model("NE_CHAING_PUSHER_PLUS",Enchanted(Material.OBSERVER),Theme.MACHINE,"网络链式推送器Plus","网络链式推送器会尝试从","指定的物品送入机器中","指定的方向可延伸64格以内的机器推送指定物品","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_MODEL_CHAING_GRABBER = Theme.model("NE_GRABBER",new ItemStack(Material.DISPENSER),Theme.MACHINE,"网络链式抓取器");
        NE_MODEL_CHAING_GRABBER_PLUS = Theme.model("NE_GRABBER_PLUS",Enchanted(Material.DISPENSER),Theme.MACHINE,"网络链式抓取器Plus");
        NEA_MODEL_IMPORT = Theme.model("NEA_IMPORT",Enchanted(Material.RED_STAINED_GLASS),Theme.MACHINE,"网络高级入口");
        NEA_MODEL_EXPORT = Theme.model("NEA_EXPORT",Enchanted(Material.BLUE_STAINED_GLASS), Theme.MACHINE,"网络高级出口");


 }
    @Nonnull
    @SafeVarargs
    public static ItemStack getPreEnchantedItemStack(Material material, boolean hide, @Nonnull Pair<Enchantment, Integer>... enchantments) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Pair<Enchantment, Integer> pair : enchantments) {
            itemMeta.addEnchant(pair.getFirstValue(), pair.getSecondValue(), true);
        }
        if (hide) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack Enchanted(Material material) {
        return getPreEnchantedItemStack(material, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1));
    }
}
