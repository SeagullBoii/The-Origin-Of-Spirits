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
    public static RegistryObject<EntityType<ManateeEntity>> MANATEE = ENTITY_TYPES.register("manatee", () -> EntityType.Builder.create(ManateeEntity::new, EntityClassification.CREATURE).size(1.6f, 1.0f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "manatee").toString()));
    public static RegistryObject<EntityType<ClamEntity>> CLAM = ENTITY_TYPES.register("clam", () -> EntityType.Builder.create(ClamEntity::new, EntityClassification.WATER_CREATURE).size(0.875f, 0.375f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "clam").toString()));

    //Neutral
    public static RegistryObject<EntityType<CluffEntity>> CLUFF = ENTITY_TYPES.register("cluff", () -> EntityType.Builder.create(CluffEntity::new, EntityClassification.CREATURE).size(1f, 0.6f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cluff").toString()));

    //Hostile
    public static RegistryObject<EntityType<LazoculusEntity>> LAZOCULUS = ENTITY_TYPES.register("lazoculus", () -> EntityType.Builder.create(LazoculusEntity::new, EntityClassification.MONSTER).size(0.85f, 2.1f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "lazoculus").toString()));
    public static RegistryObject<EntityType<CursedEyeEntity>> CURSED_EYE = ENTITY_TYPES.register("cursed_eye", () -> EntityType.Builder.create(CursedEyeEntity::new, EntityClassification.MONSTER).size(0.5f, 0.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cursed_eye").toString()));
    public static RegistryObject<EntityType<JellyfishEntity>> JELLYFISH = ENTITY_TYPES.register("jellyfish", () -> EntityType.Builder.create(JellyfishEntity::new, EntityClassification.MONSTER).size(0.6f, 0.525f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "jellyfish").toString()));
    public static RegistryObject<EntityType<BoxJellyfishEntity>> BOX_JELLYFISH = ENTITY_TYPES.register("box_jellyfish", () -> EntityType.Builder.create(BoxJellyfishEntity::new, EntityClassification.MONSTER).size(0.6f, 0.525f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "box_jellyfish").toString()));
    public static RegistryObject<EntityType<ElectricEelEntity>> ELECTRIC_EEL = ENTITY_TYPES.register("electric_eel", () -> EntityType.Builder.create(ElectricEelEntity::new, EntityClassification.MONSTER).size(1f, 0.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "electric_eel").toString()));
    public static RegistryObject<EntityType<ElectricSurgeonfishEntity>> ELECTRIC_SURGEONFISH = ENTITY_TYPES.register("electric_surgeonfish", () -> EntityType.Builder.create(ElectricSurgeonfishEntity::new, EntityClassification.MONSTER).size(0.6f, 0.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "electric_surgeonfish").toString()));

    public static RegistryObject<EntityType<CreptipiscesEntity>> CREPTIPISCES = ENTITY_TYPES.register("creptipisces", () -> EntityType.Builder.create(CreptipiscesEntity::new, EntityClassification.MONSTER).size(0.5f, 0.5f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "creptipisces").toString()));

    //Breedable
    public static final RegistryObject<EntityType<HobayoshEntity>> HOBAYOSH = ENTITY_TYPES.register("hobayosh", () -> EntityType.Builder.create(HobayoshEntity::new, EntityClassification.CREATURE).size(0.8f, 1.4f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "hobayosh").toString()));
    public static final RegistryObject<EntityType<SacFrogEntity>> SAC_FROG = ENTITY_TYPES.register("sac_frog", () -> EntityType.Builder.create(SacFrogEntity::new, EntityClassification.CREATURE).size(0.75f, 0.625f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "sac_frog").toString()));

    //MISC
    public static final RegistryObject<EntityType<SacFrogEggsEntity>> SAC_FROG_EGGS = ENTITY_TYPES.register("sac_frog_eggs", () -> EntityType.Builder.create(SacFrogEggsEntity::new, EntityClassification.CREATURE).size(0.6875f, 0.3125f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "sac_frog_eggs").toString()));

    //Projectiles
    public static final RegistryObject<EntityType<CursedLaserProjectile>> CURSED_LASER = ENTITY_TYPES.register("cursed_laser", () -> EntityType.Builder.<CursedLaserProjectile>create(CursedLaserProjectile::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cursed_laser").toString()));
    public static final RegistryObject<EntityType<CluffCloudProjectile>> CLUFF_CLOUD = ENTITY_TYPES.register("cluff_cloud", () -> EntityType.Builder.<CluffCloudProjectile>create(CluffCloudProjectile::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "cluff_cloud").toString()));
    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITY_TYPES.register("bullet", () -> EntityType.Builder.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "bullet").toString()));
    public static final RegistryObject<EntityType<MagneticRayProjectile>> MAGNETIC_RAY = ENTITY_TYPES.register("magnetic_ray", () -> EntityType.Builder.<MagneticRayProjectile>create(MagneticRayProjectile::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "magnetic_ray").toString()));
    public static final RegistryObject<EntityType<CreptipiscesSpikeEntity>> CREPTIPISCES_SPIKE = ENTITY_TYPES.register("creptipisces_spike", () -> EntityType.Builder.<CreptipiscesSpikeEntity>create(CreptipiscesSpikeEntity::new, EntityClassification.MISC).size(0.8f, 0.8f).build(new ResourceLocation(OriginOfSpirits.MOD_ID, "creptipisces_spike").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
