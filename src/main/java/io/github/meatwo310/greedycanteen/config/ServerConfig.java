package io.github.meatwo310.greedycanteen.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.DoubleValue TARGET_SATURATION = BUILDER
            .comment("The target saturation level for extra drinking/injection")
            .defineInRange("targetSaturation", 18.4, 0.0, 20.0);

    public static final ForgeConfigSpec.BooleanValue CANTEEN_ENABLE = BUILDER
            .push("canteen")
            .comment("Set to true to allow extra drinking with the canteen")
            .define("enableExtraDrinking", true);

    public static final ForgeConfigSpec.BooleanValue INJECTION_UNIT_ENABLE = BUILDER
            .pop()
            .push("injectionUnit")
            .comment("Set to true to allow extra injection with the nutritional injection unit")
            .define("enableExtraInjection", true);

    public static final ForgeConfigSpec.BooleanValue INJECTION_UNIT_PAUSE_WHEN_FULL_HEALTH = BUILDER
            .comment("Set to true to pause extra injection when the player's health is full")
            .define("pauseWhenFullHealth", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
