
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Item;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsMiscItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class BlessedSymphonyItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:blessed_symphony")
	public static final Item block = null;

	public BlessedSymphonyItem(OriginofspiritsModElements instance) {
		super(instance, 758);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}

	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, OriginofspiritsModElements.sounds.get(new ResourceLocation("originofspirits:blessed_symphony")),
					new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("blessed_symphony");
		}
	}
}
