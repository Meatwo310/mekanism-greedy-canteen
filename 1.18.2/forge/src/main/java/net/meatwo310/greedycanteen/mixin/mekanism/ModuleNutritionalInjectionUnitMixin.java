package net.meatwo310.greedycanteen.mixin.mekanism;

import net.meatwo310.greedycanteen.MekanismMixinHelper;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.mekasuit.ModuleNutritionalInjectionUnit;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModuleNutritionalInjectionUnit.class)
public abstract class ModuleNutritionalInjectionUnitMixin {
    @Redirect(
            method = "tickServer",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 0),
            remap = false)
    private int greedycanteen$calculateNeeded(
            int foodNeeded,
            int available,
            IModule<ModuleNutritionalInjectionUnit> module,
            Player player) {
        return MekanismMixinHelper.calculateNeeded(
                MekanismMixinHelper.shouldInject(player), foodNeeded, available, player);
    }

    @Redirect(
            method = "tickServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean greedycanteen$canEat(Player player, boolean canAlwaysEat) {
        return MekanismMixinHelper.canEat(
                MekanismMixinHelper.shouldInject(player), player, canAlwaysEat);
    }
}
