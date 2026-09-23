package com.marcpg.fwf;

import com.marcpg.faststats.ModContext;
import com.marcpg.fwf.compat.FreecamImplManager;
import com.mojang.blaze3d.platform.InputConstants;
import dev.faststats.data.Metric;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;

import java.nio.file.Path;

public final class FreecamWF {
    public static final String MOD_ID = "freecam_wf";

    public static final Identifier KEY_CATEGORY_IDENTIFIER = Identifier.fromNamespaceAndPath(FreecamWF.MOD_ID, "main");

    private static final ModContext context = new ModContext.Factory(FreecamWF.MOD_ID, "e2df1a870be81c5962235bd202b0f439")
            .metrics(factory -> factory
                    .addMetric(Metric.string("freecam_mod", () -> FreecamImplManager.integration().name))
                    .addMetric(Metric.string("config_tick_speed", () -> FreecamWF.config().getTickSpeedCategory()))
                    .addMetric(Metric.bool("config_world_effect_enabled", () -> FreecamWF.config().worldEffectEnabled))
                    .addMetric(Metric.bool("config_freeze_player", () -> FreecamWF.config().freezePlayer))
                    .create())
            .create();

    private static Path configFile;
    private static Logger logger;
    private static FWFConfig config;

    public static Path configFile() { return configFile; }
    public static Logger logger() { return logger; }
    public static FWFConfig config() { return config; }

    public static void setup(Path configDir, Logger logger) {
        FreecamWF.configFile = configDir.resolve(MOD_ID + ".txt");
        FreecamWF.logger = logger;

        FreecamWF.config = new FWFConfig();
        FreecamWF.config.loadConfig();

        FreecamImplManager.init();
    }

    public static KeyMapping createWorldEffectToggleKey(KeyMapping.Category category) {
        return new KeyMapping("key.freecam_wf.toggle.effect", InputConstants.Type.KEYSYM, InputConstants.KEY_F6, category);
    }

    public static KeyMapping createPlayerFreezeToggleKey(KeyMapping.Category category) {
        return new KeyMapping("key.freecam_wf.toggle.freeze_player", InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), category);
    }
}
