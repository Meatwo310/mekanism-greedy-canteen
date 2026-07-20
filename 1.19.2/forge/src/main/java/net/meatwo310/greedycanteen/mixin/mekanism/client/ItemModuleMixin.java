package net.meatwo310.greedycanteen.mixin.mekanism.client;

import net.meatwo310.greedycanteen.config.ServerConfig;
import mekanism.common.item.ItemModule;
import mekanism.common.registries.MekanismItems;
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

@Mixin(ItemModule.class)
public abstract class ItemModuleMixin {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void greedycanteen$appendHoverText(
            @NotNull ItemStack stack,
            @Nullable Level level,
            @NotNull List<Component> tooltip,
            @NotNull TooltipFlag flag,
            CallbackInfo callback) {
        if (ServerConfig.InjectionUnit.ENABLE_EXTRA_INJECTION.getAsBoolean()
                && stack.is(MekanismItems.MODULE_NUTRITIONAL_INJECTION.get())) {
            tooltip.add(Component.translatable(
                    "tooltip.greedycanteen.nutritional_injection_unit.description"));
        }
    }
}
