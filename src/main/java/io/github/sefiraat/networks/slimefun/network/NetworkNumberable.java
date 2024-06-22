package io.github.sefiraat.networks.slimefun.network;

import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public abstract class NetworkNumberable extends NetworkDirectional {

    private int currentNumber = 64;
    private int limit = 64;

    private static final CustomItemStack MINUS_ICON = new CustomItemStack (
        Material.RED_CONCRETE, Theme.NOTICE + "减少数量"
    );

    private static final CustomItemStack SHOW_ICON = new CustomItemStack (
        Material.GOLD_BLOCK,
        Theme.NOTICE + "数量",
        Theme.NOTICE + "当前数量: 64"
    );

    private static final CustomItemStack ADD_ICON = new CustomItemStack (
        Material.GREEN_CONCRETE, Theme.NOTICE + "增加数量"
    );

    public NetworkNumberable(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, NodeType nodeType, int limit) {
        super(itemGroup, item, recipeType, recipe, nodeType);
        this.currentNumber = 64;
        this.limit = limit;
    }

    @Override
    protected void onTick(BlockMenu blockMenu, Block block) {
        super.onTick(blockMenu, block);
        blockMenu.replaceExistingItem(getShowSlot(), getShowIcon());
    }

    private CustomItemStack getShowIcon() {
        return SHOW_ICON;
    }

    private CustomItemStack getMinusIcon() {
        return MINUS_ICON;
    }

    private CustomItemStack getAddIcon() {
        return ADD_ICON;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public void minusNumber(int number) {
        if (getCurrentNumber() - number >= 0) {
            setCurrentNumber(getCurrentNumber() - number);
        } else {
            setCurrentNumber(1);
        }
        updateShowIcon();
    }

    public void addNumber(int number) {
        if (getCurrentNumber() + number <= this.limit) {
            setCurrentNumber(getCurrentNumber() + number);
        } else {
            setCurrentNumber(this.limit);
        }
        updateShowIcon();
    }

    public void updateShowIcon() {
        SHOW_ICON.getItemMeta().getLore().set(0, Theme.NOTICE + "当前数量: " + getCurrentNumber());
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                addMenuClickHandler(getShowSlot(), (p, slot, item, action) -> false);

                addItem(getMinusSlot(), getMinusIcon());
                addItem(getAddSlot(), getAddIcon());
            }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
                menu.addMenuClickHandler(getAddSlot(), (p, slot, item, action) -> {
                    int n = 1;
                    if (action.isRightClicked()) {
                        n = 8;
                    }
                    if (action.isShiftClicked()) {
                        n = 64;
                    }
                    addNumber(n);
                    return false;
                });
                menu.addMenuClickHandler(getMinusSlot(), (p, slot, item, action) -> {
                    int n = 1;
                    if (action.isRightClicked()) {
                        n = 8;
                    }
                    if (action.isShiftClicked()) {
                        n = 64;
                    }
                    minusNumber(n);
                    return false;
                });
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }
        };
    }

    protected abstract int getMinusSlot();
    protected abstract int getShowSlot();
    protected abstract int getAddSlot();
}
