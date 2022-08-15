package net.seagullboi.originofspirits.world;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.api.WorldGenUtils;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.entity.*;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID)
public class ModEntityGeneration {

    /**
    * @credit Bioplethora - [missing link]
     */
    @SubscribeEvent
    public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
        ToosMobSpawns.acceptMobSpawns(event);
        //TOOSEntityGeneration.onEntitySpawn(event);
    }

    private static class ToosMobSpawns {

        static EntityClassification creature = EntityClassification.CREATURE;
        static EntityClassification monster = EntityClassification.MONSTER;
        static EntityClassification ambient = EntityClassification.AMBIENT;
        static EntityClassification waterCreature = EntityClassification.WATER_CREATURE;
        static EntityClassification waterAmbient = EntityClassification.WATER_AMBIENT;

        private static final Consumer<MobSpawnInfoBuilder> SACRED_PLAINS_ENTITIES = (builder) -> {
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.HOBAYOSH.get(), 100, 4, 6));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(PixieEntity.entity, 60, 1, 3));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.CABADOR.get(), 75, 3, 5));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(FlyingFishEntity.entity, 80, 3, 5));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.CLUFF.get(), 50, 5, 9));
        };

        private static final Consumer<MobSpawnInfoBuilder> SACRED_FOREST_ENTITIES = (builder) -> {
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.HOBAYOSH.get(), 100, 4, 6));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(PixieEntity.entity, 60, 1, 3));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.CABADOR.get(), 75, 3, 5));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(FlyingFishEntity.entity, 80, 3, 5));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.CLUFF.get(), 50, 5, 9));
        };

        private static final Consumer<MobSpawnInfoBuilder> HOLY_MARSH_ENTITIES = (builder) -> {
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.SAC_FROG.get(), 80, 6, 8));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.SAC_FROG_EGGS.get(), 80, 4, 6));
            builder.withSpawner(creature, new MobSpawnInfo.Spawners(TOOSEntityTypes.CLUFF.get(), 50, 5, 9));
        };

        private static final Consumer<MobSpawnInfoBuilder> ABYSS_ENTITIES = (builder) -> {
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(ElectricEelEntity.entity, 60, 5, 8));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(ElectricSurgeonfishEntity.entity, 90, 6, 9));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(EnchantedSquidEntity.entity, 40, 1, 3));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(EnchantedSquidEntity.entity, 40, 1, 3));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(JellyfishEntity.entity, 70, 4, 6));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(BoxJellyfishEntity.entity, 60, 3, 6));
        };
        private static final Consumer<MobSpawnInfoBuilder> COLORFUL_ABYSS_ENTITIES = (builder) -> {
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(ElectricEelEntity.entity, 60, 5, 8));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(ElectricSurgeonfishEntity.entity, 90, 6, 9));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(EnchantedSquidEntity.entity, 40, 1, 3));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(EnchantedSquidEntity.entity, 40, 1, 3));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(JellyfishEntity.entity, 70, 4, 6));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(BoxJellyfishEntity.entity, 60, 3, 6));
        };
        private static final Consumer<MobSpawnInfoBuilder> DECEPTIVE_ISLANDS_ENTITIES = (builder) -> {
         builder.withSpawner(monster, new MobSpawnInfo.Spawners(TOOSEntityTypes.LAZOCULUS.get(), 10, 1, 1));
        };

        public static void acceptMobSpawns(BiomeLoadingEvent event) {
            MobSpawnInfoBuilder spawnInfoBuilder = event.getSpawns();
            RegistryKey<Biome> biome = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, event.getName());
            Random random = new Random();
            int randomise = random.nextInt(100);

            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SACRED_PLAINS)) {
                SACRED_PLAINS_ENTITIES.accept(spawnInfoBuilder);
            }
            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SACRED_FOREST)) {
                SACRED_FOREST_ENTITIES.accept(spawnInfoBuilder);
            }
            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.HOLY_MARSH)) {
                HOLY_MARSH_ENTITIES.accept(spawnInfoBuilder);
            }

            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.ABYSS)) {
                ABYSS_ENTITIES.accept(spawnInfoBuilder);
            }

            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.COLORFUL_ABYSS)) {
                COLORFUL_ABYSS_ENTITIES.accept(spawnInfoBuilder);
            }

            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.DECEPTIVE_ISLANDS)) {
                DECEPTIVE_ISLANDS_ENTITIES.accept(spawnInfoBuilder);
            }
        }

        public static void createSpawn(MobSpawnInfoBuilder builder, EntityClassification classification, Supplier<? extends EntityType<?>> entity, int initWeight, int minSpawn, int maxSpawn, @Nullable ForgeConfigSpec.ConfigValue<Boolean> config) {
            builder.withSpawner(classification, new MobSpawnInfo.Spawners(entity.get(), initWeight, minSpawn, maxSpawn));
        }

        public static void registerSpawn() {
            EntitySpawnPlacementRegistry.register(TOOSEntityTypes.LAZOCULUS.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        }
    }
}
