package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.transportation.model;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils.DisplayGroupGenerators;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;


public class ChainGrabberModel extends NetworkDirectional {


    //这有模型的机器不完整

    private static final String TICK_COUNTER_KEY = "chain_grabber1_tick_counter";
    private static final String KEY_UUID = "display-uuid";
    public ChainGrabberModel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.GRABBER);

    }
    @Override
    public void preRegister() {
        addItemHandler(
                new BlockPlaceHandler(false) {
                    @Override
                    public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                        e.getBlock().setType(Material.BARRIER);
                        setupDisplay(e.getBlock().getLocation());
                    }
                },
                new BlockBreakHandler(false, false) {
                    @Override
                    @ParametersAreNonnullByDefault
                    public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                        Location location = e.getBlock().getLocation();
                        removeDisplay(location);
                        e.getBlock().setType(Material.AIR);
                    }
                }
                );
    }

    @Override
    protected void onTick(@Nullable BlockMenu blockMenu, @Nonnull Block block) {
        super.onTick(blockMenu, block);

        int tickCounter = getTickCounter(block);

        tickCounter++;

        if (tickCounter % 10 == 0) {
            performGrabbingOperation(blockMenu);
        }


        updateTickCounter(block, tickCounter);
    }
    private int getTickCounter(Block block) {
        String tickCounterValue = BlockStorage.getLocationInfo(block.getLocation(), TICK_COUNTER_KEY);
        return tickCounterValue != null ? Integer.parseInt(tickCounterValue) : 0;
    }
    private void updateTickCounter(Block block, int tickCounter) {
        BlockStorage.addBlockInfo(block.getLocation(), TICK_COUNTER_KEY, String.valueOf(tickCounter));
    }
    private void performGrabbingOperation(@Nullable BlockMenu blockMenu) {
        if (blockMenu != null) {
            tryGrabItem(blockMenu);
        }
    }
    private void tryGrabItem(@Nonnull BlockMenu blockMenu) {
        NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());
        if (definition == null || definition.getNode() == null) {
            return;
        }

        BlockFace direction = this.getCurrentDirection(blockMenu);
        Block currentBlock = blockMenu.getBlock().getRelative(direction);
        int maxDistance = 64;


        for (int i = 0; i < maxDistance && currentBlock.getType() != Material.AIR; i++) {
            BlockMenu targetMenu = StorageCacheUtils.getMenu(currentBlock.getLocation());
            if (targetMenu != null) {
                int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.WITHDRAW, null);
                for (int slot : slots) {
                    final ItemStack itemStack = targetMenu.getItemInSlot(slot);

                    if (itemStack != null && itemStack.getType() != Material.AIR) {
                        int before = itemStack.getAmount();
                        definition.getNode().getRoot().addItemStack(itemStack);
                        if (definition.getNode().getRoot().isDisplayParticles() && itemStack.getAmount() < before) {
                            showParticle(blockMenu.getBlock().getLocation(), direction);
                        }
                        break;
                    }
                }
            }
            currentBlock = currentBlock.getRelative(direction);
        }
    }
    private void setupDisplay(@Nonnull Location location) {
        DisplayGroup displayGroup = DisplayGroupGenerators.generateCloche(location.clone().add(0.5, 0, 0.5));
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
    @Override
    protected Particle.DustOptions getDustOptions() {
        return new Particle.DustOptions(Color.YELLOW, 5);
    }
}
