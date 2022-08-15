package net.seagullboi.originofspirits;

import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import net.seagullboi.originofspirits.block.tile_entities.screen.GunsmithingTableScreen;
import net.seagullboi.originofspirits.datagen.*;
import net.seagullboi.originofspirits.datagen.client.TOOSBlockStateProvider;
import net.seagullboi.originofspirits.datagen.client.TOOSItemModelProvider;
import net.seagullboi.originofspirits.datagen.client.TOOSBlockItemModelProvider;
import net.seagullboi.originofspirits.network.TOOSNetwork;
import net.seagullboi.originofspirits.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OriginOfSpirits.MOD_ID)
public class OriginOfSpirits {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "originofspirits";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("originofspirits", "originofspirits"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    public OriginofspiritsModElements elements;

    public OriginOfSpirits() {
        elements = new OriginofspiritsModElements();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(this);
        eventBus.addListener(this::init);
        eventBus.addListener(this::clientLoad);
        eventBus.addListener(this::doClientStuff);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::gatherData);
        MinecraftForge.EVENT_BUS.register(new OriginofspiritsModFMLBusEvents(this));

        TOOSItems.ITEMS.register(eventBus);
        TOOSBlocks.BLOCKS.register(eventBus);
        TOOSBlocks.BLOCK_ITEMS.register(eventBus);
        ModTileEntities.register(eventBus);
        TOOSContainers.register(eventBus);
        TOOSEntityTypes.register(eventBus);
        ModStructures.register(eventBus);


        //Register ourselves for server and other game events we are interested in
        // MinecraftForge.EVENT_BUS.register(new OriginofspiritsModFMLBusEvents(this));
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        Atlases.addWoodType(ModWoodTypes.SACREDWOOD);
        Atlases.addWoodType(ModWoodTypes.SWIRLWOOD);
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(net.seagullboi.originofspirits.registry.TOOSBlocks.SWEET_POTATOES.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(net.seagullboi.originofspirits.registry.TOOSBlocks.DUCKWEED.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(net.seagullboi.originofspirits.registry.TOOSBlocks.ABYSSAL_SPAWNER.get(), RenderType.getCutout());
            ScreenManager.registerFactory(TOOSContainers.GUNSMITHING_TABLE_CONTAINER.get(),
                    GunsmithingTableScreen::new);
        });
    }
    private void setup(final FMLCommonSetupEvent event) {
        TOOSNetwork.initializeNetwork();
        event.enqueueWork(() -> {
            //Entity Spawn
            EntitySpawnPlacementRegistry.register(TOOSEntityTypes.LAZOCULUS.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
            //Wood Types
            WoodType.register(ModWoodTypes.SACREDWOOD);
            WoodType.register(ModWoodTypes.SWIRLWOOD);

            ModStructures.setupStructures();
        });
    }

    private void gatherData(final GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        final ExistingFileHelper efh = event.getExistingFileHelper();
        if (event.includeServer()) {
            dataGenerator.addProvider(new TOOSBlockStateProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSBlockItemModelProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSItemModelProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSLootTableProvider(dataGenerator));
            dataGenerator.addProvider(new TOOSRecipeProvider(dataGenerator));
            dataGenerator.addProvider(new TOOSBlockTagsProvider(dataGenerator, efh));
            dataGenerator.addProvider(new TOOSLangProvider(dataGenerator, "en_us_test"));
        }
    }

    private void init(FMLCommonSetupEvent event) {
        elements.getElements().forEach(element -> element.init(event));
    }

    public void clientLoad(FMLClientSetupEvent event) {
        elements.getElements().forEach(element -> element.clientLoad(event));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(elements.getBlocks().stream().map(Supplier::get).toArray(Block[]::new));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(elements.getItems().stream().map(Supplier::get).toArray(Item[]::new));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(elements.getEntities().stream().map(Supplier::get).toArray(EntityType[]::new));
    }

    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(elements.getEnchantments().stream().map(Supplier::get).toArray(Enchantment[]::new));
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
        elements.registerSounds(event);
    }

    private static class OriginofspiritsModFMLBusEvents {
        private final OriginOfSpirits parent;

        OriginofspiritsModFMLBusEvents(OriginOfSpirits parent) {
            this.parent = parent;
        }

        @SubscribeEvent
        public void serverLoad(FMLServerStartingEvent event) {
            this.parent.elements.getElements().forEach(element -> element.serverLoad(event));
        }
    }
}
