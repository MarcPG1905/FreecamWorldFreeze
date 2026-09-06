package com.marcpg.fwf;

import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("freecam_wf")
public class FWFForge {
    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(FreecamWF.KEY_CATEGORY_IDENTIFIER);
    public static final Lazy<KeyMapping> TOGGLE_WORLD_EFFECT_KEY = Lazy.of(() -> FreecamWF.createWorldEffectToggleKey(CATEGORY));
    public static final Lazy<KeyMapping> TOGGLE_PLAYER_FREEZE_KEY = Lazy.of(() -> FreecamWF.createPlayerFreezeToggleKey(CATEGORY));

    public FWFForge(FMLJavaModLoadingContext context) {
        FreecamWF.setup(FMLPaths.CONFIGDIR.get(), LogUtils.getLogger());

        // Configuration screen
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((_, parent) -> new FWFConfig.ConfigScreen(parent)));
    }

    @Mod.EventBusSubscriber(modid = "freecam_wf", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    static final class GameBusEvents {
        @SubscribeEvent
        static void onClientTickPost(TickEvent.ClientTickEvent.Post event) {
            while (TOGGLE_WORLD_EFFECT_KEY.get().consumeClick())
                FreecamWF.config().toggleWorldEffect();

            while (TOGGLE_PLAYER_FREEZE_KEY.get().consumeClick())
                FreecamWF.config().toggleFreezePlayer();
        }

        @SubscribeEvent
        static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(TOGGLE_WORLD_EFFECT_KEY.get());
            event.register(TOGGLE_PLAYER_FREEZE_KEY.get());
        }
    }
}
