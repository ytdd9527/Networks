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
    public static final SlimefunItemStack NE_CHAING_PUSHER_PARTICLE;
    public static final SlimefunItemStack NE_CHAING_GRABBER;
    public static final SlimefunItemStack NE_CHAING_GRABBER_PLUS;
    public static final SlimefunItemStack NEA_IMPORT;
    public static final SlimefunItemStack NEA_EXPORT;

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
    //高级量子存储
    public static final SlimefunItemStack NETWORK_ADVANCED_QUANTUM_STORAGE;
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

        NE_EXPANSION_WORKBENCH = Theme.themedSlimefunItemStack("NE_EXPANSION_WORKBENCH",getPreEnchantedItemStack(Material.BAMBOO_BLOCK, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络拓展工作台","可以合成拓展机器");
        //工具
        NE_COORDINATE_CONFIGURATOR = Theme.themedSlimefunItemStack("NE_COORDINATE_CONFIGURATOR",getPreEnchantedItemStack(Material.RECOVERY_COMPASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.TOOL,"网络坐标配置器","用于储存一个坐标接收器的位置,","并设置到坐标传输中.","",MessageFormat.format("{0}右键点击: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "记录坐标接收器的当前位置"),MessageFormat.format("{0}Shift+右键点击: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "将位置设置到坐标传输器中"));
        //运输
        NE_COORDINATE_TRANSMITTER = Theme.themedSlimefunItemStack("NE_COORDINATE_TRANSMITTER",getPreEnchantedItemStack(Material.NOTE_BLOCK, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络坐标传输器","网络坐标传输器可以","将其中的物品传输到绑定的","网络坐标接收器中(只能在同一世界).","使用网络坐标配置器来进行绑定.","",MessageFormat.format("{0}网络电力消耗: {1}{2} 每次传输", Theme.CLICK_INFO, Theme.PASSIVE, 10000));
        NE_COORDINATE_RECEIVER = Theme.themedSlimefunItemStack("NE_COORDINATE_RECEIVER",getPreEnchantedItemStack(Material.JUKEBOX, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络坐标接收器","网络坐标接收器可以","接收来自绑定的网络坐标传输器","中的物品(只能在同一世界).","每粘液刻会把接收到的物品","尝试推送到网络中.");
        NE_CHAING_PUSHER = Theme.themedSlimefunItemStack("NE_CHAING_PUSHER",new ItemStack(Material.OBSERVER),Theme.MACHINE,"网络链式推送器","网络链式推送器会尝试将","指定的物品送入机器中","指定的方向可延伸32格以内的机器推送指定物品","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_CHAING_PUSHER_PARTICLE = Theme.themedSlimefunItemStack("NE_CHAING_PUSHER_PLUS",getPreEnchantedItemStack(Material.OBSERVER, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络链式推送器Plus","网络链式推送器会尝试从","指定的物品送入机器中","指定的方向可延伸64格以内的机器推送指定物品","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_CHAING_GRABBER = Theme.themedSlimefunItemStack("NE_EXPANSION_GRABBER_1",new ItemStack(Material.DISPENSER),Theme.MACHINE,"网络链式抓取器","网络链式抓取器会尝试从","指定的方向抓取延伸32格以内的机器的输出槽送回网络中","单个机器切勿使用,建议堆叠大量机器进行使用");
        NE_CHAING_GRABBER_PLUS = Theme.themedSlimefunItemStack("NE_EXPANSION_GRABBER_PLUS",getPreEnchantedItemStack(Material.DISPENSER, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络链式抓取器Plus","网络链式抓取器Plus会尝试从","指定的方向抓取延伸64格以内的机器的输出槽送回网络中","单个机器切勿使用,建议堆叠大量机器进行使用");
        NEA_IMPORT = Theme.themedSlimefunItemStack("NEA_IMPORT",getPreEnchantedItemStack(Material.RED_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)),Theme.MACHINE,"网络高级入口","网络高级入口会将其中的物品送入网络中","每个SF tick可传输最多54组物品","可接收来自货运网络的物品");
        NEA_EXPORT = Theme.themedSlimefunItemStack("NEA_EXPORT",getPreEnchantedItemStack(Material.BLUE_STAINED_GLASS, true, new Pair<>(Enchantment.ARROW_DAMAGE, 1)), Theme.MACHINE,"网络高级出口","网络高级出口可以设置成","持续将1组指定的物品送出网络","可以使用货运网络从中提取物品");
         //蓝图
        MAGIC_WORKBENCH_BLUEPRINT = Theme.themedSlimefunItemStack("NE_MAGIC_WORKBENCH_BLUEPRINT",new ItemStack(Material.BLACK_DYE),Theme.TOOL,"魔法工作台蓝图","一张空白的蓝图","可以存储一个魔法工作台配方");
        ARMOR_FORGE_BLUEPRINT = Theme.themedSlimefunItemStack("NE_ARMOR_FORGE_BLUEPRINT",new ItemStack(Material.BROWN_DYE),Theme.TOOL,"盔甲锻造台蓝图","一张空白的蓝图","可以存储一个盔甲锻造台配方");
        SMELTERY_BLUEPRINT = Theme.themedSlimefunItemStack("NE_SMELTERY_BLUEPRINT",new ItemStack(Material.LIME_DYE),Theme.TOOL,"冶炼炉蓝图","一张空白的蓝图","可以存储一个冶炼炉配方");
        QUANTUM_WORKBENCH_BLUEPRINT = Theme.themedSlimefunItemStack("NE_QUANTUM_WORKBENCH_BLUEPRINT",new ItemStack(Material.MAGENTA_DYE),Theme.TOOL,"量子工作台蓝图","一张空白的蓝图","可以存储一个量子工作台配方");
        ANCIENT_ALTAR_BLUEPRINT = Theme.themedSlimefunItemStack("NE_ANCIENT_ALTAR_BLUEPRINT",new ItemStack(Material.CYAN_DYE),Theme.TOOL,"古代祭坛蓝图","一张空白的蓝图","可以存储一个量子工作台配方");
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
        //网络高级量子存储
        NETWORK_ADVANCED_QUANTUM_STORAGE = Theme.themedSlimefunItemStack("NE_ADVANCED_QUANTUM_STORAGE",new ItemStack(Material.AMETHYST_BLOCK),Theme.MACHINE,"高级量子存储","可自定义的最大存储容量","请注意设置数量之后不能在设置小于之前设置的数量","否则清空到当前最大容量");

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
}
