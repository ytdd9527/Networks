package com.ytdd9527.networks.expansion.setup;

import com.ytdd9527.networks.expansion.core.cargoexpansion.items.CargoNodeQuickTool;
import com.ytdd9527.networks.expansion.core.cargoexpansion.items.storage.StorageUnitType;
import com.ytdd9527.networks.expansion.core.cargoexpansion.items.storage.StorageUnitUpgradeTable;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.advanced.*;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.basic.*;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.*;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder.*;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.ChainPusher;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.ChainGrabber;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.ChainDispatcher;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced.AdvancedChainGrabber;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced.AdvancedChainPusher;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced.AdvancedChainDispatcher;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.CoordinateTransmitter;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.CoordinateReceiver;
import com.ytdd9527.networks.expansion.core.item.machine.grid.NetworkCraftingGridNewStyle;
import com.ytdd9527.networks.expansion.core.item.machine.grid.NetworkEncodingGridNewStyle;
import com.ytdd9527.networks.expansion.core.item.machine.grid.NetworkGridNewStyle;
import com.ytdd9527.networks.expansion.core.item.machine.manual.ExpansionWorkbench;
import com.ytdd9527.networks.expansion.core.item.machine.network.advanced.AdvancedExport;
import com.ytdd9527.networks.expansion.core.item.machine.network.advanced.AdvancedGreedyBlock;
import com.ytdd9527.networks.expansion.core.item.machine.network.advanced.AdvancedImport;
import com.ytdd9527.networks.expansion.core.item.machine.network.advanced.AdvancedPurger;
import com.ytdd9527.networks.expansion.core.item.tool.CoordinateConfigurator;
import com.ytdd9527.networks.expansion.core.cargoexpansion.items.storage.CargoStorageUnit;

import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.slimefun.NetworksItemGroups;
import io.github.sefiraat.networks.slimefun.network.NetworkBridge;
import io.github.sefiraat.networks.slimefun.network.NetworkPowerNode;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;

@UtilityClass
public class ExpansionItems {

    public static final RecipeType STORAGE_UPGRADE_TABLE;

    static {
        STORAGE_UPGRADE_TABLE = new RecipeType(
                new NamespacedKey(Networks.getInstance(), "STORAGE_UPGRADE_TABLE"),
                ExpansionItemStacks.STORAGE_UNIT_UPGRADE_TABLE,
                StorageUnitUpgradeTable::addRecipe
        );
    }

    public static final ExpansionWorkbench NE_EXPANSION_WORKBENCH = new ExpansionWorkbench(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.NE_EXPANSION_WORKBENCH,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            ExpansionRecipes.NE_EXPANSION_WORKBENCH
    );

    public static final CoordinateConfigurator NE_COORDINATE_CONFIGURATOR = new CoordinateConfigurator(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.NE_COORDINATE_CONFIGURATOR,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.NE_COORDINATE_CONFIGURATOR
    );

    public static final AdvancedImport ADVANCED_IMPORT = new AdvancedImport(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_IMPORT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_IMPORT
    );

    public static final AdvancedExport ADVANCED_EXPORT = new AdvancedExport(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_EXPORT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_EXPORT
    );

    public static final AdvancedPurger ADVANCED_PURGER = new AdvancedPurger(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_PURGER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_PURGER
    );

    public static final AdvancedGreedyBlock ADVANCED_GREEDY_BLOCK = new AdvancedGreedyBlock(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_GREEDY_BLOCK,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_GREEDY_BLOCK
    );

    public static final NetworkPowerNode NETWORK_CAPACITOR_5 = new NetworkPowerNode(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.NETWORK_CAPACITOR_5,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.NETWORK_CAPACITOR_5,
            100000000
    );

    public static final NetworkQuantumStorage ADVANCED_QUANTUM_STORAGE = new NetworkQuantumStorage(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_QUANTUM_STORAGE,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_QUANTUM_STORAGE, NetworkQuantumStorage.getSizes(
    )[10]
    );

