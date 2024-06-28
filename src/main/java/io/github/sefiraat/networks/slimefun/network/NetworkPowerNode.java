package io.github.sefiraat.networks.slimefun.network;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import com.ytdd9527.networks.expansion.util.DisplayGroupGenerators;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.network.NodeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NetworkPowerNode extends NetworkObject implements EnergyNetComponent {

    private final int capacity;
    private boolean useSpecialModel = false;
    private static final String KEY_UUID = "display-uuid";

    public NetworkPowerNode(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int capacity) {
        super(itemGroup, item, recipeType, recipe, NodeType.POWER_NODE);
        this.capacity = capacity;
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }
    private Map<Block, Block> placedBlocks = new HashMap<>();
    @Override
    public int getCapacity() {
        return this.capacity;
    }
    @Override
    public void preRegister() {
        // 只有当 useSpecialModel 为 true 时，才添加放置处理器
        if (useSpecialModel) {
            addItemHandler(new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                    Block block = e.getBlock();
                    block.setType(Material.BARRIER);
                    Block aboveBlock = block.getWorld().getBlockAt(block.getLocation().add(0, 1, 0));
                    aboveBlock.setType(Material.BARRIER);

                    // 记录两个方块的位置关系
                    placedBlocks.put(block, aboveBlock);
                    placedBlocks.put(aboveBlock, block);

                    setupDisplay(block.getLocation());
                }
            });
        }

        // 添加破坏处理器，不管 useSpecialModel 的值如何，破坏时的逻辑都应该执行
        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block brokenBlock = e.getBlock();
                Block pairedBlock = placedBlocks.get(brokenBlock);

                if (pairedBlock != null) {
                    // 如果找到了配对的方块，执行删除逻辑
                    removeDisplay(brokenBlock.getLocation());
                    removeDisplay(pairedBlock.getLocation());

                    // 清除记录
                    placedBlocks.remove(brokenBlock);
                    placedBlocks.remove(pairedBlock);

                    // 将两个方块都设置为空气
                    brokenBlock.setType(Material.AIR);
                    pairedBlock.setType(Material.AIR);
                }
            }
        });
    }
    public void setUseSpecialModel(boolean useSpecialModel) {
        this.useSpecialModel = useSpecialModel;
    }
    private void setupDisplay(@Nonnull Location location) {
        DisplayGroup displayGroup = DisplayGroupGenerators.generatePowerNode(location.clone().add(0.5, 0, 0.5));
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
