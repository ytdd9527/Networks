package io.github.sefiraat.networks.network;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage.CargoStorageUnit;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage.StorageUnitData;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects.ItemContainer;
import com.ytdd9527.networks.expansion.core.item.machine.network.advanced.AdvancedGreedyBlock;
import io.github.mooy1.infinityexpansion.items.storage.StorageUnit;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.barrel.InfinityBarrel;
import io.github.sefiraat.networks.network.barrel.NetworkStorage;
import io.github.sefiraat.networks.network.stackcaches.BarrelIdentity;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.network.stackcaches.QuantumCache;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.slimefun.network.NetworkGreedyBlock;
import io.github.sefiraat.networks.slimefun.network.NetworkPowerNode;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkRoot extends NetworkNode {

    private final Set<Location> nodeLocations = new HashSet<>();
    private final int maxNodes;
    private boolean isOverburdened = false;

    private final Set<Location> bridges = ConcurrentHashMap.newKeySet();
    private final Set<Location> monitors = ConcurrentHashMap.newKeySet();
    private final Set<Location> importers = ConcurrentHashMap.newKeySet();
    private final Set<Location> exporters = ConcurrentHashMap.newKeySet();
    private final Set<Location> grids = ConcurrentHashMap.newKeySet();
    private final Set<Location> cells = ConcurrentHashMap.newKeySet();
    private final Set<Location> wipers = ConcurrentHashMap.newKeySet();
    private final Set<Location> grabbers = ConcurrentHashMap.newKeySet();
    private final Set<Location> pushers = ConcurrentHashMap.newKeySet();
    private final Set<Location> purgers = ConcurrentHashMap.newKeySet();
    private final Set<Location> crafters = ConcurrentHashMap.newKeySet();
    private final Set<Location> powerNodes = ConcurrentHashMap.newKeySet();
    private final Set<Location> powerDisplays = ConcurrentHashMap.newKeySet();
    private final Set<Location> encoders = ConcurrentHashMap.newKeySet();
    private final Set<Location> greedyBlocks = ConcurrentHashMap.newKeySet();
    private final Set<Location> cutters = ConcurrentHashMap.newKeySet();
    private final Set<Location> pasters = ConcurrentHashMap.newKeySet();
    private final Set<Location> vacuums = ConcurrentHashMap.newKeySet();
    private final Set<Location> wirelessTransmitters = ConcurrentHashMap.newKeySet();
    private final Set<Location> wirelessReceivers = ConcurrentHashMap.newKeySet();

    private final Set<Location> chainPushers = ConcurrentHashMap.newKeySet();
    private final Set<Location> chainGrabbers = ConcurrentHashMap.newKeySet();
    private final Set<Location> chainDispatchers = ConcurrentHashMap.newKeySet();
    private final Set<Location> advancedImporters = ConcurrentHashMap.newKeySet();
    private final Set<Location> advancedExporters = ConcurrentHashMap.newKeySet();

    private final Set<Location> advancedGreedyBlocks = ConcurrentHashMap.newKeySet();
    private final Set<Location> coordinateTransmitters = ConcurrentHashMap.newKeySet();
    private final Set<Location> coordinateReceivers = ConcurrentHashMap.newKeySet();


    private final Set<Location> powerOutlets = ConcurrentHashMap.newKeySet();
    private Set<BarrelIdentity> barrels = null;

    private Set<StorageUnitData> cargoStorageUnits = null;

    private long rootPower = 0;

    private boolean displayParticles = false;

    public NetworkRoot(@Nonnull Location location, @Nonnull NodeType type, int maxNodes) {
        super(location, type);
        this.maxNodes = maxNodes;
        this.root = this;
        NetworkNode node = new NetworkNode(location, NodeType.CONTROLLER);

        io.github.sefiraat.networks.NetworkStorage.getAllNetworkObjects().get(location).setNode(node);
    }

    public void registerNode(@Nonnull Location location, @Nonnull NodeType type) {
        nodeLocations.add(location);
        switch (type) {
            case CONTROLLER -> {
                // Nothing here guvnor
            }
            case BRIDGE -> bridges.add(location);
            case STORAGE_MONITOR -> monitors.add(location);
            case IMPORT -> importers.add(location);
            case EXPORT -> exporters.add(location);
            case GRID -> grids.add(location);
            case CELL -> cells.add(location);
            case WIPER -> wipers.add(location);
            case GRABBER -> grabbers.add(location);
            case PUSHER -> pushers.add(location);
            case PURGER -> purgers.add(location);
            case CRAFTER -> crafters.add(location);
            case POWER_NODE -> powerNodes.add(location);
            case POWER_DISPLAY -> powerDisplays.add(location);
            case ENCODER -> encoders.add(location);
            case GREEDY_BLOCK -> greedyBlocks.add(location);
            case CUTTER -> cutters.add(location);
            case PASTER -> pasters.add(location);
            case VACUUM -> vacuums.add(location);
            case WIRELESS_TRANSMITTER -> wirelessTransmitters.add(location);
            case WIRELESS_RECEIVER -> wirelessReceivers.add(location);
            case POWER_OUTLET -> powerOutlets.add(location);
            // from networks expansion
            case CHAIN_PUSHER -> chainPushers.add(location);
            case CHAIN_PUSHER_PLUS -> chainPushers.add(location);
            case CHAIN_GRABBER -> chainGrabbers.add(location);
            case CHAIN_GRABBER_PLUS -> chainGrabbers.add(location);
            case NEA_IMPORT -> advancedImporters.add(location);
            case NEA_EXPORT -> advancedExporters.add(location);
            case NEA_GREEDY_BLOCK -> advancedGreedyBlocks.add(location);
            case COORDINATE_TRANSMITTER ->coordinateTransmitters.add(location);
            case NE_COORDINATE_RECEIVER ->coordinateReceivers.add(location);
            case CHAIN_DISPATCHER -> chainDispatchers.add(location);
        }
    }

    public Set<Location> getNodeLocations() {
        return this.nodeLocations;
    }

    public int getMaxNodes() {
        return maxNodes;
    }

    public int getNodeCount() {
        return this.nodeLocations.size();
    }

    public boolean isOverburdened() {
        return isOverburdened;
    }

    public void setOverburdened(boolean overburdened) {
        if (overburdened && !isOverburdened) {
            final Location loc = this.nodePosition.clone();
            for (int x = 0; x <= 1; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = 0; z <= 1; z++) {
                        loc.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, loc.clone().add(x, y, z), 0);
                    }
                }
            }
        }
        this.isOverburdened = overburdened;
    }

    public Set<Location> getBridges() {
        return this.bridges;
    }

    public Set<Location> getMonitors() {
        return this.monitors;
    }

    public Set<Location> getImporters() {
        return this.importers;
    }

    public Set<Location> getExporters() {
        return this.exporters;
    }

    public Set<Location> getGrids() {
        return this.grids;
    }

    public Set<Location> getCells() {
        return this.cells;
    }

    public Set<Location> getWipers() {
        return this.wipers;
    }

    public Set<Location> getGrabbers() {
        return this.grabbers;
    }

    public Set<Location> getPushers() {
        return this.pushers;
    }

    public Set<Location> getPurgers() {
        return this.purgers;
    }

    public Set<Location> getCrafters() {
        return this.crafters;
    }

    public Set<Location> getPowerNodes() {
        return this.powerNodes;
    }

    public Set<Location> getPowerDisplays() {
        return this.powerDisplays;
    }

    public Set<Location> getEncoders() {
        return this.encoders;
    }
    public Set<Location> getCutters() {
        return this.cutters;
    }

    public Set<Location> getPasters() {
        return this.pasters;
    }

    public Set<Location> getVacuums() {
        return this.vacuums;
    }

    public Set<Location> getWirelessTransmitters() {
        return this.wirelessTransmitters;
    }

    public Set<Location> getWirelessReceivers() {
        return this.wirelessReceivers;
    }

    public Set<Location> getPowerOutlets() {
        return this.powerOutlets;
    }


    public Set<Location> getChainPushers() {
        return this.chainPushers;
    }

    public Set<Location> getChainGrabbers() {
        return this.chainGrabbers;
    }

    public Set<Location> getAdvancedImports() {
        return this.advancedImporters;
    }

    public Set<Location> getAdvancedExports() {
        return this.advancedExporters;
    }

    public Set<Location> getCoordinateTransmitters() {
        return this.coordinateTransmitters;
    }

    public Set<Location> getCoordinateReceivers() {
        return this.coordinateReceivers;
    }

    public Set<Location> getChainDispatchers() {
        return this.chainDispatchers;
    }
    @SuppressWarnings("deprecation")
    @Nonnull
    public Map<ItemStack, Long> getAllNetworkItems() {
        final Map<ItemStack, Long> itemStacks = new HashMap<>();

        // Barrels
        for (BarrelIdentity barrelIdentity : getBarrels()) {
            final Long currentAmount = itemStacks.get(barrelIdentity.getItemStack());
            final long newAmount;
            if (currentAmount == null) {
                newAmount = barrelIdentity.getAmount();
            } else {
                long newLong = (long) currentAmount + (long) barrelIdentity.getAmount();
                if (newLong < 0) {
                    newAmount = 0;
                } else {
                    newAmount = currentAmount + barrelIdentity.getAmount();
                }
            }
            itemStacks.put(barrelIdentity.getItemStack(), newAmount);
        }

        // Cargo storage units
        for (StorageUnitData cache : getCargoStorageUnits()) {
            for (ItemContainer itemContainer : cache.getStoredItems()) {
                final Long currentAmount = itemStacks.get(itemContainer.getSample());
                final long newAmount;
                if (currentAmount == null) {
                    newAmount = itemContainer.getAmount();
                } else {
                    long newLong = (long) currentAmount + (long) itemContainer.getAmount();
                    if (newLong < 0) {
                        newAmount = 0;
                    } else {
                        newAmount = currentAmount + itemContainer.getAmount();
                    }
                }
                itemStacks.put(itemContainer.getSample(), newAmount);
            }
        }

        for (BlockMenu blockMenu : getAdvancedGreedyBlocks()) {
            for (int slot : AdvancedGreedyBlock.INPUT_SLOTS) {
                final ItemStack itemStack = blockMenu.getItemInSlot(slot);
                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    continue;
                }
                final ItemStack clone = StackUtils.getAsQuantity(itemStack, 1);
                final Long currentAmount = itemStacks.get(clone);
                final long newAmount;
                if (currentAmount == null) {
                    newAmount = itemStack.getAmount();
                } else {
                    long newLong = (long) currentAmount + (long) itemStack.getAmount();
                    if (newLong < 0) {
                        newAmount = 0;
                    } else {
                        newAmount = currentAmount + itemStack.getAmount();
                    }
                }
                itemStacks.put(clone, newAmount);
            }
        }

        for (BlockMenu blockMenu : getGreedyBlocks()) {
            final ItemStack itemStack = blockMenu.getItemInSlot(NetworkGreedyBlock.INPUT_SLOT);
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            final ItemStack clone = StackUtils.getAsQuantity(itemStack, 1);
            final Long currentAmount = itemStacks.get(clone);
            final long newAmount;
            if (currentAmount == null) {
                newAmount = itemStack.getAmount();
            } else {
                long newLong = (long) currentAmount + (long) itemStack.getAmount();
                if (newLong < 0) {
                    newAmount = 0;
                } else {
                    newAmount = currentAmount + itemStack.getAmount();
                }
            }
            itemStacks.put(clone, newAmount);
        }

        for (BlockMenu blockMenu : getCrafterOutputs()) {
            int[] slots = blockMenu.getPreset().getSlotsAccessedByItemTransport(ItemTransportFlow.WITHDRAW);
            for (int slot : slots) {
                final ItemStack itemStack = blockMenu.getItemInSlot(slot);
                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    continue;
                }
                final ItemStack clone = StackUtils.getAsQuantity(itemStack, 1);
                final Long currentAmount = itemStacks.get(clone);
                final long newAmount;
                if (currentAmount == null) {
                    newAmount = itemStack.getAmount();
                } else {
                    long newLong = (long) currentAmount + (long) itemStack.getAmount();
                    if (newLong < 0) {
                        newAmount = 0;
                    } else {
                        newAmount = currentAmount + itemStack.getAmount();
                    }
                }
                itemStacks.put(clone, newAmount);
            }
        }

        for (BlockMenu blockMenu : getCellMenus()) {
            for (ItemStack itemStack : blockMenu.getContents()) {
                if (itemStack != null && itemStack.getType() != Material.AIR) {
                    final ItemStack clone = itemStack.clone();

                    clone.setAmount(1);

                    final Long currentAmount = itemStacks.get(clone);
                    long newAmount;

                    if (currentAmount == null) {
                        newAmount = itemStack.getAmount();
                    } else {
                        long newLong = (long) currentAmount + (long) itemStack.getAmount();
                        if (newLong < 0) {
                            newAmount = 0;
                        } else {
                            newAmount = currentAmount + itemStack.getAmount();
                        }
                    }

                    itemStacks.put(clone, newAmount);
                }
            }
        }
        return itemStacks;
    }

    @Nonnull
    public Set<BarrelIdentity> getBarrels() {

        if (this.barrels != null) {
            return this.barrels;
        }

        final Set<Location> addedLocations = ConcurrentHashMap.newKeySet();
        final Set<BarrelIdentity> barrelSet = ConcurrentHashMap.newKeySet();

        for (Location cellLocation : this.monitors) {
            final BlockFace face = NetworkDirectional.getSelectedFace(cellLocation);

            if (face == null) {
                continue;
            }

            final Location testLocation = cellLocation.clone().add(face.getDirection());

            if (addedLocations.contains(testLocation)) {
                continue;
            } else {
                addedLocations.add(testLocation);
            }

            final SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(testLocation);

            if (Networks.getSupportedPluginManager()
                    .isInfinityExpansion() && slimefunItem instanceof StorageUnit unit) {
                final BlockMenu menu = StorageCacheUtils.getMenu(testLocation);
                final InfinityBarrel infinityBarrel = getInfinityBarrel(menu, unit);
                if (infinityBarrel != null) {
                    barrelSet.add(infinityBarrel);
                }
                continue;
            }

            if (slimefunItem instanceof NetworkQuantumStorage) {
                final BlockMenu menu = StorageCacheUtils.getMenu(testLocation);
                final NetworkStorage storage = getNetworkStorage(menu);
                if (storage != null) {
                    barrelSet.add(storage);
                }
            }
        }

        this.barrels = barrelSet;
        return barrelSet;
    }

    @Nonnull
    public Set<StorageUnitData> getCargoStorageUnits() {
        if (this.cargoStorageUnits != null) {
            return this.cargoStorageUnits;
        }

        final Set<Location> addedLocations = ConcurrentHashMap.newKeySet();
        final Set<StorageUnitData> unitSet = ConcurrentHashMap.newKeySet();

        for (Location cellLocation : this.monitors) {
            final BlockFace face = NetworkDirectional.getSelectedFace(cellLocation);

            if (face == null) {
                continue;
            }

            final Location testLocation = cellLocation.clone().add(face.getDirection());

            if (addedLocations.contains(testLocation)) {
                continue;
            } else {
                addedLocations.add(testLocation);
            }

            final SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(testLocation);

            if (slimefunItem instanceof CargoStorageUnit) {
                final BlockMenu menu = StorageCacheUtils.getMenu(testLocation);
                if (menu != null) {
                    final StorageUnitData storage = getCargoStorageUnitData(menu);
                    if (storage != null) {
                        unitSet.add(storage);
                    }
                }
            }
        }

        this.cargoStorageUnits = unitSet;
        return unitSet;
    }

    @Nullable
    private InfinityBarrel getInfinityBarrel(@Nonnull BlockMenu blockMenu, @Nonnull StorageUnit storageUnit) {
        final ItemStack itemStack = blockMenu.getItemInSlot(16);
        final var data = StorageCacheUtils.getBlock(blockMenu.getLocation());
        final String storedString = data.getData("stored");

        if (storedString == null) {
            return null;
        }

        final int storedInt = Integer.parseInt(storedString);

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return null;
        }

        final io.github.mooy1.infinityexpansion.items.storage.StorageCache cache = storageUnit.getCache(blockMenu.getLocation());

        if (cache == null) {
            return null;
        }

        final ItemStack clone = itemStack.clone();
        clone.setAmount(1);

        return new InfinityBarrel(
                blockMenu.getLocation(),
                clone,
                storedInt + itemStack.getAmount(),
                cache
        );
    }

    @Nullable
    private NetworkStorage getNetworkStorage(@Nonnull BlockMenu blockMenu) {

        final QuantumCache cache = NetworkQuantumStorage.getCaches().get(blockMenu.getLocation());

        if (cache == null || cache.getItemStack() == null) {
            return null;
        }

        final ItemStack output = blockMenu.getItemInSlot(NetworkQuantumStorage.OUTPUT_SLOT);
        final ItemStack itemStack = cache.getItemStack();
        long storedInt = cache.getAmount();
        if (output != null && output.getType() != Material.AIR && StackUtils.itemsMatch(cache, output, true)) {
            storedInt = storedInt + output.getAmount();
        }

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return null;
        }

        final ItemStack clone = itemStack.clone();
        clone.setAmount(1);

        return new NetworkStorage(
                blockMenu.getLocation(),
                clone,
                storedInt
        );
    }

    @Nullable
    private StorageUnitData getCargoStorageUnitData(@Nonnull BlockMenu blockMenu) {

        final StorageUnitData cache = CargoStorageUnit.getStorageData(blockMenu.getLocation());

        if (cache == null) {
            return null;
        }

        for (ItemContainer itemContainer : cache.getStoredItems()) {
            final ItemStack itemStack = itemContainer.getSample();
            long storedInt = itemContainer.getAmount();
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                storedInt = storedInt + itemStack.getAmount();
            }

            if (itemStack == null || itemStack.getType() == Material.AIR) {
                return null;
            }

            itemStack.setAmount(1);
        }

        return cache;
    }

    @Nonnull
    public Set<BlockMenu> getCellMenus() {
        final Set<BlockMenu> menus = new HashSet<>();
        for (Location cellLocation : this.cells) {
            BlockMenu menu = StorageCacheUtils.getMenu(cellLocation);
            if (menu != null) {
                menus.add(menu);
            }
        }
        return menus;
    }

    @Nonnull
    public Set<BlockMenu> getCrafterOutputs() {
        final Set<BlockMenu> menus = new HashSet<>();
        for (Location location : this.crafters) {
            BlockMenu menu = StorageCacheUtils.getMenu(location);
            if (menu != null) {
                menus.add(menu);
            }
        }
        return menus;
    }

    @Nonnull
    public Set<BlockMenu> getGreedyBlocks() {
        final Set<BlockMenu> menus = new HashSet<>();
        for (Location location : this.greedyBlocks) {
            BlockMenu menu = StorageCacheUtils.getMenu(location);
            if (menu != null) {
                menus.add(menu);
            }
        }
        return menus;
    }

    @Nonnull
    public Set<BlockMenu> getAdvancedGreedyBlocks() {
        final Set<BlockMenu> menus = new HashSet<>();
        for (Location location : this.advancedGreedyBlocks) {
            BlockMenu menu = StorageCacheUtils.getMenu(location);
            if (menu != null) {
                menus.add(menu);
            }
        }
        return menus;
    }

    /**
     * Checks the Network's exposed items and removes items matching the request up
     * to the amount requested. Items are withdrawn in this order:
     * <p>
     * Deep Storages (Barrels)
     * Crafters
     * Cargo Storage Units
     * Advanced Greedy Blocks
     * Greedy Blocks
     * Cells
     *
     * @param request The {@link ItemRequest} being requested from the Network
     * @return The {@link ItemStack} matching the request with as many as could be found. Null if none.
     */
    @Nullable
    public ItemStack getItemStack(@Nonnull ItemRequest request) {
        ItemStack stackToReturn = null;

        // Barrels first
        for (BarrelIdentity barrelIdentity : getBarrels()) {

            final ItemStack itemStack = barrelIdentity.getItemStack();

            if (itemStack == null || !StackUtils.itemsMatch(request, itemStack, true)) {
                continue;
            }

            boolean infinity = barrelIdentity instanceof InfinityBarrel;
            final ItemStack fetched = barrelIdentity.requestItem(request);
            if (fetched == null || fetched.getType() == Material.AIR || (infinity && fetched.getAmount() == 1)) {
                continue;
            }

            // Stack is null, so we can fill it here
            if (stackToReturn == null) {
                stackToReturn = fetched.clone();
                stackToReturn.setAmount(1);
                request.receiveAmount(1);
                fetched.setAmount(fetched.getAmount() - 1);
            }

            // Escape if fulfilled request
            if (request.getAmount() <= 0) {
                return stackToReturn;
            }

            final int preserveAmount = infinity ? fetched.getAmount() - 1 : fetched.getAmount();

            if (request.getAmount() <= preserveAmount) {
                stackToReturn.setAmount(stackToReturn.getAmount() + request.getAmount());
                fetched.setAmount(fetched.getAmount() - request.getAmount());
                return stackToReturn;
            } else {
                stackToReturn.setAmount(stackToReturn.getAmount() + preserveAmount);
                request.receiveAmount(preserveAmount);
                fetched.setAmount(fetched.getAmount() - preserveAmount);
            }
        }

        // Crafters
        for (BlockMenu blockMenu : getCrafterOutputs()) {
            int[] slots = blockMenu.getPreset().getSlotsAccessedByItemTransport(ItemTransportFlow.WITHDRAW);
            for (int slot : slots) {
                final ItemStack itemStack = blockMenu.getItemInSlot(slot);
                if (itemStack == null || itemStack.getType() == Material.AIR || !StackUtils.itemsMatch(
                        request,
                        itemStack,
                        true
                )) {
                    continue;
                }

                // Stack is null, so we can fill it here
                if (stackToReturn == null) {
                    stackToReturn = itemStack.clone();
                    stackToReturn.setAmount(1);
                    request.receiveAmount(1);
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }

                // Escape if fulfilled request
                if (request.getAmount() <= 0) {
                    return stackToReturn;
                }

                if (request.getAmount() <= itemStack.getAmount()) {
                    stackToReturn.setAmount(stackToReturn.getAmount() + request.getAmount());
                    itemStack.setAmount(itemStack.getAmount() - request.getAmount());
                    return stackToReturn;
                } else {
                    stackToReturn.setAmount(stackToReturn.getAmount() + itemStack.getAmount());
                    request.receiveAmount(itemStack.getAmount());
                    itemStack.setAmount(0);
                }
            }
        }

        // Units
        for (StorageUnitData cache: getCargoStorageUnits()) {
            final List<ItemContainer> storedItems = cache.getStoredItems();
            ItemStack take = cache.requestItem(request);
            if (take != null) {
                if (stackToReturn == null) {
                    stackToReturn = take.clone();
                    stackToReturn.setAmount(take.getAmount());
                }
            }
        }

        for (BlockMenu blockMenu : getAdvancedGreedyBlocks()) {
            for (int slot : AdvancedGreedyBlock.INPUT_SLOTS) {
                final ItemStack itemStack = blockMenu.getItemInSlot(slot);
                if (itemStack == null || itemStack.getType() == Material.AIR || !StackUtils.itemsMatch(
                        request,
                        itemStack,
                        true
                )) {
                    continue;
                }

                // Stack is null, so we can fill it here
                if (stackToReturn == null) {
                    stackToReturn = itemStack.clone();
                    stackToReturn.setAmount(1);
                    request.receiveAmount(1);
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }

                // Escape if fulfilled request
                if (request.getAmount() <= 0) {
                    return stackToReturn;
                }

                if (request.getAmount() <= itemStack.getAmount()) {
                    stackToReturn.setAmount(stackToReturn.getAmount() + request.getAmount());
                    itemStack.setAmount(itemStack.getAmount() - request.getAmount());
                    return stackToReturn;
                } else {
                    stackToReturn.setAmount(stackToReturn.getAmount() + itemStack.getAmount());
                    request.receiveAmount(itemStack.getAmount());
                    itemStack.setAmount(0);
                }
            }
        }

        // Greedy Blocks
        for (BlockMenu blockMenu : getGreedyBlocks()) {
            final ItemStack itemStack = blockMenu.getItemInSlot(NetworkGreedyBlock.INPUT_SLOT);
            if (itemStack == null
                    || itemStack.getType() == Material.AIR
                    || !StackUtils.itemsMatch(request, itemStack, true)
            ) {
                continue;
            }

            // Mark the Cell as dirty otherwise the changes will not save on shutdown
            blockMenu.markDirty();

            // If the return stack is null, we need to set it up
            if (stackToReturn == null) {
                stackToReturn = itemStack.clone();
                stackToReturn.setAmount(1);
                request.receiveAmount(1);
                itemStack.setAmount(itemStack.getAmount() - 1);
            }

            // Escape if fulfilled request
            if (request.getAmount() <= 0) {
                return stackToReturn;
            }

            if (request.getAmount() <= itemStack.getAmount()) {
                // We can't take more than this stack. Level to request amount, remove items and then return
                stackToReturn.setAmount(stackToReturn.getAmount() + request.getAmount());
                itemStack.setAmount(itemStack.getAmount() - request.getAmount());
                return stackToReturn;
            } else {
                // We can take more than what is here, consume before trying to take more
                stackToReturn.setAmount(stackToReturn.getAmount() + itemStack.getAmount());
                request.receiveAmount(itemStack.getAmount());
                itemStack.setAmount(0);
            }
        }

        // Cells
        for (BlockMenu blockMenu : getCellMenus()) {
            for (ItemStack itemStack : blockMenu.getContents()) {
                if (itemStack == null
                        || itemStack.getType() == Material.AIR
                        || !StackUtils.itemsMatch(request, itemStack, true)
                ) {
                    continue;
                }

                // Mark the Cell as dirty otherwise the changes will not save on shutdown
                blockMenu.markDirty();

                // If the return stack is null, we need to set it up
                if (stackToReturn == null) {
                    stackToReturn = itemStack.clone();
                    stackToReturn.setAmount(1);
                    request.receiveAmount(1);
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }

                // Escape if fulfilled request
                if (request.getAmount() <= 0) {
                    return stackToReturn;
                }

                if (request.getAmount() <= itemStack.getAmount()) {
                    // We can't take more than this stack. Level to request amount, remove items and then return
                    stackToReturn.setAmount(stackToReturn.getAmount() + request.getAmount());
                    itemStack.setAmount(itemStack.getAmount() - request.getAmount());
                    return stackToReturn;
                } else {
                    // We can take more than what is here, consume before trying to take more
                    stackToReturn.setAmount(stackToReturn.getAmount() + itemStack.getAmount());
                    request.receiveAmount(itemStack.getAmount());
                    itemStack.setAmount(0);
                }
            }
        }

        return stackToReturn;
    }


    public boolean contains(@Nonnull ItemRequest request) {
        int found = 0;

        // Barrels
        for (BarrelIdentity barrelIdentity : getBarrels()) {
            final ItemStack itemStack = barrelIdentity.getItemStack();

            if (itemStack == null || !StackUtils.itemsMatch(request, itemStack, true)) {
                continue;
            }

            if (barrelIdentity instanceof InfinityBarrel) {
                if (barrelIdentity.getItemStack().getMaxStackSize() > 1) {
                    found += barrelIdentity.getAmount() - 2;
                }
            } else {
                found += barrelIdentity.getAmount();
            }

            // Escape if found all we need
            if (found >= request.getAmount()) {
                return true;
            }
        }

        // Crafters
        for (BlockMenu blockMenu : getCrafterOutputs()) {
            int[] slots = blockMenu.getPreset().getSlotsAccessedByItemTransport(ItemTransportFlow.WITHDRAW);
            for (int slot : slots) {
                final ItemStack itemStack = blockMenu.getItemInSlot(slot);
                if (itemStack == null
                        || itemStack.getType() == Material.AIR
                        || !StackUtils.itemsMatch(request, itemStack, true)
                ) {
                    continue;
                }

                found += itemStack.getAmount();

                // Escape if found all we need
                if (found >= request.getAmount()) {
                    return true;
                }
            }
        }

        for (StorageUnitData cache: getCargoStorageUnits()) {
            final List<ItemContainer> storedItems = cache.getStoredItems();
            for (ItemContainer itemContainer : storedItems) {
                final ItemStack itemStack = itemContainer.getSample();
                if (itemStack == null
                        || itemStack.getType() == Material.AIR
                        || !StackUtils.itemsMatch(request, itemStack, true)
                ) {
                    continue;
                }

                found += itemContainer.getAmount();


                // Escape if found all we need
                if (found >= request.getAmount()) {
                    return true;
                }
            }
        }

        for (BlockMenu blockMenu : getAdvancedGreedyBlocks()) {
            for (int slot : AdvancedGreedyBlock.INPUT_SLOTS) {
                final ItemStack itemStack = blockMenu.getItemInSlot(slot);
                if (itemStack == null
                        || itemStack.getType() == Material.AIR
                        || !StackUtils.itemsMatch(request, itemStack, true)
                ) {
                    continue;
                }

                found += itemStack.getAmount();

                // Escape if found all we need
                if (found >= request.getAmount()) {
                    return true;
                }
            }
        }

        // Greedy Blocks
        for (BlockMenu blockMenu : getGreedyBlocks()) {
            final ItemStack itemStack = blockMenu.getItemInSlot(NetworkGreedyBlock.INPUT_SLOT);
            if (itemStack == null
                    || itemStack.getType() == Material.AIR
                    || !StackUtils.itemsMatch(request, itemStack, true)
            ) {
                continue;
            }

            found += itemStack.getAmount();

            // Escape if found all we need
            if (found >= request.getAmount()) {
                return true;
            }
        }

        // Cells
        for (BlockMenu blockMenu : getCellMenus()) {
            for (ItemStack itemStack : blockMenu.getContents()) {
                if (itemStack == null
                        || itemStack.getType() == Material.AIR
                        || !StackUtils.itemsMatch(request, itemStack, true)
                ) {
                    continue;
                }

                found += itemStack.getAmount();

                // Escape if found all we need
                if (found >= request.getAmount()) {
                    return true;
                }
            }
        }

        return false;
    }
    public int getAmount(@Nonnull ItemStack itemStack) {
        int totalAmount = 0;
        for (BlockMenu menu : getAdvancedGreedyBlocks()) {
            for (int slot : AdvancedGreedyBlock.INPUT_SLOTS) {
                final ItemStack inputSlotItem = menu.getItemInSlot(slot);
                if (inputSlotItem != null && StackUtils.itemsMatch(inputSlotItem, itemStack)) {
                    totalAmount += inputSlotItem.getAmount();
                }
            }
        }
        // 遍历所有贪婪方块
        for (BlockMenu blockMenu : getGreedyBlocks()) {
            ItemStack inputSlotItem = blockMenu.getItemInSlot(NetworkGreedyBlock.INPUT_SLOT);
            if (inputSlotItem != null && StackUtils.itemsMatch(inputSlotItem, itemStack)) {
                totalAmount += inputSlotItem.getAmount();
            }
        }
        // 遍历所有桶
        for (BarrelIdentity barrelIdentity : getBarrels()) {
            if (StackUtils.itemsMatch(barrelIdentity.getItemStack(), itemStack)) {
                totalAmount += barrelIdentity.getAmount();
            }
        }
        for (StorageUnitData cache : getCargoStorageUnits()) {
            final List<ItemContainer> storedItems = cache.getStoredItems();
            for (ItemContainer itemContainer : storedItems) {
                if (StackUtils.itemsMatch(itemContainer.getSample(), itemStack)) {
                    totalAmount += itemContainer.getAmount();
                }
            }
        }
        // 遍历所有单元格菜单
        for (BlockMenu blockMenu : getCellMenus()) {
            for (ItemStack cellItem : blockMenu.getContents()) {
                if (cellItem != null && StackUtils.itemsMatch(cellItem, itemStack)) {
                    totalAmount += cellItem.getAmount();
                }
            }
        }
        return totalAmount;
    }

    public void addItemStack(@Nonnull ItemStack incoming) {
        BlockMenu fallbackBlockMenu = null;
        int fallBackSlot = 0;
        for (BlockMenu menu : getAdvancedGreedyBlocks()) {
            for (int slot : AdvancedGreedyBlock.INPUT_SLOTS) {
                final ItemStack itemStack = menu.getItemInSlot(slot);
                // If this is an empty slot - move on, if it's our first, store it for later.
                if (itemStack == null || itemStack.getType().isAir()) {
                    if (fallbackBlockMenu == null) {
                        fallbackBlockMenu = menu;
                        fallBackSlot = slot;
                    }
                    continue;
                }

                final int itemStackAmount = itemStack.getAmount();
                final int incomingStackAmount = incoming.getAmount();

                if (itemStackAmount < itemStack.getMaxStackSize() && StackUtils.itemsMatch(incoming, itemStack)) {
                    final int maxCanAdd = itemStack.getMaxStackSize() - itemStackAmount;
                    final int amountToAdd = Math.min(maxCanAdd, incomingStackAmount);

                    itemStack.setAmount(itemStackAmount + amountToAdd);
                    incoming.setAmount(incomingStackAmount - amountToAdd);

                    // Mark dirty otherwise changes will not save
                    menu.markDirty();

                    // All distributed, can escape
                    if (incomingStackAmount == 0) {
                        return;
                    }
                }
            }
        }

        // Run for matching greedy blocks
        for (BlockMenu blockMenu : getGreedyBlocks()) {
            final ItemStack template = blockMenu.getItemInSlot(NetworkGreedyBlock.TEMPLATE_SLOT);

            if (template == null || template.getType() == Material.AIR || !StackUtils.itemsMatch(incoming, template)) {
                continue;
            }

            final ItemStack itemStack = blockMenu.getItemInSlot(NetworkGreedyBlock.INPUT_SLOT);

            if (itemStack == null || itemStack.getType() == Material.AIR) {
                blockMenu.replaceExistingItem(NetworkGreedyBlock.INPUT_SLOT, incoming.clone());
                incoming.setAmount(0);
                return;
            }

            final int itemStackAmount = itemStack.getAmount();
            final int incomingStackAmount = incoming.getAmount();
            if (itemStackAmount < itemStack.getMaxStackSize() && StackUtils.itemsMatch(itemStack, incoming)) {
                final int maxCanAdd = itemStack.getMaxStackSize() - itemStackAmount;
                final int amountToAdd = Math.min(maxCanAdd, incomingStackAmount);

                itemStack.setAmount(itemStackAmount + amountToAdd);
                incoming.setAmount(incomingStackAmount - amountToAdd);
            }
            // Given we have found a match, it doesn't matter if the item moved or not, we will not bring it in
            return;
        }

        // Run for matching barrels
        for (BarrelIdentity barrelIdentity : getBarrels()) {
            if (StackUtils.itemsMatch(barrelIdentity, incoming, true)) {

                barrelIdentity.depositItemStack(incoming);

                // All distributed, can escape
                if (incoming.getAmount() == 0) {
                    return;
                }
            }
        }

        // Then run for matching items in cells
        fallbackBlockMenu = null;
        fallBackSlot = 0;

        for (BlockMenu blockMenu : getCellMenus()) {
            int i = 0;
            for (ItemStack itemStack : blockMenu.getContents()) {
                // If this is an empty slot - move on, if it's our first, store it for later.
                if (itemStack == null || itemStack.getType().isAir()) {
                    if (fallbackBlockMenu == null) {
                        fallbackBlockMenu = blockMenu;
                        fallBackSlot = i;
                    }
                    continue;
                }

                final int itemStackAmount = itemStack.getAmount();
                final int incomingStackAmount = incoming.getAmount();

                if (itemStackAmount < itemStack.getMaxStackSize() && StackUtils.itemsMatch(incoming, itemStack)) {
                    final int maxCanAdd = itemStack.getMaxStackSize() - itemStackAmount;
                    final int amountToAdd = Math.min(maxCanAdd, incomingStackAmount);

                    itemStack.setAmount(itemStackAmount + amountToAdd);
                    incoming.setAmount(incomingStackAmount - amountToAdd);

                    // Mark dirty otherwise changes will not save
                    blockMenu.markDirty();

                    // All distributed, can escape
                    if (incomingStackAmount == 0) {
                        return;
                    }
                }
                i++;
            }
        }

        // Add to fallback slot
        if (fallbackBlockMenu != null) {
            fallbackBlockMenu.replaceExistingItem(fallBackSlot, incoming.clone());
            incoming.setAmount(0);
        }

        StorageUnitData fallbackCache = null;
        for (StorageUnitData cache: getCargoStorageUnits()) {
            if (fallbackCache == null) {
                // 如果 cache 仍有空位，则记录这个 cache
                if (cache.getStoredTypeCount() < cache.getSizeType().getMaxItemCount()) {
                    fallbackCache = cache;
                }
            }
            final List<ItemContainer> storedItems = cache.getStoredItems();
            for (ItemContainer itemContainer : storedItems) {
                // 先填充存在这个物品的存储
                cache.depositItemStack(incoming, true);
            }

            // 填充完成
            if (incoming.getAmount() == 0) {
                return;
            }
        }

        if (incoming.getAmount() > 0) {
            if (fallbackCache != null) {
                fallbackCache.depositItemStack(incoming, false);
            }
        }
    }

    @Override
    public long retrieveBlockCharge() {
        return 0;
    }

    public long getRootPower() {
        return this.rootPower;
    }

    public void setRootPower(long power) {
        this.rootPower = power;
    }

    public void addRootPower(long power) {
        this.rootPower += power;
    }

    public void removeRootPower(long power) {
        int removed = 0;
        for (Location node : powerNodes) {
            final SlimefunItem item = StorageCacheUtils.getSfItem(node);
            if (item instanceof NetworkPowerNode powerNode) {
                final int charge = powerNode.getCharge(node);
                if (charge <= 0) {
                    continue;
                }
                final int toRemove = (int) Math.min(power - removed, charge);
                powerNode.removeCharge(node, toRemove);
                this.rootPower -= power;
                removed = removed + toRemove;
            }
            if (removed >= power) {
                return;
            }
        }
    }

    public boolean isDisplayParticles() {
        return displayParticles;
    }

    public void setDisplayParticles(boolean displayParticles) {
        this.displayParticles = displayParticles;
    }

    @Nonnull
    public List<ItemStack> getItemStacks(@Nonnull List<ItemRequest> itemRequests) {
        List<ItemStack> retrievedItems = new ArrayList<>();

        for (ItemRequest request : itemRequests) {
            ItemStack retrieved = getItemStack(request);
            if (retrieved != null) {
                retrievedItems.add(retrieved);
            }
        }
        return retrievedItems;
    }


}