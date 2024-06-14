package io.github.sefiraat.networks.slimefun.yitoudaidai;

import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.slimefun.NetworksItemGroups;


import io.github.sefiraat.networks.slimefun.network.NetworkBridge;

import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumWorkbench;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.autocrafter.*;

import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.blueprint.*;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.encoder.*;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.tools.CoordinateConfigurator;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.transportation.*;

import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.workbench.ExpansionWorkbench;
import io.github.sefiraat.networks.utils.StackUtils;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;



import lombok.experimental.UtilityClass;



@UtilityClass
public class ExpansionSlimefunitems {

    //工作台
    public static final ExpansionWorkbench NE_EXPANSION_WORKBENCH;
    //工具
    public static final CoordinateConfigurator NE_COORDINATE_CONFIGURATOR;
    //运输
    public static final CoordinateTransmitter NE_COORDINATE_TRANSMITTER;
    public static final CoordinateReceiver NE_COORDINATE_RECEIVER;
    public static final ChaingPusher NE_CHAING_PUSHER;
    public static final ChaingPusherPlus NE_CHAING_PUSHER_PLUS;
    public static final ChainGrabber NE_CHAING_GRABBER;
    public static final ChainGrabberPlus NE_CHAING_GRABBER_PLUS;
    public static final AdvancedImport NEA_IMPORT;
    public static final AdvancedExport NEA_EXPORT;

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
        NE_EXPANSION_WORKBENCH = new ExpansionWorkbench(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_EXPANSION_WORKBENCH, RecipeType.ENHANCED_CRAFTING_TABLE, Recipe.NE_EXPANSION_WORKBENCH);
        //工具
        NE_COORDINATE_CONFIGURATOR = new CoordinateConfigurator(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_COORDINATE_CONFIGURATOR, ExpansionWorkbench.TYPE, Recipe.NE_COORDINATE_CONFIGURATOR);

        //运输与存储
        NE_COORDINATE_TRANSMITTER = new CoordinateTransmitter(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NE_COORDINATE_TRANSMITTER, ExpansionWorkbench.TYPE, Recipe.NE_COORDINATE_TRANSMITTER);
        NE_COORDINATE_RECEIVER = new CoordinateReceiver(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NE_COORDINATE_RECEIVER, ExpansionWorkbench.TYPE, Recipe.NE_COORDINATE_RECEIVER);
        NE_CHAING_PUSHER = new ChaingPusher(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NE_CHAING_PUSHER, ExpansionWorkbench.TYPE, Recipe.NE_CHAING_PUSHER);
        NE_CHAING_PUSHER_PLUS = new ChaingPusherPlus(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NE_CHAING_PUSHER_PARTICLE, ExpansionWorkbench.TYPE, Recipe.NE_CHAING_PUSHER_PARTICLE);
        NE_CHAING_GRABBER = new ChainGrabber(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NE_CHAING_GRABBER, ExpansionWorkbench.TYPE, Recipe.NE_CHAING_GRABBER);
        NE_CHAING_GRABBER_PLUS = new ChainGrabberPlus(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NE_CHAING_GRABBER_PLUS, ExpansionWorkbench.TYPE, Recipe.NE_CHAING_GRABBER_PLUS);
        NEA_IMPORT = new AdvancedImport(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NEA_IMPORT, ExpansionWorkbench.TYPE, Recipe.NEA_IMPORT);
        NEA_EXPORT = new AdvancedExport(NetworksItemGroups.NETWORK_TRANSPORTATION, ExpansionSlimefunitemStacks.NEA_EXPORT, ExpansionWorkbench.TYPE, Recipe.NEA_EXPORT);
        NETWORK_ADVANCED_QUANTUM_STORAGE = new NetworkQuantumStorage(NetworksItemGroups.NETWORK_TRANSPORTATION,ExpansionSlimefunitemStacks.NETWORK_ADVANCED_QUANTUM_STORAGE,NetworkQuantumWorkbench.TYPE,Recipe.NETWORK_ADVANCED_QUANTUM_STORAGE,NetworkQuantumStorage.getSizes()[10]);
        NETWORK_ADVANCED_QUANTUM_STORAGE.setSupportsCustomMaxAmount(true);


