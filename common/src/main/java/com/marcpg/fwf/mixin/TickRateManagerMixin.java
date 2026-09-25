package com.marcpg.fwf.mixin;

import com.marcpg.fwf.OverridableTickRateManager;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.TickRateManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TickRateManager.class)
public abstract class TickRateManagerMixin implements OverridableTickRateManager {
    @Shadow
    public abstract boolean isFrozen();

    @Unique private float freecamWorldFreeze$tickrateOverride = -1F;
    @Unique private long freecamWorldFreeze$nanosecondsPerTickOverride = -1L;
    @Unique private boolean freecamWorldFreeze$isFrozenOverride = false;

    @Override
    public void freecamWorldFreeze$setTickRateOverride(float rate) {
        this.freecamWorldFreeze$tickrateOverride = rate;

        if (rate == -1F) {
            this.freecamWorldFreeze$nanosecondsPerTickOverride = -1L;
        } else {
            this.freecamWorldFreeze$nanosecondsPerTickOverride = (long) ((double) TimeUtil.NANOSECONDS_PER_SECOND / (double) this.freecamWorldFreeze$tickrateOverride);
        }

        if (this instanceof ServerTickRateManagerMixin serverMixin)
            serverMixin.freecamWorldFreeze$updateStateToClients();
    }

    @Override
    public float freecamWorldFreeze$tickRateOverride() {
        return this.freecamWorldFreeze$tickrateOverride;
    }

    @Override
    public long freecamWorldFreeze$nanosecondsPerTickOverride() {
        return freecamWorldFreeze$nanosecondsPerTickOverride;
    }

    @Override
    public void freecamWorldFreeze$setFrozenOverride(boolean state) {
        this.freecamWorldFreeze$isFrozenOverride = state;

        if (this instanceof ServerTickRateManagerMixin serverMixin)
            serverMixin.freecamWorldFreeze$updateStateToClients();
    }

    @Override
    public boolean freecamWorldFreeze$isFrozenOverride() {
        return freecamWorldFreeze$isFrozenOverride;
    }

    @Inject(method = "tickrate", at = @At("HEAD"), cancellable = true)
    private void tickrate(CallbackInfoReturnable<Float> cir) {
        if (freecamWorldFreeze$tickrateOverride > -1F)
            cir.setReturnValue(freecamWorldFreeze$tickrateOverride);
    }

    @Inject(method = "millisecondsPerTick", at = @At("HEAD"), cancellable = true)
    private void millisecondsPerTick(CallbackInfoReturnable<Float> cir) {
        if (freecamWorldFreeze$nanosecondsPerTickOverride > -1)
            cir.setReturnValue((float) freecamWorldFreeze$nanosecondsPerTickOverride / (float) TimeUtil.NANOSECONDS_PER_MILLISECOND);
    }

    @Inject(method = "nanosecondsPerTick", at = @At("HEAD"), cancellable = true)
    private void nanosecondsPerTick(CallbackInfoReturnable<Long> cir) {
        if (freecamWorldFreeze$nanosecondsPerTickOverride > -1)
            cir.setReturnValue(freecamWorldFreeze$nanosecondsPerTickOverride);
    }

    @Inject(method = "isFrozen", at = @At("HEAD"), cancellable = true)
    private void isFrozen(CallbackInfoReturnable<Boolean> cir) {
        if (freecamWorldFreeze$isFrozenOverride)
            cir.setReturnValue(true);
    }

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/TickRateManager;isFrozen:Z", opcode = Opcodes.GETFIELD))
    private boolean tick_isFrozen(TickRateManager instance) {
        return isFrozen();
    }
}
