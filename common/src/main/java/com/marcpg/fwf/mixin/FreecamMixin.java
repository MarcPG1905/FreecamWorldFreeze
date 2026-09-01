package com.marcpg.fwf.mixin;

import com.marcpg.fwf.FreezeManager;
import net.xolt.freecam.Freecam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Freecam.class)
public class FreecamMixin {
    @Inject(method = "toggle", at = @At("RETURN"))
    private static void toggle(CallbackInfo ci) {
        FreezeManager.updateAll();
    }
}
