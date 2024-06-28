package com.ytdd9527.networks.expansion.setup;



import com.ytdd9527.networks.expansion.core.util.ConfigUtil;
import com.ytdd9527.networks.libs.plugin.util.TextUtil;
import io.github.sefiraat.networks.Networks;
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



/**
 * @author ytdd9527
 * @since 2.0
 */
public final class ExpansionItemStacks {
    //工作台

    public static final SlimefunItemStack NETWORK_EXPANSION_WORKBENCH =  Theme.Random(
            "NTW_EXPANSION_WORKBENCH",
            new ItemStack(Material.BAMBOO_BLOCK),
            Theme.MACHINE,
            "网络拓展工作台"
    );

    //工具
    public static final SlimefunItemStack NETWORK_COORDINATE_CONFIGURATOR =  Theme.Random(
            "NTW_EXPANSION_COORDINATE_CONFIGURATOR",
            new ItemStack(Material.RECOVERY_COMPASS),
            Theme.MACHINE,
            "网络坐标配置器"
    );
    //高级网络物品
    public static final SlimefunItemStack ADVANCED_IMPORT = Theme.Random(
            "NTW_EXPANSION_ADVANCED_IMPORT",
            Enchanted(Material.RED_STAINED_GLASS),
            Theme.MACHINE,
            "高级网络入口"
    );
    public static final SlimefunItemStack ADVANCED_EXPORT = Theme.Random(
            "NTW_EXPANSION_ADVANCED_EXPORT",
            Enchanted(Material.BLUE_STAINED_GLASS),
            Theme.MACHINE,
            "高级网络出口"
    );
    public static final SlimefunItemStack ADVANCED_PURGER = Theme.Random(
            "NTW_EXPANSION_ADVANCED_PURGER",
            Enchanted(Material.YELLOW_STAINED_GLASS),
            Theme.MACHINE,
            "高级网络清除器"
    );
    public static final SlimefunItemStack ADVANCED_GREEDY_BLOCK = Theme.Random(
            "NTW_EXPANSION_ADVANCED_GREEDY_BLOCK",
            Enchanted(Material.GRAY_STAINED_GLASS),
            Theme.MACHINE,
            "高级网络阻断器"
    );

