package net.seagullboi.originofspirits.registry;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.entity.*;

public class TOOSEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, OriginOfSpirits.MOD_ID);

    //Boats
    public static final RegistryObject<EntityType<SacredwoodBoatEntity>> SACREDWOOD_BOAT = ENTITY_TYPES.register("sacredwood_boat", () -> EntityType.Builder.<SacredwoodBoatEntity>create(SacredwoodBoatEntity::new, EntityClassification.MISC).size(1.5f, 0.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "sacredwood_boat").toString()));
    public static final RegistryObject<EntityType<SwirlwoodBoatEntity>> SWIRLWOOD_BOAT = ENTITY_TYPES.register("swirlwood_boat", () -> EntityType.Builder.<SwirlwoodBoatEntity>create(SwirlwoodBoatEntity::new, EntityClassification.MISC).size(1.5f, 0.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "swirlwood_boat").toString()));

    //Passive
    public static RegistryObject<EntityType<CaecanusEntity>> CAECANUS = ENTITY_TYPES.register("caecanus", () -> EntityType.Builder.create(CaecanusEntity::new, EntityClassification.CREATURE).size(0.75f, 2.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "caecanus").toString()));
    public static RegistryObject<EntityType<CabadorEntity>> CABADOR = ENTITY_TYPES.register("cabador", () -> EntityType.Builder.create(CabadorEntity::new, EntityClassification.CREATURE).size(1.3f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cabador").toString()));

    //Neutral
    public static RegistryObject<EntityType<CluffEntity>> CLUFF = ENTITY_TYPES.register("cluff", () -> EntityType.Builder.create(CluffEntity::new, EntityClassification.CREATURE).size(1f, 0.6f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cluff").toString()));

    //Hostile
    public static RegistryObject<EntityType<LazoculusEntity>> LAZOCULUS = ENTITY_TYPES.register("lazoculus", () -> EntityType.Builder.create(LazoculusEntity::new, EntityClassification.MONSTER).size(0.85f, 2.1f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "lazoculus").toString()));


    //Breedable
    public static final RegistryObject<EntityType<HobayoshEntity>> HOBAYOSH = ENTITY_TYPES.register("hobayosh", () -> EntityType.Builder.create(HobayoshEntity::new, EntityClassification.CREATURE).size(0.8f, 1.4f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "hobayosh").toString()));
    public static final RegistryObject<EntityType<SacFrogEntity>> SAC_FROG = ENTITY_TYPES.register("sac_frog", () -> EntityType.Builder.create(SacFrogEntity::new, EntityClassification.CREATURE).size(0.75f, 0.625f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "sac_frog").toString()));

    //MISC
    public static final RegistryObject<EntityType<SacFrogEggsEntity>> SAC_FROG_EGGS = ENTITY_TYPES.register("sac_frog_eggs", () -> EntityType.Builder.create(SacFrogEggsEntity::new, EntityClassification.CREATURE).size(0.6875f, 0.3125f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "sac_frog_eggs").toString()));

    //Projectiles
    public static final RegistryObject<EntityType<CursedLaserProjectile>> CURSED_LASER = ENTITY_TYPES.register("cursed_laser", () -> EntityType.Builder.<CursedLaserProjectile>create(CursedLaserProjectile::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cursed_laser").toString()));
    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITY_TYPES.register("bullet", () -> EntityType.Builder.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "bullet").toString()));
    public static final RegistryObject<EntityType<CluffCloudProjectile>> CLUFF_CLOUD = ENTITY_TYPES.register("cluff_cloud", () -> EntityType.Builder.<CluffCloudProjectile>create(CluffCloudProjectile::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cluff_cloud").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
