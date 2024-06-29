package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.CargoInventory;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.ItemContainer;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.SourcedItemContainer;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.SourcedItemStack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;

public class StorageUnitSource implements CargoInventory {

    private final StorageUnitData data;
    private final Location l;
    private final Set<Material> possibleTypes;
    private final Map<Integer, Integer> matched;
    private final TransportMode mode;
    private final boolean locked;
    private int totalIn = 0;
    private int totalOut = 0;

    public StorageUnitSource(Block b) {
        this.l = b.getLocation();

        // Load data
        locked = CargoStorageUnit.isLocked(l);
        this.data = CargoStorageUnit.getStorageData(l);
        // Skip if no data or location is not matched
        this.mode = (data == null || !l.equals(data.getLastLocation())) ? TransportMode.REJECT : CargoStorageUnit.getTransportMode(l);

        if(mode.canInput()) {
            possibleTypes = new HashSet<>((int)(data.getSizeType().getMaxItemCount()/0.75)+1);
            if (data.getStoredTypeCount() < data.getSizeType().getMaxItemCount()) {
                possibleTypes.add(Material.AIR);
            } else {
                for (ItemContainer each : data.getStoredItems()) {
                    possibleTypes.add(each.getWrapper().getType());
                }
            }
        } else {
            possibleTypes = Collections.emptySet();
        }

        matched = mode.canOutput() ? new HashMap<>() : null;
    }

    @Override
    @NotNull
    public Set<Material> pushItem(SourcedItemContainer container, boolean oneStackMode) {
        if(mode.canInput()) {
            int tryAdd = oneStackMode ? Math.min(container.getAmount(), container.getWrapper().getMaxStackSize()) : container.getAmount();
            int actualAdd = container.removeAmount(data.addStoredItem(container.getSample(), tryAdd, locked));
            totalIn += actualAdd;
            if(actualAdd == tryAdd) {
                possibleTypes.add(container.getWrapper().getType());
            }
        }

        return possibleTypes;
    }

    @Override
    public ItemStack pushBack(ItemStack item, int id) {
        // As there is no way to received call if output is not allowed, skip mode check
        // Update amount
        int oldAmount = matched.remove(id);
        int newAmount = item.getAmount();
        if (oldAmount != newAmount) {
            int diff = oldAmount - newAmount;
            data.removeAmount(id, diff);
            totalOut += diff;
        }
        return null;
    }

    @Override
    @NotNull
    public List<SourcedItemStack> getMatched(Predicate<ItemStack> filter) {
        List<SourcedItemStack> re = new LinkedList<>();

        if (mode.canOutput()) {
            for (ItemContainer each : data.getStoredItems()) {
                if (matched.containsKey(each.getId())) {
                    // This item is processing by other frequency, just skip
                    continue;
                }
                if (filter.test(each.getWrapper())) {
                    ItemStack item = each.getSample();
                    int amount = each.getAmount();
                    if (locked) {
                        // Content lock mode, at least keep 1 for each
                        amount--;
                    }
                    if (amount <= 0) {
                        // Invalid amount, skip this item
                        continue;
                    }
                    item.setAmount(amount);
                    matched.put(each.getId(), amount);
                    re.add(new SourcedItemStack(item, this, each.getId()));
                }
            }
        }

        return re;
    }

    @Override
    @NotNull
    public Location getLocation() {
        return l;
    }

    @Override
    public void update() {
        if (mode.canOutput()) {
            // As the item in matched will be removed in pushback phase,
            // the rest should be deleted as it is not pushed back.
            for (Map.Entry<Integer, Integer> each : matched.entrySet()) {
                totalOut += each.getValue();
                if (locked) {
                    data.setItemAmount(each.getKey(), 1);
                } else {
                    data.removeItem(each.getKey());
                }
            }
        }
        if(totalIn + totalOut > 0) {
            CargoStorageUnit.update(l, new CargoReceipt(data.getId(), totalIn, totalOut, data.getTotalAmount(), data.getStoredTypeCount(), data.getSizeType()), false);
        }
    }
}
