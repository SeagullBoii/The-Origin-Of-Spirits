/*
 *    MCreator note:
 *
 *    This file is autogenerated to connect all MCreator mod elements together.
 *
 */
package net.seagullboi.originofspirits;

import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.block.Block;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

public class OriginofspiritsModElements {
	public final List<ModElement> elements = new ArrayList<>();
	public final List<Supplier<Block>> blocks = new ArrayList<>();
	public final List<Supplier<Item>> items = new ArrayList<>();
	public final List<Supplier<EntityType<?>>> entities = new ArrayList<>();
	public final List<Supplier<Enchantment>> enchantments = new ArrayList<>();
	public static Map<ResourceLocation, net.minecraft.util.SoundEvent> sounds = new HashMap<>();

	public OriginofspiritsModElements() {
		/*sounds.put(new ResourceLocation("originofspirits", "electrical_shock"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "electrical_shock")));
		sounds.put(new ResourceLocation("originofspirits", "electric_shock_explosion"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "electric_shock_explosion")));*/
		sounds.put(new ResourceLocation("originofspirits", "clam_snap"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "clam_snap")));
		sounds.put(new ResourceLocation("originofspirits", "clam_snap_middle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "clam_snap_middle")));
		sounds.put(new ResourceLocation("originofspirits", "metorite_break"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "metorite_break")));
		sounds.put(new ResourceLocation("originofspirits", "meteorite_hit"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "meteorite_hit")));
		sounds.put(new ResourceLocation("originofspirits", "meteorite_place"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "meteorite_place")));
		sounds.put(new ResourceLocation("originofspirits", "meteorite_walk"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "meteorite_walk")));
		sounds.put(new ResourceLocation("originofspirits", "skenelope_ambient"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "skenelope_ambient")));
		sounds.put(new ResourceLocation("originofspirits", "skenelope_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "skenelope_hurt")));
		sounds.put(new ResourceLocation("originofspirits", "skenelope_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "skenelope_death")));
		sounds.put(new ResourceLocation("originofspirits", "flamethrower"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "flamethrower")));
		sounds.put(new ResourceLocation("originofspirits", "wisp_explode"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "wisp_explode")));
		sounds.put(new ResourceLocation("originofspirits", "wisp_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "wisp_idle")));
		sounds.put(new ResourceLocation("originofspirits", "wisp_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "wisp_hurt")));
		sounds.put(new ResourceLocation("originofspirits", "wisp_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "wisp_death")));
		/*sounds.put(new ResourceLocation("originofspirits", "hexayosh_charge"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "hexayosh_charge")));
		sounds.put(new ResourceLocation("originofspirits", "hexayosh_ambient"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "hexayosh_ambient")));
		sounds.put(new ResourceLocation("originofspirits", "hexayosh_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "hexayosh_death")));
		sounds.put(new ResourceLocation("originofspirits", "hexayosh_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "hexayosh_hurt")));*/
		sounds.put(new ResourceLocation("originofspirits", "blessed_symphony"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "blessed_symphony")));
		sounds.put(new ResourceLocation("originofspirits", "gauntlet_shoot"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "gauntlet_shoot")));
		sounds.put(new ResourceLocation("originofspirits", "yoyo_swing"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "yoyo_swing")));
		sounds.put(new ResourceLocation("originofspirits", "frog_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "frog_death")));
		sounds.put(new ResourceLocation("originofspirits", "frog_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "frog_hurt")));
		sounds.put(new ResourceLocation("originofspirits", "frog_ambient"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("originofspirits", "frog_ambient")));
		try {
			ModFileScanData modFileInfo = ModList.get().getModFileById("originofspirits").getFile().getScanResult();
			Set<ModFileScanData.AnnotationData> annotations = modFileInfo.getAnnotations();
			for (ModFileScanData.AnnotationData annotationData : annotations) {
				if (annotationData.getAnnotationType().getClassName().equals(ModElement.Tag.class.getName())) {
					Class<?> clazz = Class.forName(annotationData.getClassType().getClassName());
					if (clazz.getSuperclass() == OriginofspiritsModElements.ModElement.class)
						elements.add((OriginofspiritsModElements.ModElement) clazz.getConstructor(this.getClass()).newInstance(this));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(elements);
		elements.forEach(OriginofspiritsModElements.ModElement::initElements);
		MinecraftForge.EVENT_BUS.register(new OriginofspiritsModVariables(this));
	}

	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		for (Map.Entry<ResourceLocation, net.minecraft.util.SoundEvent> sound : sounds.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}

	private int messageID = 0;

	public <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketBuffer> encoder, Function<PacketBuffer, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		OriginOfSpirits.PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	public List<ModElement> getElements() {
		return elements;
	}

	public List<Supplier<Block>> getBlocks() {
		return blocks;
	}

	public List<Supplier<Item>> getItems() {
		return items;
	}

	public List<Supplier<EntityType<?>>> getEntities() {
		return entities;
	}

	public List<Supplier<Enchantment>> getEnchantments() {
		return enchantments;
	}

	public static class ModElement implements Comparable<ModElement> {
		@Retention(RetentionPolicy.RUNTIME)
		public @interface Tag {
		}

		protected final OriginofspiritsModElements elements;
		protected final int sortid;

		public ModElement(OriginofspiritsModElements elements, int sortid) {
			this.elements = elements;
			this.sortid = sortid;
		}

		public void initElements() {
		}

		public void init(FMLCommonSetupEvent event) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		@OnlyIn(Dist.CLIENT)
		public void clientLoad(FMLClientSetupEvent event) {
		}

		@Override
		public int compareTo(ModElement other) {
			return this.sortid - other.sortid;
		}
	}
}
