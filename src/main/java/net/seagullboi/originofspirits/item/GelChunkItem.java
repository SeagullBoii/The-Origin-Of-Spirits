
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsMiscItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class GelChunkItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:gel_chunk")
	public static final Item block = null;

	public GelChunkItem(OriginofspiritsModElements instance) {
		super(instance, 340);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("gel_chunk");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
