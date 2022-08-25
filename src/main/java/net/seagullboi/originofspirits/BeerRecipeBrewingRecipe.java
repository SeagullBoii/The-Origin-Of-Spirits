
package net.seagullboi.originofspirits;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.ItemStack;

import net.seagullboi.originofspirits.item.BeerItem;
import net.seagullboi.originofspirits.item.BarleyStackItem;
import net.seagullboi.originofspirits.item.AlcoholBottleItem;

@OriginofspiritsModElements.ModElement.Tag
public class BeerRecipeBrewingRecipe extends OriginofspiritsModElements.ModElement {
	public BeerRecipeBrewingRecipe(OriginofspiritsModElements instance) {
		super(instance, 729);
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
			return ingredient.getItem() == BarleyStackItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(BeerItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
