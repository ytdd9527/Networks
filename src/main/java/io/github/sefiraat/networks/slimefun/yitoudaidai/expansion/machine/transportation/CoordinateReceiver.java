package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.transportation;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkObject;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CoordinateReceiver extends NetworkObject implements RecipeDisplayItem {

    private static final ItemStack AIR = new CustomItemStack(Material.AIR);
    public static final int[] RECEIVED_SLOT = new int[]{
            10,11,12,13,14,15,16,
            19,20,21,22,23,24,25,
            28,29,30,31,32,33,34,
            37,38,39,40,41,42,43
    };
    private static final int[] BACKGROUND_SLOTS = new int[]{
            0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53
    };

    private static final int[] RECEIVED_SLOTS_TEMPLATE = new int[]{
            9,17,18,26,27,35,36,44
    };

    private static final CustomItemStack RECEIVED_BACKGROUND_STACK = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE,
        Theme.SUCCESS + "坐标接收的物品"
    );

    public CoordinateReceiver(ItemGroup itemGroup,
                              SlimefunItemStack item,
                              RecipeType recipeType,
                              ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe, NodeType.NE_COORDINATE_RECEIVER);

        for (int receivedslot : RECEIVED_SLOT) {
            this.getSlotsToDrop().add(receivedslot);
        }
        addItemHandler(
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, SlimefunBlockData data) {
                    BlockMenu blockMenu = data.getBlockMenu();
                    if (blockMenu != null) {
                        addToRegistry(block);
                        onTick(blockMenu);
                    }
                }
            }
        );
    }

    private void onTick(@Nonnull BlockMenu blockMenu) {
        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }
        for (int receivedslot : RECEIVED_SLOT) {
            final ItemStack itemStack = blockMenu.getItemInSlot(receivedslot);


        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        definition.getNode().getRoot().addItemStack(itemStack);
        }
    }
    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(BACKGROUND_SLOTS);
                drawBackground(RECEIVED_BACKGROUND_STACK, RECEIVED_SLOTS_TEMPLATE);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return NetworkSlimefunItems.NETWORK_CELL.canUse(player, false)
                    && Slimefun.getProtectionManager()
                    .hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[]{0};
            }

        };
    }
    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>();
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩接收机制⇩",
                "",
                "&e核心特性&f:",
                "&f-&7 自动接收：与网络坐标传输器绑定，自动接收物品",
                "&f-&7 同步操作：确保在同一世界内实现物品的即时接收",
                "&f-&7 网络集成：接收的物品将被智能推送至网络系统",
                ""
        ));
        displayRecipes.add(AIR);
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩网络坐标接收器使用指南⇩",
                "",
                "&f-&7 绑定坐标传输器：使用坐标配置器与传输器绑定",
                "&f-&7 接收物品：接收器将自动接收来自绑定坐标传输器的物品",
                "&f-&7 网络推送：每粘液刻尝试将物品推送至网络，无需手动操作"
        ));
        return displayRecipes;
    }
}
