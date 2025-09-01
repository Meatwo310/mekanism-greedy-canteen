package io.github.meatwo310.greedycanteen.mixin.mekanism;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.meatwo310.greedycanteen.MekanismMixinHelper;
import io.github.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.content.gear.mekasuit.ModuleNutritionalInjectionUnit;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ModuleNutritionalInjectionUnit.class)
public class ModuleNutritionalInjectionUnitMixin {
    @Redirect(method = "tickServer", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0), remap = false)
    private int min(int foodNeeded, int min, @Local(argsOnly = true) Player player) {
        return MekanismMixinHelper.calcNeeded(greedycanteen$needExtraInjection(player), foodNeeded, min, player);
    }

    @Redirect(method = "tickServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean canEatRedirect(Player player, boolean canAlwaysEat) {
        return MekanismMixinHelper.canEat(greedycanteen$needExtraInjection(player), player, canAlwaysEat);
    }

    @Unique
    private static boolean greedycanteen$needExtraInjection(Player player) {
        return ServerConfig.INJECTION_UNIT_ENABLE.get() && (!ServerConfig.INJECTION_UNIT_PAUSE_WHEN_FULL_HEALTH.get() || player.getHealth() < player.getMaxHealth());
    }
}
