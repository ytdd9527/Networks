package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.model;


import io.github.sefiraat.networks.slimefun.NetworksItemGroups;
import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.sefiraat.networks.slimefun.tools.NetworkRake;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils.Skulls;
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
    public static final SlimefunItemStack NE_MODEL_CHAIN_PUSHER;
    public static final SlimefunItemStack NE_MODEL_CHAIN_PUSHER_PLUS;
    public static final SlimefunItemStack NE_MODEL_CHAIN_GRABBER;
    public static final SlimefunItemStack NE_MODEL_CHAIN_GRABBER_PLUS;
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
        NE_MODEL_COORDINATE_TRANSMITTER = Theme.model("NE_COORDINATE_TRANSMITTER",getPreEnchantedItemStack(Material.NOTE_BLOCK, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络坐标传输器","网络坐标传输器可以","将其中的物品传输到绑定的","网络坐标接收器中(只能在同一世界).","使用网络坐标配置器来进行绑定.","", MessageFormat.format("{0}网络电力消耗: {1}{2} 每次传输", Theme.CLICK_INFO, Theme.PASSIVE, 10000));
        NE_MODEL_COORDINATE_RECEIVER = Theme.model("NE_COORDINATE_RECEIVER",getPreEnchantedItemStack(Material.JUKEBOX, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络坐标接收器","网络坐标接收器可以","接收来自绑定的网络坐标传输器","中的物品(只能在同一世界).","每粘液刻会把接收到的物品","尝试推送到网络中.");
        NE_MODEL_CHAIN_PUSHER = Theme.model("NE_CHAING_PUSHER",new ItemStack(Material.OBSERVER),Theme.MACHINE,"网络链式推送器","网络链式推送器会尝试将","指定的物品送入机器中","指定的方向可延伸32格以内的机器推送指定物品","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_MODEL_CHAIN_PUSHER_PLUS = Theme.model("NE_CHAING_PUSHER_PLUS",getPreEnchantedItemStack(Material.OBSERVER, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络链式推送器Plus","网络链式推送器会尝试从","指定的物品送入机器中","指定的方向可延伸64格以内的机器推送指定物品","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_MODEL_CHAIN_GRABBER = Theme.model("NE_GRABBER",new ItemStack(Material.DISPENSER),Theme.MACHINE,"网络链式抓取器","网络链式抓取器会尝试从","指定的方向抓取延伸32格以内的机器的输出槽送回网络中","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_MODEL_CHAIN_GRABBER_PLUS = Theme.model("NE_GRABBER_PLUS",getPreEnchantedItemStack(Material.DISPENSER, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络链式抓取器Plus","网络链式抓取器Plus会尝试从","指定的方向抓取延伸64格以内的机器的输出槽送回网络中","单个机器切勿使用,建议堆叠大量机器进行使用");
        NEA_MODEL_IMPORT = Theme.model("NEA_IMPORT",getPreEnchantedItemStack(Material.RED_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络高级入口","网络高级入口会将其中的物品送入网络中","每个SF tick可传输最多54组物品","可接收来自货运网络的物品");
        NEA_MODEL_EXPORT = Theme.model("NEA_EXPORT",getPreEnchantedItemStack(Material.BLUE_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)), Theme.MACHINE,"网络高级出口","网络高级出口可以设置成","持续将1组指定的物品送出网络","可以使用货运网络从中提取物品");

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
}
