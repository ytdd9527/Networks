package io.github.sefiraat.networks.slimefun.yitoudaidai;

import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.slimefun.NetworksItemGroups;


import io.github.sefiraat.networks.slimefun.network.NetworkBridge;

import io.github.sefiraat.networks.slimefun.network.NetworkPowerNode;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumWorkbench;


import io.github.sefiraat.networks.slimefun.network.grid.NetworkCraftingGridNewStyle;
import io.github.sefiraat.networks.slimefun.network.grid.NetworkEncodingGridNewStyle;
import io.github.sefiraat.networks.slimefun.network.grid.NetworkGridNewStyle;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.autocrafter.NetworkAutoArmorForgeCrafter;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.autocrafter.NetworkAutoMagicCrafter;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.autocrafter.NetworkAutoQuantumWorkbenchCrafter;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.autocrafter.NetworkAutoSmelteryCrafter;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.autocrafter.NetworkAutoAncientAltarCrafter;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.blueprint.ArmorForgeBlueprint;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.blueprint.MagicWorkbenchBlueprint;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.blueprint.QuantumWorkbenchBlueprint;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.blueprint.SmelteryBlueprint;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.blueprint.AncientAltarBlueprint;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.encoder.NetworkArmorForgeEncoder;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.encoder.NetworkMagicEncoder;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.encoder.NetworkQuantumWorkbenchEncoder;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.encoder.NetworkSmelteryEncoder;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.CraftingSystems.encoder.NetworkAncietAltaryEncoder;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.*;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.numberable.ChainGrabberNumberable;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.numberable.ChainPusherNumberable;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.tools.CoordinateConfigurator;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.workbench.ExpansionWorkbench;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExpansionSlimefunItems {

    //工作台
    public static final ExpansionWorkbench NE_EXPANSION_WORKBENCH;
    //工具
    public static final CoordinateConfigurator NE_COORDINATE_CONFIGURATOR;
    //运输
    public static final CoordinateTransmitter NE_COORDINATE_TRANSMITTER;
    public static final CoordinateReceiver NE_COORDINATE_RECEIVER;
    public static final ChainPusher NE_CHAIN_PUSHER;
    public static final ChainPusher NE_CHAIN_PUSHER_PLUS;
    public static final ChainGrabber NE_CHAIN_GRABBER;
    public static final ChainGrabber NE_CHAIN_GRABBER_PLUS;
    public static final ChainPusherNumberable NE_CHAIN_PUSHER_NUMBERABLE;
    public static final ChainPusherNumberable NE_CHAIN_PUSHER_PLUS_NUMBERABLE;
    public static final ChainGrabberNumberable NE_CHAIN_GRABBER_NUMBERABLE;
    public static final ChainGrabberNumberable NE_CHAIN_GRABBER_PLUS_NUMBERABLE;
    public static final NetChainDispatcher NE_CHAIN_DISPATCHER;
    public static final NetChainDispatcher NE_CHAIN_DISPATCHER_PLUS;

    public static final AdvancedImport NEA_IMPORT;
    public static final AdvancedExport NEA_EXPORT;
    public static final AdvancedPurger NEA_PURGER;
    public static final NetworkPowerNode NETWORK_CAPACITOR_5;
    //网格
    public static final NetworkGridNewStyle NETWORK_GRID_NEW_STYLE;
    public static final NetworkCraftingGridNewStyle NETWORK_CRAFTING_GRID_NEW_STYLE;
    public static final NetworkEncodingGridNewStyle NETWORK_ENCODING_GRID_NEW_STYLE;
    //蓝图
    public static final MagicWorkbenchBlueprint MAGIC_WORKBENCH_BLUEPRINT;
    public static final ArmorForgeBlueprint ARMOR_FORGE_BLUEPRINT;
    public static final SmelteryBlueprint SMELTERY_BLUEPRINT;
    public static final QuantumWorkbenchBlueprint QUANTUM_WORKBENCH_BLUEPRINT;
    public static final AncientAltarBlueprint ANCIENT_ALTAR_BLUEPRINT;
    //编码器
    public static final NetworkMagicEncoder NE_MAGIC_WORKBENCH_RECIPE_ENCODER;
    public static final NetworkArmorForgeEncoder NE_ARMOR_FORGE_RECIPE_ENCODER;
    public static final NetworkSmelteryEncoder NE_SMELTERY_RECIPE_ENCODER;
    public static final NetworkQuantumWorkbenchEncoder NE_QUANTUM_WORKBENCH_RECIPE_ENCODER;
    public static final NetworkAncietAltaryEncoder NE_ANCIENT_ALTAR_RECIPE_ENCODER;
    //合成机器
    public static final NetworkAutoMagicCrafter NE_AUTO_MAGIC_WORKBENCH;
    public static final NetworkAutoMagicCrafter NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING;
    public static final NetworkAutoArmorForgeCrafter NE_AUTO_ARMOR_FORGE;
    public static final NetworkAutoArmorForgeCrafter NE_AUTO_ARMOR_FORGE_WITHHOLDING;
    public static final NetworkAutoSmelteryCrafter NE_AUTO_SMELTERY;
    public static final NetworkAutoSmelteryCrafter NE_AUTO_SMELTERY_WITHHOLDING;
    public static final NetworkAutoQuantumWorkbenchCrafter NE_AUTO_QUANTUM_WORKBENCH;
    public static final NetworkAutoQuantumWorkbenchCrafter NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING;
    public static final NetworkAutoAncientAltarCrafter NE_AUTO_ANCIENT_ALTAR;
    public static final NetworkAutoAncientAltarCrafter NE_AUTO_ANCIENT_ALTAR_WITHHOLDING;
    //网络拓展量子存储
    public static final NetworkQuantumStorage NETWORK_ADVANCED_QUANTUM_STORAGE;

    //网桥
    public static final NetworkBridge NE_BRIDGE_WHITE;
    public static final NetworkBridge NE_BRIDGE_LIGHT_GRAY;
    public static final NetworkBridge NE_BRIDGE_GRAY;
    public static final NetworkBridge NE_BRIDGE_BLACK;
    public static final NetworkBridge NE_BRIDGE_BROWN;
    public static final NetworkBridge NE_BRIDGE_RED;
    public static final NetworkBridge NE_BRIDGE_ORANGE;
    public static final NetworkBridge NE_BRIDGE_YELLOW;
    public static final NetworkBridge NE_BRIDGE_LIME;
    public static final NetworkBridge NE_BRIDGE_GREEN;
    public static final NetworkBridge NE_BRIDGE_CYAN;
    public static final NetworkBridge NE_BRIDGE_LIGHT_BLUE;
    public static final NetworkBridge NE_BRIDGE_BLUE;
    public static final NetworkBridge NE_BRIDGE_PURPLE;
    public static final NetworkBridge NE_BRIDGE_MAGENTA;
    public static final NetworkBridge NE_BRIDGE_PINK;


    static {

        //工作台
        NE_EXPANSION_WORKBENCH = new ExpansionWorkbench(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_EXPANSION_WORKBENCH, RecipeType.ENHANCED_CRAFTING_TABLE, Recipe.NE_EXPANSION_WORKBENCH);
        //工具
        NE_COORDINATE_CONFIGURATOR = new CoordinateConfigurator(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_COORDINATE_CONFIGURATOR, ExpansionWorkbench.TYPE, Recipe.NE_COORDINATE_CONFIGURATOR);

        //运输与存储
        NE_COORDINATE_TRANSMITTER = new CoordinateTransmitter(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_COORDINATE_TRANSMITTER, ExpansionWorkbench.TYPE, Recipe.NE_COORDINATE_TRANSMITTER);
        NE_COORDINATE_RECEIVER = new CoordinateReceiver(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_COORDINATE_RECEIVER, ExpansionWorkbench.TYPE, Recipe.NE_COORDINATE_RECEIVER);
        NE_CHAIN_PUSHER = new ChainPusher(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_PUSHER,"NE_CHAIN_PUSHER");
        NE_CHAIN_PUSHER_PLUS = new ChainPusher(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER_PLUS, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_PUSHER_PLUS,"NE_CHAIN_PUSHER_PLUS");
        NE_CHAIN_GRABBER = new ChainGrabber(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_GRABBER,"NE_CHAIN_GRABBER");
        NE_CHAIN_GRABBER_PLUS = new ChainGrabber(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER_PLUS, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_GRABBER_PLUS,"NE_CHAIN_GRABBER_PLUS");
        NE_CHAIN_PUSHER_NUMBERABLE = new ChainPusherNumberable(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER_NUMBERABLE, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_PUSHER_NUMBERABLE,"NE_CHAIN_PUSHER");
        NE_CHAIN_PUSHER_PLUS_NUMBERABLE = new ChainPusherNumberable(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER_PLUS_NUMBERABLE, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_PUSHER_PLUS_NUMBERABLE,"NE_CHAIN_PUSHER_PLUS");
        NE_CHAIN_GRABBER_NUMBERABLE = new ChainGrabberNumberable(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER_NUMBERABLE, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_GRABBER_NUMBERABLE,"NE_CHAIN_GRABBER");
        NE_CHAIN_GRABBER_PLUS_NUMBERABLE = new ChainGrabberNumberable(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER_PLUS_NUMBERABLE, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_GRABBER_PLUS_NUMBERABLE,"NE_CHAIN_GRABBER_PLUS");
        NE_CHAIN_DISPATCHER = new NetChainDispatcher(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_DISPATCHER, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_DISPATCHER,"NE_CHAIN_DISPATCHER");
        NE_CHAIN_DISPATCHER_PLUS = new NetChainDispatcher(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NE_CHAIN_DISPATCHER_PLUS, ExpansionWorkbench.TYPE, Recipe.NE_CHAIN_DISPATCHER_PLUS,"NE_CHAIN_DISPATCHER_PLUS");


        NEA_IMPORT = new AdvancedImport(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NEA_IMPORT, ExpansionWorkbench.TYPE, Recipe.NEA_IMPORT);
        NEA_EXPORT = new AdvancedExport(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NEA_EXPORT, ExpansionWorkbench.TYPE, Recipe.NEA_EXPORT);
        NEA_PURGER = new AdvancedPurger(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NEA_PURGER, ExpansionWorkbench.TYPE, Recipe.NEA_PURGER);

        NETWORK_CAPACITOR_5 = new NetworkPowerNode(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunItemStacks.NETWORK_CAPACITOR_5, RecipeType.ENHANCED_CRAFTING_TABLE,Recipe.NETWORK_CAPACITOR_5, 100000000);

        NETWORK_ADVANCED_QUANTUM_STORAGE = new NetworkQuantumStorage(NetworksItemGroups.NETWORK_TRANSPORTATION,ExpansionSlimefunItemStacks.NETWORK_ADVANCED_QUANTUM_STORAGE,NetworkQuantumWorkbench.TYPE,Recipe.NETWORK_ADVANCED_QUANTUM_STORAGE,NetworkQuantumStorage.getSizes()[10]);
        NETWORK_ADVANCED_QUANTUM_STORAGE.setSupportsCustomMaxAmount(true);


        //蓝图
        MAGIC_WORKBENCH_BLUEPRINT = new MagicWorkbenchBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.MAGIC_WORKBENCH_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.MAGIC_WORKBENCH_BLUEPRINT);
        ARMOR_FORGE_BLUEPRINT = new ArmorForgeBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.ARMOR_FORGE_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.ARMOR_FORGE_BLUEPRINT);
        SMELTERY_BLUEPRINT = new SmelteryBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.SMELTERY_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.SMELTERY_BLUEPRINT);
        QUANTUM_WORKBENCH_BLUEPRINT = new QuantumWorkbenchBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.QUANTUM_WORKBENCH_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.QUANTUM_WORKBENCH_BLUEPRINT);
        ANCIENT_ALTAR_BLUEPRINT = new AncientAltarBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.ANCIENT_ALTAR_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.ANCIENT_ALTAR_BLUEPRINT);

        //编码
        NE_MAGIC_WORKBENCH_RECIPE_ENCODER = new NetworkMagicEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_MAGIC_WORKBENCH_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_MAGIC_WORKBENCH_RECIPE_ENCODER);
        NE_ARMOR_FORGE_RECIPE_ENCODER = new NetworkArmorForgeEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_ARMOR_FORGE_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_ARMOR_FORGE_RECIPE_ENCODER);
        NE_SMELTERY_RECIPE_ENCODER = new NetworkSmelteryEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_SMELTERY_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_SMELTERY_RECIPE_ENCODER);
        NE_QUANTUM_WORKBENCH_RECIPE_ENCODER = new NetworkQuantumWorkbenchEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_QUANTUM_WORKBENCH_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_QUANTUM_WORKBENCH_RECIPE_ENCODER);
        NE_ANCIENT_ALTAR_RECIPE_ENCODER = new NetworkAncietAltaryEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_ANCIENT_ALTAR_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_ANCIENT_ALTAR_RECIPE_ENCODER);

        //合成机
        NE_AUTO_MAGIC_WORKBENCH = new NetworkAutoMagicCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_MAGIC_WORKBENCH, 640, false);
        NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING = new NetworkAutoMagicCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING, 1280, true);
        NE_AUTO_ARMOR_FORGE = new NetworkAutoArmorForgeCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ARMOR_FORGE, 640, false);
        NE_AUTO_ARMOR_FORGE_WITHHOLDING = new NetworkAutoArmorForgeCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ARMOR_FORGE_WITHHOLDING, 1280, true);
        NE_AUTO_SMELTERY = new NetworkAutoSmelteryCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_SMELTERY, 640, false);
        NE_AUTO_SMELTERY_WITHHOLDING = new NetworkAutoSmelteryCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_SMELTERY_WITHHOLDING, 1280, true);
        NE_AUTO_QUANTUM_WORKBENCH = new NetworkAutoQuantumWorkbenchCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_QUANTUM_WORKBENCH, 640, false);
        NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new NetworkAutoQuantumWorkbenchCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING, 1280, true);
        NE_AUTO_ANCIENT_ALTAR = new NetworkAutoAncientAltarCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ANCIENT_ALTAR, 640, false);
        NE_AUTO_ANCIENT_ALTAR_WITHHOLDING = new NetworkAutoAncientAltarCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING, 1280, true);

        //网格
        NETWORK_GRID_NEW_STYLE = new NetworkGridNewStyle(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NETWORK_GRID_NEW_STYLE,RecipeType.ENHANCED_CRAFTING_TABLE,Recipe.NETWORK_GRID_NEW_STYLE);
        NETWORK_CRAFTING_GRID_NEW_STYLE = new NetworkCraftingGridNewStyle(NetworksItemGroups.NETWORK_ITEMS_EXPANSION,ExpansionSlimefunItemStacks.NETWORK_CRAFTING_GRID_NEW_STYLE,RecipeType.ENHANCED_CRAFTING_TABLE,Recipe.NETWORK_CRAFTING_GRID_NEW_STYLE);
        NETWORK_ENCODING_GRID_NEW_STYLE = new NetworkEncodingGridNewStyle(NetworksItemGroups.NETWORK_ITEMS_EXPANSION,ExpansionSlimefunItemStacks.NETWORK_ENCODING_GRID_NEW_STYLE,RecipeType.ENHANCED_CRAFTING_TABLE,Recipe.NETWORK_ENCODING_GRID_NEW_STYLE);


        //网桥
        NE_BRIDGE_WHITE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_WHITE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_WHITE, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_WHITE, 8),"NE_BRIDGE_WHITE");
        NE_BRIDGE_LIGHT_GRAY = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_LIGHT_GRAY, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_LIGHT_GRAY, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_LIGHT_GRAY, 8));
        NE_BRIDGE_GRAY = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_GRAY, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_GRAY, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_GRAY, 8));
        NE_BRIDGE_BLACK = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_BLACK, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_BLACK, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_BLACK, 8));
        NE_BRIDGE_BROWN = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_BROWN, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_BROWN, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_BROWN, 8));
        NE_BRIDGE_RED = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_RED, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_RED, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_RED, 8));
        NE_BRIDGE_ORANGE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_ORANGE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_ORANGE, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_ORANGE, 8));
        NE_BRIDGE_YELLOW = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_YELLOW, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_YELLOW, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_YELLOW, 8));
        NE_BRIDGE_LIME = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_LIME, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_LIME, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_LIME, 8));
        NE_BRIDGE_GREEN = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_GREEN, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_GREEN, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_GREEN, 8));
        NE_BRIDGE_CYAN = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_CYAN, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_CYAN, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_CYAN, 8));
        NE_BRIDGE_LIGHT_BLUE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_LIGHT_BLUE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_LIGHT_BLUE, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_LIGHT_BLUE, 8));
        NE_BRIDGE_BLUE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_BLUE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_BLUE, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_BLUE, 8));
        NE_BRIDGE_PURPLE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_PURPLE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_PURPLE, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_PURPLE, 8));
        NE_BRIDGE_MAGENTA = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_MAGENTA, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_MAGENTA, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_MAGENTA, 8));
        NE_BRIDGE_PINK = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunItemStacks.NE_BRIDGE_PINK, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_PINK, StackUtils.getAsQuantity(ExpansionSlimefunItemStacks.NE_BRIDGE_PINK, 8));

}
    public static void setup() {
        Networks plugin = Networks.getInstance();


        //工作台
        NE_EXPANSION_WORKBENCH.register(plugin);
        //工具
        NE_COORDINATE_CONFIGURATOR.register(plugin);
        //运输
        NE_COORDINATE_TRANSMITTER.register(plugin);
        NE_COORDINATE_RECEIVER.register(plugin);
        NE_CHAIN_PUSHER.register(plugin);
        NE_CHAIN_PUSHER_PLUS.register(plugin);
        NE_CHAIN_GRABBER.register(plugin);
        NE_CHAIN_GRABBER_PLUS.register(plugin);

        NE_CHAIN_PUSHER_NUMBERABLE.register(plugin);
        NE_CHAIN_PUSHER_PLUS_NUMBERABLE.register(plugin);
        NE_CHAIN_GRABBER_NUMBERABLE.register(plugin);
        NE_CHAIN_GRABBER_PLUS_NUMBERABLE.register(plugin);

        NE_CHAIN_DISPATCHER.register(plugin);
        NE_CHAIN_DISPATCHER_PLUS.register(plugin);

        NEA_IMPORT.register(plugin);
        NEA_EXPORT.register(plugin);
        NEA_PURGER.register(plugin);
        NETWORK_CAPACITOR_5.register(plugin);

        NETWORK_ADVANCED_QUANTUM_STORAGE.register(plugin);

        //蓝图
        MAGIC_WORKBENCH_BLUEPRINT.register(plugin);
        ARMOR_FORGE_BLUEPRINT.register(plugin);
        SMELTERY_BLUEPRINT.register(plugin);
        QUANTUM_WORKBENCH_BLUEPRINT.register(plugin);
        ANCIENT_ALTAR_BLUEPRINT.register(plugin);
        //编码
        NE_MAGIC_WORKBENCH_RECIPE_ENCODER.register(plugin);
        NE_ARMOR_FORGE_RECIPE_ENCODER.register(plugin);
        NE_SMELTERY_RECIPE_ENCODER.register(plugin);
        NE_QUANTUM_WORKBENCH_RECIPE_ENCODER.register(plugin);
        NE_ANCIENT_ALTAR_RECIPE_ENCODER.register(plugin);
        //合成机
        NE_AUTO_MAGIC_WORKBENCH.register(plugin);
        NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING.register(plugin);
        NE_AUTO_ARMOR_FORGE.register(plugin);
        NE_AUTO_ARMOR_FORGE_WITHHOLDING.register(plugin);
        NE_AUTO_SMELTERY.register(plugin);
        NE_AUTO_SMELTERY_WITHHOLDING.register(plugin);
        NE_AUTO_QUANTUM_WORKBENCH.register(plugin);
        NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING.register(plugin);
        NE_AUTO_ANCIENT_ALTAR.register(plugin);
        NE_AUTO_ANCIENT_ALTAR_WITHHOLDING.register(plugin);

        //网格
        NETWORK_GRID_NEW_STYLE.register(plugin);
        NETWORK_CRAFTING_GRID_NEW_STYLE.register(plugin);
        NETWORK_ENCODING_GRID_NEW_STYLE.register(plugin);
        //网桥
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

