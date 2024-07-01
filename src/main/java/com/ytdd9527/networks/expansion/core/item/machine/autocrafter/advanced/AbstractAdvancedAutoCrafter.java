package com.ytdd9527.networks.expansion.core.item.machine.autocrafter.advanced;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.AncientAltarBlueprint;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.supportedrecipes.SupportedAncientAltarRecipes;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage.CargoStorageUnit;
import com.ytdd9527.networks.expansion.setup.ExpansionItems;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.BlueprintInstance;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.network.NetworkObject;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.sefiraat.networks.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.networks.utils.datatypes.PersistentCraftingBlueprintType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractAdvancedAutoCrafter extends NetworkObject {

    private static final int[] BACKGROUND_SLOTS = new int[]{
        3, 4, 5, 12, 13, 14, 21, 22, 23
    };
    private static final int[] BLUEPRINT_BACKGROUND = new int[]{0, 1, 2, 9, 11, 18, 19, 20};
    private static final int[] OUTPUT_BACKGROUND = new int[]{6, 7, 8, 15, 17, 24, 25, 26};

    private static final int BLUEPRINT_SLOT = 10;
    private static final int OUTPUT_SLOT = 16;

    public static final CustomItemStack BLUEPRINT_BACKGROUND_STACK = new CustomItemStack(
        Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "合成蓝图"
    );

    public static final CustomItemStack OUTPUT_BACKGROUND_STACK = new CustomItemStack(
        Material.GREEN_STAINED_GLASS_PANE, Theme.PASSIVE + "输出"
    );

    private final int chargePerCraft;
    private final boolean withholding;

    private static final Map<Location, BlueprintInstance> INSTANCE_MAP = new HashMap<>();

    public AbstractAdvancedAutoCrafter(
            ItemGroup itemGroup,
            SlimefunItemStack item,
            RecipeType recipeType,
            ItemStack[] recipe,
            int chargePerCraft,
            boolean withholding
    ) {
        super(itemGroup, item, recipeType, recipe, NodeType.CRAFTER);

        this.chargePerCraft = chargePerCraft;
        this.withholding = withholding;

        this.getSlotsToDrop().add(BLUEPRINT_SLOT);
        this.getSlotsToDrop().add(OUTPUT_SLOT);

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
                        craftPreFlight(blockMenu);
                    }
                }
            }
        );
    }

    protected void craftPreFlight(@Nonnull BlockMenu blockMenu) {

        releaseCache(blockMenu);

        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }

        final NetworkRoot root = definition.getNode().getRoot();

        if (!this.withholding) {
            final ItemStack stored = blockMenu.getItemInSlot(OUTPUT_SLOT);
            if (stored != null && stored.getType() != Material.AIR) {
                root.addItemStack(stored);
            }
        }

        final ItemStack blueprint = blockMenu.getItemInSlot(BLUEPRINT_SLOT);

        if (blueprint == null || blueprint.getType() == Material.AIR) {
            return;
        }

        final long networkCharge = root.getRootPower();

        if (networkCharge > this.chargePerCraft) {
            final SlimefunItem item = SlimefunItem.getByItem(blueprint);

            if (!isVaildBlueprint(item)) {
                return;
            }

            BlueprintInstance instance = INSTANCE_MAP.get(blockMenu.getLocation());

            if (instance == null) {
                final ItemMeta blueprintMeta = blueprint.getItemMeta();
                Optional<BlueprintInstance> optional;
                optional = DataTypeMethods.getOptionalCustom(blueprintMeta, Keys.BLUEPRINT_INSTANCE, PersistentCraftingBlueprintType.TYPE);

                if (optional.isEmpty()) {
                    optional = DataTypeMethods.getOptionalCustom(blueprintMeta, Keys.BLUEPRINT_INSTANCE2, PersistentCraftingBlueprintType.TYPE);
                }

                if (optional.isEmpty()) {
                    optional = DataTypeMethods.getOptionalCustom(blueprintMeta, Keys.BLUEPRINT_INSTANCE3, PersistentCraftingBlueprintType.TYPE);
                }

                if (optional.isEmpty()) {
                    return;
                }

                instance = optional.get();
                setCache(blockMenu, instance);
            }

            final ItemStack output = blockMenu.getItemInSlot(OUTPUT_SLOT);
            int blueprintAmount = blueprint.getAmount();

            if (output != null
                    && output.getType() != Material.AIR
                    && (output.getAmount() + instance.getItemStack().getAmount()*blueprintAmount > output.getMaxStackSize() || !StackUtils.itemsMatch(instance, output, true))) {
                return;
            }

            if (tryCraft(blockMenu, instance, root, blueprintAmount)) {
                root.removeRootPower(this.chargePerCraft);
            }
        }
    }

    private boolean tryCraft(@Nonnull BlockMenu blockMenu, @Nonnull BlueprintInstance instance, @Nonnull NetworkRoot root, @Nonnull int blueprintAmount) {
        // Get the recipe input
        final ItemStack[] inputs = new ItemStack[9];
        final ItemStack[] actualFetches = new ItemStack[9];

        /* Make sure the network has the required items
         * Needs to be revisited as matching is happening stacks 2x when I should
         * only need the one
         */
        Location storageLocation = blockMenu.getLocation();

        boolean isRetainMode = CargoStorageUnit.isLocked(storageLocation);

        HashMap<ItemStack, Integer> requiredItems = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            final ItemStack requested = instance.getRecipeItems()[i];
            if (requested != null) {
                requiredItems.merge(requested, requested.getAmount()*blueprintAmount, Integer::sum);
            }
        }
        for (ItemStack requested : instance.getRecipeItems()) {
            if (requested != null) {
                int requiredAmount = requested.getAmount() * blueprintAmount;
                if (isRetainMode && requiredAmount > 1) {
                    requiredAmount = Math.max(1, requiredAmount - 1);
                }
                requiredItems.put(requested, requiredAmount);
            }
        }
        for (Map.Entry<ItemStack, Integer> entry : requiredItems.entrySet()) {
            if (!root.contains(new ItemRequest(entry.getKey(), entry.getValue()))) {
                return false;
            }
        }

        // Then fetch the actual items
        for (int i = 0; i < 9; i++) {
            final ItemStack requested = instance.getRecipeItems()[i];
            if (requested != null) {
                final ItemStack fetched = root.getItemStack(new ItemRequest(requested, requested.getAmount()*blueprintAmount));
                actualFetches[i] = fetched;

                final ItemStack fetchedClone = fetched.clone();
                fetchedClone.setAmount(requested.getAmount());
                inputs[i] = fetchedClone;
            } else {
                inputs[i] = null;
            }
        }

        ItemStack crafted = null;

        // Go through each slimefun recipe, test and set the ItemStack if found
        for (Map.Entry<ItemStack[], ItemStack> entry : getRecipeEntries()) {
            if (getRecipeTester(inputs, entry.getKey())) {
                crafted = entry.getValue().clone();
                break;
            }
        }

        // If no item crafted OR result doesn't fit, escape
        if (crafted == null || crafted.getType() == Material.AIR) {
            returnItems(root, actualFetches);
            return false;
        }

        // Push item
        final Location location = blockMenu.getLocation().clone().add(0.5, 1.1, 0.5);
        if (root.isDisplayParticles()) {
            location.getWorld().spawnParticle(Particle.WAX_OFF, location, 0, 0, 4, 0);
        }

        crafted.setAmount(crafted.getAmount()*blueprintAmount);
        blockMenu.pushItem(crafted, OUTPUT_SLOT);
        return true;
    }

    private void returnItems(@Nonnull NetworkRoot root, @Nonnull ItemStack[] inputs) {
        for (ItemStack input : inputs) {
            if (input != null) {
                root.addItemStack(input);
            }
        }
    }

    public void releaseCache(@Nonnull BlockMenu blockMenu) {
        if (blockMenu.hasViewer()) {
            INSTANCE_MAP.remove(blockMenu.getLocation());
        }
    }

    public void setCache(@Nonnull BlockMenu blockMenu, @Nonnull BlueprintInstance blueprintInstance) {
        if (!blockMenu.hasViewer()) {
            INSTANCE_MAP.putIfAbsent(blockMenu.getLocation().clone(), blueprintInstance);
        }
    }


    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(BACKGROUND_SLOTS);
                drawBackground(BLUEPRINT_BACKGROUND_STACK, BLUEPRINT_BACKGROUND);
                drawBackground(OUTPUT_BACKGROUND_STACK, OUTPUT_BACKGROUND);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return ExpansionItems.ADVANCED_AUTO_ANCIENT_ALTAR.canUse(player, false)
                    && Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (AbstractAdvancedAutoCrafter.this.withholding && flow == ItemTransportFlow.WITHDRAW) {
                    return new int[]{OUTPUT_SLOT};
                }
                return new int[0];
            }
        };
    }

    public abstract boolean isVaildBlueprint(SlimefunItem item);
    public abstract Set<Map.Entry<ItemStack[], ItemStack>> getRecipeEntries();
    public abstract boolean getRecipeTester(ItemStack[] inputs, ItemStack[] recipe);
}
