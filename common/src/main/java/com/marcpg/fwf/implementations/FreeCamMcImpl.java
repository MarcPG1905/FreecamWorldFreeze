package com.marcpg.fwf.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

public class FreeCamMcImpl implements FreecamImpl {
    @Override
    public KeyMapping getKeyMapping() {
        return com.jasonzli.freecam.FreeCam.getInstance().getKeys().getFirst();
    }

    @Override
    public boolean isFreecamEnabled() {
        return com.jasonzli.freecam.FreeCam.getInstance().isEnabled();
    }
}
