package com.ytdd9527.networks.libs.slimefun.util.dto;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Final_ROOT
 * @since 2.2
 */
public class SlimefunCraftRegistry {
    private boolean init = false;
    private Map<String, List<String>> craftMap = new HashMap<>();
    private static volatile SlimefunCraftRegistry instance;

    public void init() {
        if(this.init) {
            return;
        }

        this.reload();

        this.init = true;
    }

    public void reload() {
        Map<String, List<String>> craftMap = new HashMap<>();

        for(SlimefunItem slimefunItem : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            ItemStack[] itemStacks = slimefunItem.getRecipe();
            for(ItemStack itemStack : itemStacks) {
                SlimefunItem craftItem = SlimefunItem.getByItem(itemStack);
                if(craftItem != null) {
                    List<String> idList;
                    if(craftMap.containsKey(craftItem.getId())) {
                        idList = craftMap.get(craftItem.getId());
                    } else {
                        idList = new ArrayList<>();
                        craftMap.put(craftItem.getId(), idList);
                    }
                    if(!idList.contains(slimefunItem.getId())) {
                        idList.add(slimefunItem.getId());
                    }
                }
            }
        }

        this.craftMap = craftMap;
    }

    @Nonnull
    public List<String> getCraftSlimefunItemIdList(@Nonnull String id) {
        if(!this.init) {
            this.init();
        }

        return this.craftMap.containsKey(id) ? this.craftMap.get(id) : new ArrayList<>();
    }

    @Nonnull
    public List<String> getCraftSlimefunItemIdList(@Nonnull SlimefunItem slimefunItem) {
        return this.getCraftSlimefunItemIdList(slimefunItem.getId());
    }

    @Nonnull
    public List<SlimefunItem> getCraftSlimefunItemList(@Nonnull String id) {
        if(!this.init) {
            this.init();
        }

        List<String> idList = this.craftMap.get(id);
        if(idList == null) {
            idList = new ArrayList<>();
        }
        List<SlimefunItem> slimefunItemList = new ArrayList<>(idList.size());
        for(String targetId : idList) {
            SlimefunItem slimefunItem = SlimefunItem.getById(targetId);
            if(slimefunItem != null) {
                slimefunItemList.add(slimefunItem);
            }
        }

        return slimefunItemList;
    }

    @Nonnull
    public List<SlimefunItem> getCraftSlimefunItemList(@Nonnull SlimefunItem slimefunItem) {
        return this.getCraftSlimefunItemList(slimefunItem.getId());
    }

    @Nonnull
    public static SlimefunCraftRegistry getInstance() {
        if (instance == null) {
            synchronized (SlimefunCraftRegistry.class) {
                if (instance == null) {
                    instance = new SlimefunCraftRegistry();
                }
            }
        }
        return instance;
    }
}
