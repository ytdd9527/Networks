package io.github.sefiraat.networks.slimefun.network.grid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.logging.Logger;

public class GridCache {

    private int page;
    private int maxPages;
    @Nonnull
    private DisplayMode displayMode;
    @Nonnull
    private SortOrder sortOrder;
    @Nullable
    private String filter;
    private List<ItemStack> pullItemHistory = new java.util.ArrayList<ItemStack>();

    public GridCache(int page, int maxPages, @Nonnull SortOrder sortOrder) {
        this.page = page;
        this.maxPages = maxPages;
        this.sortOrder = sortOrder;
        this.displayMode = DisplayMode.DISPLAY;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxPages() {
        return this.maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    @Nonnull
    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(@Nonnull SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Nullable
    public String getFilter() {
        return this.filter;
    }

    @Nullable
    public List<ItemStack> getPullItemHistory() {
        return this.pullItemHistory;
    }

    public void addPullItemHistory(@Nullable ItemStack itemStack) {
        if (itemStack != null) {
            if (getPullItemHistory().contains(itemStack)) {
                getPullItemHistory().remove(itemStack);
            }

            getPullItemHistory().add(0, itemStack);
        }
    }

    public void setFilter(@Nullable String filter) {
        this.filter = filter;
    }

    public DisplayMode getDisplayMode(){
        return this.displayMode;
    }

    public void toggleDisplayMode(){
        if(this.displayMode == DisplayMode.DISPLAY){
            this.displayMode = DisplayMode.HISTORY;
        } else {
            this.displayMode = DisplayMode.DISPLAY;
        }
    }

    public enum SortOrder {
        ALPHABETICAL,
        NUMBER
    }

    public enum DisplayMode {
        DISPLAY,
        HISTORY
    }
}
