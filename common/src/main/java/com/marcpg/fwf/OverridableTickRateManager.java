package com.marcpg.fwf;

public interface OverridableTickRateManager {
    void freecamWorldFreeze$setTickRateOverride(final float rate);
    default void freecamWorldFreeze$resetTickRateOverride() {
        freecamWorldFreeze$setTickRateOverride(-1F);
    }
    float freecamWorldFreeze$tickRateOverride();
    long freecamWorldFreeze$nanosecondsPerTickOverride();

    void freecamWorldFreeze$setFrozenOverride(boolean state);
    boolean freecamWorldFreeze$isFrozenOverride();
}
