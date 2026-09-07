package com.marcpg.fwf.compat.mixin;

import com.marcpg.fwf.EffectManager;
import com.marcpg.fwf.compat.implementations.CamEnhanceImpl;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(me.syflog.camenh.Freecam.class)
public class CamEnhanceMixin {
    @Inject(method = "_enable", at = @At("RETURN"))
    private void _enable(CallbackInfo ci) {
        EffectManager.updateAll();
    }

    @Inject(method = "_disable", at = @At("RETURN"))
    private void _disable(CallbackInfo ci) {
        EffectManager.updateAll();
    }

    @SuppressWarnings("InjectLocalCaptureCanBeReplacedWithLocal")
    @Inject(method = "<init>", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void captureKeyMapping(CallbackInfo ci, KeyMapping freecamKeyMapping) {
        CamEnhanceImpl.keyMapping = freecamKeyMapping;
    }
}

