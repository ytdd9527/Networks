package com.ytdd9527.networks.libs.plugin.dto;

import com.ytdd9527.networks.libs.plugin.util.ItemStackUtil;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Final_ROOT
 * @since 2.0
 */
public class ItemWrapper {
    @Nonnull
    private ItemStack itemStack;

    @Nullable
    private ItemMeta itemMeta;

    public static final ItemStack AIR = new ItemStack(Material.AIR);

    public ItemWrapper() {
        this.itemStack = AIR;
    }

    public ItemWrapper(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = this.itemStack.hasItemMeta() ? this.itemStack.getItemMeta() : null;
    }

    public ItemWrapper(@Nonnull ItemStack itemStack, @Nullable ItemMeta itemMeta) {
        this.itemStack = itemStack;
        this.itemMeta = itemMeta;
    }

    @Nonnull
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setItemStack(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Nullable
    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    public void setItemMeta(@Nullable ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    public boolean hasItemMeta() {
        return this.itemMeta != null;
    }

    public void updateItemMeta() {
        this.itemMeta = this.itemStack.hasItemMeta() ? this.itemStack.getItemMeta() : null;
    }

    public void newWrap(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = this.itemStack.hasItemMeta() ? this.itemStack.getItemMeta() : null;
    }

    public void newWrap(@Nonnull ItemStack itemStack, @Nullable ItemMeta itemMeta) {
        this.itemStack = itemStack;
        this.itemMeta = itemMeta;
    }

    @Nonnull
    public ItemWrapper shallowClone() {
        return new ItemWrapper(this.itemStack, this.itemMeta);
    }

    @Nonnull
    public ItemWrapper deepClone() {
        return new ItemWrapper(new ItemStack(this.itemStack));
    }

    @Override
    public int hashCode() {
        int hash = 31 + this.itemStack.getType().hashCode();
        hash = hash * 31 + this.itemStack.getAmount();
        hash = hash * 31 + (this.itemStack.getDurability() & 0xffff);
        hash = hash * 31 + (this.itemMeta != null ? (this.itemMeta.hashCode()) : 0);
        return hash;
    }

    @Override
    public boolean equals(@Nonnull Object obj) {
        if (this.itemStack instanceof ItemStackWrapper) {
            return new ItemStack(this.itemStack).equals(obj);
        } else {
            return this.itemStack.equals(obj);
        }
    }

    @Nonnull
    public static ItemStack[] getItemArray(@Nonnull ItemWrapper[] itemWrappers) {
        ItemStack[] itemStacks = new ItemStack[itemWrappers.length];
        for (int i = 0, length = itemStacks.length; i < length; i++) {
            itemStacks[i] = itemWrappers[i].getItemStack();
        }
        return itemStacks;
    }
    @Nonnull
    public static ItemStack[] getItemArray(@Nonnull List<? extends ItemWrapper> itemWrapperList) {
        ItemStack[] itemStacks = new ItemStack[itemWrapperList.size()];
        for (int i = 0, length = itemStacks.length; i < length; i++) {
            itemStacks[i] = itemWrapperList.get(i).getItemStack();
        }
        return itemStacks;
    }
    @Nonnull
    public static ItemStack[] getCopiedItemArray(@Nonnull List<? extends ItemWrapper> itemWrapperList) {
        ItemStack[] itemStacks = new ItemStack[itemWrapperList.size()];
        for (int i = 0, length = itemStacks.length; i < length; i++) {
            itemStacks[i] = ItemStackUtil.cloneItem(itemWrapperList.get(i).getItemStack());
        }
        return itemStacks;
    }

    @Nonnull
    public static List<ItemStack> getItemList(@Nonnull ItemWrapper[] itemWrappers) {
        List<ItemStack> itemStackList = new ArrayList<>(itemWrappers.length);
        for (ItemWrapper itemWrapper : itemWrappers) {
            itemStackList.add(itemWrapper.getItemStack());
        }
        return itemStackList;
    }
    @Nonnull
    public static List<ItemStack> getItemList(@Nonnull List<? extends ItemWrapper> itemWrapperList) {
        List<ItemStack> itemStackList = new ArrayList<>(itemWrapperList.size());
        for (ItemWrapper itemWrapper : itemWrapperList) {
            itemStackList.add(itemWrapper.getItemStack());
        }
        return itemStackList;
    }
}
