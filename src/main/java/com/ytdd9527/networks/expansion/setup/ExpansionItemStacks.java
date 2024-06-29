package com.ytdd9527.networks.expansion.setup;

import com.ytdd9527.networks.expansion.core.cargoexpansion.items.storage.StorageUnitType;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ytdd9527
 * @since 2.0
 */
public final class ExpansionItemStacks {

    //工作台
    public static final SlimefunItemStack NE_EXPANSION_WORKBENCH = Theme.tsItem(
        "NE_EXPANSION_WORKBENCH",
        Enchanted(Material.BAMBOO_BLOCK),
        Theme.MACHINE,
        "网络拓展工作台"
    );

    //工具
    public static final SlimefunItemStack NE_COORDINATE_CONFIGURATOR = Theme.tsItem(
        "NE_COORDINATE_CONFIGURATOR",
        Enchanted(Material.RECOVERY_COMPASS),
        Theme.TOOL,
        "网络坐标配置器"
    );

    //高级网络物品
    public static final SlimefunItemStack ADVANCED_IMPORT = Theme.tsItem(
        "NE_ADVANCED_IMPORT",
        Enchanted(Material.RED_STAINED_GLASS),
        Theme.MACHINE,
        "高级网络入口"
    );
    public static final SlimefunItemStack ADVANCED_EXPORT = Theme.tsItem(
        "NE_ADVANCED_EXPORT",
        Enchanted(Material.BLUE_STAINED_GLASS),
        Theme.MACHINE,
        "高级网络出口"
    );
    public static final SlimefunItemStack ADVANCED_PURGER = Theme.tsItem(
        "NE_ADVANCED_PURGER",
        Enchanted(Material.YELLOW_STAINED_GLASS),
        Theme.MACHINE,
        "高级网络清除器"
    );
    public static final SlimefunItemStack ADVANCED_GREEDY_BLOCK = Theme.tsItem(
        "NE_ADVANCED_GREEDY_BLOCK",
        Enchanted(Material.YELLOW_STAINED_GLASS),
        Theme.MACHINE,
        "高级网络阻断器"
    );

