package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.transportation;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinateTransmitter extends NetworkObject implements RecipeDisplayItem {


    private static final int[] TEMPLATE_SLOT = new int[]{
            10,11,12,13,14,15,16,
            19,20,21,22,23,24,25,
            28,29,30,31,32,33,34,
            37,38,39,40,41,42,43
    };
    private static final int[] BACKGROUND_SLOTS = new int[]{
        0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53
    };

    private static final int[] BACKGROUND_SLOTS_TEMPLATE = new int[]{
        9,17,18,26,27,35,36,44
    };

    private static final CustomItemStack TEMPLATE_BACKGROUND_STACK = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE,
        Theme.SUCCESS + "坐标传输的物品"
    );
    private static final ItemStack AIR = new CustomItemStack(Material.AIR);
    private static final String LINKED_LOCATION_KEY_X = "linked-location-x";
    private static final String LINKED_LOCATION_KEY_Y = "linked-location-y";
    private static final String LINKED_LOCATION_KEY_Z = "linked-location-z";

    private static final int REQUIRED_POWER = 10000;
    private static final int TICKS_PER = 2;

    private final Map<Location, Location> linkedLocations = new HashMap<>();

    public CoordinateTransmitter(ItemGroup itemGroup,
                                 SlimefunItemStack item,
                                 RecipeType recipeType,
                                 ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe, NodeType.NE_COORDINATE_TRANSMITTER);

        for (int templateslot : TEMPLATE_SLOT) {
            this.getSlotsToDrop().add(templateslot);
        }
        addItemHandler(
            new BlockTicker() {
                private final Map<Location, Integer> tickMap = new HashMap<>();
                private final Map<Location, Boolean> firstTick = new HashMap<>();

                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, SlimefunBlockData data) {
                    BlockMenu blockMenu = data.getBlockMenu();
                    if (blockMenu != null) {
                        addToRegistry(block);

                        boolean isFirstTick = firstTick.getOrDefault(block.getLocation(), true);
                        if (isFirstTick) {
                            final String xString = data.getData(LINKED_LOCATION_KEY_X);
                            final String yString = data.getData(LINKED_LOCATION_KEY_Y);
                            final String zString = data.getData(LINKED_LOCATION_KEY_Z);
                            if (xString != null && yString != null && zString != null) {
                                final Location linkedLocation = new Location(
                                    block.getWorld(),
                                    Integer.parseInt(xString),
                                    Integer.parseInt(yString),
                                    Integer.parseInt(zString)
                                );
                                linkedLocations.put(block.getLocation(), linkedLocation);
                            }
                            firstTick.put(block.getLocation(), false);
                        }

                        int tick = tickMap.getOrDefault(block.getLocation(), 0);
                        if (tick >= TICKS_PER) {
                            onTick(blockMenu);
                            tickMap.remove(block.getLocation());
                            tick = 0;
                        } else {
                            tick++;
                        }
                        tickMap.put(block.getLocation(), tick + 1);
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

        final Location location = blockMenu.getLocation();
        final Location linkedLocation = linkedLocations.get(location);

        if (linkedLocation == null) {
            return;
        }

        final SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(linkedLocation);

        if (!(slimefunItem instanceof CoordinateReceiver)) {
            linkedLocations.remove(location);
            return;
        }

        final BlockMenu linkedBlockMenu = StorageCacheUtils.getMenu(linkedLocation);
        if (linkedBlockMenu == null) {
            return;
        }
        for (int receivedslot : CoordinateReceiver.RECEIVED_SLOT) {
        final ItemStack itemStack = linkedBlockMenu.getItemInSlot(receivedslot);

        if (itemStack == null || itemStack.getType() == Material.AIR) {
        }
            for (int templateslot : TEMPLATE_SLOT) {
                final ItemStack templateStack = blockMenu.getItemInSlot(templateslot);


                if (templateStack == null || templateStack.getType() == Material.AIR) {
                    return;
                }

                if (definition.getNode().getRoot().getRootPower() < REQUIRED_POWER) {
                    return;
                }

                final ItemStack stackToPush = definition.getNode().getRoot().getItemStack(
                        new ItemRequest(templateStack.clone(), templateStack.getMaxStackSize())
                );


            if (stackToPush != null) {
                definition.getNode().getRoot().removeRootPower(REQUIRED_POWER);
                linkedBlockMenu.pushItem(stackToPush, CoordinateReceiver.RECEIVED_SLOT);
                if (definition.getNode().getRoot().isDisplayParticles()) {
                    final Location particleLocation = blockMenu.getLocation().clone().add(0.5, 1.1, 0.5);
                    final Location particleLocation2 = linkedBlockMenu.getLocation().clone().add(0.5, 2.1, 0.5);
                    particleLocation.getWorld().spawnParticle(
                        Particle.WAX_ON,
                        particleLocation,
                        0,
                        0,
                        4,
                        0
                    );
                    particleLocation2.getWorld().spawnParticle(
                        Particle.WAX_OFF,
                        particleLocation2,
                        0,
                        0,
                        -4,
                        0
                    );
                }
            }
            }
        }
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(BACKGROUND_SLOTS);
                drawBackground(TEMPLATE_BACKGROUND_STACK, BACKGROUND_SLOTS_TEMPLATE);
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

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent event) {
        super.onBreak(event);
        linkedLocations.remove(event.getBlock().getLocation());
    }

    public void addLinkedLocation(@Nonnull Block block, @Nonnull Location linkedLocation) {
        linkedLocations.put(block.getLocation(), linkedLocation);
        var blockData = StorageCacheUtils.getBlock(block.getLocation());
        blockData.setData(LINKED_LOCATION_KEY_X, String.valueOf(linkedLocation.getBlockX()));
        blockData.setData(LINKED_LOCATION_KEY_Y, String.valueOf(linkedLocation.getBlockY()));
        blockData.setData(LINKED_LOCATION_KEY_Z, String.valueOf(linkedLocation.getBlockZ()));
    }
    @NotNull
    @Override

    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>();
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩传输机制⇩",
                "",
                "&7实现物品的精确传输",
                "",
                "&e核心特性&f:",
                "&f-&7 物品传输：自动将物品传输到绑定的坐标接收器",
                "&f-&7 同一世界：传输只能在相同世界坐标内进行",
                "&f-&7 绑定设置：使用坐标配置器绑定接收器坐标"
                ));
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩网络电力消耗⇩",
                "",
                MessageFormat.format("{0}网络电力消耗: {1}{2} 每次传输", Theme.CLICK_INFO, Theme.PASSIVE, REQUIRED_POWER)
        ));
        displayRecipes.add(new CustomItemStack(Material.BOOK,
                "&a⇩网络坐标传输器使用指南⇩",
                "",
                "&f-&7 设置物品传输：将需要传输的物品放入坐标传输器(确保有网络中有足够的物品进行传输)",
                "&f-&7 绑定坐标接收器：使用坐标配置器设置接收器坐标完成绑定",
                "&f-&7 自动传输：绑定完成后，物品将自动发送至坐标接收器"
        ));
        return displayRecipes;
    }
}
