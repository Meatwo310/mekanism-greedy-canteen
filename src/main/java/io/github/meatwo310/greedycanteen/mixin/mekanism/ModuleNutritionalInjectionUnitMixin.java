package io.github.meatwo310.greedycanteen.mixin.mekanism;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.meatwo310.greedycanteen.MekanismMixinHelper;
import io.github.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.content.gear.mekasuit.ModuleNutritionalInjectionUnit;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ModuleNutritionalInjectionUnit.class, remap = false)
public class ModuleNutritionalInjectionUnitMixin {
    @Redirect(method = "tickServer", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0))
    private int min(int foodNeeded, int min, @Local(argsOnly = true) Player player) {
        return MekanismMixinHelper.calcNeeded(ServerConfig.INJECTION_UNIT_ENABLED.get(), foodNeeded, min, player);
    }

    @Redirect(method = "tickServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean canEatRedirect(Player player, boolean canAlwaysEat) {
        return MekanismMixinHelper.canEat(ServerConfig.INJECTION_UNIT_ENABLED.get(), player, canAlwaysEat);
    }
}
