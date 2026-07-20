package net.meatwo310.greedycanteen;

import net.meatwo310.greedycanteen.config.ModConfigs;
import net.meatwo310.greedycanteen.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.greedycanteen.mdk.config.VersionedConfigSpec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain(IEventBus modEventBus, ModContainer modContainer) {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.id("26.2-neo"));
        PlatformConfigRegistrar.registerAll(modContainer, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
