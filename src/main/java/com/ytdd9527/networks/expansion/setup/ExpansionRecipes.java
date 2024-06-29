package com.ytdd9527.networks.expansion.setup;

import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static io.github.sefiraat.networks.slimefun.NetworkSlimefunItems.*;
import static io.github.sefiraat.networks.slimefun.NetworkSlimefunItems.NETWORK_EXPORT;

public class ExpansionRecipes {

    public static final ItemStack[] NULL = new ItemStack[] {
        null, null, null,
        null, null, null,
        null, null, null
    };

    //工具
    public static final ItemStack[] NE_COORDINATE_CONFIGURATOR = new ItemStack[] {
        null, RADIOACTIVE_OPTIC_STAR.getItem(), null,
        null, NETWORK_CONFIGURATOR.getItem(), null,
        null, INTERDIMENSIONAL_PRESENCE.getItem(), null
    };

    //工作台
    public static final ItemStack[] NE_EXPANSION_WORKBENCH = new ItemStack[] {
        EMPOWERED_AI_CORE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, EMPOWERED_AI_CORE.getItem(),
        OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(),
        EMPOWERED_AI_CORE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, EMPOWERED_AI_CORE.getItem()
    };

    //坐标传输器
    public static final ItemStack[] COORDINATE_TRANSMITTER = new ItemStack[] {
        NETWORK_WIRELESS_TRANSMITTER.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_WIRELESS_TRANSMITTER.getItem(),
        ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.CHAIN_GRABBER, ADVANCED_NANOBOTS.getItem(),
        NETWORK_WIRELESS_TRANSMITTER.getItem(), PRISTINE_AI_CORE.getItem(), NETWORK_WIRELESS_TRANSMITTER.getItem()
    };

    //坐标接收器
    public static final ItemStack[] COORDINATE_RECEIVER = new ItemStack[] {
        NETWORK_WIRELESS_RECEIVER.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_WIRELESS_RECEIVER.getItem(),
        ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.CHAIN_GRABBER, ADVANCED_NANOBOTS.getItem(),
        NETWORK_WIRELESS_RECEIVER.getItem(), AI_CORE.getItem(), NETWORK_WIRELESS_RECEIVER.getItem()
    };

