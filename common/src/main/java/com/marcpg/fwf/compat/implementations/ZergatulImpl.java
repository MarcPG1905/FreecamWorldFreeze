package com.marcpg.fwf.compat.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

public class ZergatulImpl implements FreecamImpl {
    @Override
    public KeyMapping getKeyMapping() {
        return com.zergatul.freecam.KeyBindings.toggleFreeCam;
    }

    @Override
    public boolean isFreecamEnabled() {
        return com.zergatul.freecam.FreeCam.instance.isActive();
    }
}
