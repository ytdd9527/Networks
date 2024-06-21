package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.slimefun.network.NetworkDirectional;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils.DisplayGroupGenerators;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ChainGrabber extends NetworkDirectional implements RecipeDisplayItem {


    private boolean useSpecialModel = false;
    private static final String KEY_UUID = "display-uuid";
    // 定义一个用于记录Tick计数的键
    private static final String TICK_COUNTER_KEY = "chain_grabber_tick_counter";

    private static final ItemStack AIR = new CustomItemStack(Material.AIR);
    private int maxDistance;
    private int grabItemTick;

    /**
     * 构造函数，用于初始化ChainGrabber对象。
     * @param itemGroup 物品组
     * @param item 物品堆
     * @param recipeType 配方类型
     * @param recipe 配方数组
     * @param itemId 物品ID
     * NodeType.GRABBER 节点
     */
    public ChainGrabber(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, String itemId, int maxDistance) {
        super(itemGroup, item, recipeType, recipe, NodeType.CHAIN_GRABBER);
        this.maxDistance = maxDistance;
        loadConfigurations(itemId);
    }

    private void loadConfigurations(String itemId) {
        int defaultGrabItemTick = 10;

        FileConfiguration config = Networks.getInstance().getConfig();
        this.maxDistance = config.getInt("items." + itemId + ".max-distance", this.maxDistance);
        this.grabItemTick = config.getInt("items." + itemId + ".grabitem-tick", defaultGrabItemTick);
    }

    /**
     * 异步执行抓取操作。
     * @param blockMenu 可能为null的BlockMenu对象
     */
    private void performGrabbingOperationAsync(@Nullable BlockMenu blockMenu) {
        if (blockMenu != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    tryGrabItem(blockMenu);
                }
            }.runTaskAsynchronously(Networks.getInstance());
        }
    }

    /**
     * 每个Tick调用的方法，用于执行抓取操作。
     * @param blockMenu 可能为null的BlockMenu对象
     * @param block 当前Block对象
     */
    @Override
    protected void onTick(@Nullable BlockMenu blockMenu, @Nonnull Block block) {
        super.onTick(blockMenu, block);

        // 初始化Tick计数器
        int tickCounter = getTickCounter(block);
        tickCounter = (tickCounter + 1) % this.grabItemTick;

        // 每grabItemTick个Tick执行一次抓取操作
        if (tickCounter == 0) {
            performGrabbingOperationAsync(blockMenu);
        }

        // 更新Tick计数器
        updateTickCounter(block, tickCounter);
    }

    /**
     * 获取指定方块的Tick计数器的值。
     * @param block 当前处理的方块对象
     * @return Tick计数器的整数值
     */
    private int getTickCounter(Block block) {
        // 从BlockStorage中获取与TICK_COUNTER_KEY关联的值
        String tickCounterValue = BlockStorage.getLocationInfo(block.getLocation(), TICK_COUNTER_KEY);
        try {
            // 如果存在值，则尝试将其解析为整数
            return (tickCounterValue != null) ? Integer.parseInt(tickCounterValue) : 0;
        } catch (NumberFormatException e) {
            // 如果解析失败，则返回0
            return 0;
        }
    }

    /**
     * 更新指定方块的Tick计数器。
     * @param block 当前处理的方块对象
     * @param tickCounter 更新后的Tick计数器的值
     */
    private void updateTickCounter(Block block, int tickCounter) {
        // 将更新后的Tick计数器值存储到BlockStorage中
        BlockStorage.addBlockInfo(block.getLocation(), TICK_COUNTER_KEY, Integer.toString(tickCounter));
    }

    /**
     * 尝试从指定的BlockMenu中抓取物品。
     * @param blockMenu BlockMenu对象，不能为null
     */
    private void tryGrabItem(@Nonnull BlockMenu blockMenu) {
        if (blockMenu == null) {
            return;
        }

        NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }

        BlockFace direction = this.getCurrentDirection(blockMenu);
        Block currentBlock = blockMenu.getBlock().getRelative(direction);

        for (int i = 0; i < this.maxDistance && currentBlock.getType() != Material.AIR; i++) {
            BlockMenu targetMenu = StorageCacheUtils.getMenu(currentBlock.getLocation());

            if (targetMenu == null) {
                break;
            }
            int[] slots = targetMenu.getPreset().getSlotsAccessedByItemTransport(targetMenu, ItemTransportFlow.WITHDRAW, null);
            for (int slot : slots) {
                ItemStack itemStack = targetMenu.getItemInSlot(slot);

                if (itemStack != null) {

                    if (isItemTransferable(itemStack)) {
                        int before = itemStack.getAmount();

                        definition.getNode().getRoot().addItemStack(itemStack);

                        if (itemStack.getAmount() < before) {
                            //抓取成功显示粒子
                            //showParticle(blockMenu.getBlock().getLocation(), direction);
                            targetMenu.replaceExistingItem(slot, itemStack);
                        }
                    }
                }
            }
            currentBlock = currentBlock.getRelative(direction);
        }
    }

    /**
     * 检查物品是否可传输。
     * @param itemStack 要检查的物品堆栈
     * @return 如果物品可传输返回true，否则返回false
     */
    private boolean isItemTransferable(@Nonnull ItemStack itemStack) {
        return itemStack != null && itemStack.getType() != Material.AIR;
    }

    @Override
    public void preRegister() {
        // 只有当 useSpecialModel 为 true 时，才添加放置处理器
        if (useSpecialModel) {
            addItemHandler(new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                    // 放置方块时的逻辑
                    e.getBlock().setType(Material.BARRIER);
                    setupDisplay(e.getBlock().getLocation());
                }
            });
        }

        // 添加破坏处理器，不管 useSpecialModel 的值如何，破坏时的逻辑都应该执行
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

    /**
     * 获取显示粒子效果的配置。
     * @return 粒子效果配置对象
     */
    @Override
    protected Particle.DustOptions getDustOptions() {
        // 返回一个Particle.DustOptions对象，设置为黄绿色粒子
        return new Particle.DustOptions(Color.LIME, 5);
    }
    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes  = new ArrayList<>(4);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩频率机制⇩",
                "",
                "&7这个链式抓取器有一个简单的频率控制&f：",
                "",
                "&e执行频率&f:",
                "&f-&7 每隔 &6" + this.grabItemTick + " SfTick&7 自动执行一次抓取",
                "&f-&7 目的: 这样做可以平稳地运行，减少服务器负载",
                "",
                "&f-&7 简而言之，链式抓取器不会频繁操作，从而保持服务器流畅"
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩抓取逻辑⇩",
                "&7以下是链式抓取器的操作说明：",
                "",
                "&e最大抓取距离&7: &6" + this.maxDistance + "格",
                "&e抓取对象: &f机器方块的输出槽位中的物品",
                "",
                "&e运行流程&f:",
                "&f-&7 打开界面设置你所需的方向",
                "&f-&7 链式抓取器从当前方块开始，沿着设定方向搜索",
                "",
                "&e抓取条件&f:",
                "&f-&7 遇到有物品的输出槽位，且物品不是空气时",
                "&f-&7 将物品添加到网络中(确保网络有足够的空间不然不进行抓取)",
                "&f-&7 确保物品在机器按计划和有序地抓取，避免混乱",
                "",
                "&e停止条件&f:",
                "&f-&7 达到最大抓取距离(&6" + this.maxDistance + "&7格)",
                "&f-&7 遇到的方块为空，或者",
                "&f-&7 没有更多可抓取的物品(空间)",
                "&f-&7 抓取器将停止操作",
                "",
                "&e效率与秩序&f:",
                "&f-&7 此机制确保物品流动的高效性和有序性。"
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a ⇩链式抓取器使用指南⇩",
                "",
                "&7链式抓取器效率最大化建议：",
                "",
                "&f-&7 如果你使用了链式抓取器就没必要给机器继续使用抓取了",
                "&f-&7 不要双管齐下多此一举",
                "",
                "&f-&7 充分利用链式抓取器范围: 每个链式抓取器可以覆盖&6" + this.maxDistance + "&7格的距离",
                "&f-&7 确保您的布局设计能够覆盖多个机器，以实现最大效率",
                "",
                "&f-&7 避免单个机器配置: 不要仅在一个机器上使用链式抓取器",
                "&f-&7 这样做会限制您的自动化系统的潜力和扩展性",
                "",
                "&f-&7请遵循这些建议，您将能够最大化每个链式抓取器的工作效能，",
                "&f-&7同时保持也可以服务器流畅运行"
        ));
        return displayRecipes;
    }
}
