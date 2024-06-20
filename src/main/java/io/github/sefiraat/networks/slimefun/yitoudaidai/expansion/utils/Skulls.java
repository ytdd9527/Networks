package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;

import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public enum Skulls {

    TRASH_CAN("32d41042ce99147cc38cac9e46741576e7ee791283e6fac8d3292cae2935f1f"),

    //https://minecraft-heads.com/custom-heads/head/62847-rainbow-crate
    NE_MODEL_CELL("bdca3f370bfc0cadee7fd014579b64271649b6e7c0290d8e1acdd6f27b476bf2"),

    //https://minecraft-heads.com/custom-heads/head/46444-cargo-node
    NE_MODEL_CONTROLLER("64aeb99a68c671a03bb7415f5801960b739a98e43b1ae4e1bacd958a8b94227d"),
    //https://minecraft-heads.com/custom-heads/head/45398-mithril-infused-drill-tank
    NE_MODEL_CAPACITOR_5("ecb316f7a227a8c59d58ae0dd6768fe4fa546d55b9cfdd56cfe40b6586d81c24");


    @Getter
    protected static final Skulls[] cachedValues = values();

    @Getter
    private final String hash;

    @ParametersAreNonnullByDefault
    Skulls(String hash) {
        this.hash = hash;
    }

    public ItemStack getPlayerHead() {
        return PlayerHead.getItemStack(PlayerSkin.fromHashCode(hash));
    }

}