
package net.seagullboi.originofspirits;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.ItemStack;

import net.seagullboi.originofspirits.item.YeastItem;
import net.seagullboi.originofspirits.item.WaterAlcoholBottleItem;
import net.seagullboi.originofspirits.item.AlcoholBottleItem;

@OriginofspiritsModElements.ModElement.Tag
public class AlcoholRecipeBrewingRecipe extends OriginofspiritsModElements.ModElement {
	public AlcoholRecipeBrewingRecipe(OriginofspiritsModElements instance) {
		super(instance, 628);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}

	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			return input.getItem() == WaterAlcoholBottleItem.block;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == YeastItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return new ItemStack(AlcoholBottleItem.block);
			}
			return ItemStack.EMPTY;
		}
	}
}
