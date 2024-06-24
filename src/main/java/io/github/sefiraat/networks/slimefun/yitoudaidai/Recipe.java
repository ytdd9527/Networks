package io.github.sefiraat.networks.slimefun.yitoudaidai;


import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static io.github.sefiraat.networks.slimefun.NetworkSlimefunItems.*;


public class Recipe {
    public static final ItemStack[] NULL = {null,null,null,null,null,null,null,null,null};
    //工具
    public static final ItemStack[] NE_COORDINATE_CONFIGURATOR = {
            null, RADIOACTIVE_OPTIC_STAR.getItem(), null,
            null, NETWORK_CONFIGURATOR.getItem(), null,
            null, INTERDIMENSIONAL_PRESENCE.getItem(), null
    };
    //工作台
    public static final ItemStack[] NE_EXPANSION_WORKBENCH = {
            EMPOWERED_AI_CORE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, EMPOWERED_AI_CORE.getItem(),
            OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(),
            EMPOWERED_AI_CORE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, EMPOWERED_AI_CORE.getItem()
    };
    //坐标传输器
    public static final ItemStack[] NE_COORDINATE_TRANSMITTER = {
            NETWORK_WIRELESS_TRANSMITTER.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_WIRELESS_TRANSMITTER.getItem(),
            ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER, ADVANCED_NANOBOTS.getItem(),
            NETWORK_WIRELESS_TRANSMITTER.getItem(), PRISTINE_AI_CORE.getItem(), NETWORK_WIRELESS_TRANSMITTER.getItem(),
    };
    //坐标接收器
    public static final ItemStack[] NE_COORDINATE_RECEIVER = {
            NETWORK_WIRELESS_RECEIVER.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_WIRELESS_RECEIVER.getItem(),
            ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER, ADVANCED_NANOBOTS.getItem(),
            NETWORK_WIRELESS_RECEIVER.getItem(), AI_CORE.getItem(), NETWORK_WIRELESS_RECEIVER.getItem(),
    };
    //链式推送
    public static final ItemStack[] NE_CHAIN_PUSHER = {
            NETWORK_PUSHER.getItem(), PRISTINE_AI_CORE.getItem(), NETWORK_PUSHER.getItem(),
            OPTIC_CABLE.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), OPTIC_CABLE.getItem(),
            NETWORK_PUSHER.getItem(), NETWORK_EXPORT.getItem(), NETWORK_PUSHER.getItem(),
    };
    public static final ItemStack[] NE_CHAIN_PUSHER_PLUS = {
            ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER, PRISTINE_AI_CORE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER,
            INTERDIMENSIONAL_PRESENCE.getItem(), new ItemStack(Material.SEA_LANTERN), INTERDIMENSIONAL_PRESENCE.getItem(),
            ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER, NETWORK_IMPORT.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER,
    };
    public static final ItemStack[] NE_CHAIN_PUSHER_NUMBERABLE = {
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
            OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER, OPTIC_CABLE.getItem(),
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };
    public static final ItemStack[] NE_CHAIN_PUSHER_PLUS_NUMBERABLE = {
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
            OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER_PLUS, OPTIC_CABLE.getItem(),
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };
    //链式抓取
    public static final ItemStack[] NE_CHAIN_GRABBER = {
            NETWORK_GRABBER.getItem(), PRISTINE_AI_CORE.getItem(), NETWORK_GRABBER.getItem(),
            OPTIC_CABLE.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), OPTIC_CABLE.getItem(),
            NETWORK_GRABBER.getItem(), NETWORK_IMPORT.getItem(), NETWORK_GRABBER.getItem(),
    };
    public static final ItemStack[] NE_CHAIN_GRABBER_PLUS = {
            ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER, PRISTINE_AI_CORE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER,
            INTERDIMENSIONAL_PRESENCE.getItem(), new ItemStack(Material.SEA_LANTERN),INTERDIMENSIONAL_PRESENCE.getItem(),
            ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER, NETWORK_IMPORT.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER,
    };
    public static final ItemStack[] NE_CHAIN_GRABBER_NUMBERABLE = {
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
            OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER, OPTIC_CABLE.getItem(),
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };
    public static final ItemStack[] NE_CHAIN_GRABBER_PLUS_NUMBERABLE = {
        OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
        OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER_PLUS, OPTIC_CABLE.getItem(),
        OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };
    //链式调度器
    public static final ItemStack[] NE_CHAIN_DISPATCHER = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER, OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER,
            NETWORK_BRIDGE.getItem(), NETWORK_EXPORT.getItem(), NETWORK_BRIDGE.getItem()
    };
    public static final ItemStack[] NE_CHAIN_DISPATCHER_PLUS = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            ExpansionSlimefunItemStacks.NE_CHAIN_GRABBER_PLUS, OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_PUSHER_PLUS,
            NETWORK_BRIDGE.getItem(), NETWORK_EXPORT.getItem(), NETWORK_BRIDGE.getItem()
    };
    public static final ItemStack[] NE_CHAIN_DISPATCHER_NUMBERABLE = {
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
            OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_DISPATCHER, OPTIC_CABLE.getItem(),
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };
    public static final ItemStack[] NE_CHAIN_DISPATCHER_PLUS_NUMBERABLE = {
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem(),
            OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NE_CHAIN_DISPATCHER_PLUS, OPTIC_CABLE.getItem(),
            OPTIC_GLASS.getItem(), PRISTINE_AI_CORE.getItem(), OPTIC_GLASS.getItem()
    };
    //高级入口
    public static final ItemStack[] NEA_IMPORT = {
            NETWORK_IMPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_IMPORT.getItem(),
            ADVANCED_NANOBOTS.getItem(), PRISTINE_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(),
            NETWORK_IMPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_IMPORT.getItem(),
    };
    //高级出口
    public static final ItemStack[] NEA_EXPORT = {
            NETWORK_EXPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_EXPORT.getItem(),
            ADVANCED_NANOBOTS.getItem(), PRISTINE_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(),
            NETWORK_EXPORT.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_EXPORT.getItem(),
    };
    //高级清除器
    public static final ItemStack[] NEA_PURGER = {
            NETWORK_TRASH.getItem(), OPTIC_CABLE.getItem(), NETWORK_TRASH.getItem(),
            OPTIC_CABLE.getItem(), ADVANCED_NANOBOTS.getItem(), OPTIC_CABLE.getItem(),
            NETWORK_TRASH.getItem(), OPTIC_CABLE.getItem(), NETWORK_TRASH.getItem(),
    };
    //高级阻断器
    public static final ItemStack[] NEA_GREEDY_BLOCK = {
            NETWORK_GREEDY_BLOCK.getItem(), OPTIC_CABLE.getItem(), NETWORK_GREEDY_BLOCK.getItem(),
            OPTIC_CABLE.getItem(), ADVANCED_NANOBOTS.getItem(), OPTIC_CABLE.getItem(),
            NETWORK_GREEDY_BLOCK.getItem(), OPTIC_CABLE.getItem(), NETWORK_GREEDY_BLOCK.getItem(),
    };
    //电容5
    public static final ItemStack[] NETWORK_CAPACITOR_5 = {
            NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(),
            NETWORK_CAPACITOR_4.getItem(), SlimefunItems.ENERGIZED_CAPACITOR, NETWORK_CAPACITOR_4.getItem(),
            NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(), NETWORK_CAPACITOR_4.getItem(),
    };
    //蓝图
    public static final ItemStack[] MAGIC_WORKBENCH_BLUEPRINT = {
            OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(),
            OPTIC_CABLE.getItem(), CRAFTING_BLUEPRINT.getItem(), OPTIC_CABLE.getItem(),
            new ItemStack(Material.BOOKSHELF), new ItemStack(Material.CRAFTING_TABLE), new ItemStack(Material.DISPENSER),
    };
    public static final ItemStack[] ARMOR_FORGE_BLUEPRINT = {
            OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(), OPTIC_CABLE.getItem(),
            CRAFTING_BLUEPRINT.getItem(), new ItemStack(Material.ANVIL), CRAFTING_BLUEPRINT.getItem(),
            OPTIC_CABLE.getItem(), new ItemStack(Material.DISPENSER), OPTIC_CABLE.getItem(),
    };
    public static final ItemStack[] SMELTERY_BLUEPRINT = {
            CRAFTING_BLUEPRINT.getItem(), new ItemStack(Material.NETHER_BRICK_FENCE), CRAFTING_BLUEPRINT.getItem(),
            new ItemStack(Material.NETHER_BRICKS), new ItemStack(Material.DISPENSER), new ItemStack(Material.NETHER_BRICKS),
            CRAFTING_BLUEPRINT.getItem(), new ItemStack(Material.FLINT_AND_STEEL), CRAFTING_BLUEPRINT.getItem(),
    };
    public static final ItemStack[] QUANTUM_WORKBENCH_BLUEPRINT = {
            OPTIC_CABLE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, OPTIC_CABLE.getItem(),
            OPTIC_CABLE.getItem(), CRAFTING_BLUEPRINT.getItem(), OPTIC_CABLE.getItem(),
            OPTIC_CABLE.getItem(), SlimefunItems.ADVANCED_CIRCUIT_BOARD, OPTIC_CABLE.getItem(),
    };
    public static final ItemStack[] ANCIENT_ALTAR_BLUEPRINT = {
            SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL,
            CRAFTING_BLUEPRINT.getItem(), SlimefunItems.ANCIENT_ALTAR, CRAFTING_BLUEPRINT.getItem(),
            SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL, SlimefunItems.ANCIENT_PEDESTAL,
    };
    public static final ItemStack[] EXPANSION_WORKBENCH_BLUEPRINT = {
            NetworksSlimefunItemStacks.NETWORK_BRIDGE, SlimefunItems.ANCIENT_PEDESTAL, NetworksSlimefunItemStacks.NETWORK_BRIDGE,
            CRAFTING_BLUEPRINT.getItem(), ExpansionSlimefunItemStacks.NE_EXPANSION_WORKBENCH, CRAFTING_BLUEPRINT.getItem(),
            NetworksSlimefunItemStacks.NETWORK_BRIDGE, SlimefunItems.ANCIENT_PEDESTAL, NetworksSlimefunItemStacks.NETWORK_BRIDGE,
    };
    //编码
    public static final ItemStack[] NE_MAGIC_WORKBENCH_RECIPE_ENCODER = {
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
            ExpansionSlimefunItemStacks.MAGIC_WORKBENCH_BLUEPRINT, ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH, ExpansionSlimefunItemStacks.MAGIC_WORKBENCH_BLUEPRINT,
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD,
    };
    public static final ItemStack[] NE_ARMOR_FORGE_RECIPE_ENCODER = {
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
            ExpansionSlimefunItemStacks.ARMOR_FORGE_BLUEPRINT, ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE, ExpansionSlimefunItemStacks.ARMOR_FORGE_BLUEPRINT,
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD,
    };
    public static final ItemStack[] NE_SMELTERY_RECIPE_ENCODER = {
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
            ExpansionSlimefunItemStacks.SMELTERY_BLUEPRINT, ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY, ExpansionSlimefunItemStacks.SMELTERY_BLUEPRINT,
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD,
    };
    public static final ItemStack[] NE_QUANTUM_WORKBENCH_RECIPE_ENCODER = {
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
            ExpansionSlimefunItemStacks.QUANTUM_WORKBENCH_BLUEPRINT, ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH, ExpansionSlimefunItemStacks.QUANTUM_WORKBENCH_BLUEPRINT,
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD,
    };
    public static final ItemStack[] NE_ANCIENT_ALTAR_RECIPE_ENCODER = {
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
            ExpansionSlimefunItemStacks.ANCIENT_ALTAR_BLUEPRINT, ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR, ExpansionSlimefunItemStacks.ANCIENT_ALTAR_BLUEPRINT,
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD,
    };
    public static final ItemStack[] NE_EXPANSION_WORKBENCH_RECIPE_ENCODER = {
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.ANDROID_MEMORY_CORE, SlimefunItems.BASIC_CIRCUIT_BOARD,
            ExpansionSlimefunItemStacks.EXPANSION_WORKBENCH_BLUEPRINT, ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH, ExpansionSlimefunItemStacks.EXPANSION_WORKBENCH_BLUEPRINT,
            SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.CARGO_MOTOR, SlimefunItems.BASIC_CIRCUIT_BOARD,
    };
    //合成机
    public static final ItemStack[] NE_AUTO_MAGIC_WORKBENCH = {
            OPTIC_GLASS.getItem(), OPTIC_CABLE.getItem(), OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.MAGIC_WORKBENCH_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.MAGIC_WORKBENCH_BLUEPRINT,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_ARMOR_FORGE = {
            OPTIC_GLASS.getItem(), SlimefunItems.ARMOR_AUTO_CRAFTER, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.ARMOR_FORGE_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.ARMOR_FORGE_BLUEPRINT,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_ARMOR_FORGE_WITHHOLDING = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_SMELTERY = {
            OPTIC_GLASS.getItem(), ExpansionSlimefunItemStacks.SMELTERY_BLUEPRINT, OPTIC_GLASS.getItem(),
            SlimefunItems.ELECTRIC_SMELTERY_2, SIMPLE_NANOBOTS.getItem(), SlimefunItems.ELECTRIC_SMELTERY_2,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_SMELTERY_WITHHOLDING = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_QUANTUM_WORKBENCH = {
            OPTIC_GLASS.getItem(), NETWORK_QUANTUM_WORKBENCH.getItem(), OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.QUANTUM_WORKBENCH_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.QUANTUM_WORKBENCH_BLUEPRINT,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_ANCIENT_ALTAR = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.ANCIENT_ALTAR_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.ANCIENT_ALTAR_BLUEPRINT,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_ANCIENT_ALTAR_WITHHOLDING = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_EXPANSION_WORKBENCH = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.EXPANSION_WORKBENCH_BLUEPRINT, SIMPLE_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.EXPANSION_WORKBENCH_BLUEPRINT,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER.getItem(), OPTIC_GLASS.getItem(),
    };
    public static final ItemStack[] NE_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = {
            OPTIC_GLASS.getItem(), SlimefunItems.CRAFTER_SMART_PORT, OPTIC_GLASS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH,
            OPTIC_GLASS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), OPTIC_GLASS.getItem(),
    };
    //高级合成机
    public static final ItemStack[] NEA_AUTO_CRAFTER = {
            NETWORK_AUTO_CRAFTER.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_AUTO_CRAFTER.getItem(),
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            NETWORK_AUTO_CRAFTER.getItem(), NETWORK_RECIPE_ENCODER.getItem(), NETWORK_AUTO_CRAFTER.getItem(),
    };
    public static final ItemStack[] NEA_AUTO_CRAFTER_WITHHOLDING = {
            NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(), ADVANCED_NANOBOTS.getItem(), NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(),
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(),NETWORK_RECIPE_ENCODER.getItem(),NETWORK_AUTO_CRAFTER_WITHHOLDING.getItem(),
    };
    public static final ItemStack[] NEA_AUTO_MAGIC_WORKBENCH = {
            ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH, ExpansionSlimefunItemStacks.NE_MAGIC_WORKBENCH_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH,
    };
    public static final ItemStack[] NEA_AUTO_MAGIC_WORKBENCH_WITHHOLDING = {
            ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING, ExpansionSlimefunItemStacks.NE_MAGIC_WORKBENCH_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_MAGIC_WORKBENCH_WITHHOLDING,
    };
    public static final ItemStack[] NEA_AUTO_ARMOR_FORGE = {
            ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE, ExpansionSlimefunItemStacks.NE_ARMOR_FORGE_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE,
    };
    public static final ItemStack[] NEA_AUTO_ARMOR_FORGE_WITHHOLDING = {
            ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE_WITHHOLDING,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE_WITHHOLDING, ExpansionSlimefunItemStacks.NE_ARMOR_FORGE_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_ARMOR_FORGE_WITHHOLDING,
    };
    public static final ItemStack[] NEA_AUTO_SMELTERY = {
            ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY, ExpansionSlimefunItemStacks.NE_SMELTERY_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY,
    };
    public static final ItemStack[] NEA_AUTO_SMELTERY_WITHHOLDING = {
            ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY_WITHHOLDING,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY_WITHHOLDING, ExpansionSlimefunItemStacks.NE_SMELTERY_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_SMELTERY_WITHHOLDING,
    };
    public static final ItemStack[] NEA_AUTO_QUANTUM_WORKBENCH = {
            ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH, ExpansionSlimefunItemStacks.NE_QUANTUM_WORKBENCH_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH,
    };
    public static final ItemStack[] NEA_AUTO_QUANTUM_WORKBENCH_WITHHOLDING = {
            ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING, ExpansionSlimefunItemStacks.NE_QUANTUM_WORKBENCH_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_QUANTUM_WORKBENCH_WITHHOLDING,
    };
    public static final ItemStack[] NEA_AUTO_ANCIENT_ALTAR = {
            ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR, ExpansionSlimefunItemStacks.NE_ANCIENT_ALTAR_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR,
    };
    public static final ItemStack[] NEA_AUTO_ANCIENT_ALTAR_WITHHOLDING = {
            ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING, ExpansionSlimefunItemStacks.NE_ANCIENT_ALTAR_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_ANCIENT_ALTAR_WITHHOLDING,
    };
    public static final ItemStack[] NEA_AUTO_EXPANSION_WORKBENCH = {
            ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH, ExpansionSlimefunItemStacks.NE_EXPANSION_WORKBENCH_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH,
    };
    public static final ItemStack[] NEA_AUTO_EXPANSION_WORKBENCH_WITHHOLDING = {
            ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH_WITHHOLDING, ADVANCED_NANOBOTS.getItem(), ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
            ADVANCED_NANOBOTS.getItem(), INTERDIMENSIONAL_PRESENCE.getItem(), ADVANCED_NANOBOTS.getItem(),
            ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH_WITHHOLDING, ExpansionSlimefunItemStacks.NE_EXPANSION_WORKBENCH_RECIPE_ENCODER, ExpansionSlimefunItemStacks.NE_AUTO_EXPANSION_WORKBENCH_WITHHOLDING,
    };
    //网格
    public static final ItemStack[] NETWORK_GRID_NEW_STYLE = {
            NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem(),
            OPTIC_CABLE.getItem(), NETWORK_GRID.getItem(), OPTIC_CABLE.getItem(),
            NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NETWORK_CRAFTING_GRID_NEW_STYLE = {
            OPTIC_STAR.getItem(), OPTIC_STAR.getItem(), OPTIC_STAR.getItem(),
            OPTIC_STAR.getItem(), NETWORK_CRAFTING_GRID.getItem(), OPTIC_STAR.getItem(),
            OPTIC_STAR.getItem(), OPTIC_STAR.getItem(), OPTIC_STAR.getItem(),
    };
    public static final ItemStack[] NETWORK_ENCODING_GRID_NEW_STYLE = {
            NETWORK_BRIDGE.getItem(), NETWORK_RECIPE_ENCODER.getItem(), NETWORK_BRIDGE.getItem(),
            OPTIC_CABLE.getItem(), ExpansionSlimefunItemStacks.NETWORK_CRAFTING_GRID_NEW_STYLE, OPTIC_CABLE.getItem(),
            NETWORK_BRIDGE.getItem(), OPTIC_CABLE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    //高级量子存储
    public static final ItemStack[] NETWORK_ADVANCED_QUANTUM_STORAGE = {
            EMPOWERED_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(), EMPOWERED_AI_CORE.getItem(),
            PRISTINE_AI_CORE.getItem(), NETWORK_QUANTUM_STORAGE_8.getItem(), PRISTINE_AI_CORE.getItem(),
            EMPOWERED_AI_CORE.getItem(), ADVANCED_NANOBOTS.getItem(), EMPOWERED_AI_CORE.getItem()
    };
    //网桥
    public static final ItemStack[] NE_BRIDGE_WHITE = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.WHITE_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_LIGHT_GRAY = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.LIGHT_GRAY_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_GRAY = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.GRAY_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_BLACK = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.BLACK_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_BROWN = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.BROWN_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_RED = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.RED_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_ORANGE = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.ORANGE_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_YELLOW = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.YELLOW_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_LIME = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.LIME_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_GREEN = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.GREEN_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_CYAN = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.CYAN_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_LIGHT_BLUE = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.LIGHT_BLUE_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_BLUE = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.BLUE_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_PURPLE = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.PURPLE_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_MAGENTA = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.MAGENTA_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
    public static final ItemStack[] NE_BRIDGE_PINK = {
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), new ItemStack(Material.PINK_DYE), NETWORK_BRIDGE.getItem(),
            NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(), NETWORK_BRIDGE.getItem(),
    };
}
