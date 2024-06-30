package com.ytdd9527.networks.expansion.core.item.machine.network.advanced;

import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkObject;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class AdvancedGreedyBlock extends NetworkObject {

    public static final int TEMPLATE_SLOT = 10;
    public static final int[] INPUT_SLOTS = {6, 7, 8, 15, 16, 17, 24, 25, 26};
    private static final int[] BACKGROUND_SLOTS = new int[]{
        3, 4, 12, 13, 21, 22
    };
    private static final int[] BACKGROUND_SLOTS_TEMPLATE = new int[]{
        0, 1, 2, 9, 11, 18, 19, 20
    };
    private static final int[] BACKGROUND_SLOTS_INPUT = new int[]{
        5, 14, 23
    };

    private static final CustomItemStack TEMPLATE_BACKGROUND_STACK = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE,
        Theme.SUCCESS + "需要阻断的物品"
    );

    private static final CustomItemStack STORAGE_BACKGROUND_STACK = new CustomItemStack(
        Material.ORANGE_STAINED_GLASS_PANE,
        Theme.SUCCESS + "物品存储"
    );

    public AdvancedGreedyBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.NEA_GREEDY_BLOCK);
        for (int slot : INPUT_SLOTS) {
            this.getSlotsToDrop().add(slot);
        }
        this.getSlotsToDrop().add(TEMPLATE_SLOT);
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(BACKGROUND_SLOTS);
                drawBackground(TEMPLATE_BACKGROUND_STACK, BACKGROUND_SLOTS_TEMPLATE);
                drawBackground(STORAGE_BACKGROUND_STACK, BACKGROUND_SLOTS_INPUT);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return NetworkSlimefunItems.NETWORK_CELL.canUse(player, false)
                    && Slimefun.getProtectionManager()
                    .hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return INPUT_SLOTS;
                }
                return new int[]{0};
            }

        };
    }

}
