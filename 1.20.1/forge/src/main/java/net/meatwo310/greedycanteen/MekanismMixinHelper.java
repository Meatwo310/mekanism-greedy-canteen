package net.meatwo310.greedycanteen;

import net.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.config.MekanismConfig;
import net.minecraft.world.entity.player.Player;

public final class MekanismMixinHelper {
    private MekanismMixinHelper() {}

    public static int calculateNeeded(boolean enabled, int foodNeeded, int available, Player player) {
        if (!enabled || player.isShiftKeyDown()) {
            return Math.min(foodNeeded, available);
        }

        double saturationNeeded = ServerConfig.TARGET_SATURATION.getAsDouble()
                - player.getFoodData().getSaturationLevel();
        double saturationModifier = MekanismConfig.general.nutritionalPasteSaturation.get();
        int saturationFoodNeeded = saturationModifier <= 0
                ? 0
                : (int) Math.ceil(saturationNeeded / (saturationModifier * 2.0));
        return Math.min(Math.max(foodNeeded, saturationFoodNeeded), available);
    }

    public static boolean canEat(boolean enabled, Player player, boolean canAlwaysEat) {
        if (!enabled || player.isShiftKeyDown()) {
            return player.canEat(canAlwaysEat);
        }

        return player.canEat(canAlwaysEat)
                || player.getFoodData().getSaturationLevel() < ServerConfig.TARGET_SATURATION.getAsDouble();
    }

    public static boolean shouldInject(Player player) {
        return ServerConfig.InjectionUnit.ENABLE_EXTRA_INJECTION.getAsBoolean()
                && (!ServerConfig.InjectionUnit.PAUSE_WHEN_FULL_HEALTH.getAsBoolean()
                || player.getHealth() < player.getMaxHealth());
    }
}