    public static final SlimefunItemStack NETWORK_CAPACITOR_5 = Theme.tsItem(
        "NTW_CAPACITOR_5",
        new ItemStack(Material.CYAN_GLAZED_TERRACOTTA),
        Theme.MACHINE,
        "网络电容 (5)",
        "网络电容可以接收来自",
        "能源网络的电力并存储起来",
        "以供其他网络设备使用",
        "",
        MessageFormat.format("{0}容量: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 100000000)
    );

    //存储
    public static final SlimefunItemStack ADVANCED_QUANTUM_STORAGE = Theme.tsItem(
        "NE_ADVANCED_QUANTUM_STORAGE",
        new ItemStack(Material.AMETHYST_BLOCK),
        Theme.MACHINE,
        "高级量子存储",
        "可自定义的最大存储容量",
        "请注意设置数量之后不能在设置小于之前设置的数量",
        "否则清空到当前最大容量"
    );

    //网格
    public static final SlimefunItemStack NETWORK_GRID_NEW_STYLE = Theme.themedSlimefunItemStack(
        
        "NTW_GRID_NEW_STYLE",
        new ItemStack(Material.NOTE_BLOCK),
        Theme.MACHINE,
        "高级网格",
        "高级网格允许你查看网络中所有的物品",
        "你也可以直接放入或取出物品"
    );
    public static final SlimefunItemStack NETWORK_CRAFTING_GRID_NEW_STYLE = Theme.themedSlimefunItemStack(
        
        "NTW_CRAFTING_GRID_NEW_STYLE",
        new ItemStack(Material.JUKEBOX),
        Theme.MACHINE,
        "高级网格(带合成)",
        "这种网格与普通网格类似",
        "但会显示更少的物品",
        "不过你可以直接使用网络中的物品",
        "进行合成"
    );
    public static final SlimefunItemStack NETWORK_ENCODING_GRID_NEW_STYLE = Theme.themedSlimefunItemStack(
        
        "NTW_ENCODING_GRID_NEW_STYLE",
        new ItemStack(Material.TARGET),
        Theme.MACHINE,
        "高级网格(带编码)",
        "这种网格与高级网格类似",
        "但会显示更少的物品",
        "不过你可以直接使用网络中的物品",
        "进行编码"
    );

    //运输
    public static final SlimefunItemStack CHAIN_PUSHER = Theme.tsItem(
        "NE_CHAING_PUSHER",
        new ItemStack(Material.OBSERVER),
        Theme.MACHINE,
        "网络链式推送器"
    );
    public static final SlimefunItemStack CHAIN_GRABBER = Theme.tsItem(
        "NE_EXPANSION_GRABBER_1",
        new ItemStack(Material.DISPENSER),
        Theme.MACHINE,
        "网络链式抓取器"
    );
    public static final SlimefunItemStack CHAIN_DISPATCHER = Theme.tsItem(
        "NE_CHAIN_DISPATCHER",
        new ItemStack(Material.PISTON),
        Theme.MACHINE,
        "网链调度器"
    );

    public static final SlimefunItemStack CHAIN_PUSHER_PLUS = Theme.tsItem(
        "NE_CHAIN_PUSHER_PLUS",
        new ItemStack(Material.OBSERVER),
        Theme.MACHINE,
        "网络链式推送器Plus"
    );
    public static final SlimefunItemStack CHAIN_GRABBER_PLUS = Theme.tsItem(
        "NE_EXPANSION_GRABBER_PLUS",
        new ItemStack(Material.DISPENSER),
        Theme.MACHINE,
        "网络链式抓取器Plus"
    );
    public static final SlimefunItemStack CHAIN_DISPATCHER_PLUS = Theme.tsItem(
        "NE_CHAIN_DISPATCHER_PLUS",
        new ItemStack(Material.PISTON),
        Theme.MACHINE,
        "网链调度器Plus"
    );

    public static final SlimefunItemStack ADVANCED_CHAIN_PUSHER = Theme.tsItem(
        "NE_ADVANCED_CHAIN_PUSHER",
        Enchanted(Material.OBSERVER),
        Theme.MACHINE,
        "高级网络链式推送器"
    );
    public static final SlimefunItemStack ADVANCED_CHAIN_GRABBER = Theme.tsItem(
        "NE_ADVANCED_CHAIN_GRABBER",
        Enchanted(Material.DISPENSER),
        Theme.MACHINE,
        "高级网络链式抓取器"
    );
    public static final SlimefunItemStack ADVANCED_CHAIN_DISPATCHER = Theme.tsItem(
        "NE_ADVANCED_CHAIN_DISPATCHER",
        Enchanted(Material.STICKY_PISTON),
        Theme.MACHINE,
        "高级网链调度器"
    );

    public static final SlimefunItemStack ADVANCED_CHAIN_PUSHER_PLUS = Theme.tsItem(
        "NE_ADVANCED_CHAIN_PUSHER_PLUS",
        Enchanted(Material.OBSERVER),
        Theme.MACHINE,
        "高级网络链式推送器Plus"
    );
    public static final SlimefunItemStack ADVANCED_CHAIN_GRABBER_PLUS = Theme.tsItem(
        "NE_ADVANCED_CHAIN_GRABBER_PLUS",
        Enchanted(Material.DISPENSER),
        Theme.MACHINE,
        "高级网络链式抓取器Plus"
    );
    public static final SlimefunItemStack ADVANCED_CHAIN_DISPATCHER_PLUS = Theme.tsItem(
        "NE_ADVANCED_CHAIN_DISPATCHER_PLUS",
        Enchanted(Material.STICKY_PISTON),
        Theme.MACHINE,
        "高级网链调度器Plus"
    );

    public static final SlimefunItemStack COORDINATE_TRANSMITTER = Theme.tsItem(
        "NE_COORDINATE_TRANSMITTER",
        new ItemStack(Material.NOTE_BLOCK),
        Theme.MACHINE,
        "网络坐标传输器"
    );
    public static final SlimefunItemStack COORDINATE_RECEIVER = Theme.tsItem(
        "NE_COORDINATE_RECEIVER",
        new ItemStack(Material.JUKEBOX),
        Theme.MACHINE,
        "网络坐标接收器"
    );

    //蓝图
    public static final SlimefunItemStack MAGIC_WORKBENCH_BLUEPRINT = Theme.tsItem(
        "NE_MAGIC_WORKBENCH_BLUEPRINT",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "魔法工作台蓝图"
    );
    public static final SlimefunItemStack ARMOR_FORGE_BLUEPRINT = Theme.tsItem(
        "NE_ARMOR_FORGE_BLUEPRINT",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "盔甲锻造台蓝图"
    );
    public static final SlimefunItemStack SMELTERY_BLUEPRINT = Theme.tsItem(
        "NE_SMELTERY_BLUEPRINT",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "冶炼炉蓝图"
    );
    public static final SlimefunItemStack QUANTUM_WORKBENCH_BLUEPRINT = Theme.tsItem(
        "NE_QUANTUM_WORKBENCH_BLUEPRINT",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "量子工作台蓝图"
    );
    public static final SlimefunItemStack ANCIENT_ALTAR_BLUEPRINT = Theme.tsItem(
        "NE_ANCIENT_ALTAR_BLUEPRINT",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "古代祭坛蓝图"
    );
    public static final SlimefunItemStack EXPANSION_WORKBENCH_BLUEPRINT = Theme.tsItem(
        "NE_EXPANSION_WORKBENCH_BLUEPRINT",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "网络拓展工作台蓝图"
    );

    //编码器
    public static final SlimefunItemStack MAGIC_WORKBENCH_RECIPE_ENCODER = Theme.tsItem(
        "NE_MAGIC_WORKBENCH_RECIPE_ENCODER",
        new ItemStack(Material.OAK_HANGING_SIGN),
        Theme.MACHINE,
        "网络魔法工作台配方编码器"
    );
    public static final SlimefunItemStack ARMOR_FORGE_RECIPE_ENCODER = Theme.tsItem(
        "NE_ARMOR_FORGE_RECIPE_ENCODER",
        new ItemStack(Material.SPRUCE_HANGING_SIGN),
        Theme.MACHINE,
        "网络盔甲锻造台配方编码器"
    );
    public static final SlimefunItemStack SMELTERY_RECIPE_ENCODER = Theme.tsItem(
        "NE_SMELTERY_RECIPE_ENCODER",
        new ItemStack(Material.BIRCH_HANGING_SIGN),
        Theme.MACHINE,
        "网络冶炼炉配方编码器"
    );
    public static final SlimefunItemStack QUANTUM_WORKBENCH_RECIPE_ENCODER = Theme.tsItem(
        "NE_QUANTUM_WORKBENCH_RECIPE_ENCODER",
        new ItemStack(Material.JUNGLE_HANGING_SIGN),
        Theme.MACHINE,
        "网络量子工作台配方编码器"
    );
    public static final SlimefunItemStack ANCIENT_ALTAR_RECIPE_ENCODER = Theme.tsItem(
        "NE_ANCIENT_ALTAR_RECIPE_ENCODER",
        new ItemStack(Material.CHERRY_HANGING_SIGN),
        Theme.MACHINE,
        "网络古代祭坛配方编码器"
    );
    public static final SlimefunItemStack EXPANSION_WORKBENCH_RECIPE_ENCODER = Theme.tsItem(
        "NE_EXPANSION_WORKBENCH_RECIPE_ENCODER",
        new ItemStack(Material.ACACIA_HANGING_SIGN),
        Theme.MACHINE,
        "网络拓展工作台配方编码器"
    );

    //网络合成机
    public static final SlimefunItemStack AUTO_MAGIC_WORKBENCH = Theme.tsItem(
        "NE_AUTO_MAGIC_WORKBENCH",
        new ItemStack(Material.BOOKSHELF),
        Theme.MACHINE,
        "网络自动魔法工作台"
    );
    public static final SlimefunItemStack AUTO_MAGIC_WORKBENCH_WITHHOLDING = Theme.tsItem(
        "NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING",
        new ItemStack(Material.CHISELED_BOOKSHELF),
        Theme.MACHINE,
        "网络自动魔法工作台 (预留版)"
    );
    public static final SlimefunItemStack AUTO_ARMOR_FORGE = Theme.tsItem(
        "NE_AUTO_ARMOR_FORGE",
        new ItemStack(Material.SMITHING_TABLE),
        Theme.MACHINE,
        "网络自动盔甲锻造台"
    );
    public static final SlimefunItemStack AUTO_ARMOR_FORGE_WITHHOLDING = Theme.tsItem(
        "NE_AUTO_ARMOR_FORGE_WITHHOLDING",
        new ItemStack(Material.CARTOGRAPHY_TABLE),
        Theme.MACHINE,
        "网络自动盔甲锻造台 (预留版)"
    );
    public static final SlimefunItemStack AUTO_SMELTERY = Theme.tsItem(
        "NE_AUTO_SMELTERY",
        new ItemStack(Material.FURNACE),
        Theme.MACHINE,
        "网络自动冶炼炉"
    );
    public static final SlimefunItemStack AUTO_SMELTERY_WITHHOLDING = Theme.tsItem(
        "NE_AUTO_SMELTERY_WITHHOLDING",
        new ItemStack(Material.BLAST_FURNACE),
        Theme.MACHINE,
        "网络自动冶炼炉 (预留版)"
    );
    public static final SlimefunItemStack AUTO_QUANTUM_WORKBENCH = Theme.tsItem(
        "NE_AUTO_QUANTUM_WORKBENCH",
        new ItemStack(Material.HAY_BLOCK),
        Theme.MACHINE,
        "网络自动量子工作台"
    );
    public static final SlimefunItemStack AUTO_QUANTUM_WORKBENCH_WITHHOLDING = Theme.tsItem(
        "NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING",
        new ItemStack(Material.DRIED_KELP_BLOCK),
        Theme.MACHINE,
        "网络自动量子工作台 (预留版)"
    );
    public static final SlimefunItemStack AUTO_ANCIENT_ALTAR = Theme.tsItem(
        "NE_AUTO_ANCIENT_ALTAR",
        new ItemStack(Material.CRAFTING_TABLE),
        Theme.MACHINE,
        "网络自动古代祭坛"
    );
    public static final SlimefunItemStack AUTO_ANCIENT_ALTAR_WITHHOLDING = Theme.tsItem(
        "NE_AUTO_ANCIENT_ALTAR_WITHHOLDING",
        new ItemStack(Material.ENCHANTING_TABLE),
        Theme.MACHINE,
        "网络自动古代祭坛 (预留版)"
    );
    public static final SlimefunItemStack AUTO_EXPANSION_WORKBENCH = Theme.tsItem(
        "NE_AUTO_EXPANSION_WORKBENCH",
        new ItemStack(Material.FIRE_CORAL_BLOCK),
        Theme.MACHINE,
        "网络自动网络拓展工作台"
    );
    public static final SlimefunItemStack AUTO_EXPANSION_WORKBENCH_WITHHOLDING = Theme.tsItem(
        "NE_AUTO_EXPANSION_WORKBENCH_WITHHOLDING",
        new ItemStack(Material.HORN_CORAL_BLOCK),
        Theme.MACHINE,
        "网络自动网络拓展工作台 (预留版)"
    );

    //高级网络合成机
    public static final SlimefunItemStack ADVANCED_AUTO_MAGIC_WORKBENCH = Theme.tsItem(
        "NEA_AUTO_MAGIC_WORKBENCH",
        Enchanted(Material.BOOKSHELF),
        Theme.MACHINE,
        "高级网络自动魔法工作台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING = Theme.tsItem(
        "NEA_AUTO_MAGIC_WORKBENCH_WITHHOLDING",
        Enchanted(Material.CHISELED_BOOKSHELF),
        Theme.MACHINE,
        "高级网络自动魔法工作台 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ARMOR_FORGE = Theme.tsItem(
        "NEA_AUTO_ARMOR_FORGE",
        Enchanted(Material.SMITHING_TABLE),
        Theme.MACHINE,
        "高级网络自动盔甲锻造台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING = Theme.tsItem(
        "NEA_AUTO_ARMOR_FORGE_WITHHOLDING",
        Enchanted(Material.CARTOGRAPHY_TABLE),
        Theme.MACHINE,
        "高级网络自动盔甲锻造台 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_SMELTERY = Theme.tsItem(
        "NEA_AUTO_SMELTERY",
        Enchanted(Material.FURNACE),
        Theme.MACHINE,
        "高级网络自动冶炼炉"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_SMELTERY_WITHHOLDING = Theme.tsItem(
        "NEA_AUTO_SMELTERY_WITHHOLDING",
        Enchanted(Material.BLAST_FURNACE),
        Theme.MACHINE,
        "高级网络自动冶炼炉 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_QUANTUM_WORKBENCH = Theme.tsItem(
        "NEA_AUTO_QUANTUM_WORKBENCH",
        Enchanted(Material.HAY_BLOCK),
        Theme.MACHINE,
        "高级网络自动量子工作台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = Theme.tsItem(
        "NEA_AUTO_QUANTUM_WORKBENCH_WITHHOLDING",
        Enchanted(Material.DRIED_KELP_BLOCK),
        Theme.MACHINE,
        "高级网络自动量子工作台 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ANCIENT_ALTAR = Theme.tsItem(
        "NEA_AUTO_ANCIENT_ALTAR",
        Enchanted(Material.CRAFTING_TABLE),
        Theme.MACHINE,
        "高级网络自动古代祭坛"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING = Theme.tsItem(
        "NEA_AUTO_ANCIENT_ALTAR_WITHHOLDING",
        Enchanted(Material.ENCHANTING_TABLE),
        Theme.MACHINE,
        "高级网络自动古代祭坛 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_EXPANSION_WORKBENCH = Theme.tsItem(
        "NEA_AUTO_EXPANSION_WORKBENCH",
        Enchanted(Material.FIRE_CORAL_BLOCK),
        Theme.MACHINE,
        "高级网络自动网络拓展工作台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = Theme.tsItem(
        "NEA_AUTO_EXPANSION_WORKBENCH_WITHHOLDING",
        Enchanted(Material.HORN_CORAL_BLOCK),
        Theme.MACHINE,
        "高级网络自动网络拓展工作台 (预留版)"
    );

    public static final SlimefunItemStack ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE = Theme.tsItem(
            "NEA_AUTO_ENHANCED_CRAFTING_TABLE",
            Enchanted(Material.BLUE_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "高级网络自动合成机"
    );

    public static final SlimefunItemStack ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE_WITHHOLDING = Theme.tsItem(
            "NEA_AUTO_ENHANCED_CRAFTING_TABLE_WITHHOLDING",
            Enchanted(Material.YELLOW_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "高级网络自动合成机（预留版）"
    );
    //网桥
    public static final SlimefunItemStack NE_BRIDGE_WHITE = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_WHITE",
        new ItemStack(Material.WHITE_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(白色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_LIGHT_GRAY = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_LIGHT_GRAY",
        new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(淡灰色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_GRAY = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_GRAY",
        new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(灰色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_BLACK = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_BLACK",
        new ItemStack(Material.BLACK_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(黑色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_BROWN = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_BROWN",
        new ItemStack(Material.BROWN_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(棕色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_RED = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_RED",
        new ItemStack(Material.RED_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(红色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_ORANGE = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_ORANGE",
        new ItemStack(Material.ORANGE_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(橙色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_YELLOW = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_YELLOW",
        new ItemStack(Material.YELLOW_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(黄色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_LIME = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_LIME",
        new ItemStack(Material.LIME_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(黄绿色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_GREEN = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_GREEN",
        new ItemStack(Material.GREEN_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(绿色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_CYAN = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_CYAN",
        new ItemStack(Material.CYAN_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(青色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_LIGHT_BLUE = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_LIGHT_BLUE",
        new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(淡蓝色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_BLUE = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_BLUE",
        new ItemStack(Material.BLUE_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(蓝色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_PURPLE = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_PURPLE",
        new ItemStack(Material.PURPLE_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(紫色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_MAGENTA = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_MAGENTA",
        new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(品红色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NE_BRIDGE_PINK = Theme.themedSlimefunItemStack(
        "NE_BRIDGE_PINK",
        new ItemStack(Material.PINK_STAINED_GLASS_PANE),
        Theme.MACHINE,
        "网桥(粉红色)",
        "网桥用于连接不同的网络物品",
        "来形成一个完整的网络",
        "更加清晰的布局网络"
    );

    private static final String thanks = "&x&c&c&8&c&f&4&l魔&x&c&b&9&7&f&5&l芋&x&c&a&a&3&f&6&l粘&x&c&9&a&e&f&7&l液&x&c&8&b&9&f&8&l科&x&c&7&c&5&f&9&l技&x&c&5&d&0&f&9&l服&x&c&4&d&b&f&a&l务&x&c&3&e&6&f&b&l器&x&c&2&f&2&f&c&l提&x&c&1&f&d&f&d&l供";
    private static final Map<StorageUnitType, SlimefunItemStack> typeMap = new HashMap<>();

    public static SlimefunItemStack CARGO_NODE_QUICK_TOOL = Theme.themedSlimefunItemStack(
            "CARGO_NODE_QUICK_TOOL",
            new ItemStack(Material.BONE),
            Theme.MACHINE,
            "&b货运节点快配工具",
            "",
            "&a右键: 设置指向货运节点的配置",
            "&e下蹲+右键: 从指向的货运节点加载配置",
            thanks
    );
    public static SlimefunItemStack STORAGE_UNIT_UPGRADE_TABLE = Theme.themedSlimefunItemStack(
            "STORAGE_UPGRADE_TABLE",
            new ItemStack(Material.CARTOGRAPHY_TABLE),
            Theme.MACHINE,
            "&6货运存储单元升级台",
            "",
            "&e用于升级货运存储单元",
            "&a内部物品不会丢失",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_1 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_1",
            new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 I",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 2 种物品",
            "&7⇨ &e每种物品可容纳 65536 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_2 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_2",
            new ItemStack(Material.GRAY_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 II",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 4 种物品",
            "&7⇨ &e每种物品可容纳 131072 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_3 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_3",
            new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 III",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 8 种物品",
            "&7⇨ &e每种物品可容纳 262144 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_4 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_4",
            new ItemStack(Material.CYAN_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 IV",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 16 种物品",
            "&7⇨ &e每种物品可容纳 524288 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_5 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_5",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 V",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 32 种物品",
            "&7⇨ &e每种物品可容纳 1048576 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_6 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_6",
            new ItemStack(Material.WHITE_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 VI",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 32 种物品",
            "&7⇨ &e每种物品可容纳 2097152 个",
            thanks
    );

    public static SlimefunItemStack CARGO_STORAGE_UNIT_7 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_7",
            new ItemStack(Material.YELLOW_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 VII",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 32 种物品",
            "&7⇨ &e每种物品可容纳 4194304 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_8 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_8",
            new ItemStack(Material.ORANGE_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 VIII",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 32 种物品",
            "&7⇨ &e每种物品可容纳 8388608 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_9 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_9",
            new ItemStack(Material.MAGENTA_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 IX",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 32 种物品",
            "&7⇨ &e每种物品可容纳 16777216 个",
            thanks
    );
    public static SlimefunItemStack CARGO_STORAGE_UNIT_10 = Theme.themedSlimefunItemStack(
            "CARGO_STORAGE_UNIT_10",
            new ItemStack(Material.RED_STAINED_GLASS),
            Theme.MACHINE,
            "&b货运存储单元 X",
            "",
            "&6支持快速输入/输出",
            "",
            "&7⇨ &e可储存 32 种物品",
            "&7⇨ &e每种物品可容纳 33554432 个",
            thanks
    );

    static {
        typeMap.put(StorageUnitType.TINY, CARGO_STORAGE_UNIT_1);
        typeMap.put(StorageUnitType.MINI, CARGO_STORAGE_UNIT_2);
        typeMap.put(StorageUnitType.SMALL, CARGO_STORAGE_UNIT_3);
        typeMap.put(StorageUnitType.MEDIUM, CARGO_STORAGE_UNIT_4);
        typeMap.put(StorageUnitType.LARGE, CARGO_STORAGE_UNIT_5);
        typeMap.put(StorageUnitType.ENHANCED, CARGO_STORAGE_UNIT_6);
        typeMap.put(StorageUnitType.ADVANCED, CARGO_STORAGE_UNIT_7);
        typeMap.put(StorageUnitType.EXTRA, CARGO_STORAGE_UNIT_8);
        typeMap.put(StorageUnitType.ULTRA, CARGO_STORAGE_UNIT_9);
        typeMap.put(StorageUnitType.END_GAME, CARGO_STORAGE_UNIT_10);
    }

    public static SlimefunItemStack getStorageItemFromType(StorageUnitType type) {
        return typeMap.get(type);
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
