package com.marcpg.fwf;

import com.marcpg.fwf.compat.FreecamImplManager;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;

public enum FZFFeature {
    FREEZE(() -> FreecamImplManager.isEnabled() && FreecamWF.config().worldEffectEnabled && FreecamWF.config().tickSpeed <= 0f),
    SLOW_MOTION(() -> FreecamImplManager.isEnabled() && FreecamWF.config().worldEffectEnabled && FreecamWF.config().tickSpeed > 0f && FreecamWF.config().tickSpeed < 20f),
    FREEZE_PLAYER(() -> FreecamImplManager.isEnabled() && FreecamWF.config().freezePlayer);

    public final Component translation = Component.translatable("generic.freecam_wf.feature." + name().toLowerCase());

    private final BooleanSupplier effectCondition;

    FZFFeature(BooleanSupplier effectCondition) {
        this.effectCondition = effectCondition;
    }

    public boolean checkCondition() {
        return effectCondition.getAsBoolean() && !FreecamImplManager.integration().nativeFeatures().contains(this);
    }
}
