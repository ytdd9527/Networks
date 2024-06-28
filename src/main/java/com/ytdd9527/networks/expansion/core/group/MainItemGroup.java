package com.ytdd9527.networks.expansion.core.group;

import com.ytdd9527.networks.libs.plugin.util.TextUtil;
import com.ytdd9527.networks.libs.slimefun.util.GuideUtil;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;


import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * @author Final_ROOT
 * @since 2.0
 */
// TODO: abstract as lib
public class MainItemGroup extends FlexItemGroup {
    private static final int BACK_SLOT = 1;
    private static final int PREVIOUS_SLOT = 3;
    private static final int NEXT_SLOT = 5;
    private static final int ICON_SLOT = 7;
    private static final int[] BORDER = new int[] {0, 2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int[] MAIN_CONTENT = new int[] {18, 27, 36, 45};
    private static final int[][] SUB_CONTENT = new int[][] {
            new int[] {19, 20, 21, 22, 23, 24, 25, 26},
            new int[] {28, 29, 30, 31, 32, 33, 34, 35},
            new int[] {37, 38, 39, 40, 41, 42, 43, 44},
            new int[] {46, 47, 48, 49, 50, 51, 52, 53}};

    private List<ItemGroup> fatherItemGroupList = new ArrayList<>();
    private List<List<ItemGroup>> sonItemGroupList = new ArrayList<>();

    private Map<Integer, MainItemGroup> pageMap = new LinkedHashMap<>();

    private final ItemStack item;
    private final int page;

    public MainItemGroup(NamespacedKey key, ItemStack item, int tier) {
        super(key, item, tier);
        this.page = 1;
        this.item = item;
        this.pageMap.put(1, this);
    }

    private MainItemGroup(NamespacedKey key, ItemStack item, int tier, int page) {
        super(key, item, tier);
        this.page = page;
        this.item = item;
    }

    @Override
    public boolean isVisible(@Nonnull Player p, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode layout) {
        return layout.equals(SlimefunGuideMode.SURVIVAL_MODE) && this.page == 1;
    }

    @Override
    public void open(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode slimefunGuideMode) {
        playerProfile.getGuideHistory().add(this, this.page);
        this.generateMenu(player, playerProfile, slimefunGuideMode).open(player);
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        super.register(addon);
        for (int i = 0; i < this.fatherItemGroupList.size(); i++) {
            this.fatherItemGroupList.get(i).register(addon);
            for (ItemGroup itemGroup : this.sonItemGroupList.get(i)) {
                itemGroup.register(addon);
            }
        }
    }

    public void addTo(@Nonnull ItemGroup fatherItemGroup, @Nonnull ItemGroup... itemGroup) {
        if (this.fatherItemGroupList.contains(fatherItemGroup) && this.sonItemGroupList.size() > this.fatherItemGroupList.indexOf(fatherItemGroup)) {
            this.sonItemGroupList.get(this.fatherItemGroupList.indexOf(fatherItemGroup)).addAll(Arrays.stream(itemGroup).toList());
        } else if (!this.fatherItemGroupList.contains(fatherItemGroup)) {
            this.fatherItemGroupList.add(fatherItemGroup);
            this.sonItemGroupList.add(new ArrayList<>(Arrays.stream(itemGroup).toList()));
        }
    }

    @Nonnull
    private ChestMenu generateMenu(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode slimefunGuideMode) {
        ChestMenu chestMenu = new ChestMenu(TextUtil.colorRandomString("网络拓展 - Expansion"));

        chestMenu.setEmptySlotsClickable(false);
        chestMenu.addMenuOpeningHandler(pl -> pl.playSound(pl.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1));

        chestMenu.addItem(BACK_SLOT, ChestMenuUtils.getBackButton(player));
        chestMenu.addMenuClickHandler(BACK_SLOT, (pl, s, is, action) -> {
            GuideHistory guideHistory = playerProfile.getGuideHistory();
            if (action.isShiftClicked()) {
                SlimefunGuide.openMainMenu(playerProfile, slimefunGuideMode, guideHistory.getMainMenuPage());
            } else {
                guideHistory.goBack(Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE));
            }
            return false;
        });

        chestMenu.addItem(PREVIOUS_SLOT, ChestMenuUtils.getPreviousButton(player, this.page, (this.fatherItemGroupList.size() - 1) / MAIN_CONTENT.length + 1));
        chestMenu.addMenuClickHandler(PREVIOUS_SLOT, (p, slot, item, action) -> {
            GuideUtil.removeLastEntry(playerProfile.getGuideHistory());
            MainItemGroup mainItemGroup = this.getByPage(Math.max(this.page - 1, 1));
            mainItemGroup.open(player, playerProfile, slimefunGuideMode);
            return false;
        });

        chestMenu.addItem(NEXT_SLOT, ChestMenuUtils.getNextButton(player, this.page, (this.fatherItemGroupList.size() - 1) / MAIN_CONTENT.length + 1));
        chestMenu.addMenuClickHandler(NEXT_SLOT, (p, slot, item, action) -> {
            GuideUtil.removeLastEntry(playerProfile.getGuideHistory());
            MainItemGroup mainItemGroup = this.getByPage(Math.min(this.page + 1, (this.fatherItemGroupList.size() - 1) / MAIN_CONTENT.length + 1));
            mainItemGroup.open(player, playerProfile, slimefunGuideMode);
            return false;
        });

        chestMenu.addItem(ICON_SLOT, super.item);
        chestMenu.addMenuClickHandler(ICON_SLOT, ChestMenuUtils.getEmptyClickHandler());

        for (int slot : BORDER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground());
            chestMenu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i = this.page * MAIN_CONTENT.length - MAIN_CONTENT.length; i < this.page * MAIN_CONTENT.length; i++) {
            if (i < this.fatherItemGroupList.size() && i < this.sonItemGroupList.size()) {
                chestMenu.addItem(MAIN_CONTENT[i % MAIN_CONTENT.length], this.fatherItemGroupList.get(i).getItem(player));
                final int index = i;
                chestMenu.addMenuClickHandler(MAIN_CONTENT[i % MAIN_CONTENT.length], (p, slot, item, action) -> {
                    ItemGroup itemGroup = this.fatherItemGroupList.get(index);
                    if (itemGroup instanceof FlexItemGroup flexItemGroup) {
                        flexItemGroup.open(player, playerProfile, slimefunGuideMode);
                    }
                    return false;
                });

                List<ItemGroup> subItemGroupList = this.sonItemGroupList.get(i);
                for (int j = 0; j < subItemGroupList.size(); j++) {
                    chestMenu.addItem(SUB_CONTENT[i % MAIN_CONTENT.length][j], subItemGroupList.get(j).getItem(player));
                    final int subIndex = j;
                    chestMenu.addMenuClickHandler(SUB_CONTENT[i % MAIN_CONTENT.length][j], (p, slot, item, action) -> {
                        ItemGroup itemGroup = subItemGroupList.get(subIndex);
                        if (itemGroup instanceof FlexItemGroup flexItemGroup) {
                            flexItemGroup.open(player, playerProfile, slimefunGuideMode);
                        }
                        return false;
                    });
                }
            }
        }

        return chestMenu;
    }

    @Nonnull
    public MainItemGroup getByPage(int page) {
        if (pageMap.containsKey(page)) {
            return pageMap.get(page);
        } else {
            synchronized (this.pageMap.get(1)) {
                if (pageMap.containsKey(page)) {
                    return pageMap.get(page);
                }
                MainItemGroup mainItemGroup = pageMap.get(1);
                mainItemGroup = new MainItemGroup(new NamespacedKey(Networks.getInstance(), this.getKey().getKey() + "_" + page), mainItemGroup.item, mainItemGroup.getTier(), page);
                mainItemGroup.fatherItemGroupList = this.fatherItemGroupList;
                mainItemGroup.sonItemGroupList = this.sonItemGroupList;
                mainItemGroup.pageMap = this.pageMap;
                pageMap.put(page, mainItemGroup);
                return mainItemGroup;
            }
        }
    }
}
