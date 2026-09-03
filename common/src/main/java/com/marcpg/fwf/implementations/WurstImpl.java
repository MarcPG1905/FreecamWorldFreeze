package com.marcpg.fwf.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

public class WurstImpl implements FreecamImpl {
    @Override
    public KeyMapping getKeyMapping() {
        return net.wimods.freecam.WiFreecam.INSTANCE.getKeybinds().toggleKey;
    }

    @Override
    public boolean isFreecamEnabled() {
        return net.wimods.freecam.WiFreecam.INSTANCE.isEnabled();
    }
}
