package io.github.meatwo310.greedycanteen;

import io.github.meatwo310.greedycanteen.config.ServerConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GreedyCanteen.MODID)
public class GreedyCanteen {
    public static final String MODID = "greedycanteen";

    public GreedyCanteen(FMLJavaModLoadingContext ctx) {
        ctx.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
    }
}
