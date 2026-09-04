package com.marcpg.fwf.compat.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

public class CamEnhanceImpl implements FreecamImpl {
    public static KeyMapping keyMapping;

    private me.syflog.camenh.Freecam instance;

    @Override
    public void init() {
        try {
            Object mainInstance = me.syflog.camenh.Main.class.getMethod("get").invoke(this);
            instance = (me.syflog.camenh.Freecam) mainInstance.getClass().getMethod("freecam").invoke(mainInstance);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not get freecam instance from \"Camera Enhancements\" mod.");
        }
    }

    @Override
    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    @Override
    public boolean isFreecamEnabled() {
        return instance.getMode() != me.syflog.camenh.Freecam.Mode.DISABLED;
    }
}
