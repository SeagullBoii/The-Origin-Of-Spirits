
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsFoodItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class CookedSkenisonItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:cooked_skenison")
	public static final Item block = null;

	public CookedSkenisonItem(OriginofspiritsModElements instance) {
		super(instance, 417);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(OriginOfSpiritsFoodItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(10).saturation(0.3f)

							.meat().build()));
			setRegistryName("cooked_skenison");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
