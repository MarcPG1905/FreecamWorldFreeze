package com.marcpg.fwf.mixin;

import com.marcpg.fwf.EffectManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(com.jasonzli.freecam.FreeCam.class)
public class FreeCamMcMixin {
    @Inject(method = "enable", at = @At("RETURN"))
    private void enable(CallbackInfo ci) {
        EffectManager.updateAll();
    }

    @Inject(method = "disable", at = @At("RETURN"))
    private void disable(CallbackInfo ci) {
        EffectManager.updateAll();
    }
}
