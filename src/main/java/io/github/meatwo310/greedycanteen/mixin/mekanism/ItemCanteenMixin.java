package io.github.meatwo310.greedycanteen.mixin.mekanism;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.api.text.EnumColor;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.item.gear.ItemCanteen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = ItemCanteen.class)
public abstract class ItemCanteenMixin {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void appendHoverTextInject(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag, CallbackInfo ci) {
        if (!ServerConfig.CANTEEN_ENABLED.get()) {
            return;
        }

        if (MekKeyHandler.isKeyPressed(MekanismKeyHandler.detailsKey)) {
            tooltip.add(Component.translatable("tooltip.greedycanteen.canteen.description1"));
            tooltip.add(Component.translatable("tooltip.greedycanteen.canteen.description2"));
        } else {
            tooltip.add(MekanismLang.HOLD_FOR_DETAILS.translateColored(EnumColor.GRAY,
                    EnumColor.INDIGO,
                    MekanismKeyHandler.detailsKey.getTranslatedKeyMessage()
            ));
        }
    }

    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"))
    private int min(int foodNeeded, int min, @Local Player player) {
        if (!ServerConfig.CANTEEN_ENABLED.get() || player.isShiftKeyDown()) {
            return Math.min(foodNeeded, min);
        }

        float saturationNeeded = 20.0f - player.getFoodData().getSaturationLevel();
        float saturationModifier = MekanismConfig.general.nutritionalPasteSaturation.get();
        int saturationFoodNeeded = saturationModifier <= 0 ? 0 : (int) Math.ceil(saturationNeeded / (saturationModifier * 2.0f));
        int needed = Math.min(Math.max(foodNeeded, saturationFoodNeeded), min);
//        LogUtils.getLogger().info("Needed: {}", needed);
        return needed;
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    private boolean canEatRedirect(Player player, boolean canAlwaysEat) {
        if (!ServerConfig.CANTEEN_ENABLED.get() || player.isShiftKeyDown()) {
            return player.canEat(canAlwaysEat);
        }

        return player.canEat(canAlwaysEat) || player.getFoodData().getSaturationLevel() < 20.0f;
    }
}
