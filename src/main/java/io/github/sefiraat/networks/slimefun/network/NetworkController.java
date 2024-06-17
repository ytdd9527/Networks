package io.github.sefiraat.networks.slimefun.network;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NetworkNode;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils.DisplayGroupGenerators;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class NetworkController extends NetworkObject {

    private static final String CRAYON = "crayon";
    private static final Map<Location, NetworkRoot> NETWORKS = new HashMap<>();
    private static final Set<Location> CRAYONS = new HashSet<>();
    private boolean useSpecialModel = false;
    private static final String KEY_UUID = "display-uuid";
    private final ItemSetting<Integer> maxNodes;
    protected final Map<Location, Boolean> firstTickMap = new HashMap<>();

    public NetworkController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.CONTROLLER);

        maxNodes = new IntRangeSetting(this, "max_nodes", 10, 8000, 10000);
        addItemSetting(maxNodes);

        addItemHandler(
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block block, SlimefunItem item, SlimefunBlockData data) {

                    if (!firstTickMap.containsKey(block.getLocation())) {
                        onFirstTick(block, data);
                        firstTickMap.put(block.getLocation(), true);
                    }

                    addToRegistry(block);
                    NetworkRoot networkRoot = new NetworkRoot(block.getLocation(), NodeType.CONTROLLER, maxNodes.getValue());
                    networkRoot.addAllChildren();

                    boolean crayon = CRAYONS.contains(block.getLocation());
                    if (crayon) {
                        networkRoot.setDisplayParticles(true);
                    }

                    NETWORKS.put(block.getLocation(), networkRoot);
                }
            }
        );
    }

    private void onFirstTick(@Nonnull Block block, @Nonnull SlimefunBlockData data) {
        final String crayon = data.getData(CRAYON);
        if (Boolean.parseBoolean(crayon)) {
            CRAYONS.add(block.getLocation());
        }
    }

    public static Map<Location, NetworkRoot> getNetworks() {
        return NETWORKS;
    }

    public static Set<Location> getCrayons() {
        return CRAYONS;
    }

    public static void addCrayon(@Nonnull Location location) {
        StorageCacheUtils.setData(location, CRAYON, String.valueOf(true));
        CRAYONS.add(location);
    }

    public static void removeCrayon(@Nonnull Location location) {
        StorageCacheUtils.removeData(location, CRAYON);
        CRAYONS.remove(location);
    }

    public static boolean hasCrayon(@Nonnull Location location) {
        return CRAYONS.contains(location);
    }

    public static void wipeNetwork(@Nonnull Location location) {
        for (NetworkNode node : NETWORKS.remove(location).getChildrenNodes()) {
            NetworkStorage.removeNode(node.getNodePosition());
        }
    }

    @Override
    public void preRegister() {
        if (useSpecialModel) {
            addItemHandler(new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                    // 放置方块时的逻辑
                    e.getBlock().setType(Material.GLASS);
                    setupDisplay(e.getBlock().getLocation());
                }
            });
        }
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Location location = e.getBlock().getLocation();
                removeDisplay(location);
                e.getBlock().setType(Material.AIR);

            }
        });
    }
    public void setUseSpecialModel(boolean useSpecialModel) {
        this.useSpecialModel = useSpecialModel;
    }
    private void setupDisplay(@Nonnull Location location) {
        DisplayGroup displayGroup = DisplayGroupGenerators.generateController(location.clone().add(0.5, 0, 0.5));
        StorageCacheUtils.setData(location, KEY_UUID, displayGroup.getParentUUID().toString());
    }
    private void removeDisplay(@Nonnull Location location) {
        DisplayGroup group = getDisplayGroup(location);
        if (group != null) {
            group.remove();
        }
    }
    @Nullable
    private UUID getDisplayGroupUUID(@Nonnull Location location) {
        String uuid = StorageCacheUtils.getData(location, KEY_UUID);
        if (uuid == null) {
            return null;
        }
        return UUID.fromString(uuid);
    }
    @Nullable
    private DisplayGroup getDisplayGroup(@Nonnull Location location) {
        UUID uuid = getDisplayGroupUUID(location);
        if (uuid == null) {
            return null;
        }
        return DisplayGroup.fromUUID(uuid);
    }
}
