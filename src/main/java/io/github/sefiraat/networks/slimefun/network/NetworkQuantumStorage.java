package io.github.sefiraat.networks.slimefun.network;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.network.stackcaches.ItemStackCache;
import io.github.sefiraat.networks.network.stackcaches.QuantumCache;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils.Utils;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.sefiraat.networks.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.networks.utils.datatypes.PersistentQuantumStorageType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DistinctiveItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import net.guizhanss.guizhanlib.java.BooleanHelper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import static com.gmail.nossr50.mcMMO.p;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class NetworkQuantumStorage extends SlimefunItem implements DistinctiveItem {

    private static final int[] SIZES = new int[]{
        64,
        256,
        1024,
        4096,
        32768,
        262144,
        2097152,
        16777216,
        134217728,
        1073741824,
        Integer.MAX_VALUE
    };


    private static final String WIKI_PAGE = "network-storage/quantum-storage";

    public static final String BS_AMOUNT = "stored_amount";
    public static final String BS_VOID = "void_excess";
    public static final String BS_CUSTOM_MAX_AMOUNT = "custom_max_amount";
    public static final int INPUT_SLOT = 1;
    public static final int ITEM_SLOT = 4;
    public static final int ITEM_SET_SLOT = 13;
    public static final int OUTPUT_SLOT = 7;
    private static final int TRASH_TOGGLE_SLOT = 9;
    private static final ItemStack BACK_INPUT = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE,
        Theme.PASSIVE + "输入"
    );

    private static final ItemStack BACK_ITEM = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "当前存储的物品"
    );

    private static final ItemStack NO_ITEM = new CustomItemStack(
        Material.RED_STAINED_GLASS_PANE,
        Theme.ERROR + "未指定物品",
        Theme.PASSIVE + "拿起物品并点击下方的按钮",
        Theme.PASSIVE + "以设置量子存储保存的物品"
    );

    private static final ItemStack SET_ITEM = new CustomItemStack(
        Material.LIME_STAINED_GLASS_PANE,
        Theme.SUCCESS + "设置",
        Theme.PASSIVE + "拿起物品并点击这里以设置物品",
        Theme.CLICK_INFO + "Shift+左键点击" + Theme.PASSIVE + "切换满载清空输入");

    private static final ItemStack SET_ITEM_SUPPORTING_CUSTOM_MAX = new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            Theme.SUCCESS + "设置",
            Theme.PASSIVE + "拿起物品并点击这里以设置物品",
            Theme.PASSIVE + "点击这里以设置更改容量",
            Theme.CLICK_INFO + "Shift+左键点击" + Theme.PASSIVE + "切换满载清空输入");

    private static final ItemStack TRASH_ON_ITEM = new CustomItemStack(SlimefunItems.TRASH_CAN, "&3满载清空输入 &a(开启)",
            "&7开启后可清空无法存储的物品");
    private static final ItemStack TRASH_OFF_ITEM = new CustomItemStack(SlimefunItems.TRASH_CAN, "&3满载清空输入 &c(关闭)",
            "&7开启后可清空无法存储的物品");
    private static final ItemStack BACK_OUTPUT = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "输出"
    );

    private static final int[] INPUT_SLOTS = new int[]{0, 2};
    private static final int[] ITEM_SLOTS = new int[]{3, 5};
    private static final int[] OUTPUT_SLOTS = new int[]{6, 8};
    private static final int[] BACKGROUND_SLOTS = new int[]{10,11, 12, 14, 15, 16, 17};

    private static final Map<Location, QuantumCache> CACHES = new HashMap<>();

    static {
        final ItemMeta itemMeta = NO_ITEM.getItemMeta();
        PersistentDataAPI.setBoolean(itemMeta, Keys.newKey("display"), true);
        NO_ITEM.setItemMeta(itemMeta);
    }

    private final List<Integer> slotsToDrop = new ArrayList<>();

    private final int maxAmount;
    private boolean supportsCustomMaxAmount = false;
    public NetworkQuantumStorage(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int maxAmount) {
        super(itemGroup, item, recipeType, recipe);
        this.maxAmount = maxAmount;
        slotsToDrop.add(INPUT_SLOT);
        slotsToDrop.add(OUTPUT_SLOT);
    }

    @Override
    public void preRegister() {
        addItemHandler(
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block b, SlimefunItem item, SlimefunBlockData data) {
                    onTick(b);
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                @ParametersAreNonnullByDefault
                public void onPlayerBreak(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                    onBreak(event);
                }
            },
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                    onPlace(event);
                }
            }

        );
    }

    private void onTick(Block block) {
        final BlockMenu blockMenu = StorageCacheUtils.getMenu(block.getLocation());

        if (blockMenu == null) {
            CACHES.remove(block.getLocation());
            return;
        }

        final QuantumCache cache = CACHES.get(blockMenu.getLocation());

        if (cache == null) {
            return;
        }

        if (blockMenu.hasViewer()) {
            updateDisplayItem(blockMenu, cache);
        }

        // Move items from the input slot into the card
        final ItemStack input = blockMenu.getItemInSlot(INPUT_SLOT);
        if (input != null && input.getType() != Material.AIR) {
            tryInputItem(blockMenu.getLocation(), new ItemStack[]{input}, cache);
        }

        // Output items
        final ItemStack output = blockMenu.getItemInSlot(OUTPUT_SLOT);
        ItemStack fetched = null;
        if (output == null || output.getType() == Material.AIR) {
            // No item in output, try output
            fetched = cache.withdrawItem();
        } else if (StackUtils.itemsMatch(cache, output, true) && output.getAmount() < output.getMaxStackSize()) {
            // There is an item, but it's not filled so lets top it up if we can
            final int requestAmount = output.getMaxStackSize() - output.getAmount();
            fetched = cache.withdrawItem(requestAmount);
        }

        if (fetched != null && fetched.getType() != Material.AIR) {
            blockMenu.pushItem(fetched, OUTPUT_SLOT);
            syncBlock(blockMenu.getLocation(), cache);
        }

        CACHES.put(blockMenu.getLocation().clone(), cache);
    }

    private void toggleVoid(@Nonnull BlockMenu blockMenu) {
        final QuantumCache cache = CACHES.get(blockMenu.getLocation());
        cache.setVoidExcess(!cache.isVoidExcess());
        updateDisplayItem(blockMenu, cache);
        syncBlock(blockMenu.getLocation(), cache);
        CACHES.put(blockMenu.getLocation(), cache);
    }

    private void setItem(@Nonnull BlockMenu blockMenu, @Nonnull Player player) {
        final ItemStack itemStack = player.getItemOnCursor().clone();

        if (isBlacklisted(itemStack)) {
            return;
        }

        final QuantumCache cache = CACHES.get(blockMenu.getLocation());
        if (cache == null || cache.getAmount() > 0) {
            player.sendMessage(Theme.WARNING + "量子存储必须为空才能更换物品");
            return;
        }
        itemStack.setAmount(1);
        cache.setItemStack(itemStack);
        updateDisplayItem(blockMenu, cache);
        syncBlock(blockMenu.getLocation(), cache);
        CACHES.put(blockMenu.getLocation(), cache);
    }

    private void setCustomMaxAmount(@Nonnull BlockMenu blockMenu, @Nonnull Player player, @Nonnull int newMaxAmount ) {
        final QuantumCache cache = CACHES.get(blockMenu.getLocation());
        if (cache == null || !cache.supportsCustomMaxAmount()) {
            Utils.send(player,"高级量子存储不存在 不可设置 请检查高级量子存储是否存在!");

            return;
        }
        cache.setLimit(newMaxAmount);
        updateDisplayItem(blockMenu, cache);
        syncBlock(blockMenu.getLocation(), cache);
        CACHES.put(blockMenu.getLocation(), cache);

        player.sendMessage(
                Theme.PASSIVE + "[" + Theme.GOLD + "网络拓展" + Theme.PASSIVE + "] " +
                   Theme.SUCCESS + "已更改容量: " + newMaxAmount
        );}

    public void setSupportsCustomMaxAmount(boolean supportsCustomMaxAmount) {
        this.supportsCustomMaxAmount = supportsCustomMaxAmount;
    }

    @Override
    public void postRegister() {
        addWikiPage(WIKI_PAGE);
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                for (int i : INPUT_SLOTS) {
                    addItem(i, BACK_INPUT, (p, slot, item, action) -> false);
                }
                for (int i : ITEM_SLOTS) {
                    addItem(i, BACK_ITEM, (p, slot, item, action) -> false);
                }
                for (int i : OUTPUT_SLOTS) {
                    addItem(i, BACK_OUTPUT, (p, slot, item, action) -> false);
                }
                ItemStack setItemItemstack = supportsCustomMaxAmount ? SET_ITEM_SUPPORTING_CUSTOM_MAX : SET_ITEM;
                addItem(ITEM_SET_SLOT, setItemItemstack, (p, slot, item, action) -> false);
                addMenuClickHandler(ITEM_SLOT, ChestMenuUtils.getEmptyClickHandler());
                drawBackground(BACKGROUND_SLOTS);

            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return new int[]{INPUT_SLOT};
                } else if (flow == ItemTransportFlow.WITHDRAW) {
                    return new int[]{OUTPUT_SLOT};
                }
                return new int[0];
            }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block block) {
                menu.addMenuClickHandler(ITEM_SET_SLOT, (p, slot, item, action) -> {
                    final QuantumCache cache = CACHES.get(menu.getLocation());
                    if (action.isShiftClicked()) {
                        toggleVoid(menu);
                    } else if (
                            cache != null &&
                                    cache.supportsCustomMaxAmount() &&
                                    p.getItemOnCursor().getType() == Material.AIR
                    ) {
                        p.closeInventory();
                        p.sendMessage(
                                Theme.PASSIVE + "[" + Theme.GOLD + "网络拓展" + Theme.PASSIVE + "] " +
                                   Theme.WARNING +"请输入网络高级量子存储的容量.最大限制为: " + Integer.MAX_VALUE + " !");
                        ChatUtils.awaitInput(p, s -> {
                            // Catching the error is cleaner than directly validating the string
                            try {
                                if (s.isBlank()) {
                                    return;
                                }
                                int newMax = Math.max(1, Math.min(Integer.parseInt(s), maxAmount));
                                setCustomMaxAmount(menu, p, newMax);
                            } catch (NumberFormatException e) {
                                p.sendMessage(
                                        Theme.PASSIVE + "[" + Theme.GOLD + "网络拓展" + Theme.PASSIVE + "] " +
                                        Theme.ERROR + "网络高级量子存储必须为: 1 至 " + Integer.MAX_VALUE + " !");
                            }
                        });
                    } else {
                        setItem(menu, p);
                    }
                    return false;
                });
                // Toggle trash (Dynamic button)
                String trash = StorageCacheUtils.getData(block.getLocation(), "trash");

                if (trash == null || trash.equals("false")) {
                    menu.replaceExistingItem(TRASH_TOGGLE_SLOT, TRASH_OFF_ITEM);
                } else {
                    menu.replaceExistingItem(TRASH_TOGGLE_SLOT, TRASH_ON_ITEM);
                }
                menu.addMenuClickHandler(TRASH_TOGGLE_SLOT, (pl, slot, item, action) -> {
                    toggleTrash(block);
                    return false;
                });


                // Insert all
                int INSERT_ALL_SLOT = 16;
                menu.replaceExistingItem(INSERT_ALL_SLOT,
                        new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, "&b快速存入",
                                "&7> 点击此处将物品栏中所有可用物品", "&7存入高级量子存储"));
                menu.addMenuClickHandler(INSERT_ALL_SLOT, (pl, slot, item, action) -> {
                    insertAll(pl, menu, block);
                    return false;
                });

                // Extract all
                int EXTRACT_SLOT = 17;
                menu.replaceExistingItem(EXTRACT_SLOT,
                        new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&6快速取出",
                                "&7> [左键]点击将物品取出并填满你的物品栏",
                                "&7> [右键]点击取出1个物品",
                                "&7> [shift+右键]点击取出64个物品"
                        ));
                menu.addMenuClickHandler(EXTRACT_SLOT, (pl, slot, item, action) -> {
                    extract(pl, menu, block, action);
                    return false;
                });


                // Cache may exist if placed with items held inside.
                QuantumCache cache = CACHES.get(block.getLocation());
                if (cache == null) {
                    cache = addCache(menu);
                }
                updateDisplayItem(menu, cache);
            }
        };
    }
    public void insertAll(Player p, BlockMenu menu, Block b) {
        PlayerInventory inv = p.getInventory();
        QuantumCache cache = CACHES.get(menu.getLocation());
        if (cache == null) return;

        ItemStack storedItem = cache.getItemStack();
        int capacity = cache.getLimit();

        ItemStack[] contents = inv.getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item == null || item.getType() == Material.AIR) continue;

            ItemStackCache storedItemCache = new ItemStackCache(storedItem);


            if (StackUtils.itemsMatch(storedItemCache, item, true)) {
                int toAdd = Math.min(item.getAmount(), capacity - cache.getAmount());
                if (toAdd > 0) {

                    item.setAmount(item.getAmount() - toAdd);
                    cache.increaseAmount(toAdd);

                    if (item.getAmount() == 0) {
                        inv.setItem(i, null);
                    }
                }
            }
        }

        // 同步更新区块状态和界面显示
        syncBlock(b.getLocation(), cache);
        updateDisplayItem(menu, cache);
    }
    public void extract(Player p, BlockMenu menu, Block b, ClickAction action) {
        QuantumCache cache = CACHES.get(menu.getLocation());
        if (cache == null) return;

        ItemStack storedItem = cache.getItemStack();
        int stored = cache.getAmount();
        if (action.isShiftClicked() && action.isRightClicked()) {
            // 如果同时按下Shift和右键，提取64个物品
            ItemStack extractedItem = cache.withdrawItem(64);
            if (extractedItem != null) {
                Utils.giveOrDropItem(p, extractedItem);
            }
        } else if (action.isRightClicked()) {
            // 如果只按下右键，提取单个物品
            ItemStack extractedItem = cache.withdrawItem(1);
            if (extractedItem != null) {
                Utils.giveOrDropItem(p, extractedItem);
            }
        }

        else {
            PlayerInventory inv = p.getInventory();
            ItemStack[] contents = inv.getStorageContents(); // 获取玩家背包内容

            for (int i = 0; i < contents.length; i++) {
                if (contents[i] == null || contents[i].getType() == Material.AIR) {
                    // 玩家背包中有空槽位
                    if (stored == 0) break; // 如果量子存储已空，退出循环

                    int amountToExtract = Math.min(stored, storedItem.getMaxStackSize());
                    ItemStack itemToInsert = storedItem.clone();
                    itemToInsert.setAmount(amountToExtract);
                    contents[i] = itemToInsert; // 尝试填入物品

                    cache.reduceAmount(amountToExtract); // 减少量子存储中的物品数量
                    stored -= amountToExtract; // 更新已存储的物品数量

                    if (stored == 0) break; // 如果量子存储已空，退出循环
                }
            }

            p.getInventory().setStorageContents(contents); // 设置玩家背包内容
            syncBlock(b.getLocation(), cache); // 同步区块状态
            updateDisplayItem(menu, cache); // 更新界面显示
        }
    }

    private QuantumCache addCache(@Nonnull BlockMenu blockMenu) {
        final Location location = blockMenu.getLocation();
        var blockData = StorageCacheUtils.getBlock(location);
        final String amountString = blockData.getData(BS_AMOUNT);
        final String voidString = blockData.getData(BS_VOID);
        final long amount = amountString == null ? 0 : Integer.parseInt(amountString);
        final boolean voidExcess = voidString == null || Boolean.parseBoolean(voidString);
        int maxAmount = this.maxAmount;
        if (this.supportsCustomMaxAmount) {
            final String customMaxAmountString = BlockStorage.getLocationInfo(blockMenu.getLocation(), BS_CUSTOM_MAX_AMOUNT);
            if (customMaxAmountString != null) {
                maxAmount = Integer.parseInt(customMaxAmountString);
            }
        }
        final ItemStack itemStack = blockMenu.getItemInSlot(ITEM_SLOT);

        QuantumCache cache = createCache(itemStack, blockMenu, amount, maxAmount, voidExcess);


        CACHES.put(location, cache);
        return cache;
    }

    private QuantumCache createCache(@Nullable ItemStack itemStack, @Nonnull BlockMenu menu, long amount, int maxAmount, boolean voidExcess) {
        if (itemStack == null || itemStack.getType() == Material.AIR || isDisplayItem(itemStack)) {
            menu.addItem(ITEM_SLOT, NO_ITEM);
            return new QuantumCache(null, 0, maxAmount, true, this.supportsCustomMaxAmount);
        } else {
            final ItemStack clone = itemStack.clone();
            final ItemMeta itemMeta = clone.getItemMeta();
            final List<String> lore = itemMeta.getLore();
            if (lore != null){
                for (int i = 0; i < 3; i++) {
                    if (lore.size() > 0){
                        lore.remove(lore.size() - 1);
                    }
                }
            }
            itemMeta.setLore(lore.isEmpty() ? null : lore);
            clone.setItemMeta(itemMeta);

            final QuantumCache cache = new QuantumCache(clone, amount, maxAmount, voidExcess, this.supportsCustomMaxAmount);

            updateDisplayItem(menu, cache);
            return cache;
        }
    }

    private boolean isDisplayItem(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.getBoolean(itemStack.getItemMeta(), Keys.newKey("display"));
    }

    protected void onBreak(@Nonnull BlockBreakEvent event) {
        final Location location = event.getBlock().getLocation();
        final BlockMenu blockMenu = StorageCacheUtils.getMenu(event.getBlock().getLocation());

        if (blockMenu != null) {
            final QuantumCache cache = CACHES.remove(blockMenu.getLocation());

            if (cache != null && cache.getAmount() > 0 && cache.getItemStack() != null) {
                final ItemStack itemToDrop = this.getItem().clone();
                final ItemMeta itemMeta = itemToDrop.getItemMeta();

                DataTypeMethods.setCustom(itemMeta, Keys.QUANTUM_STORAGE_INSTANCE, PersistentQuantumStorageType.TYPE, cache);
                cache.addMetaLore(itemMeta);
                itemToDrop.setItemMeta(itemMeta);
                location.getWorld().dropItem(location.clone().add(0.5, 0.5, 0.5), itemToDrop);
                event.setDropItems(false);
            }

            for (int i : this.slotsToDrop) {
                blockMenu.dropItems(location, i);
            }
        }
    }

    protected void onPlace(@Nonnull BlockPlaceEvent event) {
        final ItemStack itemStack = event.getItemInHand();
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final QuantumCache cache = DataTypeMethods.getCustom(itemMeta, Keys.QUANTUM_STORAGE_INSTANCE, PersistentQuantumStorageType.TYPE);

        if (cache == null) {
            return;
        }

        syncBlock(event.getBlock().getLocation(), cache);
        CACHES.put(event.getBlock().getLocation(), cache);
    }

    public int getMaxAmount() {
        return maxAmount;
    }
    public boolean supportsCustomMaxAmount() {
        return this.supportsCustomMaxAmount;
    }

    @ParametersAreNonnullByDefault
    public static void tryInputItem(Location location, ItemStack[] input, QuantumCache cache) {
        if (cache.getItemStack() == null) {
            return;
        }
        for (ItemStack itemStack : input) {
            if (isBlacklisted(itemStack)) {
                continue;
            }
            if (StackUtils.itemsMatch(cache, itemStack, true)) {
                int leftover = cache.increaseAmount(itemStack.getAmount());
                itemStack.setAmount(leftover);
            }
        }
        syncBlock(location, cache);
    }

    private static boolean isBlacklisted(@Nonnull ItemStack itemStack) {
        return itemStack.getType() == Material.AIR
            || itemStack.getType().getMaxDurability() < 0
            || Tag.SHULKER_BOXES.isTagged(itemStack.getType());
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public static ItemStack getItemStack(@Nonnull QuantumCache cache, @Nonnull BlockMenu blockMenu) {
        if (cache.getItemStack() == null || cache.getAmount() <= 0) {
            return null;
        }
        return getItemStack(cache, blockMenu, cache.getItemStack().getMaxStackSize());
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public static ItemStack getItemStack(@Nonnull QuantumCache cache, @Nonnull BlockMenu blockMenu, int amount) {
        if (cache.getAmount() < amount) {
            // Storage has no content or not enough, mix and match!
            ItemStack output = blockMenu.getItemInSlot(OUTPUT_SLOT);
            ItemStack fetched = cache.withdrawItem(amount);

            if (output != null
                && output.getType() != Material.AIR
                && StackUtils.itemsMatch(cache, output, true)
            ) {
                // We have an output item we can use also
                if (fetched == null || fetched.getType() == Material.AIR) {
                    // Storage is totally empty - just use output slot
                    fetched = output.clone();
                    if (fetched.getAmount() > amount) {
                        fetched.setAmount(amount);
                    }
                    output.setAmount(output.getAmount() - fetched.getAmount());
                } else {
                    // Storage has content, lets add on top of it
                    int additional = Math.min(amount - fetched.getAmount(), output.getAmount());
                    output.setAmount(output.getAmount() - additional);
                    fetched.setAmount(fetched.getAmount() + additional);
                }
            }
            syncBlock(blockMenu.getLocation(), cache);
            return fetched;
        } else {
            // Storage has everything we need
            syncBlock(blockMenu.getLocation(), cache);
            return cache.withdrawItem(amount);
        }
    }

    private static void updateDisplayItem(@Nonnull BlockMenu menu, @Nonnull QuantumCache cache) {
        if (cache.getItemStack() == null) {
            menu.replaceExistingItem(ITEM_SLOT, NO_ITEM);
        } else {
            final ItemStack itemStack = cache.getItemStack().clone();
            final ItemMeta itemMeta = itemStack.getItemMeta();
            final List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

            ItemStack trashItem = cache.isVoidExcess() ? TRASH_ON_ITEM : TRASH_OFF_ITEM;
            menu.replaceExistingItem(TRASH_TOGGLE_SLOT, trashItem);

            lore.add("");
            lore.add(Theme.CLICK_INFO + "满载清空输入: " + Theme.PASSIVE + BooleanHelper.enabledOrDisabled(cache.isVoidExcess()));
            lore.add(Theme.CLICK_INFO + "数量: " + Theme.PASSIVE + cache.getAmount());
            if (cache.supportsCustomMaxAmount()) {
                // Cache limit is set at the potentially custom max amount set
                // The player could set the custom maximum amount to be the actual maximum amount
                lore.add(Theme.CLICK_INFO + "当前容量: " + Theme.SUCCESS + cache.getLimit());
            }
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            itemStack.setAmount(1);
            menu.replaceExistingItem(ITEM_SLOT, itemStack);
            //menu.setPlayerInventoryClickable(false);
            //此代码不可交换在设置后
        }
    }

    private static void syncBlock(@Nonnull Location location, @Nonnull QuantumCache cache) {
        var blockData = StorageCacheUtils.getBlock(location);
        blockData.setData(BS_AMOUNT, String.valueOf(cache.getAmount()));
        blockData.setData(BS_VOID, String.valueOf(cache.isVoidExcess()));
        if (cache.supportsCustomMaxAmount()) {
            BlockStorage.addBlockInfo(location, BS_CUSTOM_MAX_AMOUNT, String.valueOf(cache.getLimit()));
        }
    }

    public static Map<Location, QuantumCache> getCaches() {
        return CACHES;
    }

    public static int[] getSizes() {
        return SIZES;
    }
    private void putBlockData(Block b, int slot, String key, ItemStack displayItem, boolean data) {
        StorageCacheUtils.setData(b.getLocation(), key, String.valueOf(data));
        StorageCacheUtils.getMenu(b.getLocation()).replaceExistingItem(slot, displayItem);
    }

    private void toggleTrash(Block b) {
        BlockMenu blockMenu = StorageCacheUtils.getMenu(b.getLocation());
        QuantumCache cache = CACHES.get(blockMenu.getLocation());
        // 切换状态
        cache.setVoidExcess(!cache.isVoidExcess());
        // 更新界面显示
        updateDisplayItem(blockMenu, cache);
        // 同步数据到区块存储
        syncBlock(b.getLocation(), cache);
        // 更新垃圾桶图标
        ItemStack trashItem = cache.isVoidExcess() ? TRASH_ON_ITEM : TRASH_OFF_ITEM;
        blockMenu.replaceExistingItem(TRASH_TOGGLE_SLOT, trashItem);
    }

    @Override
    public boolean canStack(@Nonnull ItemMeta sfItemMeta, @Nonnull ItemMeta itemMeta) {
        return sfItemMeta.getPersistentDataContainer().equals(itemMeta.getPersistentDataContainer());
    }
}
