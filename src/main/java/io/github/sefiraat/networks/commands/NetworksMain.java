package io.github.sefiraat.networks.commands;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data.DataStorage;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage.CargoStorageUnit;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage.StorageUnitData;
import com.ytdd9527.networks.expansion.setup.ExpansionItemStacks;
import io.github.sefiraat.networks.network.stackcaches.BlueprintInstance;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.network.stackcaches.QuantumCache;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.slimefun.tools.CraftingBlueprint;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.Theme;
import io.github.sefiraat.networks.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.networks.utils.datatypes.PersistentCraftingBlueprintType;
import io.github.sefiraat.networks.utils.datatypes.PersistentQuantumStorageType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;

public class NetworksMain implements TabExecutor {

    private static final Map<Integer, NetworkQuantumStorage> QUANTUM_REPLACEMENT_MAP = new HashMap<>();

    static {
        QUANTUM_REPLACEMENT_MAP.put(4096, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_1);
        QUANTUM_REPLACEMENT_MAP.put(32768, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_2);
        QUANTUM_REPLACEMENT_MAP.put(262144, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_3);
        QUANTUM_REPLACEMENT_MAP.put(2097152, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_4);
        QUANTUM_REPLACEMENT_MAP.put(16777216, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_5);
        QUANTUM_REPLACEMENT_MAP.put(134217728, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_6);
        QUANTUM_REPLACEMENT_MAP.put(1073741824, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_7);
        QUANTUM_REPLACEMENT_MAP.put(Integer.MAX_VALUE, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_8);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        switch (args[0]) {
            case "fillquantum":
            case "fixblueprint":
            case "addstorageitem":
            case "reducestorageitem":
            case "setquantum":
            case "restore":
                if (!(sender instanceof Player)) {
                    sender.sendMessage(Theme.ERROR + "只有玩家才能执行该命令");
                    return false;
                }
        }
        if (sender instanceof Player player) {
            if (args.length == 0) {
                return false;
            }

            if (args[0].equalsIgnoreCase("fillquantum")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.fillquantum")) && args.length >= 2) {
                    if (args.length >= 2) {
                        try {
                            int number = Integer.parseInt(args[1]);
                            fillQuantum(player, number);
                            return true;
                        } catch (NumberFormatException exception) {
                            player.sendMessage(Theme.ERROR + "Wrong argument: <amount>. Range: 0 ~ 2147483647");
                        }
                    } else {
                        player.sendMessage(Theme.ERROR + "Missing argument: <amount>");
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "你没有权限执行该命令");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("fixblueprint")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.fixblueprint"))) {
                    if (args.length >= 2) {
                        String before = args[1];
                        fixBlueprint(player, before);
                    } else {
                        player.sendMessage(Theme.ERROR + "Missing argument: <keyInMeta>");
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "你没有权限执行该命令");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("restore")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.restore"))) {
                    restore(player);
                } else {
                    player.sendMessage(Theme.ERROR + "你没有权限执行该命令");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("setQuantum")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.setquantum"))) {
                    if (args.length >= 2) {
                        setQuantum(player, Integer.parseInt(args[1]));
                    } else {
                        player.sendMessage(Theme.ERROR + "Missing argument: <amount>");
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "你没有权限执行该命令");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("addstorage")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.addstorage"))) {
                    if (args.length >= 2) {
                        int amount = Integer.parseInt(args[1]);
                        addStorageItem(player, amount);
                    } else {
                        player.sendMessage(Theme.ERROR + "Missing argument: <amount>");
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "你没有权限执行该命令");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("reducestorage")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.reducestorage"))) {
                    if (args.length >= 2) {
                        int amount = Integer.parseInt(args[1]);
                        reduceStorageItem(player, amount);
                    } else {
                        player.sendMessage(Theme.ERROR + "Missing argument: <amount>");
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "你没有权限执行该命令");
                }
                return true;
            }
        } else {
            // 非玩家执行命令时的检测
        }
        return true;
    }

    public void fillQuantum(Player player, int amount) {
        final ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            player.sendMessage(Theme.ERROR + "你必须手持量子存储");
            return;
        }

        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if (!(slimefunItem instanceof NetworkQuantumStorage)) {
            player.sendMessage(Theme.ERROR + "你手中的物品必须为量子存储");
            return;
        }

        ItemMeta meta = itemStack.getItemMeta();
        final QuantumCache quantumCache = DataTypeMethods.getCustom(
                meta,
                Keys.QUANTUM_STORAGE_INSTANCE,
                PersistentQuantumStorageType.TYPE
        );

        if (quantumCache == null || quantumCache.getItemStack() == null) {
            player.sendMessage(Theme.ERROR + "量子存储未指定物品或已损坏");
            return;
        }

        quantumCache.setAmount(amount);
        DataTypeMethods.setCustom(meta, Keys.QUANTUM_STORAGE_INSTANCE, PersistentQuantumStorageType.TYPE, quantumCache);
        quantumCache.updateMetaLore(meta);
        itemStack.setItemMeta(meta);
        player.sendMessage(Theme.SUCCESS + "已更新物品");
    }

    public void fixBlueprint(Player player, String before) {
        ItemStack blueprint = player.getInventory().getItemInMainHand();
        if (blueprint == null || blueprint.getType() == Material.AIR) {
            player.sendMessage(Theme.ERROR + "你必须手持蓝图");
            return;
        }

        final SlimefunItem item = SlimefunItem.getByItem(blueprint);

        if (!(item instanceof CraftingBlueprint)) {
            player.sendMessage(Theme.ERROR + "你必须手持网络蓝图");
            return;
        }

        ItemMeta blueprintMeta = blueprint.getItemMeta();

        final Optional<BlueprintInstance> optional = DataTypeMethods.getOptionalCustom(
                blueprintMeta,
                new NamespacedKey(before, "ntw_blueprint"),
                PersistentCraftingBlueprintType.TYPE
        );

        if (optional.isEmpty()) {
            player.sendMessage(Theme.ERROR + "无法获取 instance");
            return;
        }

        BlueprintInstance instance = optional.get();

        ItemStack fix = NetworksSlimefunItemStacks.CRAFTING_BLUEPRINT.clone();
        CraftingBlueprint.setBlueprint(fix, instance.getRecipeItems(), instance.getItemStack());

        blueprint.setItemMeta(fix.getItemMeta());

        player.sendMessage(Theme.SUCCESS + "已修复蓝图");

        return;
    }

    public static void restore(Player p) {
        Block target = p.getTargetBlockExact(5);
        if (target == null || target.getType().isAir()) {
            p.sendMessage(ChatColor.RED+"请指向一个失效的货运存储单元");
        }
        Location l = target.getLocation();
        SlimefunBlockData blockData = StorageCacheUtils.getBlock(l);
        if (blockData != null) {
            String id = blockData.getData("containerId");
            if(id != null) {
                p.sendMessage(ChatColor.RED+"该单元的数据正常，无需恢复。");
            }
        }
        p.sendMessage(ChatColor.GREEN+"正在查询，请稍候...");
        DataStorage.restoreFromLocation(l, opData -> {
            if (opData.isPresent()) {
                StorageUnitData data = opData.get();
                String sfId = ExpansionItemStacks.getStorageItemFromType(data.getSizeType()).getItemId();
                CargoStorageUnit.addBlockInfo(l, data.getId());
                Slimefun.getDatabaseManager().getBlockDataController().createBlock(l, sfId);
                p.sendMessage(ChatColor.GREEN+"已成功恢复！");
            } else {
                p.sendMessage(ChatColor.RED+"未找到数据。");
            }
        });
    }

    public static void setQuantum(Player player, int amount) {
        Block targetBlock = player.getTargetBlockExact(8, FluidCollisionMode.NEVER);
        final ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你必须手持物品才能执行该指令!");
            return;
        }

        if (targetBlock == null || targetBlock.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你必须指着一个网络存储才能执行该指令!");
            return;
        }

        SlimefunBlockData blockData = StorageCacheUtils.getBlock(targetBlock.getLocation());
        if (blockData == null) {
            player.sendMessage(ChatColor.RED + "你必须指着一个网络存储才能执行该指令!");
            return;
        }

        SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(targetBlock.getLocation());
        if (slimefunItem == null) {
            player.sendMessage(ChatColor.RED + "你必须指着一个网络存储才能执行该指令!");
            return;
        }

        Location targetLocation = targetBlock.getLocation();
        ItemStack clone = itemInHand.clone();
        if (slimefunItem instanceof NetworkQuantumStorage) {
            BlockMenu blockMenu = StorageCacheUtils.getMenu(targetLocation);
            if (blockMenu == null) {
                player.sendMessage(Theme.ERROR + "Cannot set item for air");
                return;
            }

            NetworkQuantumStorage.setItem(blockMenu, clone, amount);
        } else {
            player.sendMessage(ChatColor.RED + "你必须指着一个网络存储才能执行该指令!");
            return;
        }
    }

    private static void addStorageItem(Player player, int amount) {
        Block targetBlock = player.getTargetBlockExact(8, FluidCollisionMode.NEVER);
        final ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你必须手持物品才能执行该指令!");
            return;
        }

        if (targetBlock == null || targetBlock.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你必须指着一个货运存储才能执行该指令!");
            return;
        }

        SlimefunBlockData blockData = StorageCacheUtils.getBlock(targetBlock.getLocation());
        if (blockData == null) {
            player.sendMessage(ChatColor.RED + "你必须指着一个货运存储才能执行该指令!");
            return;
        }

        SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(targetBlock.getLocation());
        if (slimefunItem == null) {
            player.sendMessage(ChatColor.RED + "你必须指着一个货运存储才能执行该指令!");
            return;
        }

        Location targetLocation = targetBlock.getLocation();
        ItemStack clone = itemInHand.clone();
        if (slimefunItem instanceof CargoStorageUnit) {
            StorageUnitData data = CargoStorageUnit.getStorageData(targetLocation);
            if (data == null) {
                player.sendMessage(Theme.ERROR + "该存储单元不存在或已损坏!");
                return;
            }

            clone.setAmount(amount);
            data.depositItemStack(clone, false);

        }
    }

    private static void reduceStorageItem(Player player, int amount) {
        Block targetBlock = player.getTargetBlockExact(8, FluidCollisionMode.NEVER);
        final ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你必须手持物品才能执行该指令!");
            return;
        }

        if (targetBlock == null || targetBlock.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你必须指着一个货运存储才能执行该指令!");
            return;
        }

        SlimefunBlockData blockData = StorageCacheUtils.getBlock(targetBlock.getLocation());
        if (blockData == null) {
            player.sendMessage(ChatColor.RED + "你必须指着一个货运存储才能执行该指令!");
            return;
        }

        SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(targetBlock.getLocation());
        if (slimefunItem == null) {
            player.sendMessage(ChatColor.RED + "你必须指着一个货运存储才能执行该指令!");
            return;
        }

        Location targetLocation = targetBlock.getLocation();
        ItemStack clone = itemInHand.clone();
        if (slimefunItem instanceof CargoStorageUnit) {
            StorageUnitData data = CargoStorageUnit.getStorageData(targetLocation);
            if (data == null) {
                player.sendMessage(Theme.ERROR + "该存储单元不存在或已损坏!");
                return;
            }

            clone.setAmount(1);
            data.requestItem(new ItemRequest(clone, amount));

        }
    }

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> raw = onTabCompleteRaw(args);
        return StringUtil.copyPartialMatches(args[args.length - 1], raw, new ArrayList<>());
    }

    public @NotNull List<String> onTabCompleteRaw(@NotNull String[] args) {
        if (args.length == 1) {
            return List.of("fillquantum", "fixblueprint", "restore");
        } else if (args.length == 2) {
            return switch (args[0]) {
                case "fillquantum" -> List.of("<amount>");
                case "fixblueprint" -> List.of("<keyInMeta>");
                case "addstorageitem"  -> List.of("<amount>");
                case "reducestorageitem"  -> List.of("<amount>");
                case "setquantum" -> List.of("<amount>");
                case "restore" -> List.of();
                default -> List.of();
            };
        }
        return new ArrayList<>();
    }
}
