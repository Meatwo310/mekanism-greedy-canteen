package io.github.meatwo310.greedycanteen.mixin.mekanism;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.meatwo310.greedycanteen.MekanismMixinHelper;
import io.github.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.item.gear.ItemCanteen;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemCanteen.class)
public abstract class ItemCanteenMixin {
    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"))
    private int min(int foodNeeded, int min, @Local Player player) {
        return MekanismMixinHelper.calcNeeded(ServerConfig.CANTEEN_ENABLE.get(), foodNeeded, min, player);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean canEatRedirect(Player player, boolean canAlwaysEat) {
        return MekanismMixinHelper.canEat(ServerConfig.CANTEEN_ENABLE.get(), player, canAlwaysEat);
    }
}
