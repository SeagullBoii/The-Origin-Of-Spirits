package net.seagullboi.originofspirits.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.*;
import net.seagullboi.originofspirits.particle.CursedFlameParticle;
import net.seagullboi.originofspirits.registry.ModSpawnEggs;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.seagullboi.originofspirits.registry.TOOSParticles;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TOOSEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(TOOSEntityTypes.CAECANUS.get(), CaecanusEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.HOBAYOSH.get(), HobayoshEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.SAC_FROG_EGGS.get(), HobayoshEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.SAC_FROG.get(), SacFrogEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.LAZOCULUS.get(), LazoculusEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.CLUFF.get(), CluffEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.CABADOR.get(), CabadorEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.CURSED_EYE.get(), CursedEyeEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggs.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(TOOSParticles.CURSED_FLAME_PARTICLE.get(), CursedFlameParticle.Factory::new);
    }

}
