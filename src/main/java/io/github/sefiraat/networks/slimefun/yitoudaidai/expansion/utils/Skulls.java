package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public enum Skulls {

    //https://minecraft-heads.com/custom-heads/head/62847-rainbow-crate
    NE_MODEL_CELL("bdca3f370bfc0cadee7fd014579b64271649b6e7c0290d8e1acdd6f27b476bf2"),
    //https://minecraft-heads.com/custom-heads/head/46444-cargo-node
    NE_MODEL_CONTROLLER("64aeb99a68c671a03bb7415f5801960b739a98e43b1ae4e1bacd958a8b94227d"),

    NE_MODEL_("bdca3f370bfc0cadee7fd014579b64271649b6e7c0290d8e1acdd6f27b476bf2");


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