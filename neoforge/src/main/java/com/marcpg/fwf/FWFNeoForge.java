package com.marcpg.fwf;

import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.util.Lazy;

@Mod("freecam_wf")
public final class FWFNeoForge {
    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(FreecamWF.KEY_CATEGORY_IDENTIFIER);
    public static final Lazy<KeyMapping> TOGGLE_FREEZE_KEY = Lazy.of(() -> FreecamWF.createToggleFreezeKey(CATEGORY));

    public FWFNeoForge(ModContainer container) {
        FreecamWF.setup(FMLPaths.CONFIGDIR.get(), LogUtils.getLogger());

        // Configuration screen
        container.registerExtensionPoint(IConfigScreenFactory.class, (_, parent) -> new FWFConfig.ConfigScreen(parent));
    }

    @EventBusSubscriber(modid = "freecam_wf", value = Dist.CLIENT)
    static final class ModBusEvents {
        @SubscribeEvent
        static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
            event.registerCategory(CATEGORY);
            event.register(TOGGLE_FREEZE_KEY.get());
        }
    }

    @EventBusSubscriber(modid = "freecam_wf", value = Dist.CLIENT)
    static final class GameBusEvents {
        @SubscribeEvent
        static void onClientTickPost(ClientTickEvent.Post event) {
            while (TOGGLE_FREEZE_KEY.get().consumeClick())
                FreecamWF.config().toggleWorldEffect();
        }
    }
}
