package com.marcpg.fwf.compat.mixin;

import com.marcpg.fwf.EffectManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.wimods.freecam.WiFreecam.class)
public class WurstMixin {
    @Inject(method = "setEnabled", at = @At("RETURN"))
    private void setEnabled(boolean enabled, CallbackInfo ci) {
        EffectManager.updateAll();
    }
}