    public static final NetworkGridNewStyle NETWORK_GRID_NEW_STYLE = new NetworkGridNewStyle(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.NETWORK_GRID_NEW_STYLE,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.NETWORK_GRID_NEW_STYLE
    );

    public static final NetworkCraftingGridNewStyle NETWORK_CRAFTING_GRID_NEW_STYLE = new NetworkCraftingGridNewStyle(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.NETWORK_CRAFTING_GRID_NEW_STYLE,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.NETWORK_CRAFTING_GRID_NEW_STYLE
    );

    public static final NetworkEncodingGridNewStyle NETWORK_ENCODING_GRID_NEW_STYLE = new NetworkEncodingGridNewStyle(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.NETWORK_ENCODING_GRID_NEW_STYLE,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.NETWORK_ENCODING_GRID_NEW_STYLE
    );

    public static final ChainPusher CHAIN_PUSHER = new ChainPusher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CHAIN_PUSHER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.CHAIN_PUSHER,
            "NE_CHAIN_PUSHER"
    );

    public static final ChainPusher CHAIN_PUSHER_PLUS = new ChainPusher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CHAIN_PUSHER_PLUS,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.CHAIN_PUSHER_PLUS,
            "NE_CHAIN_PUSHER_PLUS"
    );

    public static final ChainGrabber CHAIN_GRABBER = new ChainGrabber(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CHAIN_GRABBER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.CHAIN_GRABBER,
            "NE_CHAIN_GRABBER"
    );

    public static final ChainGrabber CHAIN_GRABBER_PLUS = new ChainGrabber(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CHAIN_GRABBER_PLUS,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.CHAIN_GRABBER_PLUS,
            "NE_CHAIN_GRABBER_PLUS"
    );

    public static final ChainDispatcher CHAIN_DISPATCHER = new ChainDispatcher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CHAIN_DISPATCHER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.CHAIN_DISPATCHER,
            "CHAIN_DISPATCHER"
    );

    public static final ChainDispatcher CHAIN_DISPATCHER_PLUS = new ChainDispatcher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CHAIN_DISPATCHER_PLUS,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.CHAIN_DISPATCHER_PLUS,
            "CHAIN_DISPATCHER_PLUS"
    );

    public static final AdvancedChainPusher ADVANCED_CHAIN_PUSHER = new AdvancedChainPusher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_CHAIN_PUSHER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_CHAIN_PUSHER,
            "NE_ADVANCED_CHAIN_PUSHER"
    );

    public static final AdvancedChainPusher ADVANCED_CHAIN_PUSHER_PLUS = new AdvancedChainPusher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_CHAIN_PUSHER_PLUS,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_CHAIN_PUSHER_PLUS,
            "NE_ADVANCED_CHAIN_PUSHER_PLUS"
    );

    public static final AdvancedChainGrabber ADVANCED_CHAIN_GRABBER = new AdvancedChainGrabber(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_CHAIN_GRABBER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_CHAIN_GRABBER,
            "NE_ADVANCED_CHAIN_GRABBER"
    );

    public static final AdvancedChainGrabber ADVANCED_CHAIN_GRABBER_PLUS = new AdvancedChainGrabber(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_CHAIN_GRABBER_PLUS,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_CHAIN_GRABBER_PLUS,
            "NE_ADVANCED_CHAIN_GRABBER_PLUS"
    );

    public static final AdvancedChainDispatcher ADVANCED_CHAIN_DISPATCHER = new AdvancedChainDispatcher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_CHAIN_DISPATCHER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_CHAIN_DISPATCHER,
            "NE_ADVANCED_CHAIN_DISPATCHER"
    );

    public static final AdvancedChainDispatcher ADVANCED_CHAIN_DISPATCHER_PLUS = new AdvancedChainDispatcher(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.ADVANCED_CHAIN_DISPATCHER_PLUS,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_CHAIN_DISPATCHER_PLUS,
            "NE_ADVANCED_CHAIN_DISPATCHER_PLUS"
    );

    public static final CoordinateTransmitter COORDINATE_TRANSMITTER = new CoordinateTransmitter(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.COORDINATE_TRANSMITTER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.COORDINATE_RECEIVER
    );

    public static final CoordinateReceiver COORDINATE_RECEIVER = new CoordinateReceiver(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.COORDINATE_RECEIVER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.COORDINATE_RECEIVER
    );

    //蓝图
    public static final MagicWorkbenchBlueprint MAGIC_WORKBENCH_BLUEPRINT = new MagicWorkbenchBlueprint(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.MAGIC_WORKBENCH_BLUEPRINT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.MAGIC_WORKBENCH_BLUEPRINT
    );

    public static final ArmorForgeBlueprint ARMOR_FORGE_BLUEPRINT = new ArmorForgeBlueprint(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ARMOR_FORGE_BLUEPRINT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ARMOR_FORGE_BLUEPRINT
    );

    public static final SmelteryBlueprint SMELTERY_BLUEPRINT = new SmelteryBlueprint(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.SMELTERY_BLUEPRINT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.SMELTERY_BLUEPRINT
    );

    public static final QuantumWorkbenchBlueprint QUANTUM_WORKBENCH_BLUEPRINT = new QuantumWorkbenchBlueprint(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.QUANTUM_WORKBENCH_BLUEPRINT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.QUANTUM_WORKBENCH_BLUEPRINT
    );

    public static final AncientAltarBlueprint ANCIENT_ALTAR_BLUEPRINT = new AncientAltarBlueprint(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ANCIENT_ALTAR_BLUEPRINT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ANCIENT_ALTAR_BLUEPRINT
    );

    public static final ExpansionWorkbenchBlueprint EXPANSION_WORKBENCH_BLUEPRINT = new ExpansionWorkbenchBlueprint(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.EXPANSION_WORKBENCH_BLUEPRINT,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.EXPANSION_WORKBENCH_BLUEPRINT
    );

    //编码器
    public static final MagicWorkbenchEncoder MAGIC_WORKBENCH_RECIPE_ENCODER = new MagicWorkbenchEncoder(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.MAGIC_WORKBENCH_RECIPE_ENCODER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.MAGIC_WORKBENCH_RECIPE_ENCODER
    );

    public static final ArmorForgeEncoder ARMOR_FORGE_RECIPE_ENCODER = new ArmorForgeEncoder(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ARMOR_FORGE_RECIPE_ENCODER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ARMOR_FORGE_RECIPE_ENCODER
    );

    public static final SmelteryEncoder SMELTERY_RECIPE_ENCODER = new SmelteryEncoder(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.SMELTERY_RECIPE_ENCODER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.SMELTERY_RECIPE_ENCODER
    );

    public static final QuantumWorkbenchEncoder QUANTUM_WORKBENCH_RECIPE_ENCODER = new QuantumWorkbenchEncoder(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.QUANTUM_WORKBENCH_RECIPE_ENCODER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.QUANTUM_WORKBENCH_RECIPE_ENCODER
    );

    public static final AncientAltarEncoder ANCIENT_ALTAR_RECIPE_ENCODER = new AncientAltarEncoder(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ANCIENT_ALTAR_RECIPE_ENCODER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ANCIENT_ALTAR_RECIPE_ENCODER
    );

    public static final ExpansionWorkbenchEncoder EXPANSION_WORKBENCH_RECIPE_ENCODER = new ExpansionWorkbenchEncoder(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.EXPANSION_WORKBENCH_RECIPE_ENCODER,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.EXPANSION_WORKBENCH_RECIPE_ENCODER
    );

    //合成机
    public static final AutoMagicWorkbenchCrafter AUTO_MAGIC_WORKBENCH = new AutoMagicWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_MAGIC_WORKBENCH,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_MAGIC_WORKBENCH,
            640,
            false
    );

    public static final AutoMagicWorkbenchCrafter AUTO_MAGIC_WORKBENCH_WITHHOLDING = new AutoMagicWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_MAGIC_WORKBENCH_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_MAGIC_WORKBENCH_WITHHOLDING,
            1280,
            true
    );

    public static final AutoArmorForgeCrafter AUTO_ARMOR_FORGE = new AutoArmorForgeCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_ARMOR_FORGE,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_ARMOR_FORGE,
            640,
            false
    );

    public static final AutoArmorForgeCrafter AUTO_ARMOR_FORGE_WITHHOLDING = new AutoArmorForgeCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_ARMOR_FORGE_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_ARMOR_FORGE_WITHHOLDING,
            1280,
            true
    );

    public static final AutoSmelteryCrafter AUTO_SMELTERY = new AutoSmelteryCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_SMELTERY,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_SMELTERY,
            640,
            false
    );

    public static final AutoSmelteryCrafter AUTO_SMELTERY_WITHHOLDING = new AutoSmelteryCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_SMELTERY_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_SMELTERY_WITHHOLDING,
            1280,
            true
    );

    public static final AutoQuantumWorkbenchCrafter AUTO_QUANTUM_WORKBENCH = new AutoQuantumWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_QUANTUM_WORKBENCH,
            640,
            false
    );

    public static final AutoQuantumWorkbenchCrafter AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new AutoQuantumWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
            1280,
            true
    );

    public static final AutoAncientAltarCrafter AUTO_ANCIENT_ALTAR = new AutoAncientAltarCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_ANCIENT_ALTAR,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_ANCIENT_ALTAR,
            640,
            false
    );

    public static final AutoAncientAltarCrafter AUTO_ANCIENT_ALTAR_WITHHOLDING = new AutoAncientAltarCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_ANCIENT_ALTAR_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_ANCIENT_ALTAR_WITHHOLDING,
            1280,
            true
    );

    public static final AutoExpansionWorkbenchCrafter AUTO_EXPANSION_WORKBENCH = new AutoExpansionWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_EXPANSION_WORKBENCH,
            640,
            false
    );

    public static final AutoExpansionWorkbenchCrafter AUTO_EXPANSION_WORKBENCH_WITHHOLDING = new AutoExpansionWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
            1280,
            true
    );

    //高级合成机
    public static final AdvancedAutoMagicWorkbenchCrafter ADVANCED_AUTO_MAGIC_WORKBENCH = new AdvancedAutoMagicWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_MAGIC_WORKBENCH,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_MAGIC_WORKBENCH,
            6400,
            false
    );

    public static final AdvancedAutoMagicWorkbenchCrafter ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING = new AdvancedAutoMagicWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING,
            12800,
            true
    );

    public static final AdvancedAutoArmorForgeCrafter ADVANCED_AUTO_ARMOR_FORGE = new AdvancedAutoArmorForgeCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_ARMOR_FORGE,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_ARMOR_FORGE,
            6400,
            false
    );

    public static final AdvancedAutoArmorForgeCrafter ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING = new AdvancedAutoArmorForgeCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING,
            12800,
            true
    );

    public static final AdvancedAutoSmelteryCrafter ADVANCED_AUTO_SMELTERY = new AdvancedAutoSmelteryCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_SMELTERY,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_SMELTERY,
            6400,
            false
    );

    public static final AdvancedAutoSmelteryCrafter ADVANCED_AUTO_SMELTERY_WITHHOLDING = new AdvancedAutoSmelteryCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_SMELTERY_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_SMELTERY_WITHHOLDING,
            12800,
            true
    );

    public static final AdvancedAutoQuantumWorkbenchCrafter ADVANCED_AUTO_QUANTUM_WORKBENCH = new AdvancedAutoQuantumWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_QUANTUM_WORKBENCH,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_QUANTUM_WORKBENCH,
            6400,
            false
    );

    public static final AdvancedAutoQuantumWorkbenchCrafter ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new AdvancedAutoQuantumWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
            12800,
            true
    );

    public static final AdvancedAutoAncientAltarCrafter ADVANCED_AUTO_ANCIENT_ALTAR = new AdvancedAutoAncientAltarCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_ANCIENT_ALTAR,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_ANCIENT_ALTAR,
            6400,
            false
    );

    public static final AdvancedAutoAncientAltarCrafter ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING = new AdvancedAutoAncientAltarCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING,
            12800,
            true
    );

    public static final AdvancedAutoExpansionWorkbenchCrafter ADVANCED_AUTO_EXPANSION_WORKBENCH = new AdvancedAutoExpansionWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_EXPANSION_WORKBENCH,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_EXPANSION_WORKBENCH,
            6400,
            false
    );

    public static final AdvancedAutoExpansionWorkbenchCrafter ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = new AdvancedAutoExpansionWorkbenchCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
            ExpansionWorkbench.TYPE,
            ExpansionRecipes.ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
            12800,
            true
    );

    public static final AdvancedAutoEnhancedCraftingTableCrafter ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE = new AdvancedAutoEnhancedCraftingTableCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            ExpansionRecipes.ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE,
            6400,
            false
    );

    public static final AdvancedAutoEnhancedCraftingTableCrafter ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE_WITHHOLDING = new AdvancedAutoEnhancedCraftingTableCrafter(
            NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
            ExpansionItemStacks.ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE_WITHHOLDING,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            ExpansionRecipes.ADVANCED_AUTO_ENHANCED_CRAFTING_TABLE_WITHHOLDING,
            12800,
            true
    );

    public static final CargoNodeQuickTool CARGO_NODE_QUICK_TOOL = new CargoNodeQuickTool(
            NetworksItemGroups.TOOLS,
            ExpansionItemStacks.CARGO_NODE_QUICK_TOOL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            ExpansionRecipes.CARGO_NODE_QUICK_TOOL
    );

    public static final StorageUnitUpgradeTable STORAGE_UNIT_UPGRADE_TABLE = new StorageUnitUpgradeTable(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.STORAGE_UNIT_UPGRADE_TABLE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            ExpansionRecipes.STORAGE_UNIT_UPGRADE_TABLE
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_1 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_1,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_1,
            StorageUnitType.TINY
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_2 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_2,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_2,
            StorageUnitType.MINI
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_3 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_3,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_3,
            StorageUnitType.SMALL
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_4 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_4,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_4,
            StorageUnitType.MEDIUM
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_5 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_5,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_5,
            StorageUnitType.LARGE
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_6 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_6,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_6,
            StorageUnitType.ENHANCED
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_7 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_7,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_7,
            StorageUnitType.ADVANCED
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_8 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_8,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_8,
            StorageUnitType.EXTRA
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_9 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_9,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_9,
            StorageUnitType.ULTRA
    );

    public static final CargoStorageUnit CARGO_STORAGE_UNIT_10 = new CargoStorageUnit(
            NetworksItemGroups.NETWORK_TRANSPOR_TATION,
            ExpansionItemStacks.CARGO_STORAGE_UNIT_10,
            STORAGE_UPGRADE_TABLE,
            ExpansionRecipes.CARGO_STORAGE_UNIT_10,
            StorageUnitType.END_GAME
    );

    public static final NetworkBridge NE_BRIDGE_WHITE = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_WHITE,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_WHITE,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_WHITE, 8),
          "NE_BRIDGE_WHITE"
    );

    public static final NetworkBridge NE_BRIDGE_LIGHT_GRAY = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_LIGHT_GRAY,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_LIGHT_GRAY,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_LIGHT_GRAY, 8)
    );
    public static final NetworkBridge NE_BRIDGE_GRAY = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_GRAY,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_GRAY,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_GRAY, 8)
    );
    public static final NetworkBridge NE_BRIDGE_BLACK = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_BLACK,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_BLACK,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_BLACK, 8)
    );
    public static final NetworkBridge NE_BRIDGE_BROWN = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_BROWN,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_BROWN,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_BROWN, 8)
    );
    public static final NetworkBridge NE_BRIDGE_RED = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_RED,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_RED,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_RED, 8)
    );
    public static final NetworkBridge NE_BRIDGE_ORANGE = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_ORANGE,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_ORANGE,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_ORANGE, 8)
    );
    public static final NetworkBridge NE_BRIDGE_YELLOW = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_YELLOW,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_YELLOW,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_YELLOW, 8)
    );
    public static final NetworkBridge NE_BRIDGE_LIME = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_LIME,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_LIME,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_LIME, 8)
    );
    public static final NetworkBridge NE_BRIDGE_GREEN = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_GREEN,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_GREEN,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_GREEN, 8)
    );
    public static final NetworkBridge NE_BRIDGE_CYAN = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_CYAN,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_CYAN,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_CYAN, 8)
    );
    public static final NetworkBridge NE_BRIDGE_LIGHT_BLUE = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_LIGHT_BLUE,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_LIGHT_BLUE,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_LIGHT_BLUE, 8)
    );
    public static final NetworkBridge NE_BRIDGE_BLUE = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_BLUE,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_BLUE,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_BLUE, 8)
    );
    public static final NetworkBridge NE_BRIDGE_PURPLE = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_PURPLE,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_PURPLE,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_PURPLE, 8)
    );
    public static final NetworkBridge NE_BRIDGE_MAGENTA = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_MAGENTA,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_MAGENTA,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_MAGENTA, 8)
    );
    public static final NetworkBridge NE_BRIDGE_PINK = new NetworkBridge(
          NetworksItemGroups.NETWORK_ITEMS_EXPANSION,
          ExpansionItemStacks.NE_BRIDGE_PINK,
          ExpansionWorkbench.TYPE,
          ExpansionRecipes.NE_BRIDGE_PINK,
          StackUtils.getAsQuantity(ExpansionItemStacks.NE_BRIDGE_PINK, 8)
    );

    static {
        ADVANCED_QUANTUM_STORAGE.setSupportsCustomMaxAmount(true);
    }

    public static void setup() {
        Networks plugin = Networks.getInstance();

        NE_EXPANSION_WORKBENCH.register(plugin);
        NE_COORDINATE_CONFIGURATOR.register(plugin);

        ADVANCED_IMPORT.register(plugin);
        ADVANCED_EXPORT.register(plugin);
        ADVANCED_PURGER.register(plugin);
        NETWORK_CAPACITOR_5.register(plugin);
        ADVANCED_QUANTUM_STORAGE.register(plugin);

        NETWORK_GRID_NEW_STYLE.register(plugin);
        NETWORK_CRAFTING_GRID_NEW_STYLE.register(plugin);
        NETWORK_ENCODING_GRID_NEW_STYLE.register(plugin);

        COORDINATE_TRANSMITTER.register(plugin);
        COORDINATE_RECEIVER.register(plugin);

        CHAIN_PUSHER.register(plugin);
        CHAIN_GRABBER.register(plugin);
        CHAIN_DISPATCHER.register(plugin);

        CHAIN_PUSHER_PLUS.register(plugin);
        CHAIN_GRABBER_PLUS.register(plugin);
        CHAIN_DISPATCHER_PLUS.register(plugin);

        ADVANCED_CHAIN_PUSHER.register(plugin);
        ADVANCED_CHAIN_GRABBER.register(plugin);
        ADVANCED_CHAIN_DISPATCHER.register(plugin);
        ADVANCED_CHAIN_PUSHER_PLUS.register(plugin);
        ADVANCED_CHAIN_GRABBER_PLUS.register(plugin);
        ADVANCED_CHAIN_DISPATCHER_PLUS.register(plugin);

        MAGIC_WORKBENCH_BLUEPRINT.register(plugin);
        ARMOR_FORGE_BLUEPRINT.register(plugin);
        SMELTERY_BLUEPRINT.register(plugin);
        QUANTUM_WORKBENCH_BLUEPRINT.register(plugin);
        ANCIENT_ALTAR_BLUEPRINT.register(plugin);
        EXPANSION_WORKBENCH_BLUEPRINT.register(plugin);
        MAGIC_WORKBENCH_RECIPE_ENCODER.register(plugin);
        ARMOR_FORGE_RECIPE_ENCODER.register(plugin);
        SMELTERY_RECIPE_ENCODER.register(plugin);
        QUANTUM_WORKBENCH_RECIPE_ENCODER.register(plugin);
        ANCIENT_ALTAR_RECIPE_ENCODER.register(plugin);
        EXPANSION_WORKBENCH_RECIPE_ENCODER.register(plugin);

        AUTO_MAGIC_WORKBENCH.register(plugin);
        AUTO_MAGIC_WORKBENCH_WITHHOLDING.register(plugin);
        AUTO_ARMOR_FORGE.register(plugin);
        AUTO_ARMOR_FORGE_WITHHOLDING.register(plugin);
        AUTO_SMELTERY.register(plugin);
        AUTO_SMELTERY_WITHHOLDING.register(plugin);
        AUTO_QUANTUM_WORKBENCH.register(plugin);
        AUTO_QUANTUM_WORKBENCH_WITHHOLDING.register(plugin);
        AUTO_ANCIENT_ALTAR.register(plugin);
        AUTO_ANCIENT_ALTAR_WITHHOLDING.register(plugin);
        AUTO_EXPANSION_WORKBENCH.register(plugin);
        AUTO_EXPANSION_WORKBENCH_WITHHOLDING.register(plugin);

        ADVANCED_AUTO_MAGIC_WORKBENCH.register(plugin);
        ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING.register(plugin);
        ADVANCED_AUTO_ARMOR_FORGE.register(plugin);
        ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING.register(plugin);
        ADVANCED_AUTO_SMELTERY.register(plugin);
        ADVANCED_AUTO_SMELTERY_WITHHOLDING.register(plugin);
        ADVANCED_AUTO_QUANTUM_WORKBENCH.register(plugin);
        ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING.register(plugin);
        ADVANCED_AUTO_ANCIENT_ALTAR.register(plugin);
        ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING.register(plugin);
        ADVANCED_AUTO_EXPANSION_WORKBENCH.register(plugin);
        ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING.register(plugin);

        NE_BRIDGE_WHITE.register(plugin);
        NE_BRIDGE_LIGHT_GRAY.register(plugin);
        NE_BRIDGE_GRAY.register(plugin);
        NE_BRIDGE_BLACK.register(plugin);
        NE_BRIDGE_BROWN.register(plugin);
        NE_BRIDGE_RED.register(plugin);
        NE_BRIDGE_ORANGE.register(plugin);
        NE_BRIDGE_YELLOW.register(plugin);
        NE_BRIDGE_LIME.register(plugin);
        NE_BRIDGE_GREEN.register(plugin);
        NE_BRIDGE_CYAN.register(plugin);
        NE_BRIDGE_LIGHT_BLUE.register(plugin);
        NE_BRIDGE_BLUE.register(plugin);
        NE_BRIDGE_PURPLE.register(plugin);
        NE_BRIDGE_MAGENTA.register(plugin);
        NE_BRIDGE_PINK.register(plugin);

        CARGO_NODE_QUICK_TOOL.register(plugin);
        STORAGE_UNIT_UPGRADE_TABLE.register(plugin);
        CARGO_STORAGE_UNIT_1.register(plugin);
        CARGO_STORAGE_UNIT_2.register(plugin);
        CARGO_STORAGE_UNIT_3.register(plugin);
        CARGO_STORAGE_UNIT_4.register(plugin);
        CARGO_STORAGE_UNIT_5.register(plugin);
        CARGO_STORAGE_UNIT_6.register(plugin);
        CARGO_STORAGE_UNIT_7.register(plugin);
        CARGO_STORAGE_UNIT_8.register(plugin);
        CARGO_STORAGE_UNIT_9.register(plugin);
        CARGO_STORAGE_UNIT_10.register(plugin);
    }
}
