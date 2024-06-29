package com.ytdd9527.networks.expansion.core.cargoexpansion.data;

import com.ytdd9527.networks.expansion.core.cargoexpansion.items.storage.StorageUnitData;
import com.ytdd9527.networks.expansion.core.cargoexpansion.items.storage.StorageUnitType;
import io.github.sefiraat.networks.Networks;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class DataStorage {

    private static final DataSource dataSource = Networks.getDataSource();
    private static final Map<Integer, Boolean> state = new ConcurrentHashMap<>(4096);
    private static final Map<Integer, Optional<StorageUnitData>> cache = new ConcurrentHashMap<>(4096);
    private static Map<Integer, Map<Integer, Integer>> changes = new ConcurrentHashMap<>(4096);

    public static void requestStorageData(int id) {
        // First check if loading or already loaded
        if (!state.containsKey(id)) {
            Networks.getQueryQueue().scheduleQuery(() -> {
                loadContainer(id);
                return true;
            });
        }
    }

    public static void restoreFromLocation(Location l, Consumer<Optional<StorageUnitData>> usage) {
        new BukkitRunnable() {
            @Override
            public void run() {
                int id = dataSource.getIdFromLocation(l);
                if (id == -1) {
                    usage.accept(Optional.empty());
                    return;
                }
                if (!isContainerLoaded(id)) {
                    loadContainer(id);
                }
                usage.accept(getCachedStorageData(id));
            }
        }.runTaskAsynchronously(Networks.getInstance());
    }

    @NotNull
    public static Optional<StorageUnitData> getCachedStorageData(int id) {
        return cache.getOrDefault(id, Optional.empty());
    }

    public static int getItemId(ItemStack item) {
        return dataSource.getItemId(item);
    }

    public static synchronized StorageUnitData createStorageUnitData(OfflinePlayer owner, StorageUnitType sizeType, Location placedLocation) {
        StorageUnitData re = new StorageUnitData(dataSource.getNextContainerId(), owner.getUniqueId().toString(), sizeType, true, placedLocation);

        dataSource.saveNewStorageData(re);
        cache.put(re.getId(), Optional.of(re));
        state.put(re.getId(), true);

        return re;
    }

    public static void setContainerStatus(int id, boolean isPlaced) {
        if (isContainerLoaded(id)) {
            getCachedStorageData(id).ifPresent(data -> data.setPlaced(isPlaced));
        }
        dataSource.updateContainer(id, "IsPlaced", String.valueOf(isPlaced ? 1 : 0));
    }

    public static void setContainerSizeType(int id, StorageUnitType type) {
        if (isContainerLoaded(id)) {
            getCachedStorageData(id).ifPresent(data -> data.setSizeType(type));
        }
        dataSource.updateContainer(id, "SizeType", String.valueOf(type.ordinal()));
    }

    public static void setContainerLocation(int id, Location l) {
        if (isContainerLoaded(id)) {
            getCachedStorageData(id).ifPresent(data -> data.setLastLocation(l));
        }
        dataSource.updateContainer(id, "LastLocation", formatLocation(l));
    }

    // This will not update the current data cache!
    public static void addStoredItem(int containerId, int itemId, int amount) {
        dataSource.addStoredItem(containerId, itemId, amount);
    }

    // This will not update the current data cache!
    public static void deleteStoredItem(int containerId, int itemId) {
        dataSource.deleteStoredItem(containerId, itemId);
    }

    // This will not update the current data cache!
    public static void setStoredAmount(int containerId, int itemId, int amount) {
        Map<Integer, Integer> items = changes.computeIfAbsent(containerId, k -> new HashMap<>());
        items.put(itemId, amount);
    }

    public static void saveAmountChange() {

        Map<Integer, Map<Integer, Integer>> lastChanges = changes;
        changes = new ConcurrentHashMap<>();
        for (Map.Entry<Integer, Map<Integer, Integer>> each : lastChanges.entrySet()) {
            for ( Map.Entry<Integer, Integer> eachItem : each.getValue().entrySet()) {
                dataSource.updateItemAmount(each.getKey(),eachItem.getKey(), eachItem.getValue());
            }
        }

    }

    public static boolean isContainerLoaded(int id) {
        if (id == -1) return true;
        return state.getOrDefault(id, false);
    }

    static void setContainerLoaded(int id) {
        state.put(id, true);
    }

    static String formatLocation(Location l) {
        return Objects.requireNonNull(l.getWorld()).getUID() + ";" + l.getBlockX() + ";" + l.getBlockY() + ";" + l.getBlockZ();
    }

    private static void loadContainer(int id) {
        StorageUnitData data = dataSource.getStorageData(id);
        cache.put(id, Optional.ofNullable(data));
        state.put(id, data == null);
    }

}
