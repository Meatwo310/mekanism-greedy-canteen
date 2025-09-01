package io.github.meatwo310.greedycanteen;

import mekanism.common.config.MekanismConfig;
import net.minecraft.world.entity.player.Player;

public class MekanismMixinHelper {
    public static int calcNeeded(boolean enabled, int foodNeeded, int min, Player player) {
        if (!enabled || player.isShiftKeyDown()) {
            return Math.min(foodNeeded, min);
        }

        float saturationNeeded = 20.0f - player.getFoodData().getSaturationLevel();
        float saturationModifier = MekanismConfig.general.nutritionalPasteSaturation.get();
        int saturationFoodNeeded = saturationModifier <= 0 ? 0 : (int) Math.ceil(saturationNeeded / (saturationModifier * 2.0f));
        int needed = Math.min(Math.max(foodNeeded, saturationFoodNeeded), min);
        //        LogUtils.getLogger().info("Needed: {}", needed);
        return needed;
    }

    public static boolean canEat(boolean enabled, Player player, boolean canAlwaysEat) {
        if (!enabled || player.isShiftKeyDown()) {
            return player.canEat(canAlwaysEat);
        }

        return player.canEat(canAlwaysEat) || player.getFoodData().getSaturationLevel() < 20.0f;
    }
}
