package io.github.sefiraat.networks.slimefun.network;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.utils.NetworkUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import net.guizhanss.guizhanlib.minecraft.helper.MaterialHelper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("deprecation")
public abstract class NetworkNumberable extends NetworkDirectional {

    private static final int NORTH_SLOT = 12;
    private static final int SOUTH_SLOT = 30;
    private static final int EAST_SLOT = 22;
    private static final int WEST_SLOT = 20;
    private static final int UP_SLOT = 15;
    private static final int DOWN_SLOT = 33;

    protected static final String DIRECTION = "direction";
    protected static final String OWNER_KEY = "uuid";

    private int currentNumber = 64;
    private int limit = 64;
    private static final Set<BlockFace> VALID_FACES = EnumSet.of(
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
    );

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

    NetworkDirectional instance = this;

    private static final Map<Location, BlockFace> SELECTED_DIRECTION_MAP = new HashMap<>();

    protected NetworkNumberable(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, NodeType type, int limit) {
        super(itemGroup, item, recipeType, recipe, type);
        this.currentNumber = 64;
        this.limit = limit;

        addItemHandler(
                new BlockPlaceHandler(false) {
                    @Override
                    public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                        NetworkStorage.removeNode(event.getBlock().getLocation());
                        var blockData = StorageCacheUtils.getBlock(event.getBlock().getLocation());
                        blockData.setData(OWNER_KEY, event.getPlayer().getUniqueId().toString());
                        blockData.setData(DIRECTION, BlockFace.SELF.name());
                        NetworkUtils.applyConfig(instance, blockData.getBlockMenu(), event.getPlayer());
                    }
                },
                new BlockTicker() {

                    private int tick = 1;

                    @Override
                    public boolean isSynchronized() {
                        return runSync();
                    }

                    @Override
                    public void tick(Block block, SlimefunItem slimefunItem, SlimefunBlockData data) {
                        if (tick <= 1) {
                            onTick(data.getBlockMenu(), block);
                        }
                    }

                    @Override
                    public void uniqueTick() {
                        onUniqueTick();
                    }
                }
        );
    }

    public void updateGui(@Nullable BlockMenu blockMenu) {
        if (blockMenu == null || !blockMenu.hasViewer()) {
            return;
        }

        BlockFace direction = getCurrentDirection(blockMenu);

        for (BlockFace blockFace : VALID_FACES) {
            final Block block = blockMenu.getBlock().getRelative(blockFace);
            final SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(block.getLocation());
            if (slimefunItem != null) {
                switch (blockFace) {
                    case NORTH -> blockMenu.replaceExistingItem(getNorthSlot(), getDirectionalSlotPane(blockFace, slimefunItem, blockFace == direction));
                    case SOUTH -> blockMenu.replaceExistingItem(getSouthSlot(), getDirectionalSlotPane(blockFace, slimefunItem, blockFace == direction));
                    case EAST -> blockMenu.replaceExistingItem(getEastSlot(), getDirectionalSlotPane(blockFace, slimefunItem, blockFace == direction));
                    case WEST -> blockMenu.replaceExistingItem(getWestSlot(), getDirectionalSlotPane(blockFace, slimefunItem, blockFace == direction));
                    case UP -> blockMenu.replaceExistingItem(getUpSlot(), getDirectionalSlotPane(blockFace, slimefunItem, blockFace == direction));
                    case DOWN -> blockMenu.replaceExistingItem(getDownSlot(), getDirectionalSlotPane(blockFace, slimefunItem, blockFace == direction));
                    default -> throw new IllegalStateException("意外的值: " + blockFace);
                }
            } else {
                final Material material = block.getType();
                switch (blockFace) {
                    case NORTH -> blockMenu.replaceExistingItem(getNorthSlot(), getDirectionalSlotPane(blockFace, material, blockFace == direction));
                    case SOUTH -> blockMenu.replaceExistingItem(getSouthSlot(), getDirectionalSlotPane(blockFace, material, blockFace == direction));
                    case EAST -> blockMenu.replaceExistingItem(getEastSlot(), getDirectionalSlotPane(blockFace, material, blockFace == direction));
                    case WEST -> blockMenu.replaceExistingItem(getWestSlot(), getDirectionalSlotPane(blockFace, material, blockFace == direction));
                    case UP -> blockMenu.replaceExistingItem(getUpSlot(), getDirectionalSlotPane(blockFace, material, blockFace == direction));
                    case DOWN -> blockMenu.replaceExistingItem(getDownSlot(), getDirectionalSlotPane(blockFace, material, blockFace == direction));
                    default -> throw new IllegalStateException("意外的值: " + blockFace);
                }
            }
        }
        blockMenu.replaceExistingItem(getShowSlot(), getShowIcon());
    }

    @Nonnull
    protected BlockFace getCurrentDirection(@Nonnull BlockMenu blockMenu) {
        BlockFace direction = SELECTED_DIRECTION_MAP.get(blockMenu.getLocation().clone());

        if (direction == null) {
            direction = BlockFace.valueOf(StorageCacheUtils.getData(blockMenu.getLocation(), DIRECTION));
            SELECTED_DIRECTION_MAP.put(blockMenu.getLocation().clone(), direction);
        }
        return direction;
    }

    @OverridingMethodsMustInvokeSuper
    protected void onTick(@Nullable BlockMenu blockMenu, @Nonnull Block block) {
        addToRegistry(block);
        updateGui(blockMenu);
    }

    protected void onUniqueTick() {}

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(getBackgroundSlots());

                if (getOtherBackgroundSlots() != null && getOtherBackgroundStack() != null) {
                    drawBackground(getOtherBackgroundStack(), getOtherBackgroundSlots());
                }

                addItem(getNorthSlot(), getDirectionalSlotPane(BlockFace.NORTH, Material.AIR, false), (player, i, itemStack, clickAction) -> false);
                addItem(getSouthSlot(), getDirectionalSlotPane(BlockFace.SOUTH, Material.AIR, false), (player, i, itemStack, clickAction) -> false);
                addItem(getEastSlot(), getDirectionalSlotPane(BlockFace.EAST, Material.AIR, false), (player, i, itemStack, clickAction) -> false);
                addItem(getWestSlot(), getDirectionalSlotPane(BlockFace.WEST, Material.AIR, false), (player, i, itemStack, clickAction) -> false);
                addItem(getUpSlot(), getDirectionalSlotPane(BlockFace.UP, Material.AIR, false), (player, i, itemStack, clickAction) -> false);
                addItem(getDownSlot(), getDirectionalSlotPane(BlockFace.DOWN, Material.AIR, false), (player, i, itemStack, clickAction) -> false);
                addItem(getAddSlot(), getAddIcon());
                addMenuClickHandler(getAddSlot(), (p, slot, item, action) -> {
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
                addItem(getMinusSlot(), getMinusIcon());
                addMenuClickHandler(getMinusSlot(), (p, slot, item, action) -> {
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
            public void newInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block b) {
                final BlockFace direction;
                final String string = StorageCacheUtils.getData(blockMenu.getLocation(), DIRECTION);

                if (string == null) {
                    // This likely means a block was placed before I made it directional
                    direction = BlockFace.SELF;
                    StorageCacheUtils.setData(blockMenu.getLocation(), DIRECTION, BlockFace.SELF.name());
                } else {
                    direction = BlockFace.valueOf(string);
                }
                SELECTED_DIRECTION_MAP.put(blockMenu.getLocation().clone(), direction);

                blockMenu.addMenuClickHandler(getNorthSlot(), (player, i, itemStack, clickAction) ->
                        directionClick(player, clickAction, blockMenu, BlockFace.NORTH));
                blockMenu.addMenuClickHandler(getSouthSlot(), (player, i, itemStack, clickAction) ->
                        directionClick(player, clickAction, blockMenu, BlockFace.SOUTH));
                blockMenu.addMenuClickHandler(getEastSlot(), (player, i, itemStack, clickAction) ->
                        directionClick(player, clickAction, blockMenu, BlockFace.EAST));
                blockMenu.addMenuClickHandler(getWestSlot(), (player, i, itemStack, clickAction) ->
                        directionClick(player, clickAction, blockMenu, BlockFace.WEST));
                blockMenu.addMenuClickHandler(getUpSlot(), (player, i, itemStack, clickAction) ->
                        directionClick(player, clickAction, blockMenu, BlockFace.UP));
                blockMenu.addMenuClickHandler(getDownSlot(), (player, i, itemStack, clickAction) ->
                        directionClick(player, clickAction, blockMenu, BlockFace.DOWN));
                blockMenu.addMenuClickHandler(getShowSlot(), (player, i, itemStack, clickAction) -> false);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return this.getSlimefunItem().canUse(player, false)
                        && Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                }
            }
        };
    }

    @ParametersAreNonnullByDefault
    public boolean directionClick(Player player, ClickAction action, BlockMenu blockMenu, BlockFace blockFace) {
        if (action.isShiftClicked()) {
            openDirection(player, blockMenu, blockFace);
        } else {
            setDirection(blockMenu, blockFace);
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public void openDirection(Player player, BlockMenu blockMenu, BlockFace blockFace) {
        final BlockMenu targetMenu = StorageCacheUtils.getMenu(blockMenu.getBlock().getRelative(blockFace).getLocation());
        if (targetMenu != null) {
            final Location location = targetMenu.getLocation();
            final SlimefunItem item = StorageCacheUtils.getSfItem(location);
            if (item.canUse(player, true)
                    && Slimefun.getProtectionManager().hasPermission(player, blockMenu.getLocation(), Interaction.INTERACT_BLOCK)
            ) {
                targetMenu.open(player);
            }
        }
    }

    @ParametersAreNonnullByDefault
    public void setDirection(BlockMenu blockMenu, BlockFace blockFace) {
        SELECTED_DIRECTION_MAP.put(blockMenu.getLocation().clone(), blockFace);
        StorageCacheUtils.setData(blockMenu.getBlock().getLocation(), DIRECTION, blockFace.name());
    }

    @Nonnull
    protected int[] getBackgroundSlots() {
        return new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 16, 17, 18, 19, 21, 23, 24, 25, 26, 27, 28, 29, 21, 31, 32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
        };
    }

    @Nullable
    protected int[] getOtherBackgroundSlots() {
        return null;
    }

    @Nullable
    protected CustomItemStack getOtherBackgroundStack() {
        return null;
    }

    public int getNorthSlot() {
        return NORTH_SLOT;
    }

    public int getSouthSlot() {
        return SOUTH_SLOT;
    }

    public int getEastSlot() {
        return EAST_SLOT;
    }

    public int getWestSlot() {
        return WEST_SLOT;
    }

    public int getUpSlot() {
        return UP_SLOT;
    }

    public int getDownSlot() {
        return DOWN_SLOT;
    }

    public int[] getItemSlots() {
        return new int[]{};
    }

    public int[] getInputSlots() { return new int[0]; }

    public int[] getOutputSlots() { return new int[0]; }

    @Nonnull
    public static ItemStack getDirectionalSlotPane(@Nonnull BlockFace blockFace, @Nonnull SlimefunItem slimefunItem, boolean active) {
        final ItemStack displayStack = new CustomItemStack(
                slimefunItem.getItem(),
                Theme.PASSIVE + "设置朝向: " + blockFace.name() + " (" + ChatColor.stripColor(slimefunItem.getItemName()) + ")"
        );
        final ItemMeta itemMeta = displayStack.getItemMeta();
        if (active) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemMeta.setLore(List.of(
                Theme.CLICK_INFO + "左键点击: " + Theme.PASSIVE + "设置朝向",
                Theme.CLICK_INFO + "Shift+左键点击: " + Theme.PASSIVE + "打开目标方块"
        ));
        displayStack.setItemMeta(itemMeta);
        return displayStack;
    }

    @Nonnull
    public static ItemStack getDirectionalSlotPane(@Nonnull BlockFace blockFace, @Nonnull Material blockMaterial, boolean active) {
        if (blockMaterial.isItem() && !blockMaterial.isAir()) {
            final ItemStack displayStack = new CustomItemStack(
                    blockMaterial,
                    Theme.PASSIVE + "设置朝向 " + blockFace.name() + " (" + MaterialHelper.getName(blockMaterial) + ")"
            );
            final ItemMeta itemMeta = displayStack.getItemMeta();
            if (active) {
                itemMeta.addEnchant(Enchantment.LUCK, 1, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            itemMeta.setLore(List.of(
                    Theme.CLICK_INFO + "左键点击: " + Theme.PASSIVE + "设置朝向",
                    Theme.CLICK_INFO + "Shift+左键点击: " + Theme.PASSIVE + "打开目标方块"
            ));
            displayStack.setItemMeta(itemMeta);
            return displayStack;
        } else {
            Material material = active ? Material.GREEN_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
            return new CustomItemStack(
                    material,
                    ChatColor.GRAY + "设置朝向: " + blockFace.name()
            );
        }
    }

    @Nullable
    public static BlockFace getSelectedFace(@Nonnull Location location) {
        return SELECTED_DIRECTION_MAP.get(location);
    }

    protected Particle.DustOptions getDustOptions() {
        return new Particle.DustOptions(Color.RED, 1);
    }

    protected void showParticle(@Nonnull Location location, @Nonnull BlockFace blockFace) {
        final Vector faceVector = blockFace.getDirection().clone().multiply(-1);
        final Vector pushVector = faceVector.clone().multiply(2);
        final Location displayLocation = location.clone().add(0.5, 0.5, 0.5).add(faceVector);
        location.getWorld().spawnParticle(Particle.REDSTONE, displayLocation, 0, pushVector.getX(), pushVector.getY(), pushVector.getZ(), getDustOptions());
    }
    public CustomItemStack getShowIcon() {
        return SHOW_ICON;
    }

    public CustomItemStack getMinusIcon() {
        return MINUS_ICON;
    }

    public CustomItemStack getAddIcon() {
        return ADD_ICON;
    }

    public int getCurrentNumber() {
        return this.currentNumber;
    }

    public void setCurrentNumber(int number) {
        this.currentNumber = number;
    }

    public void minusNumber(int number) {
        if (this.currentNumber - number >= 1) {
            setCurrentNumber(this.currentNumber - number);
        } else {
            setCurrentNumber(1);
        }        updateShowIcon();
    }

    public void addNumber(int number) {
        if (this.currentNumber + number <= this.limit) {
            setCurrentNumber(this.currentNumber + number);
        } else {
            setCurrentNumber(this.limit);
        }
        updateShowIcon();
    }

    public void updateShowIcon() {
        ItemMeta itemMeta = SHOW_ICON.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.set(0, Theme.NOTICE + "当前数量: " + this.currentNumber);
        itemMeta.setLore(lore);
        SHOW_ICON.setItemMeta(itemMeta);
    }

    protected abstract int getMinusSlot();
    protected abstract int getShowSlot();
    protected abstract int getAddSlot();
}
