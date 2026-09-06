package com.marcpg.fwf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import org.slf4j.LoggerFactory;

public final class FWFFabric implements ClientModInitializer {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(FreecamWF.KEY_CATEGORY_IDENTIFIER);
    private static final KeyMapping TOGGLE_WORLD_EFFECT_KEY = KeyMappingHelper.registerKeyMapping(FreecamWF.createWorldEffectToggleKey(CATEGORY));
    private static final KeyMapping TOGGLE_PLAYER_FREEZE_KEY = KeyMappingHelper.registerKeyMapping(FreecamWF.createPlayerFreezeToggleKey(CATEGORY));

    @Override
    public void onInitializeClient() {
        FreecamWF.setup(FabricLoader.getInstance().getConfigDir(), LoggerFactory.getLogger(FreecamWF.MOD_ID));

        // Freeze key pressing
        ClientTickEvents.END_CLIENT_TICK.register(_ -> {
            while (TOGGLE_WORLD_EFFECT_KEY.consumeClick())
                FreecamWF.config().toggleWorldEffect();

            while (TOGGLE_PLAYER_FREEZE_KEY.consumeClick())
                FreecamWF.config().toggleFreezePlayer();
        });
    }
}
