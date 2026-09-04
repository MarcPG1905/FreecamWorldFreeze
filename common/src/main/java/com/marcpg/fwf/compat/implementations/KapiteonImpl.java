package com.marcpg.fwf.compat.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

import java.lang.reflect.Field;

public class KapiteonImpl implements FreecamImpl {
    private KeyMapping keyMapping;

    @Override
    public void init() {
        try {
            Field keyMappingField = com.kapiteon.freecam.FreeCam.INSTANCE.getClass().getDeclaredField("keyOnOff");
            keyMappingField.setAccessible(true);
            keyMapping = (KeyMapping) keyMappingField.get(com.kapiteon.freecam.FreeCam.INSTANCE);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not access fields from \"Freecam by kapiteon\" mod.");
        }
    }

    @Override
    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    @Override
    public boolean isFreecamEnabled() {
        return com.kapiteon.freecam.FreeCam.INSTANCE.isEnabled();
    }
}
