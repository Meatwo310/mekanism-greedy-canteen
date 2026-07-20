package net.meatwo310.greedycanteen.mixin.mekanism;

import net.meatwo310.greedycanteen.MekanismMixinHelper;
import net.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.item.gear.ItemCanteen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemCanteen.class)
public abstract class ItemCanteenMixin {
    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"))
    private int greedycanteen$calculateNeeded(
            int foodNeeded, int available, ItemStack stack, Level level, LivingEntity entity) {
        return MekanismMixinHelper.calculateNeeded(
                ServerConfig.Canteen.ENABLE_EXTRA_DRINKING.getAsBoolean(),
                foodNeeded,
                available,
                (Player) entity);
    }

    @Redirect(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean greedycanteen$canEat(Player player, boolean canAlwaysEat) {
        return MekanismMixinHelper.canEat(
                ServerConfig.Canteen.ENABLE_EXTRA_DRINKING.getAsBoolean(), player, canAlwaysEat);
    }
}
