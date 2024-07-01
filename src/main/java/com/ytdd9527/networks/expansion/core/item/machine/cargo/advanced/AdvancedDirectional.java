package com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced;

import com.ibm.icu.impl.CacheValue.Strength;
import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
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
import me.mrCookieSlime.Slimefun.api.BlockStorage;
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
import java.util.*;

@SuppressWarnings("deprecation")
public abstract class AdvancedDirectional extends NetworkDirectional {

    private static final int NORTH_SLOT = 12;
    private static final int SOUTH_SLOT = 30;
    private static final int EAST_SLOT = 22;
    private static final int WEST_SLOT = 20;
    private static final int UP_SLOT = 15;
    private static final int DOWN_SLOT = 33;

    protected static final String DIRECTION = "direction";
    protected static final String OWNER_KEY = "uuid";
    protected static final String LIMIT_KEY = "transport_limit";
    protected static final String TRANSPORT_MODE_KEY = "transport_mode";

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

    private static final CustomItemStack TRANSPORT_MODE_ICON = new CustomItemStack (
        Material.GREEN_CONCRETE, 
        Theme.NOTICE + "运输模式",
        Theme.NOTICE + "当前模式：" + Theme.MECHANISM + "无"
    );

    public static final String TRANSPORT_MODE_NONE = "NONE";
    public static final String TRANSPORT_MODE_NULL_ONLY = "NULL_ONLY";
    public static final String TRANSPORT_MODE_NONNULL_ONLY = "NONNULL_ONLY";

    private final ItemStack showIconClone;
    private final ItemStack transportModeIconClone;

    NetworkDirectional instance = this;

    private static final Map<Location, BlockFace> SELECTED_DIRECTION_MAP = new HashMap<>();
    private static final Map<Location, Integer> NETWORK_NUMBER_MAP = new HashMap<>();
    private static final Map<Location, String> NETWORK_TRANSPORT_MODE_MAP = new HashMap<>();

