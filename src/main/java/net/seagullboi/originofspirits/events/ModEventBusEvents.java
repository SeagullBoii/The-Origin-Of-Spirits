package net.seagullboi.originofspirits.events;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.*;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.seagullboi.originofspirits.registry.ModSpawnEggs;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new FishingJunkAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(OriginOfSpirits.MOD_ID,"gel_from_fishing")));
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(TOOSEntityTypes.CAECANUS.get(), CaecanusEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.HOBAYOSH.get(), HobayoshEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.SAC_FROG_EGGS.get(), HobayoshEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.SAC_FROG.get(), SacFrogEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.LAZOCULUS.get(), LazoculusEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.CLUFF.get(), CluffEntity.setCustomAttributes().create());
        event.put(TOOSEntityTypes.CABADOR.get(), CabadorEntity.setCustomAttributes().create());

    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggs.initSpawnEggs();
    }


}
