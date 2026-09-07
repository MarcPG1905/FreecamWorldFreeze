package com.marcpg.fwf.mixin;

import com.marcpg.fwf.FZFFeature;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), cancellable = true)
    private void setDeltaMovement(Vec3 deltaMovement, CallbackInfo ci) {
        if (FZFFeature.FREEZE_PLAYER.checkCondition() && this.freecam_wf$this() == Minecraft.getInstance().player)
            ci.cancel();
    }

    @Inject(method = "moveRelative", at = @At("HEAD"), cancellable = true)
    private void moveRelative(float speed, Vec3 input, CallbackInfo ci) {
        if (FZFFeature.FREEZE_PLAYER.checkCondition() && this.freecam_wf$this() == Minecraft.getInstance().player)
            ci.cancel();
    }

    @Inject(method = "setPos(DDD)V", at = @At("HEAD"), cancellable = true)
    private void setPos(double x, double y, double z, CallbackInfo ci) {
        if (FZFFeature.FREEZE_PLAYER.checkCondition() && this.freecam_wf$this() == Minecraft.getInstance().player)
            ci.cancel();
    }

    @Inject(method = "setPosRaw", at = @At("HEAD"), cancellable = true)
    private void setPosRaw(double x, double y, double z, CallbackInfo ci) {
        if (FZFFeature.FREEZE_PLAYER.checkCondition() && this.freecam_wf$this() == Minecraft.getInstance().player)
            ci.cancel();
    }

    @Unique
    private Entity freecam_wf$this() {
        return (Entity) (Object) this;
    }
}