    protected AdvancedDirectional(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, NodeType type, int limit) {
        super(itemGroup, item, recipeType, recipe, type);
        this.limit = limit;
        this.showIconClone = SHOW_ICON.clone();
        this.transportModeIconClone = TRANSPORT_MODE_ICON.clone();

        addItemHandler(
                new BlockPlaceHandler(false) {
                    @Override
                    public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                        NetworkStorage.removeNode(event.getBlock().getLocation());
                        var blockData = StorageCacheUtils.getBlock(event.getBlock().getLocation());
                        blockData.setData(OWNER_KEY, event.getPlayer().getUniqueId().toString());
                        blockData.setData(DIRECTION, BlockFace.SELF.name());
                        blockData.setData(LIMIT_KEY, String.valueOf(limit));
                        blockData.setData(TRANSPORT_MODE_KEY, TRANSPORT_MODE_NONE);
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
        updateIcons(block);
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
                addItem(getAddSlot(), getAddIcon(), (p, i, itemStack, clickAction) -> false);
                addItem(getMinusSlot(), getMinusIcon(), (p, i, itemStack, clickAction) -> false);
                addItem(getShowSlot(), getShowIcon(), (p, i, itemStack, clickAction) -> false);
                if (getTransportModeSlot() != -1) {
                    addItem(getTransportModeSlot(), getTransportModeIcon(), (p, i, itemStack, clickAction) -> false);
                }
            }

            @Override
            public void newInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block b) {
                String mode;
                final BlockFace direction;
                final String string = StorageCacheUtils.getData(blockMenu.getLocation(), DIRECTION);
                final String rawLimit = StorageCacheUtils.getData(blockMenu.getLocation(), LIMIT_KEY);
                final String rawMode = StorageCacheUtils.getData(blockMenu.getLocation(), TRANSPORT_MODE_KEY);

                if (string == null) {
                    // This likely means a block was placed before I made it directional
                    direction = BlockFace.SELF;
                    StorageCacheUtils.setData(blockMenu.getLocation(), DIRECTION, BlockFace.SELF.name());
                } else {
                    direction = BlockFace.valueOf(string);
                }
                SELECTED_DIRECTION_MAP.put(blockMenu.getLocation().clone(), direction);
                
                if (rawLimit == null) {
                    limit = 64;
                    StorageCacheUtils.setData(blockMenu.getLocation(), LIMIT_KEY, String.valueOf(limit));
                } else {
                    limit = Integer.valueOf(rawLimit);
                }
                NETWORK_NUMBER_MAP.put(blockMenu.getLocation().clone(), limit);

                if (rawMode == null) {
                    mode = TRANSPORT_MODE_NONE;
                    StorageCacheUtils.setData(blockMenu.getLocation(), TRANSPORT_MODE_KEY, mode);
                } else {
                    mode = rawMode;
                }
                NETWORK_TRANSPORT_MODE_MAP.put(blockMenu.getLocation().clone(), mode);

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

                blockMenu.addMenuClickHandler(getAddSlot(), (p, slot, item, action) -> 
                    addClick(blockMenu.getBlock(), action));
                blockMenu.addMenuClickHandler(getMinusSlot(), (p, slot, item, action) -> 
                    minusClick(blockMenu.getBlock(), action));

                if (getTransportModeSlot() != -1) {
                    blockMenu.addMenuClickHandler(getTransportModeSlot(), (p, slot, item, action) -> 
                        toggleTransportMode(blockMenu.getBlock()));
                }
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

    @ParametersAreNonnullByDefault
    public boolean minusClick(Block block, ClickAction action) {
        int n = 1;
        if (action.isRightClicked()) {
            n = 8;
        }
        if (action.isShiftClicked()) {
            n = 64;
        }
        minusNumber(block, n);
        return false;
    }

    @ParametersAreNonnullByDefault
    public boolean addClick(Block block, ClickAction action) {
        int n = 1;
        if (action.isRightClicked()) {
            n = 8;
        }
        if (action.isShiftClicked()) {
            n = 64;
        }
        addNumber(block, n);
        return false;
    }

    public void updateIcons(Block block) {
        updateShowIcon(block);
        updateTransportModeIcon(block);
    }

    @Nonnull
    protected abstract int[] getBackgroundSlots();

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
        return new int[0];
    }

    public int[] getInputSlots() {
        return new int[0];
    }

    public int[] getOutputSlots() {
        return new int[0];
    }

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
    public ItemStack getShowIcon() {
        return this.showIconClone;
    }

    public CustomItemStack getMinusIcon() {
        return MINUS_ICON;
    }

    public CustomItemStack getAddIcon() {
        return ADD_ICON;
    }

    public CustomItemStack getTransportModeIcon() {
        return TRANSPORT_MODE_ICON;
    }



    public int getCurrentNumber(Block block) {
        Integer number = NETWORK_NUMBER_MAP.get(block.getLocation().clone());
        if (number == null) {
            number = Integer.parseInt(BlockStorage.getLocationInfo(block.getLocation(), LIMIT_KEY));
            NETWORK_NUMBER_MAP.put(block.getLocation().clone(), number);
        }
        return number;
    }

    public void setCurrentNumber(Block block, int number) {
        NETWORK_NUMBER_MAP.put(block.getLocation().clone(), number);
        BlockStorage.addBlockInfo(block.getLocation(), LIMIT_KEY, Integer.toString(number));
    }

    public String getCurrentTransportMode(Block block) {
        String mode = NETWORK_TRANSPORT_MODE_MAP.get(block.getLocation().clone());
        if (mode == null) {
            mode = BlockStorage.getLocationInfo(block.getLocation(), TRANSPORT_MODE_KEY);
            NETWORK_TRANSPORT_MODE_MAP.put(block.getLocation().clone(), mode);
        }
        return mode;
    }

    public void setTransportMode(Block block, String mode) {
        NETWORK_TRANSPORT_MODE_MAP.put(block.getLocation().clone(), mode);
        BlockStorage.addBlockInfo(block.getLocation(), TRANSPORT_MODE_KEY, mode);
    }

    public boolean toggleTransportMode(Block block) {
        String mode = getCurrentTransportMode(block);
        switch (mode) {
            case TRANSPORT_MODE_NONE -> {
                setTransportMode(block, TRANSPORT_MODE_NULL_ONLY);
            }
            case TRANSPORT_MODE_NULL_ONLY -> {
                setTransportMode(block, TRANSPORT_MODE_NONNULL_ONLY);
            }
            case TRANSPORT_MODE_NONNULL_ONLY -> {
                setTransportMode(block, TRANSPORT_MODE_NONE);
            }
            default -> {
                setTransportMode(block, TRANSPORT_MODE_NONE);
            }
        }
        updateTransportModeIcon(block);
        return false;
    }

    public void minusNumber(Block block, int number) {
        if (getCurrentNumber(block) - number >= 1) {
            setCurrentNumber(block, getCurrentNumber(block) - number);
        } else {
            setCurrentNumber(block, 1);
        }        
        updateShowIcon(block);
    }
    public void addNumber(Block block, int number) {
        int currentNumber = getCurrentNumber(block);
        int newNumber = currentNumber + number;
        if (newNumber > limit) {
            newNumber = limit; // 限制增加后的数字不超过 limit
        }
        setCurrentNumber(block, newNumber);
        updateShowIcon(block);
    }
    public void setLimit(int newLimit) {
        if (newLimit > 0 && newLimit <= 3456) {
            this.limit = newLimit;
        } else {
        }
    }
    public void updateShowIcon(Block block) {

        ItemMeta itemMeta = this.showIconClone.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        lore.set(0, Theme.NOTICE + "当前数量: " + getCurrentNumber(block));
        itemMeta.setLore(lore);
        this.showIconClone.setItemMeta(itemMeta);

        BlockMenu blockMenu = StorageCacheUtils.getMenu(block.getLocation());
        if (blockMenu != null) {
            blockMenu.replaceExistingItem(getShowSlot(), this.showIconClone);
        }
    }

    public void updateTransportModeIcon(Block block) {
        ItemMeta itemMeta = this.transportModeIconClone.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        lore.set(0, Theme.NOTICE + "当前模式: " + Theme.MECHANISM + toText(getCurrentTransportMode(block)));
        itemMeta.setLore(lore);
        this.transportModeIconClone.setItemMeta(itemMeta);

        BlockMenu blockMenu = StorageCacheUtils.getMenu(block.getLocation());
        if (blockMenu != null) {
            int slot = getTransportModeSlot();
            if (slot != -1) {
                blockMenu.replaceExistingItem(slot, this.transportModeIconClone);
            }
        }
    }
    public String toText(String mode) {
        switch (mode) {
            case TRANSPORT_MODE_NONE -> {
                return "无";
            }
            case TRANSPORT_MODE_NULL_ONLY -> {
                return "仅空";
            }
            case TRANSPORT_MODE_NONNULL_ONLY -> {
                return "仅非空";
            }
            default -> {
                return "未知";
            }
        }
    }

    protected abstract int getMinusSlot();
    protected abstract int getShowSlot();
    protected abstract int getAddSlot();
    protected abstract int getTransportModeSlot();
}
