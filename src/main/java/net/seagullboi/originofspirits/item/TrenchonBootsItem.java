
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsAccessoriesItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

import java.util.List;

@OriginofspiritsModElements.ModElement.Tag
public class TrenchonBootsItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:trenchon_boots")
	public static final Item block = null;

	public TrenchonBootsItem(OriginofspiritsModElements instance) {
		super(instance, 370);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(OriginOfSpiritsAccessoriesItemGroup.tab).maxStackSize(1).rarity(Rarity.COMMON));
			setRegistryName("trenchon_boots");
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

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u00A72[Allows You To Walk On Water]"));
		}
	}
}
