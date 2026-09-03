package com.marcpg.fwf.compat;

public final class FreecamImplManager {
    private static FreecamImpl impl;

    public static void init() {
        FreecamIntegrations.Integration integration = FreecamIntegrations.AVAILABLE.stream()
                .filter(FreecamIntegrations.Integration::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No supported freecam mod installed"));

        try {
            impl = (FreecamImpl) Class.forName(integration.implementation()).getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        impl.init();
    }

    public static boolean isEnabled() {
        return impl != null && impl.isFreecamEnabled();
    }
}
