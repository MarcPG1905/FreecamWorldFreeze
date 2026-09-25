package com.marcpg.fwf.mixin;

import net.minecraft.server.ServerTickRateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerTickRateManager.class)
public interface ServerTickRateManagerMixin {
    @Invoker("updateStateToClients")
    void freecamWorldFreeze$updateStateToClients();
}
