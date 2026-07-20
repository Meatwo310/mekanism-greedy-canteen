package net.meatwo310.greedycanteen;

import net.meatwo310.greedycanteen.config.ModConfigs;
import net.meatwo310.greedycanteen.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.greedycanteen.mdk.config.VersionedConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain(FMLJavaModLoadingContext ctx) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.21.8-forge"));
        PlatformConfigRegistrar.registerAll(ctx.getContainer(), VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
