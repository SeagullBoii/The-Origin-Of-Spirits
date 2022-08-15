
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Item;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsMiscItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class ArchieTheAbyssItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:archie_the_abyss")
	public static final Item block = null;

	public ArchieTheAbyssItem(OriginofspiritsModElements instance) {
		super(instance, 587);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}

	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, OriginofspiritsModElements.sounds.get(new ResourceLocation("originofspirits:archie_the_abyss")),
					new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("archie_the_abyss");
		}
	}
}
