package io.github.meatwo310.greedycanteen.mixin.mekanism;

import com.llamalad7.mixinextras.sugar.Local;
import mekanism.common.config.MekanismConfig;
import mekanism.common.item.gear.ItemCanteen;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemCanteen.class)
public abstract class ItemCanteenMixin {
    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"))
    private int min(int foodNeeded, int min, @Local Player player) {
        float saturationNeeded = 20.0f - player.getFoodData().getSaturationLevel();
        float saturationModifier = MekanismConfig.general.nutritionalPasteSaturation.get();
        int saturationFoodNeeded = saturationModifier <= 0 ? 0 : (int) Math.ceil(saturationNeeded / (saturationModifier * 2.0f));
        int needed = Math.min(Math.max(foodNeeded, saturationFoodNeeded), min);
//        LogUtils.getLogger().info("Needed: {}", needed);
        return needed;
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean canEatRedirect(Player player, boolean canAlwaysEat) {
        return player.canEat(canAlwaysEat) || player.getFoodData().getSaturationLevel() < 20.0f;
    }
}
