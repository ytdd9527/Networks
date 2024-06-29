package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items.storage;

public class CargoReceipt {

    private final int cid;
    private final int in;
    private final int out;
    private final int total;
    private final int typeCount;
    private final StorageUnitType sizeType;

    public CargoReceipt(int containerId, int in, int out, int total, int typeCount, StorageUnitType sizeType) {
        this.cid = containerId;
        this.in = in;
        this.out = out;
        this.total = total;
        this.typeCount = typeCount;
        this.sizeType = sizeType;
    }

    public int getAmountIn() {
        return in;
    }

    public int getAmountOut() {
        return out;
    }

    public int getTotal() {
        return total;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public int getContainerId() {
        return cid;
    }

    public StorageUnitType getSizeType() {
        return sizeType;
    }

}
