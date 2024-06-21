package io.github.sefiraat.networks.slimefun.yitoudaidai;


import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.text.MessageFormat;

/**
 * Creating SlimefunItemstacks here due to some items being created in Enums so this will
 * act as a one-stop-shop for the stacks themselves.
 */
@UtilityClass
public class ExpansionSlimefunitemStacks {


    //工作台
    public static final SlimefunItemStack NE_EXPANSION_WORKBENCH;
    //工具
    public static final SlimefunItemStack NE_COORDINATE_CONFIGURATOR;
    //运输
    public static final SlimefunItemStack NE_COORDINATE_TRANSMITTER;
    public static final SlimefunItemStack NE_COORDINATE_RECEIVER;
    public static final SlimefunItemStack NE_CHAING_PUSHER;
    public static final SlimefunItemStack NE_CHAING_PUSHER_PLUS;
    public static final SlimefunItemStack NE_CHAING_GRABBER;
    public static final SlimefunItemStack NE_CHAING_GRABBER_PLUS;
    public static final SlimefunItemStack NE_CHAIN_DISPATCHER;
    public static final SlimefunItemStack NE_CHAIN_DISPATCHER_PLUS;
    public static final SlimefunItemStack NEA_IMPORT;
    public static final SlimefunItemStack NEA_EXPORT;
    public static final SlimefunItemStack NEA_PURGER;

    public static final SlimefunItemStack NETWORK_CAPACITOR_5;

    public static final SlimefunItemStack NETWORK_ADVANCED_QUANTUM_STORAGE;
    //蓝图
    public static final SlimefunItemStack MAGIC_WORKBENCH_BLUEPRINT;
    public static final SlimefunItemStack ARMOR_FORGE_BLUEPRINT;
    public static final SlimefunItemStack SMELTERY_BLUEPRINT;
    public static final SlimefunItemStack QUANTUM_WORKBENCH_BLUEPRINT;
    public static final SlimefunItemStack ANCIENT_ALTAR_BLUEPRINT;
    //编码器
    public static final SlimefunItemStack NE_MAGIC_WORKBENCH_RECIPE_ENCODER;
    public static final SlimefunItemStack NE_ARMOR_FORGE_RECIPE_ENCODER;
    public static final SlimefunItemStack NE_SMELTERY_RECIPE_ENCODER;
    public static final SlimefunItemStack NE_QUANTUM_WORKBENCH_RECIPE_ENCODER;
    public static final SlimefunItemStack NE_ANCIENT_ALTAR_RECIPE_ENCODER;
    //合成机器
    public static final SlimefunItemStack NE_AUTO_MAGIC_WORKBENCH;
    public static final SlimefunItemStack NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING;
    public static final SlimefunItemStack NE_AUTO_ARMOR_FORGE;
    public static final SlimefunItemStack NE_AUTO_ARMOR_FORGE_WITHHOLDING;
    public static final SlimefunItemStack NE_AUTO_SMELTERY;
    public static final SlimefunItemStack NE_AUTO_SMELTERY_WITHHOLDING;
    public static final SlimefunItemStack NE_AUTO_QUANTUM_WORKBENCH;
    public static final SlimefunItemStack NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING;
    public static final SlimefunItemStack NE_AUTO_ANCIENT_ALTAR;
    public static final SlimefunItemStack NE_AUTO_ANCIENT_ALTAR_WITHHOLDING;

