package com.marcpg.fwf.compat;

import net.minecraft.client.KeyMapping;

public interface FreecamImpl {
    default void init() {}

    KeyMapping getKeyMapping();
    boolean isFreecamEnabled();
}
