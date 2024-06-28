package com.ytdd9527.networks.expansion.core.item.tool;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import com.ytdd9527.networks.expansion.core.item.AbstractMySlimefunItem;
import de.jeff_media.morepersistentdatatypes.DataType;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.CoordinateReceiver;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.CoordinateTransmitter;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CoordinateConfigurator extends AbstractMySlimefunItem {

    private static final NamespacedKey TARGET_LOCATION = Keys.newKey("target-location");

    public CoordinateConfigurator(ItemGroup itemGroup,
                                  SlimefunItemStack item,
                                  RecipeType recipeType,
                                  ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(
            new ItemUseHandler() {
                @Override
                public void onRightClick(PlayerRightClickEvent e) {
                    final Player player = e.getPlayer();
                    final Optional<Block> optional = e.getClickedBlock();
                    if (optional.isPresent()) {
                        final Block block = optional.get();
                        final SlimefunItem slimefunItem = StorageCacheUtils.getSfItem(block.getLocation());
                        if (Slimefun.getProtectionManager().hasPermission(player, block, Interaction.INTERACT_BLOCK)) {
                            final ItemStack heldItem = player.getInventory().getItemInMainHand();
                            final BlockMenu blockMenu = StorageCacheUtils.getMenu(block.getLocation());
                            if (slimefunItem instanceof CoordinateTransmitter transmitter && player.isSneaking()) {
                                setTransmitter(transmitter, heldItem, blockMenu, player);
                            } else if (slimefunItem instanceof CoordinateReceiver && !player.isSneaking()) {
                                setReceiver(heldItem, blockMenu, player);
                            }
                        } else {
                            player.sendMessage(Theme.ERROR + "必须以坐标传输接收 方块为目标");
                        }
                    }
                    e.cancel();
                }
            }
        );
    }

    private void setTransmitter(@Nonnull CoordinateTransmitter transmitter,
                                @Nonnull ItemStack itemStack,
                                @Nonnull BlockMenu blockMenu,
                                @Nonnull Player player
    ) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final Location location = PersistentDataAPI.get(itemMeta, TARGET_LOCATION, DataType.LOCATION);

        if (location == null) {
            player.sendMessage(Theme.ERROR + "尚未设置坐标接收器");
            return;
        }

        if (location.getWorld() != blockMenu.getLocation().getWorld()) {
            player.sendMessage(Theme.ERROR + "坐标接收器位于不同的世界");
            return;
        }

        transmitter.addLinkedLocation(blockMenu.getBlock(), location);
        player.sendMessage(Theme.SUCCESS + "设置坐标传输器的坐标接收器位置");
    }

    private void setReceiver(@Nonnull ItemStack itemStack, @Nonnull BlockMenu blockMenu, @Nonnull Player player) {
        final Location location = blockMenu.getLocation();
        final ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.set(itemMeta, TARGET_LOCATION, DataType.LOCATION, location);
        itemStack.setItemMeta(itemMeta);
        player.sendMessage(Theme.SUCCESS + "坐标接收器已设置");
    }


}