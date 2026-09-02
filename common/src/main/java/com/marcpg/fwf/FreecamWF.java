package com.marcpg.fwf;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;

import java.nio.file.Path;

public final class FreecamWF {
    public static final String MOD_ID = "freecam_wf";

    public static final Identifier KEY_CATEGORY_IDENTIFIER = Identifier.fromNamespaceAndPath(FreecamWF.MOD_ID, "main");
    public static KeyMapping createToggleFreezeKey(KeyMapping.Category category) {
        return new KeyMapping("key." + FreecamWF.MOD_ID + ".toggle", InputConstants.Type.KEYSYM, InputConstants.KEY_F6, category);
    }

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
    }
}
