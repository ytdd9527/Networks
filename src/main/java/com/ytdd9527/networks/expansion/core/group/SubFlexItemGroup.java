package com.ytdd9527.networks.expansion.core.group;

import com.ytdd9527.networks.libs.plugin.util.ItemStackUtil;
import com.ytdd9527.networks.libs.slimefun.util.GuideUtil;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerPreResearchEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;


import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Final_ROOT
 * @since 2.0
 */
// TODO: abstract as lib
public class SubFlexItemGroup extends FlexItemGroup {
    private static final int BACK_SLOT = 1;
    private static final int PREVIOUS_SLOT = 3;
    private static final int NEXT_SLOT = 5;
    private static final int ICON_SLOT = 7;
    private static final int[] BORDER = new int[] {0, 2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int[][] MAIN_CONTENT_L = new int[][] {
            new int[] {18, 19, 20, 21, 22, 23, 24, 25, 26},
            new int[] {27, 28, 29, 30, 31, 32, 33, 34, 35},
            new int[] {36, 37, 38, 39, 40, 41, 42, 43, 44},
            new int[] {45, 46, 47, 48, 49, 50, 51, 52, 53}};

    private static final JavaPlugin JAVA_PLUGIN = Networks.getInstance();

    /**
     * One SlimefunItem List should only contain 9 SlimefunItems at most.
     */
    private List<List<SlimefunItem>> slimefunItemList = new ArrayList<>();

    private final ItemStack item;
    private final int page;

    private Map<Integer, SubFlexItemGroup> pageMap = new LinkedHashMap<>();

    public SubFlexItemGroup(NamespacedKey key, ItemStack item, int tier) {
        super(key, item, tier);
        this.item = item;
        this.page = 1;
        this.pageMap.put(1, this);
    }

    public SubFlexItemGroup(NamespacedKey key, ItemStack item, int tier, int page) {
        super(key, item, tier);
        this.item = item;
        this.page = page;
    }

    @Override
    public boolean isVisible(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode slimefunGuideMode) {
        return false;
    }

    @Override
    public boolean isAccessible(@Nonnull Player p) {
        return false;
    }

    @Override
    public void open(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode slimefunGuideMode) {
        playerProfile.getGuideHistory().add(this, this.page);
        this.generateMenu(player, playerProfile, slimefunGuideMode).open(player);
    }

    public void refresh(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode slimefunGuideMode) {
        GuideUtil.removeLastEntry(playerProfile.getGuideHistory());
        this.open(player, playerProfile, slimefunGuideMode);
    }

    public void addTo(@Nonnull SlimefunItem... slimefunItems) {
        for (int j = 0; j * 9 < slimefunItems.length; j++) {
            List<SlimefunItem> slimefunItemList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (j * 9 + i < slimefunItems.length) {
                    slimefunItemList.add(slimefunItems[j * 9 + i]);
                }
            }
            this.slimefunItemList.add(slimefunItemList);
        }
    }

