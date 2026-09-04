package com.marcpg.fwf.compat.mixin;

import com.marcpg.fwf.EffectManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(cameratweaks.Freecam.class)
public class CamTweaksMixin {
    @Inject(method = "enable", at = @At("RETURN"))
    private static void enable(CallbackInfo ci) {
        EffectManager.updateAll();
    }

    @Inject(method = "disable", at = @At("RETURN"))
    private static void disable(CallbackInfo ci) {
        EffectManager.updateAll();
    }
}
