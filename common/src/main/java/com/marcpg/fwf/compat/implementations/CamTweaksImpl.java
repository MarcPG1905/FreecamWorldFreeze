package com.marcpg.fwf.compat.implementations;

import com.marcpg.fwf.compat.FreecamImpl;
import net.minecraft.client.KeyMapping;

public class CamTweaksImpl implements FreecamImpl {
    @Override
    public KeyMapping getKeyMapping() {
        return cameratweaks.Keybinds.freecam;
    }

    @Override
    public boolean isFreecamEnabled() {
        return cameratweaks.Keybinds.freecam.enabled();
    }
}
