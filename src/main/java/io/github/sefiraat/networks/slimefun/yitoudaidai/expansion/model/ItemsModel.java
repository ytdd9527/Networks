package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.model;

import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.slimefun.NetworksItemGroups;
import io.github.sefiraat.networks.slimefun.network.NetworkCell;
import io.github.sefiraat.networks.slimefun.network.NetworkPowerNode;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.ChainPusher;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.ChainGrabber;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.CoordinateReceiver;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.CoordinateTransmitter;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.AdvancedImport;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.AdvancedExport;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.transportation.AdvancedPurger;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ItemsModel {

    public static final CoordinateTransmitter NE_MODEL_COORDINATE_TRANSMITTER;
    public static final CoordinateReceiver NE_MODEL_COORDINATE_RECEIVER;
    public static final ChainPusher NE_MODEL_CHAING_PUSHER;
    public static final ChainPusher NE_MODEL_CHAING_PUSHER_PLUS;
    public static final ChainGrabber NE_MODEL_CHAING_GRABBER;
    public static final ChainGrabber NE_MODEL_CHAING_GRABBER_PLUS;
    public static final AdvancedImport NEA_MODEL_IMPORT;
    public static final AdvancedExport NEA_MODEL_EXPORT;
    public static final AdvancedPurger NEA_MODEL_PURGER;
    public static final NetworkCell NE_MODEL_CELL;
    public static final NetworkPowerNode NE_MODEL_CAPACITOR_5;

    static {


        NE_MODEL_COORDINATE_TRANSMITTER = new CoordinateTransmitter(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_COORDINATE_TRANSMITTER, RecipeType.NULL,null);
        NE_MODEL_COORDINATE_RECEIVER = new CoordinateReceiver(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_COORDINATE_RECEIVER, RecipeType.NULL,null);
        NE_MODEL_CHAING_PUSHER = new ChainPusher(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_CHAIN_PUSHER, RecipeType.NULL, null, "NE_CHAIN_PUSHER", 32);
        NE_MODEL_CHAING_PUSHER_PLUS = new ChainPusher(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_CHAIN_PUSHER_PLUS,RecipeType.NULL,null, "NE_CHAIN_PUSHER_PLUS", 64);
        NE_MODEL_CHAING_GRABBER = new ChainGrabber(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_CHAIN_GRABBER, RecipeType.NULL,null, "NE_CHAIN_GRABBER", 32);
        NE_MODEL_CHAING_GRABBER_PLUS = new ChainGrabber(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_CHAIN_GRABBER_PLUS,RecipeType.NULL,null, "NE_CHAIN_GRABBER_PLUS", 64);
        NEA_MODEL_IMPORT = new AdvancedImport(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NEA_MODEL_IMPORT,RecipeType.NULL,null);
        NEA_MODEL_EXPORT = new AdvancedExport(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NEA_MODEL_EXPORT,RecipeType.NULL,null);
        NEA_MODEL_PURGER = new AdvancedPurger(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NEA_MODEL_PURGER, RecipeType.NULL,null);

        NE_MODEL_CELL = new NetworkCell(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_CELL, RecipeType.NULL,null);
        NE_MODEL_CAPACITOR_5 = new NetworkPowerNode(NetworksItemGroups.DISABLED_ITEMS, ItemStacksModel.NE_MODEL_CAPACITOR_5, RecipeType.NULL,null,100000000);

    }

    static {
        NE_MODEL_CHAING_GRABBER.setUseSpecialModel(true);
        NEA_MODEL_PURGER.setUseSpecialModel(true);
        NE_MODEL_CELL.setUseSpecialModel(true);
        NE_MODEL_CAPACITOR_5.setUseSpecialModel(true);
    }
    public static void setup() {
        Networks plugin = Networks.getInstance();

        NE_MODEL_COORDINATE_TRANSMITTER.register(plugin);
        NE_MODEL_COORDINATE_RECEIVER.register(plugin);
        NE_MODEL_CHAING_PUSHER.register(plugin);
        NE_MODEL_CHAING_PUSHER_PLUS.register(plugin);
        NE_MODEL_CHAING_GRABBER.register(plugin);
        NE_MODEL_CHAING_GRABBER_PLUS.register(plugin);
        NEA_MODEL_IMPORT.register(plugin);
        NEA_MODEL_EXPORT.register(plugin);
        NEA_MODEL_PURGER.register(plugin);
        NE_MODEL_CELL.register(plugin);
        NE_MODEL_CAPACITOR_5.register(plugin);
    }
}
