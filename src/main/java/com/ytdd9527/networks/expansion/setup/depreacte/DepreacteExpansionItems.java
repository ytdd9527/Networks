package com.ytdd9527.networks.expansion.setup.depreacte;

import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.advanced.*;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.basic.*;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.blueprint.*;
import com.ytdd9527.networks.expansion.core.item.machine.autocrafter.systems.encoder.*;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.LineTransferPusher;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.LineTransferGrabber;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.LineTransfer;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced.AdvancedLineTransferGrabber;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced.AdvancedLineTransferPusher;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.advanced.AdvancedLineTransfer;
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

import com.ytdd9527.networks.expansion.setup.ExpansionRecipes;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.slimefun.NetworksItemGroups;
import io.github.sefiraat.networks.slimefun.network.NetworkBridge;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DepreacteExpansionItems {

    public static final ExpansionWorkbench NE_EXPANSION_WORKBENCH = new ExpansionWorkbench(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.NE_EXPANSION_WORKBENCH,
            RecipeType.NULL,
        ExpansionRecipes.NE_EXPANSION_WORKBENCH
    );

    public static final CoordinateConfigurator NE_COORDINATE_CONFIGURATOR = new CoordinateConfigurator(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.NE_COORDINATE_CONFIGURATOR,
        RecipeType.NULL,
        ExpansionRecipes.NE_COORDINATE_CONFIGURATOR
    );

    public static final AdvancedImport ADVANCED_IMPORT = new AdvancedImport(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_IMPORT,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_IMPORT
    );

    public static final AdvancedExport ADVANCED_EXPORT = new AdvancedExport(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_EXPORT,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_EXPORT
    );

    public static final AdvancedPurger ADVANCED_PURGER = new AdvancedPurger(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_PURGER,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_PURGER
    );

    public static final AdvancedGreedyBlock ADVANCED_GREEDY_BLOCK = new AdvancedGreedyBlock(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_GREEDY_BLOCK,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_GREEDY_BLOCK
    );

    public static final AdvancedImport NETWORK_CAPACITOR_5 = new AdvancedImport(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.NETWORK_CAPACITOR_5,
        RecipeType.NULL,
        ExpansionRecipes.NETWORK_CAPACITOR_5
    );

    public static final NetworkQuantumStorage ADVANCED_QUANTUM_STORAGE = new NetworkQuantumStorage(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_QUANTUM_STORAGE,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_QUANTUM_STORAGE,NetworkQuantumStorage.getSizes(
        )[10]
    );

    public static final NetworkGridNewStyle NETWORK_GRID_NEW_STYLE = new NetworkGridNewStyle(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.NETWORK_GRID_NEW_STYLE,
        RecipeType.NULL,
        ExpansionRecipes.NETWORK_GRID_NEW_STYLE
    );

    public static final NetworkCraftingGridNewStyle NETWORK_CRAFTING_GRID_NEW_STYLE = new NetworkCraftingGridNewStyle(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.NETWORK_CRAFTING_GRID_NEW_STYLE,
        RecipeType.NULL,
        ExpansionRecipes.NETWORK_CRAFTING_GRID_NEW_STYLE
    );

    public static final NetworkEncodingGridNewStyle NETWORK_ENCODING_GRID_NEW_STYLE = new NetworkEncodingGridNewStyle(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.NETWORK_ENCODING_GRID_NEW_STYLE,
        RecipeType.NULL,
        ExpansionRecipes.NETWORK_ENCODING_GRID_NEW_STYLE
    );

    public static final LineTransferPusher CHAIN_PUSHER = new LineTransferPusher(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.CHAING_PUSHER,
        RecipeType.NULL,
        ExpansionRecipes.LINE_TRANSFER_PUSHER,
        "NE_CHAIN_PUSHER"
    );

    public static final LineTransferPusher CHAIN_PUSHER_PLUS = new LineTransferPusher(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.CHAIN_PUSHER_PLUS,
        RecipeType.NULL,
        ExpansionRecipes.LINE_TRANSFER_PLUS_PUSHER,
        "NE_CHAIN_PUSHER_PLUS"
    );

    public static final LineTransferGrabber CHAIN_GRABBER = new LineTransferGrabber(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.CHAIN_GRABBER,
        RecipeType.NULL,
        ExpansionRecipes.LINE_TRANSFER_GRABBER,
        "NE_CHAIN_GRABBER"
    );

    public static final LineTransferGrabber CHAIN_GRABBER_PLUS = new LineTransferGrabber(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.CHAIN_GRABBER_PLUS,
        RecipeType.NULL,
        ExpansionRecipes.LINE_TRANSFER_PLUS_GRABBER,
        "NE_CHAIN_GRABBER_PLUS"
    );

    public static final LineTransfer CHAIN_DISPATCHER = new LineTransfer(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.CHAIN_DISPATCHER,
        RecipeType.NULL,
        ExpansionRecipes.LINE_TRANSFER,
        "CHAIN_DISPATCHER"
    );

    public static final LineTransfer CHAIN_DISPATCHER_PLUS = new LineTransfer(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.CHAIN_DISPATCHER_PLUS,
        RecipeType.NULL,
        ExpansionRecipes.LINE_TRANSFER_PLUS,
        "CHAIN_DISPATCHER_PLUS"
    );

    public static final AdvancedLineTransferPusher ADVANCED_CHAIN_PUSHER = new AdvancedLineTransferPusher(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_CHAIN_PUSHER,
        RecipeType.NULL,
        ExpansionRecipes.NULL,
        "NE_ADVANCED_CHAING_PUSHER"
    );

    public static final AdvancedLineTransferPusher ADVANCED_CHAIN_PUSHER_PLUS = new AdvancedLineTransferPusher(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_CHAIN_PUSHER_PLUS,
        RecipeType.NULL,
        ExpansionRecipes.NULL,
        "NE_ADVANCED_CHAING_PUSHER_PLUS"
    );

    public static final AdvancedLineTransferGrabber ADVANCED_CHAIN_GRABBER = new AdvancedLineTransferGrabber(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_CHAIN_GRABBER,
        RecipeType.NULL,
        ExpansionRecipes.NULL,
        "NE_ADVANCED_CHAING_GRABBER"
    );

    public static final AdvancedLineTransferGrabber ADVANCED_CHAIN_GRABBER_PLUS = new AdvancedLineTransferGrabber(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_CHAIN_GRABBER_PLUS,
        RecipeType.NULL,
        ExpansionRecipes.NULL,
        "NE_ADVANCED_CHAING_GRABBER_PLUS"
    );

    public static final AdvancedLineTransfer ADVANCED_CHAIN_DISPATCHER = new AdvancedLineTransfer(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_CHAIN_DISPATCHER,
        RecipeType.NULL,
        ExpansionRecipes.NULL,
        "NE_ADVANCED_CHAIN_DISPATCHER"
    );

    public static final AdvancedLineTransfer ADVANCED_CHAIN_DISPATCHER_PLUS = new AdvancedLineTransfer(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_CHAIN_DISPATCHER_PLUS,
        RecipeType.NULL,
        ExpansionRecipes.NULL,
        "NE_ADVANCED_CHAING_DISPATCHER_PLUS"
    );

    public static final CoordinateTransmitter COORDINATE_TRANSMITTER = new CoordinateTransmitter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.COORDINATE_TRANSMITTER,
        RecipeType.NULL,
        ExpansionRecipes.COORDINATE_RECEIVER
    );

    public static final CoordinateReceiver COORDINATE_RECEIVER = new CoordinateReceiver(
            NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.COORDINATE_RECEIVER,
        RecipeType.NULL,
        ExpansionRecipes.COORDINATE_RECEIVER
    );

    //蓝图
    public static final MagicWorkbenchBlueprint MAGIC_WORKBENCH_BLUEPRINT = new MagicWorkbenchBlueprint(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.MAGIC_WORKBENCH_BLUEPRINT,
        RecipeType.NULL,
        ExpansionRecipes.MAGIC_WORKBENCH_BLUEPRINT
    );

    public static final ArmorForgeBlueprint ARMOR_FORGE_BLUEPRINT = new ArmorForgeBlueprint(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ARMOR_FORGE_BLUEPRINT,
        RecipeType.NULL,
        ExpansionRecipes.ARMOR_FORGE_BLUEPRINT
    );

    public static final SmelteryBlueprint SMELTERY_BLUEPRINT = new SmelteryBlueprint(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.SMELTERY_BLUEPRINT,
        RecipeType.NULL,
        ExpansionRecipes.SMELTERY_BLUEPRINT
    );

    public static final QuantumWorkbenchBlueprint QUANTUM_WORKBENCH_BLUEPRINT = new QuantumWorkbenchBlueprint(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.QUANTUM_WORKBENCH_BLUEPRINT,
        RecipeType.NULL,
        ExpansionRecipes.QUANTUM_WORKBENCH_BLUEPRINT
    );

    public static final AncientAltarBlueprint ANCIENT_ALTAR_BLUEPRINT = new AncientAltarBlueprint(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ANCIENT_ALTAR_BLUEPRINT,
        RecipeType.NULL,
        ExpansionRecipes.ANCIENT_ALTAR_BLUEPRINT
    );

    public static final ExpansionWorkbenchBlueprint EXPANSION_WORKBENCH_BLUEPRINT = new ExpansionWorkbenchBlueprint(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.EXPANSION_WORKBENCH_BLUEPRINT,
        RecipeType.NULL,
        ExpansionRecipes.EXPANSION_WORKBENCH_BLUEPRINT
    );

    //编码器
    public static final MagicWorkbenchEncoder MAGIC_WORKBENCH_RECIPE_ENCODER = new MagicWorkbenchEncoder(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.MAGIC_WORKBENCH_RECIPE_ENCODER,
        RecipeType.NULL,
        ExpansionRecipes.MAGIC_WORKBENCH_RECIPE_ENCODER
    );

    public static final ArmorForgeEncoder ARMOR_FORGE_RECIPE_ENCODER = new ArmorForgeEncoder(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ARMOR_FORGE_RECIPE_ENCODER,
        RecipeType.NULL,
        ExpansionRecipes.ARMOR_FORGE_RECIPE_ENCODER
    );

    public static final SmelteryEncoder SMELTERY_RECIPE_ENCODER = new SmelteryEncoder(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.SMELTERY_RECIPE_ENCODER,
        RecipeType.NULL,
        ExpansionRecipes.SMELTERY_RECIPE_ENCODER
    );

    public static final QuantumWorkbenchEncoder QUANTUM_WORKBENCH_RECIPE_ENCODER = new QuantumWorkbenchEncoder(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.QUANTUM_WORKBENCH_RECIPE_ENCODER,
        RecipeType.NULL,
        ExpansionRecipes.QUANTUM_WORKBENCH_RECIPE_ENCODER
    );

    public static final AncientAltarEncoder ANCIENT_ALTAR_RECIPE_ENCODER = new AncientAltarEncoder(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ANCIENT_ALTAR_RECIPE_ENCODER,
        RecipeType.NULL,
        ExpansionRecipes.ANCIENT_ALTAR_RECIPE_ENCODER
    );

    public static final ExpansionWorkbenchEncoder EXPANSION_WORKBENCH_RECIPE_ENCODER = new ExpansionWorkbenchEncoder(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.EXPANSION_WORKBENCH_RECIPE_ENCODER,
        RecipeType.NULL,
        ExpansionRecipes.EXPANSION_WORKBENCH_RECIPE_ENCODER
    );

    //合成机
    public static final AutoMagicWorkbenchCrafter AUTO_MAGIC_WORKBENCH = new AutoMagicWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_MAGIC_WORKBENCH,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_MAGIC_WORKBENCH,
        640,
        false
    );

    public static final AutoMagicWorkbenchCrafter AUTO_MAGIC_WORKBENCH_WITHHOLDING = new AutoMagicWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_MAGIC_WORKBENCH_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_MAGIC_WORKBENCH_WITHHOLDING,
        1280,
        true
    );

    public static final AutoArmorForgeCrafter AUTO_ARMOR_FORGE = new AutoArmorForgeCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_ARMOR_FORGE,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_ARMOR_FORGE,
        640,
        false
    );

    public static final AutoArmorForgeCrafter AUTO_ARMOR_FORGE_WITHHOLDING = new AutoArmorForgeCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_ARMOR_FORGE_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_ARMOR_FORGE_WITHHOLDING,
        1280,
        true
    );

    public static final AutoSmelteryCrafter AUTO_SMELTERY = new AutoSmelteryCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_SMELTERY,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_SMELTERY,
        640,
        false
    );

    public static final AutoSmelteryCrafter AUTO_SMELTERY_WITHHOLDING = new AutoSmelteryCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_SMELTERY_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_SMELTERY_WITHHOLDING,
        1280,
        true
    );

    public static final AutoQuantumWorkbenchCrafter AUTO_QUANTUM_WORKBENCH = new AutoQuantumWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_QUANTUM_WORKBENCH,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_QUANTUM_WORKBENCH,
        640,
        false
    );

    public static final AutoQuantumWorkbenchCrafter AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new AutoQuantumWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
        1280,
        true
    );

    public static final AutoAncientAltarCrafter AUTO_ANCIENT_ALTAR = new AutoAncientAltarCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_ANCIENT_ALTAR,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_ANCIENT_ALTAR,
        640,
        false
    );

    public static final AutoAncientAltarCrafter AUTO_ANCIENT_ALTAR_WITHHOLDING = new AutoAncientAltarCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_ANCIENT_ALTAR_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_ANCIENT_ALTAR_WITHHOLDING,
        1280,
        true
    );

    public static final AutoExpansionWorkbenchCrafter AUTO_EXPANSION_WORKBENCH = new AutoExpansionWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_EXPANSION_WORKBENCH,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_EXPANSION_WORKBENCH,
        640,
        false
    );

    public static final AutoExpansionWorkbenchCrafter AUTO_EXPANSION_WORKBENCH_WITHHOLDING = new AutoExpansionWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
        1280,
        true
    );

    //高级合成机
    public static final AdvancedAutoMagicWorkbenchCrafter ADVANCED_AUTO_MAGIC_WORKBENCH = new AdvancedAutoMagicWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_MAGIC_WORKBENCH,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_MAGIC_WORKBENCH,
        6400,
        false
    );

    public static final AdvancedAutoMagicWorkbenchCrafter ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING = new AdvancedAutoMagicWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING,
        12800,
        true
    );

    public static final AdvancedAutoArmorForgeCrafter ADVANCED_AUTO_ARMOR_FORGE = new AdvancedAutoArmorForgeCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_ARMOR_FORGE,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_ARMOR_FORGE,
        6400,
        false
    );

    public static final AdvancedAutoArmorForgeCrafter ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING = new AdvancedAutoArmorForgeCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING,
        12800,
        true
    );

    public static final AdvancedAutoSmelteryCrafter ADVANCED_AUTO_SMELTERY = new AdvancedAutoSmelteryCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_SMELTERY,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_SMELTERY,
        6400,
        false
    );

    public static final AdvancedAutoSmelteryCrafter ADVANCED_AUTO_SMELTERY_WITHHOLDING = new AdvancedAutoSmelteryCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_SMELTERY_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_SMELTERY_WITHHOLDING,
        12800,
        true
    );

    public static final AdvancedAutoQuantumWorkbenchCrafter ADVANCED_AUTO_QUANTUM_WORKBENCH = new AdvancedAutoQuantumWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_QUANTUM_WORKBENCH,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_QUANTUM_WORKBENCH,
        6400,
        false
    );

    public static final AdvancedAutoQuantumWorkbenchCrafter ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new AdvancedAutoQuantumWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
        12800,
        true
    );

    public static final AdvancedAutoAncientAltarCrafter ADVANCED_AUTO_ANCIENT_ALTAR = new AdvancedAutoAncientAltarCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_ANCIENT_ALTAR,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_ANCIENT_ALTAR,
        6400,
        false
    );

    public static final AdvancedAutoAncientAltarCrafter ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING = new AdvancedAutoAncientAltarCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING,
        12800,
        true
    );

    public static final AdvancedAutoExpansionWorkbenchCrafter ADVANCED_AUTO_EXPANSION_WORKBENCH = new AdvancedAutoExpansionWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_EXPANSION_WORKBENCH,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_EXPANSION_WORKBENCH,
        6400,
        false
    );

    public static final AdvancedAutoExpansionWorkbenchCrafter ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = new AdvancedAutoExpansionWorkbenchCrafter(
        NetworksItemGroups.DISABLED_ITEMS,
        DeprecateExpansionItemStacks.ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
        RecipeType.NULL,
        ExpansionRecipes.ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
        12800,
        true
    );

    public static final NetworkBridge NE_BRIDGE_WHITE = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_WHITE, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_WHITE, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_WHITE, 8),"NE_BRIDGE_WHITE");
    public static final NetworkBridge NE_BRIDGE_LIGHT_GRAY = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_LIGHT_GRAY,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_LIGHT_GRAY, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_LIGHT_GRAY, 8));
    public static final NetworkBridge NE_BRIDGE_GRAY = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_GRAY,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_GRAY, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_GRAY, 8));
    public static final NetworkBridge NE_BRIDGE_BLACK = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_BLACK, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_BLACK, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_BLACK, 8));
    public static final NetworkBridge NE_BRIDGE_BROWN = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_BROWN,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_BROWN, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_BROWN, 8));
    public static final NetworkBridge NE_BRIDGE_RED = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_RED, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_RED, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_RED, 8));
    public static final NetworkBridge NE_BRIDGE_ORANGE = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_ORANGE,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_ORANGE, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_ORANGE, 8));
    public static final NetworkBridge NE_BRIDGE_YELLOW = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_YELLOW, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_YELLOW, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_YELLOW, 8));
    public static final NetworkBridge NE_BRIDGE_LIME = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_LIME,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_LIME, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_LIME, 8));
    public static final NetworkBridge NE_BRIDGE_GREEN = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_GREEN,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_GREEN, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_GREEN, 8));
    public static final NetworkBridge NE_BRIDGE_CYAN = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_CYAN, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_CYAN, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_CYAN, 8));
    public static final NetworkBridge NE_BRIDGE_LIGHT_BLUE = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_LIGHT_BLUE,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_LIGHT_BLUE, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_LIGHT_BLUE, 8));
    public static final NetworkBridge NE_BRIDGE_BLUE = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_BLUE,RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_BLUE, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_BLUE, 8));
    public static final NetworkBridge NE_BRIDGE_PURPLE = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_PURPLE, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_PURPLE, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_PURPLE, 8));
    public static final NetworkBridge NE_BRIDGE_MAGENTA = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_MAGENTA, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_MAGENTA, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_MAGENTA, 8));
    public static final NetworkBridge NE_BRIDGE_PINK = new NetworkBridge(NetworksItemGroups.DISABLED_ITEMS, DeprecateExpansionItemStacks.NE_BRIDGE_PINK, RecipeType.NULL, ExpansionRecipes.NETWORK_BRIDGE_PINK, StackUtils.getAsQuantity(DeprecateExpansionItemStacks.NE_BRIDGE_PINK, 8));

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
        ADVANCED_GREEDY_BLOCK.register(plugin);
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
    }
}
