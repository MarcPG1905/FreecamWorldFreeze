package com.marcpg.fwf.compat;

import java.util.Arrays;

public final class FreecamImplManager {
    private static FreecamImpl impl;
    private static FreecamIntegrations integration;

    public static void init() {
        FreecamIntegrations foundIntegration = Arrays.stream(FreecamIntegrations.values())
                .filter(FreecamIntegrations::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No supported freecam mod installed"));

        try {
            impl = (FreecamImpl) Class.forName(foundIntegration.implementation).getConstructor().newInstance();
            integration = foundIntegration;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        impl.init();
    }

    public static boolean isEnabled() {
        return impl != null && impl.isFreecamEnabled();
    }

    public static FreecamIntegrations integration() {
        return integration;
    }
}
