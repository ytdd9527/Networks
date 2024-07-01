package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data.DataStorage;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.ItemContainer;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StorageUnitData {

    private final int id;
    private final OfflinePlayer owner;
    private final Map<Integer, ItemContainer> storedItems;
    private boolean isPlaced;
    private StorageUnitType sizeType;
    private Location lastLocation;

    public StorageUnitData(int id, String ownerUUID, StorageUnitType sizeType, boolean isPlaced, Location lastLocation) {
        this(id,Bukkit.getOfflinePlayer(UUID.fromString(ownerUUID)), sizeType, isPlaced, lastLocation, new HashMap<>());
    }

    public StorageUnitData(int id, String ownerUUID, StorageUnitType sizeType, boolean isPlaced, Location lastLocation, Map<Integer, ItemContainer> storedItems) {
        this(id,Bukkit.getOfflinePlayer(UUID.fromString(ownerUUID)), sizeType, isPlaced, lastLocation, storedItems);
    }

    public StorageUnitData(int id, OfflinePlayer owner, StorageUnitType sizeType, boolean isPlaced, Location lastLocation, Map<Integer, ItemContainer> storedItems) {
        this.id = id;
        this.owner = owner;
        this.sizeType = sizeType;
        this.isPlaced = isPlaced;
        this.lastLocation = lastLocation;
        this.storedItems = storedItems;
    }

    /**
     * Add item to unit, the amount will be the item stack amount
     * @param item: item will be added
     * @return the amount actual added
     */
    public int addStoredItem(ItemStack item, boolean contentLocked) {
        return addStoredItem(item, item.getAmount(), contentLocked);
    }

    /**
     * Add item to unit
     * @param item: item will be added
     * @param amount: amount will be added
     * @return the amount actual added
     */
    public int addStoredItem(ItemStack item, int amount, boolean contentLocked) {
        ItemStackWrapper wrapper = ItemStackWrapper.wrap(item);
        int add = 0;
        boolean isVoidExcess = StorageCacheUtils.getData(getLastLocation(), "voidExcess") != null;
        for (ItemContainer each : storedItems.values()) {
            if(each.isSimilar(wrapper)) {
                // Found existing one, add amount
                if (isVoidExcess) {
                    add = Math.min(amount, sizeType.getEachMaxSize() - each.getAmount());
                    if (add > 0) {
                        each.addAmount(add);
                        DataStorage.setStoredAmount(id, each.getId(), each.getAmount());
                    }
                    return add;
                } else {
                    add = Math.min(amount, sizeType.getEachMaxSize() - each.getAmount());
                    each.addAmount(add);
                    DataStorage.setStoredAmount(id, each.getId(), each.getAmount());
                    return add;
                }
            }
        }
        // If in content locked mode or void excess mode, no new input allowed
        if (contentLocked || StorageCacheUtils.getData(getLastLocation(), "locked") != null) return 0;
        // Not found, new one
        if (storedItems.size() < sizeType.getMaxItemCount()) {
            add = Math.min(amount,sizeType.getEachMaxSize());
            int itemId = DataStorage.getItemId(item);
            storedItems.put(itemId, new ItemContainer(itemId, item, add));
            DataStorage.addStoredItem(id, itemId, add);
            return add;
        }
        return add;
    }

    public synchronized void setPlaced(boolean isPlaced) {
        if (this.isPlaced != isPlaced) {
            this.isPlaced = isPlaced;
            DataStorage.setContainerStatus(id, isPlaced);
        }
    }

    public int getId() {
        return id;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public StorageUnitType getSizeType() {
        return sizeType;
    }

    public synchronized void setSizeType(StorageUnitType sizeType) {
        if (this.sizeType != sizeType) {
            this.sizeType = sizeType;
            DataStorage.setContainerSizeType(id, sizeType);
        }
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void removeItem(int itemId) {
        if (storedItems.remove(itemId) != null) {
            DataStorage.deleteStoredItem(id, itemId);
        }
    }

    public void setItemAmount(int itemId, int amount) {
        if (amount <= 0) {
            // Directly remove
            removeItem(itemId);
            return;
        }
        ItemContainer container = storedItems.get(itemId);
        if (container != null) {
            container.setAmount(amount);
            DataStorage.setStoredAmount(id, itemId, amount);
        }
    }

    public void removeAmount(int itemId, int amount) {
        ItemContainer container = storedItems.get(itemId);
        if (container != null) {
            container.removeAmount(amount);
            if (container.getAmount() <= 0) {
                removeItem(itemId);
                return;
            }
            DataStorage.setStoredAmount(id, itemId, container.getAmount());
        }
    }

    public int getStoredTypeCount() {
        return storedItems.size();
    }

    public synchronized void setLastLocation(Location lastLocation) {
        if (this.lastLocation != lastLocation) {
            this.lastLocation = lastLocation;
            DataStorage.setContainerLocation(id, lastLocation);
        }
    }

    public int getTotalAmount() {
        int re = 0;
        for ( ItemContainer each : storedItems.values()) {
            re += each.getAmount();
        }
        return re;
    }

    public List<ItemContainer> getStoredItems() {
        return new ArrayList<>(storedItems.values());
    }

    public OfflinePlayer getOwner() {
        return owner;
    }

    @Nullable
    public ItemStack requestItem(@NotNull ItemRequest itemRequest) {
        ItemStack item = itemRequest.getItemStack();
        if (item == null) {
            return null;
        }

        int amount = itemRequest.getAmount();
        for (ItemContainer itemContainer: getStoredItems()) {
            int containerAmount = itemContainer.getAmount();
            if (StackUtils.itemsMatch(itemContainer.getSample(), item)) {
                if (StorageCacheUtils.getData(getLastLocation(), "locked") != null) {
                    containerAmount--;
                }
                int take = Math.min(amount, containerAmount);
                if (take <= 0) {
                    break;
                }
                itemContainer.removeAmount(take);
                DataStorage.setStoredAmount(id, itemContainer.getId(), itemContainer.getAmount());
                ItemStack clone = item.clone();
                clone.setAmount(take);
                CargoReceipt receipt = new CargoReceipt(this.id, 0, take, this.getTotalAmount(), this.getStoredTypeCount(), this.sizeType);
                CargoStorageUnit.putRecord(getLastLocation(), receipt);
                return clone;
            }
        }
        return null;
    }

    public void depositItemStack(ItemStack[] itemsToDeposit, boolean contentLocked) {
        for (ItemStack item : itemsToDeposit) {
            depositItemStack(item, contentLocked);
        }
    }

    public static boolean isBlacklisted(@Nonnull ItemStack itemStack) {
        return itemStack.getType() == Material.AIR
                || itemStack.getType().getMaxDurability() < 0
                || Tag.SHULKER_BOXES.isTagged(itemStack.getType())
                || itemStack.getType() == Material.BUNDLE;
    }

    public void depositItemStack(ItemStack itemsToDeposit, boolean contentLocked) {
        if (itemsToDeposit == null || isBlacklisted(itemsToDeposit)) {
            return;
        }
        int actualAdded = addStoredItem(itemsToDeposit, contentLocked);
        itemsToDeposit.setAmount(itemsToDeposit.getAmount() - actualAdded);
        CargoReceipt receipt = new CargoReceipt(this.id, actualAdded, 0, this.getTotalAmount(), this.getStoredTypeCount(), this.sizeType);
        CargoStorageUnit.putRecord(getLastLocation(), receipt);
    }

    public String toString() {
        return "StorageUnitData{" +
                "id=" + id +
                ", owner=" + owner +
                ", storedItems=" + storedItems +
                ", isPlaced=" + isPlaced +
                ", sizeType=" + sizeType +
                ", lastLocation=" + lastLocation +
                '}';
    }
}