    //网格
    public static final SlimefunItemStack NETWORK_GRID_NEW_STYLE;
    public static final SlimefunItemStack NETWORK_CRAFTING_GRID_NEW_STYLE;
    public static final SlimefunItemStack NETWORK_ENCODING_GRID_NEW_STYLE;
    //网桥
    public static final SlimefunItemStack NE_BRIDGE_WHITE;
    public static final SlimefunItemStack NE_BRIDGE_LIGHT_GRAY;
    public static final SlimefunItemStack NE_BRIDGE_GRAY;
    public static final SlimefunItemStack NE_BRIDGE_BLACK;
    public static final SlimefunItemStack NE_BRIDGE_BROWN;
    public static final SlimefunItemStack NE_BRIDGE_RED;
    public static final SlimefunItemStack NE_BRIDGE_ORANGE;
    public static final SlimefunItemStack NE_BRIDGE_YELLOW;
    public static final SlimefunItemStack NE_BRIDGE_LIME;
    public static final SlimefunItemStack NE_BRIDGE_GREEN;
    public static final SlimefunItemStack NE_BRIDGE_CYAN;
    public static final SlimefunItemStack NE_BRIDGE_LIGHT_BLUE;
    public static final SlimefunItemStack NE_BRIDGE_BLUE;
    public static final SlimefunItemStack NE_BRIDGE_PURPLE;
    public static final SlimefunItemStack NE_BRIDGE_MAGENTA;
    public static final SlimefunItemStack NE_BRIDGE_PINK;
    static {

        NE_EXPANSION_WORKBENCH = Theme.tsItem("NE_EXPANSION_WORKBENCH",Enchanted(Material.BAMBOO_BLOCK),Theme.MACHINE,"网络拓展工作台");;
        //工具
        NE_COORDINATE_CONFIGURATOR = Theme.tsItem("NE_COORDINATE_CONFIGURATOR",Enchanted(Material.RECOVERY_COMPASS),Theme.TOOL,"网络坐标配置器");;
        //运输与存储电力
        NE_CHAING_PUSHER = Theme.tsItem("NE_CHAING_PUSHER",new ItemStack(Material.OBSERVER),Theme.MACHINE,"网络链式推送器");
        NE_CHAING_GRABBER = Theme.tsItem("NE_EXPANSION_GRABBER_1",new ItemStack(Material.DISPENSER),Theme.MACHINE,"网络链式抓取器");
        NE_CHAIN_DISPATCHER = Theme.tsItem("NE_CHAIN_DISPATCHER", new ItemStack(Material.PISTON),Theme.MACHINE, "网链调度器");

        NE_CHAING_PUSHER_PLUS = Theme.tsItem("NE_CHAING_PUSHER_PLUS",Enchanted(Material.OBSERVER),Theme.MACHINE,"网络链式推送器Plus");
        NE_CHAING_GRABBER_PLUS = Theme.tsItem("NE_EXPANSION_GRABBER_PLUS",Enchanted(Material.DISPENSER),Theme.MACHINE,"网络链式抓取器Plus");
        NE_CHAIN_DISPATCHER_PLUS = Theme.tsItem("NE_CHAIN_DISPATCHER_PLUS",Enchanted(Material.STICKY_PISTON),Theme.MACHINE,"网链调度器Plus");

        NE_COORDINATE_TRANSMITTER = Theme.themedSlimefunItemStack("NE_COORDINATE_TRANSMITTER",getPreEnchantedItemStack(Material.NOTE_BLOCK, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络坐标传输器");
        NE_COORDINATE_RECEIVER = Theme.themedSlimefunItemStack("NE_COORDINATE_RECEIVER",getPreEnchantedItemStack(Material.JUKEBOX, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络坐标接收器");
        NEA_IMPORT = Theme.themedSlimefunItemStack("NEA_IMPORT",getPreEnchantedItemStack(Material.RED_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络高级入口");
        NEA_EXPORT = Theme.themedSlimefunItemStack("NEA_EXPORT",getPreEnchantedItemStack(Material.BLUE_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)), Theme.MACHINE,"网络高级出口");
        NEA_PURGER = Theme.themedSlimefunItemStack("NEA_PURGER",getPreEnchantedItemStack(Material.YELLOW_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)), Theme.MACHINE,"网络高级清除器");
        NETWORK_CAPACITOR_5 = Theme.themedSlimefunItemStack("NTW_CAPACITOR_5", new ItemStack(Material.CYAN_GLAZED_TERRACOTTA), Theme.MACHINE, "网络电容 (5)");;

        NETWORK_ADVANCED_QUANTUM_STORAGE = Theme.themedSlimefunItemStack("NE_ADVANCED_QUANTUM_STORAGE",new ItemStack(Material.AMETHYST_BLOCK),Theme.MACHINE,"高级量子存储","可自定义的最大存储容量","请注意设置数量之后不能在设置小于之前设置的数量","否则清空到当前最大容量");

        //蓝图
        MAGIC_WORKBENCH_BLUEPRINT = Theme.themedSlimefunItemStack("NE_MAGIC_WORKBENCH_BLUEPRINT",new ItemStack(Material.BLACK_DYE),Theme.TOOL,"魔法工作台蓝图","一张空白的蓝图","可以存储一个魔法工作台配方");
        ARMOR_FORGE_BLUEPRINT = Theme.themedSlimefunItemStack("NE_ARMOR_FORGE_BLUEPRINT",new ItemStack(Material.BROWN_DYE),Theme.TOOL,"盔甲锻造台蓝图","一张空白的蓝图","可以存储一个盔甲锻造台配方");
        SMELTERY_BLUEPRINT = Theme.themedSlimefunItemStack("NE_SMELTERY_BLUEPRINT",new ItemStack(Material.LIME_DYE),Theme.TOOL,"冶炼炉蓝图","一张空白的蓝图","可以存储一个冶炼炉配方");
        QUANTUM_WORKBENCH_BLUEPRINT = Theme.themedSlimefunItemStack("NE_QUANTUM_WORKBENCH_BLUEPRINT",new ItemStack(Material.MAGENTA_DYE),Theme.TOOL,"量子工作台蓝图","一张空白的蓝图","可以存储一个量子工作台配方");
        ANCIENT_ALTAR_BLUEPRINT = Theme.themedSlimefunItemStack("NE_ANCIENT_ALTAR_BLUEPRINT",new ItemStack(Material.CYAN_DYE),Theme.TOOL,"古代祭坛蓝图","一张空白的蓝图","可以存储一个古代祭坛配方");
        //编码器
        NE_MAGIC_WORKBENCH_RECIPE_ENCODER = Theme.themedSlimefunItemStack("NE_MAGIC_WORKBENCH_RECIPE_ENCODER",new ItemStack(Material.OAK_HANGING_SIGN),Theme.MACHINE,"网络魔法工作台配方编码器","可以根据输入的物品来制作蓝图","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次编码", Theme.CLICK_INFO, Theme.PASSIVE, 20000));
        NE_ARMOR_FORGE_RECIPE_ENCODER = Theme.themedSlimefunItemStack("NE_ARMOR_FORGE_RECIPE_ENCODER",new ItemStack(Material.SPRUCE_HANGING_SIGN),Theme.MACHINE,"网络盔甲锻造台配方编码器","可以根据输入的物品来制作蓝图","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次编码", Theme.CLICK_INFO, Theme.PASSIVE, 20000));
        NE_SMELTERY_RECIPE_ENCODER = Theme.themedSlimefunItemStack("NE_SMELTERY_RECIPE_ENCODER",new ItemStack(Material.BIRCH_HANGING_SIGN),Theme.MACHINE,"网络冶炼炉配方编码器","可以根据输入的物品来制作蓝图","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次编码", Theme.CLICK_INFO, Theme.PASSIVE, 20000));
        NE_QUANTUM_WORKBENCH_RECIPE_ENCODER = Theme.themedSlimefunItemStack("NE_QUANTUM_WORKBENCH_RECIPE_ENCODER",new ItemStack(Material.JUNGLE_HANGING_SIGN),Theme.MACHINE,"网络量子工作台配方编码器","可以根据输入的物品来制作蓝图","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次编码", Theme.CLICK_INFO, Theme.PASSIVE, 20000));
        NE_ANCIENT_ALTAR_RECIPE_ENCODER = Theme.themedSlimefunItemStack("NE_ANCIENT_ALTAR_RECIPE_ENCODER",new ItemStack(Material.LODESTONE),Theme.MACHINE,"网络古代祭坛配方编码器","可以根据输入的物品来制作蓝图","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次编码", Theme.CLICK_INFO, Theme.PASSIVE, 20000));
        //自动合成
        NE_AUTO_MAGIC_WORKBENCH = Theme.themedSlimefunItemStack("NE_AUTO_MAGIC_WORKBENCH",new ItemStack(Material.BOOKSHELF),Theme.MACHINE,"网络自动魔法工作台","需要魔法工作台蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 640));
        NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING = Theme.themedSlimefunItemStack("NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING",new ItemStack(Material.CHISELED_BOOKSHELF),Theme.MACHINE,"网络自动魔法工作台 (预留版)","需要魔法工作台蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","","预留版的自动合成机会不断进行合成","直到输出栏拥有1组物品","这一组物品可以在网络中访问","也可以通过货运系统取出","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 1280));
        NE_AUTO_ARMOR_FORGE = Theme.themedSlimefunItemStack("NE_AUTO_ARMOR_FORGE",new ItemStack(Material.SMITHING_TABLE),Theme.MACHINE,"网络自动盔甲锻造台","需要盔甲锻造台蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 640));
        NE_AUTO_ARMOR_FORGE_WITHHOLDING = Theme.themedSlimefunItemStack("NE_AUTO_ARMOR_FORGE_WITHHOLDING",new ItemStack(Material.CARTOGRAPHY_TABLE),Theme.MACHINE,"网络自动盔甲锻造台 (预留版)","需要盔甲锻造台蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","","预留版的自动合成机会不断进行合成","直到输出栏拥有1组物品","这一组物品可以在网络中访问","也可以通过货运系统取出","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 1280));
        NE_AUTO_SMELTERY = Theme.themedSlimefunItemStack("NE_AUTO_SMELTERY",new ItemStack(Material.FURNACE),Theme.MACHINE,"网络自动冶炼炉","需要冶炼炉蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 640));
        NE_AUTO_SMELTERY_WITHHOLDING = Theme.themedSlimefunItemStack("NE_AUTO_SMELTERY_WITHHOLDING",new ItemStack(Material.BLAST_FURNACE),Theme.MACHINE,"网络自动冶炼炉 (预留版)","需要冶炼炉蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","","预留版的自动合成机会不断进行合成","直到输出栏拥有1组物品","这一组物品可以在网络中访问","也可以通过货运系统取出","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 1280));
        NE_AUTO_QUANTUM_WORKBENCH = Theme.themedSlimefunItemStack("NE_AUTO_QUANTUM_WORKBENCH",new ItemStack(Material.HAY_BLOCK),Theme.MACHINE,"网络自动量子工作台","需要量子工作台蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 640));
        NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = Theme.themedSlimefunItemStack("NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING",new ItemStack(Material.DRIED_KELP_BLOCK),Theme.MACHINE,"网络自动量子工作台 (预留版)","需要量子工作台蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","","预留版的自动合成机会不断进行合成","直到输出栏拥有1组物品","这一组物品可以在网络中访问","也可以通过货运系统取出","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 1280));
        NE_AUTO_ANCIENT_ALTAR = Theme.themedSlimefunItemStack("NE_AUTO_ANCIENT_ALTAR",new ItemStack(Material.CRAFTING_TABLE),Theme.MACHINE,"网络自动古代祭坛","需要古代祭坛蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 640));
        NE_AUTO_ANCIENT_ALTAR_WITHHOLDING = Theme.themedSlimefunItemStack("NE_AUTO_ANCIENT_ALTAR_WITHHOLDING",new ItemStack(Material.ENCHANTING_TABLE),Theme.MACHINE,"网络自动古代祭坛 (预留版)","需要古代祭坛蓝图才能工作。","当网络中没有蓝图的目标物品时，","机器会自动从网络中选取材料进行合成","(需要网络中有足够的原材料)","","预留版的自动合成机会不断进行合成","直到输出栏拥有1组物品","这一组物品可以在网络中访问","也可以通过货运系统取出","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次合成", Theme.CLICK_INFO, Theme.PASSIVE, 1280));

        NETWORK_GRID_NEW_STYLE = Theme.themedSlimefunItemStack("NTW_GRID_NEW_STYLE",new ItemStack(Material.NOTE_BLOCK),Theme.MACHINE,"高级网格","高级网格允许你查看网络中所有的物品","你也可以直接放入或取出物品");
        NETWORK_CRAFTING_GRID_NEW_STYLE = Theme.themedSlimefunItemStack("NTW_CRAFTING_GRID_NEW_STYLE",new ItemStack(Material.JUKEBOX),Theme.MACHINE,"高级网格(带合成)","这种网格与普通网格类似","但会显示更少的物品","不过你可以直接使用网络中的物品","进行合成");
        NETWORK_ENCODING_GRID_NEW_STYLE = Theme.themedSlimefunItemStack("NTW_ENCODING_GRID_NEW_STYLE",new ItemStack(Material.TARGET),Theme.MACHINE,"高级网格(带编码)","这种网格与高级网格类似", "但会显示更少的物品","不过你可以直接使用网络中的物品","进行编码");

        //网桥
        NE_BRIDGE_WHITE = Theme.themedSlimefunItemStack("NE_BRIDGE_WHITE",new ItemStack(Material.WHITE_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(白色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_LIGHT_GRAY = Theme.themedSlimefunItemStack("NE_BRIDGE_LIGHT_GRAY",new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(淡灰色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_GRAY = Theme.themedSlimefunItemStack("NE_BRIDGE_GRAY",new ItemStack(Material.GRAY_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(灰色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_BLACK = Theme.themedSlimefunItemStack("NE_BRIDGE_BLACK",new ItemStack(Material.BLACK_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(黑色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_BROWN = Theme.themedSlimefunItemStack("NE_BRIDGE_BROWN",new ItemStack(Material.BROWN_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(棕色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_RED = Theme.themedSlimefunItemStack("NE_BRIDGE_RED",new ItemStack(Material.RED_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(红色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_ORANGE = Theme.themedSlimefunItemStack("NE_BRIDGE_ORANGE",new ItemStack(Material.ORANGE_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(橙色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_YELLOW = Theme.themedSlimefunItemStack("NE_BRIDGE_YELLOW",new ItemStack(Material.YELLOW_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(黄色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_LIME = Theme.themedSlimefunItemStack("NE_BRIDGE_LIME",new ItemStack(Material.LIME_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(黄绿色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_GREEN = Theme.themedSlimefunItemStack("NE_BRIDGE_GREEN",new ItemStack(Material.GREEN_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(绿色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_CYAN = Theme.themedSlimefunItemStack("NE_BRIDGE_CYAN",new ItemStack(Material.CYAN_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(青色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_LIGHT_BLUE = Theme.themedSlimefunItemStack("NE_BRIDGE_LIGHT_BLUE",new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(淡蓝色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_BLUE = Theme.themedSlimefunItemStack("NE_BRIDGE_BLUE",new ItemStack(Material.BLUE_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(蓝色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_PURPLE = Theme.themedSlimefunItemStack("NE_BRIDGE_PURPLE",new ItemStack(Material.PURPLE_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(紫色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_MAGENTA = Theme.themedSlimefunItemStack("NE_BRIDGE_MAGENTA",new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(品红色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");
        NE_BRIDGE_PINK = Theme.themedSlimefunItemStack("NE_BRIDGE_PINK",new ItemStack(Material.PINK_STAINED_GLASS_PANE),Theme.MACHINE,"网桥(粉红色)","网桥用于连接不同的网络物品","来形成一个完整的网络","更加清晰的布局网络");

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