    public void addTo(@Nonnull SlimefunItemStack... slimefunItemStacks) {
        for (int j = 0; j * 9 < slimefunItemStacks.length; j++) {
            List<SlimefunItem> slimefunItemList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (j * 9 + i < slimefunItemStacks.length) {
                    SlimefunItemStack slimefunItemStack = slimefunItemStacks[j * 9 + i];
                    SlimefunItem slimefunItem = SlimefunItem.getByItem(slimefunItemStack);
                    if (slimefunItem != null) {
                        slimefunItemList.add(slimefunItem);
                    }
                }
            }
            this.slimefunItemList.add(slimefunItemList);
        }
    }

    public void addTo(@Nonnull List<SlimefunItem> slimefunItemList) {
        for (int j = 0; j * 9 < slimefunItemList.size(); j++) {
            List<SlimefunItem> aSlimefunItemList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (j * 9 + i < slimefunItemList.size()) {
                    slimefunItemList.add(slimefunItemList.get(j * 9 + i));
                }
            }
            this.slimefunItemList.add(aSlimefunItemList);
        }
    }

    public void addFrom(@Nonnull SubFlexItemGroup... subFlexItemGroups) {
        for (SubFlexItemGroup subFlexItemGroup : subFlexItemGroups) {
            this.slimefunItemList.addAll(subFlexItemGroup.slimefunItemList);
        }
    }

    public List<SlimefunItem> getSlimefunItems() {
        List<SlimefunItem> result = new ArrayList<>();
        for(List<SlimefunItem> list : this.slimefunItemList) {
            result.addAll(list);
        }
        return result;
    }

    @Nonnull
    private ChestMenu generateMenu(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode slimefunGuideMode) {
        ChestMenu chestMenu = new ChestMenu(ItemStackUtil.getItemName(super.item));

        chestMenu.setEmptySlotsClickable(false);
        chestMenu.addMenuOpeningHandler(pl -> pl.playSound(pl.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1));

        chestMenu.addItem(BACK_SLOT, ChestMenuUtils.getBackButton(player));
        chestMenu.addMenuClickHandler(1, (pl, s, is, action) -> {
            GuideHistory guideHistory = playerProfile.getGuideHistory();
            if (action.isShiftClicked()) {
                SlimefunGuide.openMainMenu(playerProfile, slimefunGuideMode, guideHistory.getMainMenuPage());
            } else {
                guideHistory.goBack(Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE));
            }
            return false;
        });

        chestMenu.addItem(PREVIOUS_SLOT, ChestMenuUtils.getPreviousButton(player, this.page, (slimefunItemList.size() - 1) / MAIN_CONTENT_L.length + 1));
        chestMenu.addMenuClickHandler(PREVIOUS_SLOT, (p, slot, item, action) -> {
            GuideUtil.removeLastEntry(playerProfile.getGuideHistory());
            SubFlexItemGroup subFlexItemGroup = this.getByPage(Math.max(this.page - 1, 1));
            subFlexItemGroup.open(player, playerProfile, slimefunGuideMode);
            return false;
        });

        chestMenu.addItem(NEXT_SLOT, ChestMenuUtils.getNextButton(player, this.page, (slimefunItemList.size() - 1) / MAIN_CONTENT_L.length + 1));
        chestMenu.addMenuClickHandler(NEXT_SLOT, (p, slot, item, action) -> {
            GuideUtil.removeLastEntry(playerProfile.getGuideHistory());
            SubFlexItemGroup subFlexItemGroup = this.getByPage(Math.min(this.page + 1, (slimefunItemList.size() - 1) / MAIN_CONTENT_L.length + 1));
            subFlexItemGroup.open(player, playerProfile, slimefunGuideMode);
            return false;
        });

        chestMenu.addItem(ICON_SLOT, super.item);
        chestMenu.addMenuClickHandler(ICON_SLOT, ChestMenuUtils.getEmptyClickHandler());

        for (int slot : BORDER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground());
            chestMenu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i = 0; i < MAIN_CONTENT_L.length; i++) {
            int index = i + this.page * MAIN_CONTENT_L.length - MAIN_CONTENT_L.length;
            if (index < slimefunItemList.size()) {
                List<SlimefunItem> slimefunItemList = this.slimefunItemList.get(index);
                for (int j = 0; j < slimefunItemList.size(); j++) {
                    SlimefunItem slimefunItem = slimefunItemList.get(j);
                    Research research = slimefunItem.getResearch();
                    if (playerProfile.hasUnlocked(research)) {
                        ItemStack itemStack = ItemStackUtil.cloneWithoutNBT(slimefunItem.getItem());
                        ItemStackUtil.addLoreToFirst(itemStack, "§7" + slimefunItem.getId());
                        chestMenu.addItem(MAIN_CONTENT_L[i][j], itemStack);
                        chestMenu.addMenuClickHandler(MAIN_CONTENT_L[i][j], (p, slot, item, action) -> {
                            RecipeItemGroup recipeItemGroup = RecipeItemGroup.getByItemStack(player, playerProfile, slimefunGuideMode, slimefunItem.getItem());
                            if (recipeItemGroup != null) {
                                Bukkit.getScheduler().runTask(JAVA_PLUGIN, () -> recipeItemGroup.open(player, playerProfile, slimefunGuideMode));
                            }
                            return false;
                        });
                    } else {
                        ItemStack icon = ItemStackUtil.cloneItem(ChestMenuUtils.getNotResearchedItem());
                        ItemStackUtil.setLore(icon,
                                "§7" + research.getName(player),
                                "§4§l" + Slimefun.getLocalization().getMessage(player, "guide.locked"),
                                "",
                                "§a> 点击解锁",
                                "",
                                "§7花费: §b" + research.getCost() + " 等级经验");
                        chestMenu.addItem(MAIN_CONTENT_L[i][j], icon);
                        chestMenu.addMenuClickHandler(MAIN_CONTENT_L[i][j], (p, slot, item, action) -> {
                            PlayerPreResearchEvent event = new PlayerPreResearchEvent(player, research, slimefunItem);
                            Bukkit.getPluginManager().callEvent(event);

                            if (!event.isCancelled() && !playerProfile.hasUnlocked(research)) {
                                if (research.canUnlock(player)) {
                                    Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE).unlockItem(player, slimefunItem, player1 -> this.refresh(player, playerProfile, slimefunGuideMode));
                                } else {
                                    this.refresh(player, playerProfile, slimefunGuideMode);
                                    Slimefun.getLocalization().sendMessage(player, "messages.not-enough-xp", true);
                                }
                            } else {
                                GuideUtil.removeLastEntry(playerProfile.getGuideHistory());
                                this.open(player, playerProfile, slimefunGuideMode);
                            }
                            return false;
                        });
                    }
                }
            }
        }

        return chestMenu;
    }

    @Nonnull
    private SubFlexItemGroup getByPage(int page) {
        if (this.pageMap.containsKey(page)) {
            return this.pageMap.get(page);
        } else {
            synchronized (this.pageMap.get(1)) {
                if (this.pageMap.containsKey(page)) {
                    return this.pageMap.get(page);
                }
                SubFlexItemGroup subFlexItemGroup = new SubFlexItemGroup(new NamespacedKey(JAVA_PLUGIN, this.getKey().getKey() + "_" + page), this.item, this.getTier(), page);
                subFlexItemGroup.slimefunItemList = this.slimefunItemList;
                subFlexItemGroup.pageMap = this.pageMap;
                this.pageMap.put(page, subFlexItemGroup);
                return subFlexItemGroup;
            }
        }
    }

    // TODO
    @Nonnull
    public static SubFlexItemGroup generateFromItemGroup(@Nonnull ItemGroup itemGroup, @Nonnull Player player) {
        SubFlexItemGroup subFlexItemGroup = new SubFlexItemGroup(new NamespacedKey(JAVA_PLUGIN, itemGroup.getKey().getNamespace()), itemGroup.getItem(player), itemGroup.getTier());
        subFlexItemGroup.addTo(itemGroup.getItems());
        return subFlexItemGroup;
    }
}
