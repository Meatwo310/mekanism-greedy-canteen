package net.meatwo310.greedycanteen;

import net.meatwo310.greedycanteen.config.ModConfigs;
import net.meatwo310.greedycanteen.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.greedycanteen.mdk.config.VersionedConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain() {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.loc("1.18.2-forge"));
        PlatformConfigRegistrar.registerAll(VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
