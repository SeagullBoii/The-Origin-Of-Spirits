
package net.seagullboi.originofspirits;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.ItemStack;

import net.seagullboi.originofspirits.item.FairyDustItem;
import net.seagullboi.originofspirits.item.FairyBeerItem;
import net.seagullboi.originofspirits.item.BeerItem;

@OriginofspiritsModElements.ModElement.Tag
public class FairyBeerRecipeBrewingRecipe extends OriginofspiritsModElements.ModElement {
	public FairyBeerRecipeBrewingRecipe(OriginofspiritsModElements instance) {
		super(instance, 730);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}

	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == BeerItem.block;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == FairyDustItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(FairyBeerItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
