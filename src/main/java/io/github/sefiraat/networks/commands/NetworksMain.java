package io.github.sefiraat.networks.commands;

import io.github.sefiraat.networks.network.stackcaches.BlueprintInstance;
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

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if (sender instanceof Player player) {

            if (args.length == 0) {
                return false;
            }

            if (args[0].equalsIgnoreCase("fillquantum")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.fillquantum")) && args.length >= 2) {
                    try {
                        int number = Integer.parseInt(args[1]);
                        fillQuantum(player, number);
                        return true;
                    } catch (NumberFormatException exception) {
                        return false;
                    }
                }
            }
            if (args[0].equalsIgnoreCase("fixblueprint")) {
                if ((player.isOp() || player.hasPermission("networks.admin") || player.hasPermission("networks.commands.fixblueprint"))) {
                    if (args.length >= 2) {
                        String before = args[1];
                        fixBlueprint(player, before);
                        return true;
                    }
                }
            }
        } else {
            sender.sendMessage(Theme.ERROR + "只有玩家才能执行该命令");
            return false;
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

    @SuppressWarnings("deprecation")
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

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> raw = onTabCompleteRaw(args);
        return StringUtil.copyPartialMatches(args[args.length - 1], raw, new ArrayList<>());
    }

    public @NotNull List<String> onTabCompleteRaw(@NotNull String[] args) {
        if (args.length == 1) {
            return List.of("fillquantum", "fixblueprint");
        } else if (args.length == 2) {
            return switch (args[0]) {
                case "fillquantum" -> List.of("<amount>");
                case "fixblueprint" -> List.of("<keyInMeta>");
                default -> new ArrayList<>();
            };
        }
        return new ArrayList<>();
    }
}
