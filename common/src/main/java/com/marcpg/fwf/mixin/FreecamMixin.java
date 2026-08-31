package com.marcpg.fwf.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.xolt.freecam.Freecam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Freecam.class)
public class FreecamMixin {
    @Shadow private static boolean freecamEnabled;
    @Shadow private static boolean tripodEnabled;

    @Unique private static boolean freecamWorldFreeze$wasFrozenBefore;

    @Inject(method = "toggle", at = @At("RETURN"))
    private static void toggle(CallbackInfo ci) {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null)
            return;

        // Don't freeze when using the tripod thing.
        if (freecamEnabled && !tripodEnabled) {
            freecamWorldFreeze$wasFrozenBefore = server.tickRateManager().isFrozen();
            server.tickRateManager().setFrozen(true);
        } else {
            server.tickRateManager().setFrozen(freecamWorldFreeze$wasFrozenBefore);
        }
    }
}
