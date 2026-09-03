package com.marcpg.fwf;

import com.marcpg.fwf.compat.FreecamImplManager;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.TickRateManager;

public final class EffectManager {
    private static int previousFrozen = -1;
    private static boolean modifiedFrozen = false;

    private static float previousTickrate = -1f;
    private static float modifiedTickrate = -1f;

    public static void updateAll() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null)
            return;

        TickRateManager trm = server.tickRateManager();
        updateFrozenStatus(trm);
        updateTickrateStatus(trm);
    }

    public static void updateFrozenStatus(TickRateManager trm) {
        if (FreecamImplManager.isEnabled() && FreecamWF.config().enabled && FreecamWF.config().tickSpeed <= 0f) {
            if (previousFrozen == -1)
                previousFrozen = trm.isFrozen() ? 1 : 0;

            trm.setFrozen(true);
            modifiedFrozen = true;
        } else if (modifiedFrozen) {
            if (previousFrozen != -1) {
                trm.setFrozen(previousFrozen == 1);
                previousFrozen = -1;
            }
            modifiedFrozen = false;
        }
    }

    public static void updateTickrateStatus(TickRateManager trm) {
        float targetTickSpeed = FreecamWF.config().tickSpeed;
        if (FreecamImplManager.isEnabled() && FreecamWF.config().enabled && targetTickSpeed > 0f && targetTickSpeed < 20f) {
            if (previousTickrate == -1)
                previousTickrate = trm.tickrate();

            trm.setTickRate(targetTickSpeed);
            modifiedTickrate = targetTickSpeed;
        } else if (modifiedTickrate != -1f) {
            if (previousTickrate != -1f) {
                trm.setTickRate(previousTickrate);
                previousTickrate = -1f;
            }
            modifiedTickrate = -1;
        }
    }
}
