package net.meatwo310.greedycanteen;

import net.fabricmc.api.ModInitializer;
import net.meatwo310.greedycanteen.config.ModConfigs;
import net.meatwo310.greedycanteen.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.greedycanteen.mdk.config.VersionedConfigSpec;

public class ModMain implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.id("26.1.2-fabric"));
        PlatformConfigRegistrar.registerAll(Constants.MODID, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