    public static final SlimefunItemStack NETWORK_CAPACITOR_5 = Theme.Random(
            "NTW_EXPANSION_CAPACITOR_5",
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
    public static final SlimefunItemStack ADVANCED_QUANTUM_STORAGE = Theme.Random(
            "NTW_EXPANSION_ADVANCED_QUANTUM_STORAGE",
            new ItemStack(Material.AMETHYST_BLOCK),
            Theme.MACHINE,
            "高级量子存储",
            "可自定义的最大存储容量",
            "请注意设置数量之后不能在设置小于之前设置的数量",
            "否则清空到当前最大容量"
    );


    //运输 LINE_TRANSFER POINT_TRANSFER_PUSHER

    //对点传输器
    public static final SlimefunItemStack LINE_TRANSFER_PUSHER = Theme.Random(
            "NTW_EXPANSION_LINE_TRANSFER_PUSHER",
            new ItemStack(Material.OBSERVER),
            Theme.MACHINE,
            "对点传输器 [推送]");
    public static final SlimefunItemStack LINE_TRANSFER_GRABBER = Theme.Random(
            "NTW_EXPANSION_LINE_TRANSFER_GRABBER",
            new ItemStack(Material.DISPENSER),
            Theme.MACHINE,
            "对点传输器 [抓取]"
    );
    public static final SlimefunItemStack LINE_TRANSFER_TRANSFER = Theme.Random(
            "NTW_EXPANSION_LINE_TRANSFER",
            new ItemStack(Material.PISTON),
            Theme.MACHINE,
            "对点传输器"
    );

    //对点传输器Plus
    public static final SlimefunItemStack LINE_TRANSFER_PLUS_PUSHER = Theme.Random(
            "NTW_EXPANSION_LINE_TRANSFER_PLUS_PUSHER",
            new ItemStack(Material.OBSERVER),
            Theme.MACHINE,
            "对点传输器Plus [推送]");
    public static final SlimefunItemStack LINE_TRANSFER_PLUS_PLUS_GRABBER = Theme.Random(
            "NTW_EXPANSION_LINE_TRANSFER_GRABBER",
            new ItemStack(Material.DISPENSER),
            Theme.MACHINE,
            "对点传输器Plus [抓取]"
    );
    public static final SlimefunItemStack LINE_TRANSFER_PLUS_PLUS_TRANSFER = Theme.Random(
            "NTW_EXPANSION_LINE_TRANSFER",
            new ItemStack(Material.PISTON),
            Theme.MACHINE,
            "对点传输器Plus"
    );


    //链式传输
    public static final SlimefunItemStack POINT_TRANSFER_PUSHER = Theme.Random(
            "NTW_EXPANSION_POINT_TRANSFER_PUSHER",
            new ItemStack(Material.OBSERVER),
            Theme.MACHINE,
            "链式传输器 [推送]");
    public static final SlimefunItemStack POINT_TRANSFER_GRABBER = Theme.Random(
            "NTW_EXPANSION_POINT_TRANSFER_GRABBER",
            new ItemStack(Material.DISPENSER),
            Theme.MACHINE,
            "链式传输器 [抓取]"
    );
    public static final SlimefunItemStack POINT_TRANSFER = Theme.Random(
            "NTW_EXPANSION_POINT_TRANSFER",
            new ItemStack(Material.PISTON),
            Theme.MACHINE,
            "链式传输器"
    );

    //链式传输Plus
    public static final SlimefunItemStack POINT_TRANSFER_PLUS_PUSHER = Theme.Random(
            "NTW_EXPANSION_POINT_TRANSFER_PLUS_PUSHER",
            new ItemStack(Material.OBSERVER),
            Theme.MACHINE,
            "链式传输器Plus [推送]");
    public static final SlimefunItemStack POINT_TRANSFER_PLUS_GRABBER = Theme.Random(
            "NTW_EXPANSION_POINT_TRANSFER_PLUS_GRABBER",
            new ItemStack(Material.DISPENSER),
            Theme.MACHINE,
            "链式传输器Plus [抓取]"
    );
    public static final SlimefunItemStack POINT_TRANSFER_PLUS = Theme.Random(
            "NTW_EXPANSION_POINT_TRANSFER_PLUS",
            new ItemStack(Material.STICKY_PISTON),
            Theme.MACHINE,
            "链式传输器Plus"
    );

    //高级链式传输
    public static final SlimefunItemStack ADVANCED_POINT_TRANSFER_PUSHER = Theme.Random(
            "NTW_EXPANSION_ADVANCED_POINT_TRANSFER_PUSHER",
            Enchanted(Material.OBSERVER),
            Theme.MACHINE,
            "高级链式传输 [推送]");
    public static final SlimefunItemStack ADVANCED_POINT_TRANSFER_GRABBER = Theme.Random(
            "NTW_EXPANSION_POINT_ADVANCED_TRANSFER_GRABBER",
            Enchanted(Material.DISPENSER),
            Theme.MACHINE,
            "高级链式传输 [抓取]"
    );
    public static final SlimefunItemStack ADVANCED_POINT_TRANSFER = Theme.Random(
            "NTW_EXPANSION_ADVANCED_POINT_TRANSFER",
            Enchanted(Material.PISTON),
            Theme.MACHINE,
            "高级链式传输"
    );

    //高级链式传输Plus
    public static final SlimefunItemStack ADVANCED_POINT_TRANSFER_PLUS_PUSHER = Theme.Random(
            "NTW_EXPANSION_ADVANCED_POINT_TRANSFER_PLUS_PUSHER",
            Enchanted(Material.OBSERVER),
            Theme.MACHINE,
            "高级链式传输Plus [推送]");
    public static final SlimefunItemStack ADVANCED_POINT_TRANSFER_PLUS_GRABBER = Theme.Random(
            "NTW_EXPANSION_POINT_ADVANCED_TRANSFER_PLUS_GRABBER",
            Enchanted(Material.DISPENSER),
            Theme.MACHINE,
            "高级链式传输Plus [抓取]"
    );
    public static final SlimefunItemStack ADVANCED_POINT_TRANSFER_PLUS = Theme.Random(
            "NTW_EXPANSION_ADVANCED_POINT_TRANSFER_PLUS",
            Enchanted(Material.STICKY_PISTON),
            Theme.MACHINE,
            "高级链式传输Plus"
    );


    //网格
    public static final SlimefunItemStack NETWORK_GRID_NEW_STYLE = Theme.Random(
            "NTW_EXPANSION_GRID_NEW_STYLE",
            new ItemStack(Material.NOTE_BLOCK),
            Theme.MACHINE,
            "高级网格",
            "高级网格允许你查看网络中所有的物品",
            "你也可以直接放入或取出物品"
    );
    public static final SlimefunItemStack NETWORK_CRAFTING_GRID_NEW_STYLE = Theme.Random(
            "NTW_EXPANSION_CRAFTING_GRID_NEW_STYLE",
            new ItemStack(Material.JUKEBOX),
            Theme.MACHINE,
            "高级网格(带合成)",
            "这种网格与普通网格类似",
            "但会显示更少的物品",
            "不过你可以直接使用网络中的物品",
            "进行合成"
    );
    public static final SlimefunItemStack NETWORK_ENCODING_GRID_NEW_STYLE = Theme.Random(
            "NTW_EXPANSION_ENCODING_GRID_NEW_STYLE",
            new ItemStack(Material.TARGET),
            Theme.MACHINE,
            "高级网格(带编码)",
            "这种网格与高级网格类似",
            "但会显示更少的物品",
            "不过你可以直接使用网络中的物品",
            "进行编码"
    );
    //蓝图
    public static final SlimefunItemStack MAGIC_WORKBENCH_BLUEPRINT = Theme.Random(
            "NTW_EXPANSION_MAGIC_WORKBENCH_BLUEPRINT",
            new ItemStack(Material.RED_DYE),
            Theme.MACHINE,
            "魔法工作台蓝图"
    );
    public static final SlimefunItemStack ARMOR_FORGE_BLUEPRINT = Theme.Random(
            "NTW_EXPANSION_ARMOR_FORGE_BLUEPRINT",
            new ItemStack(Material.ORANGE_DYE),
            Theme.MACHINE,
            "盔甲锻造台蓝图"
    );
    public static final SlimefunItemStack SMELTERY_BLUEPRINT = Theme.Random(
            "NTW_EXPANSION_SMELTERY_BLUEPRINT",
            new ItemStack(Material.YELLOW_DYE),
            Theme.MACHINE,
            "冶炼炉蓝图"
    );
    public static final SlimefunItemStack QUANTUM_WORKBENCH_BLUEPRINT = Theme.Random(
            "NTW_EXPANSION_QUANTUM_WORKBENCH_BLUEPRINT",
            new ItemStack(Material.LIME_DYE),
            Theme.MACHINE,
            "量子工作台蓝图"
    );
    public static final SlimefunItemStack ANCIENT_ALTAR_BLUEPRINT = Theme.Random(
            "NTW_EXPANSION_ANCIENT_ALTAR_BLUEPRINT",
            new ItemStack(Material.CYAN_DYE),
            Theme.MACHINE,
            "古代祭坛蓝图"
    );
    public static final SlimefunItemStack EXPANSION_WORKBENCH_BLUEPRINT = Theme.Random(
            "NTW_EXPANSION_EXPANSION_WORKBENCH_BLUEPRINT",
            new ItemStack(Material.BROWN_DYE),
            Theme.MACHINE,
            "网络拓展工作台蓝图"
    );

    //编码器
    public static final SlimefunItemStack MAGIC_WORKBENCH_RECIPE_ENCODER = Theme.Random(
            "NTW_EXPANSION_MAGIC_WORKBENCH_RECIPE_ENCODER",
            new ItemStack(Material.OAK_HANGING_SIGN),
            Theme.MACHINE,
            "网络魔法工作台配方编码器"
    );
    public static final SlimefunItemStack ARMOR_FORGE_RECIPE_ENCODER = Theme.Random(
            "NTW_EXPANSION_ARMOR_FORGE_RECIPE_ENCODER",
            new ItemStack(Material.SPRUCE_HANGING_SIGN),
            Theme.MACHINE,
            "网络盔甲锻造台配方编码器"
    );
    public static final SlimefunItemStack SMELTERY_RECIPE_ENCODER = Theme.Random(
            "NTW_EXPANSION_SMELTERY_RECIPE_ENCODER",
            new ItemStack(Material.BIRCH_HANGING_SIGN),
            Theme.MACHINE,
            "网络冶炼炉配方编码器"
    );
    public static final SlimefunItemStack QUANTUM_WORKBENCH_RECIPE_ENCODER = Theme.Random(
            "NTW_EXPANSION_QUANTUM_WORKBENCH_RECIPE_ENCODER",
            new ItemStack(Material.JUNGLE_HANGING_SIGN),
            Theme.MACHINE,
            "网络量子工作台配方编码器"
    );
    public static final SlimefunItemStack ANCIENT_ALTAR_RECIPE_ENCODER = Theme.Random(
            "NTW_EXPANSION_ANCIENT_ALTAR_RECIPE_ENCODER",
            new ItemStack(Material.CHERRY_HANGING_SIGN),
            Theme.MACHINE,
            "网络古代祭坛配方编码器"
    );
    public static final SlimefunItemStack EXPANSION_WORKBENCH_RECIPE_ENCODER = Theme.Random(
            "NTW_EXPANSION_EXPANSION_WORKBENCH_RECIPE_ENCODER",
            new ItemStack(Material.ACACIA_HANGING_SIGN),
            Theme.MACHINE,
            "网络拓展工作台配方编码器"
    );


    //网络合成机
    public static final SlimefunItemStack AUTO_MAGIC_WORKBENCH = Theme.Random(
            "NTW_EXPANSION_AUTO_MAGIC_WORKBENCH",
            new ItemStack(Material.BOOKSHELF),
            Theme.MACHINE,
            "网络自动魔法工作台"
    );
    public static final SlimefunItemStack AUTO_MAGIC_WORKBENCH_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_AUTO_MAGIC_WORKBENCH_WITHHOLDING",
            new ItemStack(Material.CHISELED_BOOKSHELF),
            Theme.MACHINE,
            "网络自动魔法工作台 (预留版)"
    );
    public static final SlimefunItemStack AUTO_ARMOR_FORGE = Theme.Random(
            "NTW_EXPANSION_AUTO_ARMOR_FORGE",
            new ItemStack(Material.SMITHING_TABLE),
            Theme.MACHINE,
            "网络自动盔甲锻造台"
    );
    public static final SlimefunItemStack AUTO_ARMOR_FORGE_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_AUTO_ARMOR_FORGE_WITHHOLDING",
            new ItemStack(Material.CARTOGRAPHY_TABLE),
            Theme.MACHINE,
            "网络自动盔甲锻造台 (预留版)"
    );
    public static final SlimefunItemStack AUTO_SMELTERY = Theme.Random(
            "NTW_EXPANSION_AUTO_SMELTERY",
            new ItemStack(Material.FURNACE),
            Theme.MACHINE,
            "网络自动冶炼炉"
    );
    public static final SlimefunItemStack AUTO_SMELTERY_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_AUTO_SMELTERY_WITHHOLDING",
            new ItemStack(Material.BLAST_FURNACE),
            Theme.MACHINE,
            "网络自动冶炼炉 (预留版)"
    );
    public static final SlimefunItemStack AUTO_QUANTUM_WORKBENCH = Theme.Random(
            "NTW_EXPANSION_AUTO_QUANTUM_WORKBENCH",
            new ItemStack(Material.HAY_BLOCK),
            Theme.MACHINE,
            "网络自动量子工作台"
    );
    public static final SlimefunItemStack AUTO_QUANTUM_WORKBENCH_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_AUTO_QUANTUM_WORKBENCH_WITHHOLDING",
            new ItemStack(Material.DRIED_KELP_BLOCK),
            Theme.MACHINE,
            "网络自动量子工作台 (预留版)"
    );
    public static final SlimefunItemStack AUTO_ANCIENT_ALTAR = Theme.Random(
            "NTW_EXPANSION_AUTO_ANCIENT_ALTAR",
            new ItemStack(Material.CRAFTING_TABLE),
            Theme.MACHINE,
            "网络自动古代祭坛"
    );
    public static final SlimefunItemStack AUTO_ANCIENT_ALTAR_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_AUTO_ANCIENT_ALTAR_WITHHOLDING",
            new ItemStack(Material.ENCHANTING_TABLE),
            Theme.MACHINE,
            "网络自动古代祭坛 (预留版)"
    );
    public static final SlimefunItemStack AUTO_EXPANSION_WORKBENCH = Theme.Random(
            "NTW_EXPANSION_AUTO_EXPANSION_WORKBENCH",
            new ItemStack(Material.FIRE_CORAL_BLOCK),
            Theme.MACHINE,
            "网络自动网络拓展工作台"
    );
    public static final SlimefunItemStack AUTO_EXPANSION_WORKBENCH_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_AUTO_EXPANSION_WORKBENCH_WITHHOLDING",
            new ItemStack(Material.HORN_CORAL_BLOCK),
            Theme.MACHINE,
            "网络自动网络拓展工作台 (预留版)"
    );

    //高级网络合成机
    public static final SlimefunItemStack ADVANCED_AUTO_MAGIC_WORKBENCH = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_MAGIC_WORKBENCH",
            Enchanted(Material.BOOKSHELF),
            Theme.MACHINE,
            "高级网络自动魔法工作台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING",
            Enchanted(Material.CHISELED_BOOKSHELF),
            Theme.MACHINE,
            "高级网络自动魔法工作台 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ARMOR_FORGE = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_ARMOR_FORGE",
            Enchanted(Material.SMITHING_TABLE),
            Theme.MACHINE,
            "高级网络自动盔甲锻造台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING",
            Enchanted(Material.CARTOGRAPHY_TABLE),
            Theme.MACHINE,
            "高级网络自动盔甲锻造台 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_SMELTERY = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_SMELTERY",
            Enchanted(Material.FURNACE),
            Theme.MACHINE,
            "高级网络自动冶炼炉"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_SMELTERY_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_SMELTERY_WITHHOLDING",
            Enchanted(Material.BLAST_FURNACE),
            Theme.MACHINE,
            "高级网络自动冶炼炉 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_QUANTUM_WORKBENCH = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_QUANTUM_WORKBENCH",
            Enchanted(Material.HAY_BLOCK),
            Theme.MACHINE,
            "高级网络自动量子工作台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING",
            Enchanted(Material.DRIED_KELP_BLOCK),
            Theme.MACHINE,
            "高级网络自动量子工作台 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ANCIENT_ALTAR = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_ANCIENT_ALTAR",
            Enchanted(Material.CRAFTING_TABLE),
            Theme.MACHINE,
            "高级网络自动古代祭坛"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING",
            Enchanted(Material.ENCHANTING_TABLE),
            Theme.MACHINE,
            "高级网络自动古代祭坛 (预留版)"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_EXPANSION_WORKBENCH = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_EXPANSION_WORKBENCH",
            Enchanted(Material.FIRE_CORAL_BLOCK),
            Theme.MACHINE,
            "高级网络自动网络拓展工作台"
    );
    public static final SlimefunItemStack ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = Theme.Random(
            "NTW_EXPANSION_ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING",
            Enchanted(Material.HORN_CORAL_BLOCK),
            Theme.MACHINE,
            "高级网络自动网络拓展工作台 (预留版)"
    );

    //网桥
    public static final SlimefunItemStack NETWORK_BRIDGE_WHITE = Theme.Random(
            "NTW_EXPANSION_BRIDGE_WHITE",
            new ItemStack(Material.WHITE_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(白色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_LIGHT_GRAY = Theme.Random(
            "NTW_EXPANSION_BRIDGE_LIGHT_GRAY",
            new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(淡灰色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_GRAY = Theme.Random(
            "NTW_EXPANSION_BRIDGE_GRAY",
            new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(灰色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_BLACK = Theme.Random(
            "NTW_EXPANSION_BRIDGE_BLACK",
            new ItemStack(Material.BLACK_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(黑色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_BROWN = Theme.Random(
            "NTW_EXPANSION_BRIDGE_BROWN",
            new ItemStack(Material.BROWN_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(棕色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_RED = Theme.Random(
            "NTW_EXPANSION_BRIDGE_RED",
            new ItemStack(Material.RED_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(红色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_ORANGE = Theme.Random(
            "NTW_EXPANSION_BRIDGE_ORANGE",
            new ItemStack(Material.ORANGE_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(橙色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_YELLOW = Theme.Random(
            "NTW_EXPANSION_BRIDGE_YELLOW",
            new ItemStack(Material.YELLOW_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(黄色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_LIME = Theme.Random(
            "NTW_EXPANSION_BRIDGE_LIME",
            new ItemStack(Material.LIME_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(黄绿色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_GREEN = Theme.Random(
            "NTW_EXPANSION_BRIDGE_GREEN",
            new ItemStack(Material.GREEN_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(绿色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_CYAN = Theme.Random(
            "NTW_EXPANSION_BRIDGE_CYAN",
            new ItemStack(Material.CYAN_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(青色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_LIGHT_BLUE = Theme.Random(
            "NTW_EXPANSION_BRIDGE_LIGHT_BLUE",
            new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(淡蓝色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_BLUE = Theme.Random(
            "NTW_EXPANSION_BRIDGE_BLUE",
            new ItemStack(Material.BLUE_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(蓝色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_PURPLE = Theme.Random(
            "NTW_EXPANSION_BRIDGE_PURPLE",
            new ItemStack(Material.PURPLE_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(紫色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_MAGENTA = Theme.Random(
            "NTW_EXPANSION_BRIDGE_MAGENTA",
            new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(品红色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );
    public static final SlimefunItemStack NETWORK_BRIDGE_PINK = Theme.Random(
            "NTW_EXPANSION_BRIDGE_PINK",
            new ItemStack(Material.PINK_STAINED_GLASS_PANE),
            Theme.MACHINE,
            "网桥(粉红色)",
            "网桥用于连接不同的网络物品",
            "来形成一个完整的网络",
            "更加清晰的布局网络"
    );

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
