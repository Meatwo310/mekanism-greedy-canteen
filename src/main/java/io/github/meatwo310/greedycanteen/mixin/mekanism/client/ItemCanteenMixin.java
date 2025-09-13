package io.github.meatwo310.greedycanteen.mixin.mekanism.client;

import io.github.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.api.text.EnumColor;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.item.gear.ItemCanteen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemCanteen.class)
public class ItemCanteenMixin {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void appendHoverTextInject(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag, CallbackInfo ci) {
        if (!ServerConfig.CANTEEN_ENABLE.get()) {
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
}
