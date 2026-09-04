package com.marcpg.fwf.compat.mixin;

import com.marcpg.fwf.EffectManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.xolt.freecam.Freecam.class)
public class XoltMixin {
    @Inject(method = "toggle", at = @At("RETURN"))
    private static void toggle(CallbackInfo ci) {
        EffectManager.updateAll();
    }
}
