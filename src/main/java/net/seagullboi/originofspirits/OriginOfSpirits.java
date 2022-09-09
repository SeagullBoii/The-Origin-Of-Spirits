package net.seagullboi.originofspirits;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;
import net.seagullboi.originofspirits.registry.*;
import net.seagullboi.originofspirits.registry.worldgen.ModStructures;
import net.seagullboi.originofspirits.registry.worldgen.TOOSFeatures;
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
        MinecraftForge.EVENT_BUS.register(new OriginofspiritsModFMLBusEvents(this));

        TOOSItems.ITEMS.register(eventBus);
        TOOSBlocks.BLOCKS.register(eventBus);
        TOOSBlocks.BLOCK_ITEMS.register(eventBus);
        TOOSBlocks.FLUIDS.register(eventBus);
        ModTileEntities.register(eventBus);
        TOOSContainers.register(eventBus);
        TOOSEntityTypes.register(eventBus);
        ModStructures.register(eventBus);
        TOOSSoundEvents.register(eventBus);
        TOOSFeatures.register(eventBus);

        //Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new OriginofspiritsModFMLBusEvents(this));
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
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
