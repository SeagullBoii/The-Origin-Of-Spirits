package net.seagullboi.originofspirits.events;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.OriginOfSpirits;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TOOSRegistryEvent {
    @SubscribeEvent
    public static void registerConfig(RegistryEvent.Register<Feature<?>> event) {
        //Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OriginOfSpirits.MOD_ID, "blueberry_bush_patch"), TOOSConfiguredFeatures.Configs.BLUEBERRY_BUSH_PATCH);
    }

}
