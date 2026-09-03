package com.marcpg.fwf.compat;

import java.util.List;

public final class FreecamIntegrations {
    private FreecamIntegrations() {}

    public static final List<Integration> AVAILABLE = List.of(
            new Integration("Freecam", "net.xolt.freecam.Freecam", "Xolt")
    );

    public record Integration(
            String name,
            String checkedClass,
            String implementation,
            String mixin
    ) {
        public Integration(String name, String checkedClass, String prefix) {
            this(name, checkedClass, "com.marcpg.fwf.implementations." + prefix + "Impl", "com.marcpg.fwf.mixin." + prefix + "Mixin");
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