        //蓝图
        MAGIC_WORKBENCH_BLUEPRINT = new MagicWorkbenchBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.MAGIC_WORKBENCH_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.MAGIC_WORKBENCH_BLUEPRINT);
        ARMOR_FORGE_BLUEPRINT = new ArmorForgeBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.ARMOR_FORGE_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.ARMOR_FORGE_BLUEPRINT);
        SMELTERY_BLUEPRINT = new SmelteryBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.SMELTERY_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.SMELTERY_BLUEPRINT);
        QUANTUM_WORKBENCH_BLUEPRINT = new QuantumWorkbenchBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.QUANTUM_WORKBENCH_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.QUANTUM_WORKBENCH_BLUEPRINT);
        ANCIENT_ALTAR_BLUEPRINT = new AncientAltarBlueprint(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.ANCIENT_ALTAR_BLUEPRINT, ExpansionWorkbench.TYPE, Recipe.ANCIENT_ALTAR_BLUEPRINT);

        //编码
        NE_MAGIC_WORKBENCH_RECIPE_ENCODER = new NetworkMagicEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_MAGIC_WORKBENCH_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_MAGIC_WORKBENCH_RECIPE_ENCODER);
        NE_ARMOR_FORGE_RECIPE_ENCODER = new NetworkArmorForgeEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_ARMOR_FORGE_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_ARMOR_FORGE_RECIPE_ENCODER);
        NE_SMELTERY_RECIPE_ENCODER = new NetworkSmelteryEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_SMELTERY_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_SMELTERY_RECIPE_ENCODER);
        NE_QUANTUM_WORKBENCH_RECIPE_ENCODER = new NetworkQuantumWorkbenchEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_QUANTUM_WORKBENCH_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_QUANTUM_WORKBENCH_RECIPE_ENCODER);
        NE_ANCIENT_ALTAR_RECIPE_ENCODER = new NetworkAncietAltaryEncoder(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_ANCIENT_ALTAR_RECIPE_ENCODER, ExpansionWorkbench.TYPE, Recipe.NE_ANCIENT_ALTAR_RECIPE_ENCODER);

        //合成机
        NE_AUTO_MAGIC_WORKBENCH = new NetworkAutoMagicCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_MAGIC_WORKBENCH, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_MAGIC_WORKBENCH, 640, false);
        NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING = new NetworkAutoMagicCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING, 1280, true);
        NE_AUTO_ARMOR_FORGE = new NetworkAutoArmorForgeCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_ARMOR_FORGE, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ARMOR_FORGE, 640, false);
        NE_AUTO_ARMOR_FORGE_WITHHOLDING = new NetworkAutoArmorForgeCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_ARMOR_FORGE_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ARMOR_FORGE_WITHHOLDING, 1280, true);
        NE_AUTO_SMELTERY = new NetworkAutoSmelteryCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_SMELTERY, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_SMELTERY, 640, false);
        NE_AUTO_SMELTERY_WITHHOLDING = new NetworkAutoSmelteryCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_SMELTERY_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_SMELTERY_WITHHOLDING, 1280, true);
        NE_AUTO_QUANTUM_WORKBENCH = new NetworkAutoQuantumWorkbenchCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_QUANTUM_WORKBENCH, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_QUANTUM_WORKBENCH, 640, false);
        NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new NetworkAutoQuantumWorkbenchCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING, 1280, true);
        NE_AUTO_ANCIENT_ALTAR = new NetworkAutoAncientAltarCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_ANCIENT_ALTAR, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ANCIENT_ALTAR, 640, false);
        NE_AUTO_ANCIENT_ALTAR_WITHHOLDING = new NetworkAutoAncientAltarCrafter(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING, ExpansionWorkbench.TYPE, Recipe.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING, 1280, true);




        //网桥
        NE_BRIDGE_WHITE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_WHITE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_WHITE, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_WHITE, 8));
        NE_BRIDGE_LIGHT_GRAY = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_LIGHT_GRAY, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_LIGHT_GRAY, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_LIGHT_GRAY, 8));
        NE_BRIDGE_GRAY = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_GRAY, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_GRAY, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_GRAY, 8));
        NE_BRIDGE_BLACK = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_BLACK, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_BLACK, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_BLACK, 8));
        NE_BRIDGE_BROWN = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_BROWN, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_BROWN, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_BROWN, 8));
        NE_BRIDGE_RED = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_RED, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_RED, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_RED, 8));
        NE_BRIDGE_ORANGE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_ORANGE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_ORANGE, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_ORANGE, 8));
        NE_BRIDGE_YELLOW = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_YELLOW, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_YELLOW, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_YELLOW, 8));
        NE_BRIDGE_LIME = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_LIME, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_LIME, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_LIME, 8));
        NE_BRIDGE_GREEN = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_GREEN, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_GREEN, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_GREEN, 8));
        NE_BRIDGE_CYAN = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_CYAN, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_CYAN, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_CYAN, 8));
        NE_BRIDGE_LIGHT_BLUE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_LIGHT_BLUE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_LIGHT_BLUE, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_LIGHT_BLUE, 8));
        NE_BRIDGE_BLUE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_BLUE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_BLUE, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_BLUE, 8));
        NE_BRIDGE_PURPLE = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_PURPLE, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_PURPLE, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_PURPLE, 8));
        NE_BRIDGE_MAGENTA = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_MAGENTA, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_MAGENTA, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_MAGENTA, 8));
        NE_BRIDGE_PINK = new NetworkBridge(NetworksItemGroups.NETWORK_ITEMS_EXPANSION, ExpansionSlimefunitemStacks.NE_BRIDGE_PINK, ExpansionWorkbench.TYPE, Recipe.NE_BRIDGE_PINK, StackUtils.getAsQuantity(ExpansionSlimefunitemStacks.NE_BRIDGE_PINK, 8));

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
        NE_CHAING_PUSHER.register(plugin);
        NE_CHAING_PUSHER_PLUS.register(plugin);
        NE_CHAING_GRABBER.register(plugin);
        NE_CHAING_GRABBER_PLUS.register(plugin);
        NEA_IMPORT.register(plugin);
        NEA_EXPORT.register(plugin);


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

        //合成机
        NETWORK_ADVANCED_QUANTUM_STORAGE.register(plugin);

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

