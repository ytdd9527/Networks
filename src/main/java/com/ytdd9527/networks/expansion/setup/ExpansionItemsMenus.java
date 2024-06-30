package com.ytdd9527.networks.expansion.setup;

import com.ytdd9527.networks.expansion.core.group.MainItemGroup;
import com.ytdd9527.networks.expansion.core.group.SubFlexItemGroup;
import com.ytdd9527.networks.expansion.core.util.ConfigUtil;
import com.ytdd9527.networks.libs.plugin.util.TextUtil;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

/**
 * @author Final_ROOT
 */
public final class ExpansionItemsMenus {
    // TODO head texture

    /* Slimefun item group */
    public static final NestedItemGroup MAIN_MENU = new NestedItemGroup(new NamespacedKey(Networks.getInstance(), "NET_EXPANSION_CATEGORY_MAIN"), new CustomItemStack(Material.LECTERN)) {
        @Override
        public boolean isVisible(@Nonnull Player p, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode mode) {
            return false;
        }
    };

    public static final SubItemGroup MENU_ITEMS = new SubItemGroup(new NamespacedKey(Networks.getInstance(), "NET_EXPANSION_ITEMS"), MAIN_MENU , new CustomItemStack(Material.AMETHYST_SHARD,TextUtil.colorRandomString( ("网络拓展 - 物品"))));
    public static final SubItemGroup MENU_CARGO_SYSTEM = new SubItemGroup(new NamespacedKey(Networks.getInstance(), "NET_EXPANSION_SYSTEM"), MAIN_MENU, new CustomItemStack(Material.FURNACE_MINECART, TextUtil.colorRandomString( ("网络拓展 - 货运与存储"))));
    public static final SubItemGroup MENU_FUNCTIONAL_MACHINE = new SubItemGroup(new NamespacedKey(Networks.getInstance(), "NET_EXPANSION_FUNCTIONAL_MACHINE"), MAIN_MENU, new CustomItemStack(Material.LECTERN,TextUtil.colorRandomString( ("网络拓展 - 功能机器"))));

    /* My item group */
    public static final MainItemGroup MAIN_ITEM_GROUP = ConfigUtil.getMainItemGroup("NET_EXPANSION_ITEM_GROUP", Material.CHEST_MINECART,  TextUtil.colorRandomString("Networks - Expansion"));
    // item
    public static final SubFlexItemGroup MAIN_MENU_ITEM = ConfigUtil.getSubFlexItemGroup( "NET_EXPANSION_MAIN_MENU_ITEM", Material.AMETHYST_SHARD, TextUtil.colorRandomString("物品"));
    public static final SubFlexItemGroup SUB_MENU_TOOL = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_TOOL", Material.SPYGLASS, TextUtil.colorRandomString("物品 - 工具"));
    public static final SubFlexItemGroup SUB_MENU_BLUEPRINT = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_BLUEPRINT", Material.BLUE_DYE, TextUtil.colorRandomString("物品 - 蓝图"));

    // cargo system
    public static final SubFlexItemGroup MAIN_MENU_CARGO_SYSTEM = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_MAIN_MENU_CARGO_SYSTEM", Material.FURNACE_MINECART, TextUtil.colorRandomString("货运与存储"));
    public static final SubFlexItemGroup SUB_MENU_ADVANCED_STORAGE = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_ADVANCED_STORAGE", Material.BOOKSHELF, TextUtil.colorRandomString("货运与存储 - 高级存储"));
    public static final SubFlexItemGroup SUB_MENU_CARGO = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_CARGO", Material.END_ROD, TextUtil.colorRandomString("货运与存储 - 货运"));

    // functional machine
    public static final SubFlexItemGroup MAIN_MENU_FUNCTIONAL_MACHINE = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_MAIN_MENU_FUNCTIONAL_MACHINE", Material.LECTERN, TextUtil.colorRandomString("功能机器"));
    public static final SubFlexItemGroup SUB_MENU_CORE_MACHINE = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_CORE_MACHINE", Material.AMETHYST_BLOCK, TextUtil.colorRandomString("功能机器 - 核心机器"));
    public static final SubFlexItemGroup SUB_MENU_ADVANCED_NET = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_ADVANCED_NET", Material.BLACK_STAINED_GLASS, TextUtil.colorRandomString("功能机器 - 更高级的网络机器"));
    public static final SubFlexItemGroup SUB_MENU_BRIDGE = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_BRIDGE", Material.WHITE_STAINED_GLASS, TextUtil.colorRandomString("功能机器 - 更多的网桥"));
    public static final SubFlexItemGroup SUB_MENU_GRID = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_GRID", Material.NOTE_BLOCK, TextUtil.colorRandomString("功能机器 - 网格"));
    public static final SubFlexItemGroup SUB_MENU_ENCODER = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_ENCODER", Material.TARGET, TextUtil.colorRandomString("功能机器 - 编码器"));
    public static final SubFlexItemGroup SUB_MENU_CRAFTER_MACHINE = ConfigUtil.getSubFlexItemGroup("NET_EXPANSION_SUB_MENU_CRAFTER_MACHINE", Material.CRAFTING_TABLE, TextUtil.colorRandomString("功能机器 - 自动合成机器"));

}
