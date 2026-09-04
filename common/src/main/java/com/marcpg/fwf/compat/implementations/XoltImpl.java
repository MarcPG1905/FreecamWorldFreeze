package com.marcpg.fwf.compat.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

public class XoltImpl implements FreecamImpl {
    @Override
    public KeyMapping getKeyMapping() {
        return net.xolt.freecam.config.ModBindings.KEY_TOGGLE.get();
    }

    @Override
    public boolean isFreecamEnabled() {
        return net.xolt.freecam.Freecam.isEnabled();
    }
}
