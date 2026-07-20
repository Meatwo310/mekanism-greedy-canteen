package net.meatwo310.greedycanteen;

import net.meatwo310.greedycanteen.config.ModConfigs;
import net.meatwo310.greedycanteen.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.greedycanteen.mdk.config.VersionedConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain(FMLJavaModLoadingContext context) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.19.2-forge"));
        PlatformConfigRegistrar.registerAll(context, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
