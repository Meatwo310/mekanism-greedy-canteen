package net.meatwo310.greedycanteen.config;

import net.meatwo310.greedycanteen.mdk.config.ConfigEntries;
import net.meatwo310.greedycanteen.mdk.config.ConfigEntry;
import net.meatwo310.greedycanteen.mdk.config.ConfigEntryBuilder;

public class ServerConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntry.DoubleEntry TARGET_SATURATION = BUILDER
            .comment("The target saturation level for extra drinking and injection.")
            .defineInRange("targetSaturation", 18.4, 0.0, 20.0);

    public static final ConfigEntries CANTEEN = BUILDER
            .comment("Canteen settings.")
            .category("canteen", Canteen.ENTRIES);

    public static final ConfigEntries INJECTION_UNIT = BUILDER
            .comment("Nutritional Injection Unit settings.")
            .category("injectionUnit", InjectionUnit.ENTRIES);

    public static final ConfigEntries ENTRIES = BUILDER.build();

    public static final class Canteen {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntry.BooleanEntry ENABLE_EXTRA_DRINKING = BUILDER
                .comment("Allow the canteen to keep drinking until the target saturation is reached.")
                .define("enableExtraDrinking", true);

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final class InjectionUnit {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntry.BooleanEntry ENABLE_EXTRA_INJECTION = BUILDER
                .comment("Allow the Nutritional Injection Unit to feed until the target saturation is reached.")
                .define("enableExtraInjection", true);

        public static final ConfigEntry.BooleanEntry PAUSE_WHEN_FULL_HEALTH = BUILDER
                .comment("Pause extra injection while the player's health is full.")
                .define("pauseWhenFullHealth", true);

        public static final ConfigEntries ENTRIES = BUILDER.build();
    }
}
