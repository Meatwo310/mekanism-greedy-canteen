package io.github.meatwo310.greedycanteen.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue CANTEEN_ENABLED = BUILDER
            .push("canteen")
            .comment("Whether the canteen modification is enabled or not")
            .define("enabled", true);

    public static final ForgeConfigSpec.BooleanValue INJECTION_UNIT_ENABLED = BUILDER
            .pop()
            .push("injectionUnit")
            .comment("Whether the nutritional injection unit modification is enabled or not")
            .define("enabled", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
