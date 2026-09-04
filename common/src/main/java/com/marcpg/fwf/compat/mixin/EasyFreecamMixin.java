package com.marcpg.fwf.compat.mixin;

import com.marcpg.fwf.EffectManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Hooking into enable() and disable() instead of toggle(), because maybe the mod manuallyenabled/disables somewhere
// instead of toggling and I just didn't find it.
@Mixin(dev.elpu7.easyFreecam.client.FreecamController.class)
public class EasyFreecamMixin {
    @Inject(method = "enable", at = @At("RETURN"))
    private static void enable(CallbackInfo ci) {
        EffectManager.updateAll();
    }

    @Inject(method = "disable", at = @At("RETURN"))
    private static void disable(CallbackInfo ci) {
        EffectManager.updateAll();
    }
}
