package com.marcpg.fwf.compat;

import com.marcpg.fwf.FZFFeature;

import java.util.List;
import java.util.Set;

public final class FreecamIntegrations {
    private FreecamIntegrations() {}

    private static final String BASE_PACKAGE = "com.marcpg.fwf.compat";

    public static final List<Integration> AVAILABLE = List.of(
            new Integration("Freecam", "net.xolt.freecam.Freecam", "Xolt", FZFFeature.FREEZE_PLAYER),
            new Integration("FreeCamMc", "com.jasonzli.freecam.FreeCam", "FreeCamMc"),
            new Integration("Camera Tweaks", "cameratweaks.Freecam", "CamTweaks"),
            new Integration("Camera Enhancements", "me.syflog.camenh.Freecam", "CamEnhance"),
            new Integration("Easy Freecam", "dev.elpu7.easyFreecam.client.FreecamController", "EasyFreecam"),
            new Integration("WI Freecam", "net.wimods.freecam.WiFreecam", "Wurst"),
            new Integration("FreeCam by kapiteon", "com.kapiteon.freecam.FreeCam", "Kapiteon"),
            new Integration("FreeCam by Zergatul", "com.zergatul.freecam.FreeCam", "Zergatul")
    );

    public record Integration(
            String name,
            String checkedClass,
            String implementation,
            String mixin,
            Set<FZFFeature> nativeFeatures
    ) {
        public Integration(String name, String checkedClass, String prefix, FZFFeature... disabledExtensions) {
            this(
                    name,
                    checkedClass,
                    BASE_PACKAGE + ".implementations." + prefix + "Impl",
                    BASE_PACKAGE + ".mixin." + prefix + "Mixin",
                    Set.of(disabledExtensions)
            );
        }

        public boolean isAvailable() {
            String resource = checkedClass.replace('.', '/') + ".class";
            try { // Use this weird checking procedure to not even hint at the JVM to load the class,
                // because apparently, the JVM wants to load it VERY badly.
                return Thread.currentThread().getContextClassLoader().getResource(resource) != null;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
