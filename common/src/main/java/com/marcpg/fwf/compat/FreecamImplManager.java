package com.marcpg.fwf.compat;

public final class FreecamImplManager {
    private static FreecamImpl impl;
    private static FreecamIntegrations.Integration integration;

    public static void init() {
        FreecamIntegrations.Integration foundIntegration = FreecamIntegrations.AVAILABLE.stream()
                .filter(FreecamIntegrations.Integration::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No supported freecam mod installed"));

        try {
            impl = (FreecamImpl) Class.forName(foundIntegration.implementation()).getConstructor().newInstance();
            integration = foundIntegration;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        impl.init();
    }

    public static boolean isEnabled() {
        return impl != null && impl.isFreecamEnabled();
    }

    public static FreecamIntegrations.Integration integration() {
        return integration;
    }
}
