package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.objects;

public interface QueuedTask {

    /**
     * The code will be invoked
     * @return true if do callback, else false.
     */
    boolean execute();

    /**
     * Invoke after execute()
     * @return true if abort the processor thread, else false.
     */
    default boolean callback() {
        return false;
    }

}