    //链式推送
    public static final ItemStack[] CHAIN_PUSHER = new ItemStack[] {
        NETWORK_PUSHER.getItem(), PRISTINE_AI_CORE.getItem(), NETWORK_PUSHER.getItem(),
        OPTIC_CABLE.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), OPTIC_CABLE.getItem(),
        NETWORK_PUSHER.getItem(), NETWORK_EXPORT.getItem(), NETWORK_PUSHER.getItem()
    };

    public static final ItemStack[] CHAIN_PUSHER_PLUS = new ItemStack[] {
        ExpansionItemStacks.CHAIN_GRABBER, PRISTINE_AI_CORE.getItem(), ExpansionItemStacks.CHAIN_GRABBER,
        INTERDIMENSIONAL_PRESENCE.getItem(), new ItemStack(Material.SEA_LANTERN), INTERDIMENSIONAL_PRESENCE.getItem(),
        ExpansionItemStacks.CHAIN_GRABBER, NETWORK_IMPORT.getItem(), ExpansionItemStacks.CHAIN_GRABBER
    };

    //链式抓取
    public static final ItemStack[] CHAIN_GRABBER = new ItemStack[] {
        NETWORK_GRABBER.getItem(), PRISTINE_AI_CORE.getItem(), NETWORK_GRABBER.getItem(),
        OPTIC_CABLE.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), OPTIC_CABLE.getItem(),
        NETWORK_GRABBER.getItem(), NETWORK_IMPORT.getItem(), NETWORK_GRABBER.getItem()
    };

    public static final ItemStack[]CHAIN_GRABBER_PLUS = new ItemStack[] {
        ExpansionItemStacks.CHAIN_GRABBER, PRISTINE_AI_CORE.getItem(), ExpansionItemStacks.CHAIN_GRABBER,
        INTERDIMENSIONAL_PRESENCE.getItem(), new ItemStack(Material.SEA_LANTERN),INTERDIMENSIONAL_PRESENCE.getItem(),
        ExpansionItemStacks.CHAIN_GRABBER, NETWORK_IMPORT.getItem(), ExpansionItemStacks.CHAIN_GRABBER
    };
    //链式调度器
    public static final ItemStack[] CHAIN_DISPATCHER = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        ExpansionItemStacks.CHAIN_GRABBER, OPTIC_CABLE.getItem(), ExpansionItemStacks.CHAIN_GRABBER,
        NETWORK_BRIDGE.getItem(), NETWORK_EXPORT.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] CHAIN_DISPATCHER_PLUS = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        ExpansionItemStacks.CHAIN_GRABBER_PLUS, OPTIC_CABLE.getItem(), ExpansionItemStacks.CHAIN_GRABBER_PLUS,
        NETWORK_BRIDGE.getItem(), NETWORK_EXPORT.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_CHAIN_DISPATCHER_NUMBERABLE = new ItemStack[] {
        OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
        OPTIC_CABLE.getItem(), ExpansionItemStacks.CHAIN_DISPATCHER, OPTIC_CABLE.getItem(),
        OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] NE_CHAIN_DISPATCHER_PLUS_NUMBERABLE = new ItemStack[] {
        OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
        OPTIC_CABLE.getItem(), ExpansionItemStacks.CHAIN_DISPATCHER_PLUS, OPTIC_CABLE.getItem(),
        OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] ADVANCED_IMPORT = new ItemStack[] {
        NETWORK_IMPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_IMPORT.getItem(),
        ADVANCED_NANOBOTS.getItem(), PRISTINE_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(),
        NETWORK_IMPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_IMPORT.getItem()
    };

    public static final ItemStack[] ADVANCED_EXPORT = new ItemStack[] {
        NETWORK_EXPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_EXPORT.getItem(),
        ADVANCED_NANOBOTS.getItem(), PRISTINE_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(),
        NETWORK_EXPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_EXPORT.getItem()
    };

    public static final ItemStack[] ADVANCED_PURGER = new ItemStack[] {
        NETWORK_TRASH.getItem(), OPTIC_CABLE.getItem(), NETWORK_TRASH.getItem(),
        OPTIC_CABLE.getItem(), ADVANCED_NANOBOTS.getItem(), OPTIC_CABLE.getItem(),
        NETWORK_TRASH.getItem(), OPTIC_CABLE.getItem(), NETWORK_TRASH.getItem()
    };

    public static final ItemStack[] ADVANCED_GREEDY_BLOCK = {
        NETWORK_GREEDY_BLOCK.getItem(), OPTIC_CABLE.getItem(), NETWORK_GREEDY_BLOCK.getItem(),
        OPTIC_CABLE.getItem(), ADVANCED_NANOBOTS.getItem(), OPTIC_CABLE.getItem(),
        NETWORK_GREEDY_BLOCK.getItem(), OPTIC_CABLE.getItem(), NETWORK_GREEDY_BLOCK.getItem()
    };

    public static final ItemStack[] NETWORK_CAPACITOR_5 = new ItemStack[] {
        NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(),
        NETWORK_CAPACITOR_4.getItem(), SlimefunItems.ENERGIZED_CAPACITOR, NETWORK_CAPACITOR_4.getItem(),
        NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem()
    };

    public static final ItemStack[] MAGIC_WORKBENCH_BLUEPRINT = new ItemStack[] {
        OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(),
        OPTIC_CABLE.getItem(), CRAFTING_BLUEPRINT.getItem(), OPTIC_CABLE.getItem(),
        new ItemStack(Material.BOOKSHELF), new ItemStack(Material.CRAFTING_TABLE), new ItemStack(Material.DISPENSER)
    };

    public static final ItemStack[] ARMOR_FORGE_BLUEPRINT = new ItemStack[] {
        OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(),
        CRAFTING_BLUEPRINT.getItem(), new ItemStack(Material.ANVIL), CRAFTING_BLUEPRINT.getItem(),
        OPTIC_CABLE.getItem(), new ItemStack(Material.DISPENSER), OPTIC_CABLE.getItem()
    };

    public static final ItemStack[] SMELTERY_BLUEPRINT = new ItemStack[] {
        CRAFTING_BLUEPRINT.getItem(), new ItemStack(Material.NETHER_BRICK_FENCE), CRAFTING_BLUEPRINT.getItem(),
        new ItemStack(Material.NETHER_BRICKS), new ItemStack(Material.DISPENSER), new ItemStack(Material.NETHER_BRICKS),
        CRAFTING_BLUEPRINT.getItem(), new ItemStack(Material.FLINT_AND_STEEL), CRAFTING_BLUEPRINT.getItem()
    };

    public static final ItemStack[] QUANTUM_WORKBENCH_BLUEPRINT = new ItemStack[] {
        OPTIC_CABLE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, OPTIC_CABLE.getItem(),
        OPTIC_CABLE.getItem(), CRAFTING_BLUEPRINT.getItem(), OPTIC_CABLE.getItem(),
        OPTIC_CABLE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, OPTIC_CABLE.getItem()
    };

    public static final ItemStack[] ANCIENT_ALTAR_BLUEPRINT = new ItemStack[] {
        SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL,
        CRAFTING_BLUEPRINT.getItem(), SlimefunItems.ANCIENT_ALTAR, CRAFTING_BLUEPRINT.getItem(),
        SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL
    };

    public static final ItemStack[] EXPANSION_WORKBENCH_BLUEPRINT =  new ItemStack[] {
        NetworksSlimefunItemStacks.NETWORK_BRIDGE, SlimefunItems.ANCIENT_PEDESTAL, NetworksSlimefunItemStacks.NETWORK_BRIDGE,
        CRAFTING_BLUEPRINT.getItem(), ExpansionItemStacks.NE_EXPANSION_WORKBENCH, CRAFTING_BLUEPRINT.getItem(),
        NetworksSlimefunItemStacks.NETWORK_BRIDGE, SlimefunItems.ANCIENT_PEDESTAL, NetworksSlimefunItemStacks.NETWORK_BRIDGE
    };

    public static final ItemStack[] MAGIC_WORKBENCH_RECIPE_ENCODER = new ItemStack[] {
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
        ExpansionItemStacks.MAGIC_WORKBENCH_BLUEPRINT, ExpansionItemStacks.AUTO_MAGIC_WORKBENCH, ExpansionItemStacks.MAGIC_WORKBENCH_BLUEPRINT,
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD
    };

    public static final ItemStack[] ARMOR_FORGE_RECIPE_ENCODER = new ItemStack[] {
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
        ExpansionItemStacks.ARMOR_FORGE_BLUEPRINT, ExpansionItemStacks.AUTO_ARMOR_FORGE, ExpansionItemStacks.ARMOR_FORGE_BLUEPRINT,
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD
    };

    public static final ItemStack[] SMELTERY_RECIPE_ENCODER = new ItemStack[] {
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
        ExpansionItemStacks.SMELTERY_BLUEPRINT, ExpansionItemStacks.AUTO_SMELTERY, ExpansionItemStacks.SMELTERY_BLUEPRINT,
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD
    };

    public static final ItemStack[] QUANTUM_WORKBENCH_RECIPE_ENCODER = new ItemStack[] {
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
        ExpansionItemStacks.QUANTUM_WORKBENCH_BLUEPRINT, ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH, ExpansionItemStacks.QUANTUM_WORKBENCH_BLUEPRINT,
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD
    };

    public static final ItemStack[] ANCIENT_ALTAR_RECIPE_ENCODER = new ItemStack[] {
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
        ExpansionItemStacks.ANCIENT_ALTAR_BLUEPRINT, ExpansionItemStacks.AUTO_ANCIENT_ALTAR, ExpansionItemStacks.ANCIENT_ALTAR_BLUEPRINT,
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD
    };

    public static final ItemStack[] EXPANSION_WORKBENCH_RECIPE_ENCODER = new ItemStack[] {
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
        ExpansionItemStacks.EXPANSION_WORKBENCH_BLUEPRINT, ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH, ExpansionItemStacks.EXPANSION_WORKBENCH_BLUEPRINT,
        SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD
    };
    //合成机
    public static final ItemStack[] AUTO_MAGIC_WORKBENCH = new ItemStack[] {
        OPTIC_GLASS.getItem(), OPTIC_CABLE.getItem(), OPTIC_GLASS.getItem(),
        ExpansionItemStacks.MAGIC_WORKBENCH_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionItemStacks.MAGIC_WORKBENCH_BLUEPRINT,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_MAGIC_WORKBENCH_WITHHOLDING = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.AUTO_MAGIC_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_MAGIC_WORKBENCH,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_ARMOR_FORGE = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.ARMOR_AUTO_CRAFTER, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.ARMOR_FORGE_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionItemStacks.ARMOR_FORGE_BLUEPRINT,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_ARMOR_FORGE_WITHHOLDING = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.AUTO_ARMOR_FORGE, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_ARMOR_FORGE,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_SMELTERY = new ItemStack[] {
        OPTIC_GLASS.getItem(), ExpansionItemStacks.SMELTERY_BLUEPRINT, OPTIC_GLASS.getItem(),
        SlimefunItems.ELECTRIC_SMELTERY_2, SIMPLE_NANOBOTS.getItem(), SlimefunItems.ELECTRIC_SMELTERY_2,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_SMELTERY_WITHHOLDING = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.AUTO_SMELTERY, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_SMELTERY,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_QUANTUM_WORKBENCH = new ItemStack[] {
        OPTIC_GLASS.getItem(), NETWORK_QUANTUM_WORKBENCH.getItem(), OPTIC_GLASS.getItem(),
        ExpansionItemStacks.QUANTUM_WORKBENCH_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionItemStacks.QUANTUM_WORKBENCH_BLUEPRINT,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_ANCIENT_ALTAR = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.ANCIENT_ALTAR_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionItemStacks.ANCIENT_ALTAR_BLUEPRINT,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_ANCIENT_ALTAR_WITHHOLDING = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.AUTO_ANCIENT_ALTAR, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_ANCIENT_ALTAR,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_EXPANSION_WORKBENCH = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.EXPANSION_WORKBENCH_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionItemStacks.EXPANSION_WORKBENCH_BLUEPRINT,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem()
    };

    public static final ItemStack[] AUTO_EXPANSION_WORKBENCH_WITHHOLDING = new ItemStack[] {
        OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
        ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH,
        OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem()
    };
    //高级合成机
    public static final ItemStack[] ADVANCED_AUTO_CRAFTER = new ItemStack[] {
        NETWORK_AUTO_CRAFTER.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_AUTO_CRAFTER.getItem(),
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        NETWORK_AUTO_CRAFTER.getItem(), NETWORK_RECIPE_ENCODER.getItem(), NETWORK_AUTO_CRAFTER.getItem()
    };

    public static final ItemStack[] ADVANCED_AUTO_CRAFTER_WITHHOLDING = new ItemStack[] {
        NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(),
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(),NETWORK_RECIPE_ENCODER.getItem(),NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem()
    };

    public static final ItemStack[] ADVANCED_AUTO_MAGIC_WORKBENCH = new ItemStack[] {
        ExpansionItemStacks.AUTO_MAGIC_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_MAGIC_WORKBENCH,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_MAGIC_WORKBENCH, ExpansionItemStacks.MAGIC_WORKBENCH_RECIPE_ENCODER, ExpansionItemStacks.AUTO_MAGIC_WORKBENCH
    };

    public static final ItemStack[] ADVANCED_AUTO_MAGIC_WORKBENCH_WITHHOLDING = new ItemStack[] {
        ExpansionItemStacks.AUTO_MAGIC_WORKBENCH_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_MAGIC_WORKBENCH_WITHHOLDING,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_MAGIC_WORKBENCH_WITHHOLDING, ExpansionItemStacks.MAGIC_WORKBENCH_RECIPE_ENCODER, ExpansionItemStacks.AUTO_MAGIC_WORKBENCH_WITHHOLDING
    };

    public static final ItemStack[] ADVANCED_AUTO_ARMOR_FORGE = new ItemStack[] {
        ExpansionItemStacks.AUTO_ARMOR_FORGE, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_ARMOR_FORGE,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_ARMOR_FORGE, ExpansionItemStacks.ARMOR_FORGE_RECIPE_ENCODER, ExpansionItemStacks.AUTO_ARMOR_FORGE
    };

    public static final ItemStack[] ADVANCED_AUTO_ARMOR_FORGE_WITHHOLDING = new ItemStack[] {
        ExpansionItemStacks.AUTO_ARMOR_FORGE_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_ARMOR_FORGE_WITHHOLDING,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_ARMOR_FORGE_WITHHOLDING, ExpansionItemStacks.ARMOR_FORGE_RECIPE_ENCODER, ExpansionItemStacks.AUTO_ARMOR_FORGE_WITHHOLDING
    };

    public static final ItemStack[] ADVANCED_AUTO_SMELTERY = new ItemStack[] {
        ExpansionItemStacks.AUTO_SMELTERY, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_SMELTERY,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_SMELTERY, ExpansionItemStacks.SMELTERY_RECIPE_ENCODER, ExpansionItemStacks.AUTO_SMELTERY
    };

    public static final ItemStack[] ADVANCED_AUTO_SMELTERY_WITHHOLDING = new ItemStack[] {
        ExpansionItemStacks.AUTO_SMELTERY_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_SMELTERY_WITHHOLDING,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_SMELTERY_WITHHOLDING, ExpansionItemStacks.SMELTERY_RECIPE_ENCODER, ExpansionItemStacks.AUTO_SMELTERY_WITHHOLDING
    };

    public static final ItemStack[] ADVANCED_AUTO_QUANTUM_WORKBENCH = new ItemStack[] {
        ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH, ExpansionItemStacks.QUANTUM_WORKBENCH_RECIPE_ENCODER, ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH
    };

    public static final ItemStack[] ADVANCED_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = new ItemStack[] {
        ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH_WITHHOLDING, ExpansionItemStacks.QUANTUM_WORKBENCH_RECIPE_ENCODER, ExpansionItemStacks.AUTO_QUANTUM_WORKBENCH_WITHHOLDING
    };

    public static final ItemStack[] ADVANCED_AUTO_ANCIENT_ALTAR = {
        ExpansionItemStacks.AUTO_ANCIENT_ALTAR, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_ANCIENT_ALTAR,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_ANCIENT_ALTAR, ExpansionItemStacks.ANCIENT_ALTAR_RECIPE_ENCODER, ExpansionItemStacks.AUTO_ANCIENT_ALTAR
    };

    public static final ItemStack[] ADVANCED_AUTO_ANCIENT_ALTAR_WITHHOLDING = {
        ExpansionItemStacks.AUTO_ANCIENT_ALTAR_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_ANCIENT_ALTAR_WITHHOLDING,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_ANCIENT_ALTAR_WITHHOLDING, ExpansionItemStacks.ANCIENT_ALTAR_RECIPE_ENCODER, ExpansionItemStacks.AUTO_ANCIENT_ALTAR_WITHHOLDING
    };

    public static final ItemStack[] ADVANCED_AUTO_EXPANSION_WORKBENCH = {
        ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH, ExpansionItemStacks.EXPANSION_WORKBENCH_RECIPE_ENCODER, ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH
    };

    public static final ItemStack[] ADVANCED_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = {
        ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
        ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
        ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH_WITHHOLDING, ExpansionItemStacks.EXPANSION_WORKBENCH_RECIPE_ENCODER, ExpansionItemStacks.AUTO_EXPANSION_WORKBENCH_WITHHOLDING
    };
    //网格
    public static final ItemStack[] NETWORK_GRID_NEW_STYLE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem(),
        OPTIC_CABLE.getItem(), NETWORK_GRID.getItem(), OPTIC_CABLE.getItem(),
        NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NETWORK_CRAFTING_GRID_NEW_STYLE = new ItemStack[] {
        OPTIC_STAR.getItem(), OPTIC_STAR.getItem(), OPTIC_STAR.getItem(),
        OPTIC_STAR.getItem(), NETWORK_CRAFTING_GRID.getItem(), OPTIC_STAR.getItem(),
        OPTIC_STAR.getItem(), OPTIC_STAR.getItem(), OPTIC_STAR.getItem()
    };

    public static final ItemStack[] NETWORK_ENCODING_GRID_NEW_STYLE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_RECIPE_ENCODER.getItem(), NETWORK_BRIDGE.getItem(),
        OPTIC_CABLE.getItem(), ExpansionItemStacks.NETWORK_CRAFTING_GRID_NEW_STYLE, OPTIC_CABLE.getItem(),
        NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem()
    };
    //高级量子存储
    public static final ItemStack[] ADVANCED_QUANTUM_STORAGE = new ItemStack[] {
        EMPOWERED_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(), EMPOWERED_AI_CORE.getItem(),
        PRISTINE_AI_CORE.getItem(), NETWORK_QUANTUM_STORAGE_8.getItem(), PRISTINE_AI_CORE.getItem(),
        EMPOWERED_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(), EMPOWERED_AI_CORE.getItem()
    };
    //网桥
    public static final ItemStack[] NE_BRIDGE_WHITE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.WHITE_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_LIGHT_GRAY = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.LIGHT_GRAY_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_GRAY = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.GRAY_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_BLACK = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.BLACK_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_BROWN = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.BROWN_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_RED = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.RED_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_ORANGE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.ORANGE_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_YELLOW = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.YELLOW_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_LIME = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.LIME_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_GREEN = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.GREEN_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_CYAN = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.CYAN_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_LIGHT_BLUE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.LIGHT_BLUE_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_BLUE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.BLUE_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_PURPLE = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.PURPLE_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_MAGENTA = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.MAGENTA_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] NE_BRIDGE_PINK = new ItemStack[] {
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), new ItemStack(Material.PINK_DYE), NETWORK_BRIDGE.getItem(),
        NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem()
    };

    public static final ItemStack[] CARGO_NODE_QUICK_TOOL = new ItemStack[]{
            new ItemStack(Material.LEATHER), SlimefunItems.SOLAR_PANEL, new ItemStack(Material.LEATHER),
            new ItemStack(Material.LEATHER), SlimefunItems.ANDROID_MEMORY_CORE, new ItemStack(Material.LEATHER),
            SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD
    };

    public static final ItemStack[] STORAGE_UNIT_UPGRADE_TABLE = new ItemStack[] {
        SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3,
        SlimefunItems.CARGO_MOTOR, SlimefunItems.BOUND_BACKPACK, SlimefunItems.CARGO_MOTOR,
        SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_1 = new ItemStack[] {
        SlimefunItems.SYNTHETIC_SHULKER_SHELL, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.SYNTHETIC_SHULKER_SHELL,
        SlimefunItems.CARGO_MOTOR, SlimefunItems.BOUND_BACKPACK, SlimefunItems.CARGO_MOTOR,
        SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.WITHER_PROOF_GLASS
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_2 = new ItemStack[] {
        SlimefunItems.CRAFTING_MOTOR, SlimefunItems.BOUND_BACKPACK, SlimefunItems.CRAFTING_MOTOR,
        SlimefunItems.CARGO_MOTOR, ExpansionItemStacks.CARGO_STORAGE_UNIT_1, SlimefunItems.CARGO_MOTOR,
        SlimefunItems.GPS_TRANSMITTER, SlimefunItems.MEDIUM_CAPACITOR,SlimefunItems.GPS_TRANSMITTER
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_3 = new ItemStack[] {
        SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.VILLAGER_RUNE, SlimefunItems.ANDROID_MEMORY_CORE,
        SlimefunItems.ENHANCED_AUTO_CRAFTER, ExpansionItemStacks.CARGO_STORAGE_UNIT_2, SlimefunItems.ENHANCED_AUTO_CRAFTER,
        SlimefunItems.GPS_TRANSMITTER_2, SlimefunItems.MEDIUM_CAPACITOR,SlimefunItems.GPS_TRANSMITTER_2
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_4 = new ItemStack[] {
        SlimefunItems.PROGRAMMABLE_ANDROID, SlimefunItems.GPS_TELEPORTER_PYLON, SlimefunItems.PROGRAMMABLE_ANDROID,
        SlimefunItems.COOLING_UNIT, ExpansionItemStacks.CARGO_STORAGE_UNIT_3, SlimefunItems.COOLING_UNIT,
        SlimefunItems.GPS_TRANSMITTER_3, SlimefunItems.CARBONADO_EDGED_CAPACITOR,SlimefunItems.GPS_TRANSMITTER_3
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_5 = new ItemStack[] {
        SlimefunItems.PROGRAMMABLE_ANDROID_3, SlimefunItems.NETHER_STAR_REACTOR, SlimefunItems.PROGRAMMABLE_ANDROID_3,
        SlimefunItems.ENCHANTMENT_RUNE, ExpansionItemStacks.CARGO_STORAGE_UNIT_4, SlimefunItems.ENCHANTMENT_RUNE,
        SlimefunItems.GPS_TRANSMITTER_4, SlimefunItems.CARBONADO_EDGED_CAPACITOR,SlimefunItems.GPS_TRANSMITTER_4
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_6 = new ItemStack[] {
        SlimefunItems.PROGRAMMABLE_ANDROID_3, SlimefunItems.NETHER_STAR_REACTOR, SlimefunItems.PROGRAMMABLE_ANDROID_3,
        SlimefunItems.NETHER_STAR_REACTOR, ExpansionItemStacks.CARGO_STORAGE_UNIT_5, SlimefunItems.NETHER_STAR_REACTOR,
        SlimefunItems.WITHER_ASSEMBLER, SlimefunItems.ENERGIZED_CAPACITOR,SlimefunItems.WITHER_ASSEMBLER
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_7 = new ItemStack[] {
        SlimefunItems.PROGRAMMABLE_ANDROID_3, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.PROGRAMMABLE_ANDROID_3,
        SlimefunItems.NETHER_STAR_REACTOR, ExpansionItemStacks.CARGO_STORAGE_UNIT_6, SlimefunItems.NETHER_STAR_REACTOR,
        SlimefunItems.IRON_GOLEM_ASSEMBLER, SlimefunItems.ENERGIZED_CAPACITOR,SlimefunItems.IRON_GOLEM_ASSEMBLER
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_8 = new ItemStack[] {
        SlimefunItems.ENDER_RUNE, SlimefunItems.PROGRAMMABLE_ANDROID_3, SlimefunItems.ENDER_RUNE,
        SlimefunItems.SOULBOUND_ELYTRA, ExpansionItemStacks.CARGO_STORAGE_UNIT_7, SlimefunItems.SOULBOUND_ELYTRA,
        SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.ENERGIZED_CAPACITOR,SlimefunItems.ENERGIZED_CAPACITOR
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_9 = new ItemStack[] {
        SlimefunItems.SOLAR_GENERATOR_4, new ItemStack(Material.TOTEM_OF_UNDYING), SlimefunItems.SOLAR_GENERATOR_4,
        SlimefunItems.INFUSED_HOPPER, ExpansionItemStacks.CARGO_STORAGE_UNIT_8, SlimefunItems.INFUSED_HOPPER,
        SlimefunItems.ENDER_RUNE, SlimefunItems.ENERGIZED_CAPACITOR,SlimefunItems.ENDER_RUNE
    };

    public static final ItemStack[] CARGO_STORAGE_UNIT_10 = new ItemStack[] {
        SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.ESSENCE_OF_AFTERLIFE,
        SlimefunItems.ESSENCE_OF_AFTERLIFE, ExpansionItemStacks.CARGO_STORAGE_UNIT_9, SlimefunItems.ESSENCE_OF_AFTERLIFE,
        SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.ESSENCE_OF_AFTERLIFE,SlimefunItems.ESSENCE_OF_AFTERLIFE
    };

}
