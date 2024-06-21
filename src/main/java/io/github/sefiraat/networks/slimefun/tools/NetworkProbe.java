package io.github.sefiraat.networks.slimefun.tools;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.slimefun.network.NetworkController;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class NetworkProbe extends SlimefunItem implements CanCooldown {

    private static final MessageFormat MESSAGE_FORMAT = new MessageFormat("{0}{1}: {2}{3}", Locale.ROOT);

    public NetworkProbe(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) this::onUse);
    }

    protected void onUse(PlayerRightClickEvent e) {
        final Optional<Block> optional = e.getClickedBlock();
        if (optional.isPresent()) {
            final Block block = optional.get();
            final Player player = e.getPlayer();
            if (canBeUsed(player, e.getItem())) {
                var blockData = StorageCacheUtils.getBlock(block.getLocation());
                if (blockData == null) {
                    return;
                }

                var slimefunItem = SlimefunItem.getById(blockData.getSfId());
                if (slimefunItem instanceof NetworkController) {
                    e.cancel();
                    displayToPlayer(block, player);
                    putOnCooldown(e.getItem());
                }
            }
        }
    }
    private void displayToPlayer(@Nonnull Block block, @Nonnull Player player) {
        final NetworkRoot root = NetworkController.getNetworks().get(block.getLocation());
        if (root != null) {
            final int bridges = root.getBridges().size();
            final int monitors = root.getMonitors().size();
            final int importers = root.getImporters().size();
            final int exporters = root.getExporters().size();
            final int grids = root.getGrids().size();
            final int cells = root.getCells().size();
            final int wipers = root.getWipers().size();
            final int grabbers = root.getGrabbers().size();
            final int pushers = root.getPushers().size();
            final int purgers = root.getPurgers().size();
            final int crafters = root.getCrafters().size();
            final int powerNodes = root.getPowerNodes().size();
            final int powerDisplays = root.getPowerDisplays().size();
            final int encoders = root.getEncoders().size();
            final int cutters = root.getCutters().size();
            final int pasters = root.getPasters().size();
            final int vacuums = root.getVacuums().size();
            final int wirelessTransmitters = root.getWirelessTransmitters().size();
            final int wirelessReceivers = root.getWirelessReceivers().size();
            final int powerOutlets = root.getPowerOutlets().size();
            final int greedyBlocks = root.getGreedyBlocks().size();
            final Map<ItemStack, Long> allNetworkItems = root.getAllNetworkItems();
            final int distinctItems = allNetworkItems.size();

            final int chainPushers = root.getChainPushers().size();
            final int chainGrabbers = root.getChainGrabbers().size();
            final int advancedImporters = root.getAdvancedImports().size();
            final int advancedExporters = root.getAdvancedExports().size();
            final int coordinateTransmitters = root.getCoordinateTransmitters().size();
            final int coordinateReceivers = root.getCoordinateReceivers().size();
            final int chainDispatchers = root.getChainDispatchers().size();

            long totalItems = allNetworkItems.values().stream().mapToLong(integer -> integer).sum();

            final String nodeCount = root.getNodeCount() >= root.getMaxNodes()
                    ? Theme.ERROR + "" + root.getNodeCount() + "+"
                    : String.valueOf(root.getNodeCount());

            final ChatColor c = Theme.CLICK_INFO.getColor();
            final ChatColor p = Theme.SUCCESS.getColor();

            player.sendMessage("------------------------------");
            player.sendMessage("         网络 - 组件统计        ");
            player.sendMessage("------------------------------");

            player.sendMessage(formatter("网桥", bridges));
            player.sendMessage(formatter("网络监测器", monitors));
            player.sendMessage(formatter("网络入口", importers));
            player.sendMessage(formatter("网络出口", exporters));
            player.sendMessage(formatter("网格", grids));
            player.sendMessage(formatter("网络单元", cells));
            player.sendMessage(formatter("网络内存清除器", wipers));
            player.sendMessage(formatter("网络抓取器", grabbers));
            player.sendMessage(formatter("网络推送器", pushers));
            player.sendMessage(formatter("网络清除器", purgers));
            player.sendMessage(formatter("网络自动合成机", crafters));
            player.sendMessage(formatter("网络能源节点", powerNodes));
            player.sendMessage(formatter("网络电表", powerDisplays));
            player.sendMessage(formatter("网络配方编码器", encoders));
            player.sendMessage(formatter("网络剪切器", cutters));
            player.sendMessage(formatter("网络粘贴器", pasters));
            player.sendMessage(formatter("网络吸尘器", vacuums));
            player.sendMessage(formatter("网络无线发射器", wirelessTransmitters));
            player.sendMessage(formatter("网络无线接收器", wirelessReceivers));
            player.sendMessage(formatter("网络插口", powerOutlets));
            player.sendMessage(formatter("网络阻断器", greedyBlocks));
            player.sendMessage("------------------------------");
            player.sendMessage("         网络拓展 - 组件统计        ");
            player.sendMessage("------------------------------");
            player.sendMessage(formatter("网络链式推送器", chainPushers));
            player.sendMessage(formatter("网络链式抓取器", chainGrabbers));
            player.sendMessage(formatter("网链调度器", chainDispatchers));
            player.sendMessage(formatter("网络高级入口", advancedImporters));
            player.sendMessage(formatter("网络高级出口", advancedExporters));
            player.sendMessage(formatter("网络坐标传输器", coordinateTransmitters));
            player.sendMessage(formatter("网络坐标接收器", coordinateReceivers));
            player.sendMessage("------------------------------");
            player.sendMessage(formatter("物品类型数量", distinctItems));
            player.sendMessage(formatter("累计物品数量", totalItems));
            player.sendMessage("------------------------------");
            player.sendMessage(MESSAGE_FORMAT.format(new Object[]{c, "累计节点", p, nodeCount + "/" + root.getMaxNodes()}, new StringBuffer(), null).toString());
            if (root.isOverburdened()) {
                player.sendMessage(Theme.ERROR + "警告: " + Theme.PASSIVE +
                        "该网络已达到最大节点数量限制，部分节点可能会无法正常工作。请减少网络节点的数量。"
                );
            }
        }
    }
    @Override
    public int cooldownDuration() {
        return 10;
    }
    public String formatter(String name, long count) {
        return MESSAGE_FORMAT.format(new Object[]{Theme.CLICK_INFO.getColor(), name, Theme.SUCCESS.getColor(), count}, new StringBuffer(), null).toString();
    }
}
