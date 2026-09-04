package com.marcpg.fwf.compat.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

import java.lang.reflect.Field;

public class EasyFreecamImpl implements FreecamImpl {
    private Field enabledField;
    private KeyMapping keyMapping;

    @Override
    public void init() {
        try {
            enabledField = dev.elpu7.easyFreecam.client.FreecamController.class.getDeclaredField("enabled");
            enabledField.setAccessible(true);

            Field keyMappingField = dev.elpu7.easyFreecam.client.FreecamController.class.getDeclaredField("TOGGLE_KEY");
            keyMappingField.setAccessible(true);
            keyMapping = (KeyMapping) keyMappingField.get(this.getClass());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not access fields from \"Easy Freecam\" mod.");
        }
    }

    @Override
    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    @Override
    public boolean isFreecamEnabled() {
        try {
            return (boolean) enabledField.get(this.getClass());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Field `enabled` from \"Easy Freecam\" mod is not accessible anymore.");
        }
    }
}
