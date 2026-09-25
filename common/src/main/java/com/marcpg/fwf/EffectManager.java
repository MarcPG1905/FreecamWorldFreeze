package com.marcpg.fwf;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public final class EffectManager {
    public static void updateAll() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null)
            return;

        OverridableTickRateManager trm = (OverridableTickRateManager) server.tickRateManager();

        trm.freecamWorldFreeze$setFrozenOverride(FZFFeature.FREEZE.checkCondition());

        if (FZFFeature.SLOW_MOTION.checkCondition()) {
            trm.freecamWorldFreeze$setTickRateOverride(FreecamWF.config().tickSpeed);
        } else {
            trm.freecamWorldFreeze$resetTickRateOverride();
        }
    }
}
