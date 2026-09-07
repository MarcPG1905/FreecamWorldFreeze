package com.marcpg.fwf.mixin;

import com.marcpg.fwf.FZFFeature;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
    private void hurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (FZFFeature.FREEZE_PLAYER.checkCondition() && this.freecam_wf$this() == Minecraft.getInstance().player)
            cir.setReturnValue(false); // Apparently also calls `cir.cancel()`.
    }

    @Unique
    private Entity freecam_wf$this() {
        return (Entity) (Object) this;
    }
}
