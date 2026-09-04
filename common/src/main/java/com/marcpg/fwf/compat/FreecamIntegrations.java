package com.marcpg.fwf.compat;

import java.util.List;

public final class FreecamIntegrations {
    private FreecamIntegrations() {}

    private static final String BASE_PACKAGE = "com.marcpg.fwf.compat";

    public static final List<Integration> AVAILABLE = List.of(
            new Integration("Freecam", "net.xolt.freecam.Freecam", "Xolt"),
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
            String mixin
    ) {
        public Integration(String name, String checkedClass, String prefix) {
            this(name, checkedClass,
                    BASE_PACKAGE + ".implementations." + prefix + "Impl",
                    BASE_PACKAGE + ".mixin." + prefix + "Mixin"
            );
        }

        public boolean isAvailable() {
            try {
                Class.forName(checkedClass, false, getClass().getClassLoader());
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }
    }
}
