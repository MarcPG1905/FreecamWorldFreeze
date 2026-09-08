package com.marcpg.fwf.compat;

import com.marcpg.fwf.FZFFeature;

import java.util.Set;

public enum FreecamIntegrations {
    XOLT("Freecam", "net.xolt.freecam.Freecam", "Xolt", FZFFeature.FREEZE_PLAYER),
    FREE_CAM_MC("FreeCamMc", "com.jasonzli.freecam.FreeCam", "FreeCamMc"),
    CAM_TWEAKS("Camera Tweaks", "cameratweaks.Freecam", "CamTweaks"),
    CAM_ENHANCE("Camera Enhancements", "me.syflog.camenh.Freecam", "CamEnhance"),
    EASY_FREECAM("Easy Freecam", "dev.elpu7.easyFreecam.client.FreecamController", "EasyFreecam"),
    WURST("WI Freecam", "net.wimods.freecam.WiFreecam", "Wurst"),
    KAPITEON("FreeCam by kapiteon", "com.kapiteon.freecam.FreeCam", "Kapiteon"),
    ZERGATUL("FreeCam by Zergatul", "com.zergatul.freecam.FreeCam", "Zergatul");

    private static final String BASE_PACKAGE = "com.marcpg.fwf.compat";

    public final String name;
    public final String checkedClass;
    public final String implementation;
    public final String mixin;
    public final Set<FZFFeature> nativeFeatures;

    FreecamIntegrations(String name, String checkedClass, String implementation, String mixin, Set<FZFFeature> nativeFeatures) {
        this.name = name;
        this.checkedClass = checkedClass;
        this.implementation = implementation;
        this.mixin = mixin;
        this.nativeFeatures = nativeFeatures;
    }

    FreecamIntegrations(String name, String checkedClass, String prefix, FZFFeature... disabledExtensions) {
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
