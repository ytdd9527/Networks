package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage;

import org.jetbrains.annotations.Nullable;

public enum StorageUnitType {

    TINY(2, 65536),
    MINI(4, 131072),
    SMALL(8, 262144),
    MEDIUM(16, 524288),
    LARGE(32, 1048576),
    ENHANCED(32, 2097152),
    ADVANCED(32, 4194304),
    EXTRA(32, 8388608),
    ULTRA(32, 16777216),
    END_GAME_BASIC(32, 33554432),
    END_GAME_INTERMEDIATE(32, 134217728),
    END_GAME_ADVANCED(32, 1073741824),
    END_GAME_MAX(35, Integer.MAX_VALUE);

    private final int maxItemStored;
    private final int maxStoredAmountEach;

    StorageUnitType(int maxItem, int maxEachAmount) {
        this.maxItemStored = maxItem;
        this.maxStoredAmountEach = maxEachAmount;
    }

    public int getMaxItemCount() {
        return maxItemStored;
    }

    public int getEachMaxSize() {
        return maxStoredAmountEach;
    }

    @Nullable
    public StorageUnitType next() {
        int index = this.ordinal();
        if(index == values().length) {
            return null;
        }
        return values()[index];
    }
}
