
package net.seagullboi.originofspirits;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.ItemStack;

import net.seagullboi.originofspirits.item.EnlightenedVodkaItem;
import net.seagullboi.originofspirits.item.BuddhasHandItem;
import net.seagullboi.originofspirits.item.AlcoholBottleItem;

@OriginofspiritsModElements.ModElement.Tag
public class EnlightenedVodkaRecipeBrewingRecipe extends OriginofspiritsModElements.ModElement {
	public EnlightenedVodkaRecipeBrewingRecipe(OriginofspiritsModElements instance) {
		super(instance, 631);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}

	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == AlcoholBottleItem.block;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == BuddhasHandItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(EnlightenedVodkaItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
