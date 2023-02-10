package net.seagullboi.originofspirits.world;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.api.WorldGenUtils;
import net.seagullboi.originofspirits.entity.*;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
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
        //   ToosMobSpawns.addEntityToSpecificBiomes(event, TOOSEntityTypes.JELLYFISH.get(), 50, 4, 6, Biomes.WARM_OCEAN, Biomes.DEEP_WARM_OCEAN);

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

        private static final Consumer<MobSpawnInfoBuilder> DECEPTIVE_ISLANDS_ENTITIES = (builder) -> {
            builder.withSpawner(monster, new MobSpawnInfo.Spawners(TOOSEntityTypes.LAZOCULUS.get(), 10, 1, 1));
            builder.withSpawner(monster, new MobSpawnInfo.Spawners(TOOSEntityTypes.CURSED_EYE.get(), 10, 1, 1));
        };

        private static final Consumer<MobSpawnInfoBuilder> ABYSS_ENTITIES = (builder) -> {
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.ELECTRIC_EEL.get(), 15, 1, 3));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(TOOSEntityTypes.ELECTRIC_SURGEONFISH.get(), 40, 6, 9));
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.JELLYFISH.get(), 50, 1, 2));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(EnchantedSquidEntity.entity, 5, 1, 3));
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.BOX_JELLYFISH.get(), 10, 1, 1));
        };

        private static final Consumer<MobSpawnInfoBuilder> COLORFUL_ABYSS_ENTITIES = (builder) -> {
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.ELECTRIC_EEL.get(), 15, 1, 3));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(TOOSEntityTypes.ELECTRIC_SURGEONFISH.get(), 40, 6, 9));
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.JELLYFISH.get(), 50, 1, 2));
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(EnchantedSquidEntity.entity, 5, 1, 3));
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.BOX_JELLYFISH.get(), 10, 1, 1));
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.CREPTIPISCES.get(), 30, 3, 6));
        };

        private static final Consumer<MobSpawnInfoBuilder> JELLYFISH = (builder) -> {
            builder.withSpawner(waterAmbient, new MobSpawnInfo.Spawners(TOOSEntityTypes.JELLYFISH.get(), 50, 1, 2));
        };

        private static final Consumer<MobSpawnInfoBuilder> WARM_OCEAN_ENTITIES = (builder) -> {
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(TOOSEntityTypes.JELLYFISH.get(), 50, 6, 8));
        };

        private static final Consumer<MobSpawnInfoBuilder> SWAMP_ENTITIES = (builder) -> {
            builder.withSpawner(waterCreature, new MobSpawnInfo.Spawners(TOOSEntityTypes.MANATEE.get(), 10, 3, 5));
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

            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.WARM_OCEAN)) {
                WARM_OCEAN_ENTITIES.accept(spawnInfoBuilder);

            }

            if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SWAMP)) {
                SWAMP_ENTITIES.accept(spawnInfoBuilder);
            }
        }

        private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type, int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
            // Goes through each entry in the biomes and sees if it matches the current biome we are loading
            boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
                    .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

            if(isBiomeSelected) {
                addEntityToAllBiomes(event, type, weight, minCount, maxCount);
            }
        }

        private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                 int weight, int minCount, int maxCount) {
            List<MobSpawnInfo.Spawners> base = event.getSpawns().getSpawner(type.getClassification());
            base.add(new MobSpawnInfo.Spawners(type,weight, minCount, maxCount));
        }


        public static void createSpawn(MobSpawnInfoBuilder builder, EntityClassification classification, Supplier<? extends EntityType<?>> entity, int initWeight, int minSpawn, int maxSpawn, @Nullable ForgeConfigSpec.ConfigValue<Boolean> config) {
            builder.withSpawner(classification, new MobSpawnInfo.Spawners(entity.get(), initWeight, minSpawn, maxSpawn));
        }


    }
}
