package io.github.meatwo310.greedycanteen.mixin.mekanism;

import io.github.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.item.ItemModule;
import mekanism.common.registries.MekanismItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemModule.class)
public class ItemModuleMixin {
    @Inject(method = "appendHoverText", at = @At(
            value = "INVOKE",
            target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
            shift = At.Shift.AFTER,
            ordinal = 4
    ))
    private void appendHoverTextInject(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag, CallbackInfo ci) {
        if (!ServerConfig.INJECTION_UNIT_ENABLE.get() || !stack.is(MekanismItems.MODULE_NUTRITIONAL_INJECTION.get())) {
            return;
        }

        tooltip.add(Component.translatable("tooltip.greedycanteen.nutritional_injection_unit.description"));
    }
}
