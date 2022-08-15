
package net.seagullboi.originofspirits;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.potion.Potions;
import net.minecraft.potion.PotionUtils;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

import net.seagullboi.originofspirits.potion.ManaHealingPotionPotion;
import net.seagullboi.originofspirits.item.CrystalOfMagicItem;

@OriginofspiritsModElements.ModElement.Tag
public class ManaRegenerationPotionRecipeBrewingRecipe extends OriginofspiritsModElements.ModElement {
	public ManaRegenerationPotionRecipeBrewingRecipe(OriginofspiritsModElements instance) {
		super(instance, 284);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
	}

	public static class CustomBrewingRecipe implements IBrewingRecipe {
		@Override
		public boolean isInput(ItemStack input) {
			Item inputItem = input.getItem();
			return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION)
					&& PotionUtils.getPotionFromItem(input) == Potions.AWKWARD;
		}

		@Override
		public boolean isIngredient(ItemStack ingredient) {
			return ingredient.getItem() == CrystalOfMagicItem.block;
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (isInput(input) && isIngredient(ingredient)) {
				return PotionUtils.addPotionToItemStack(new ItemStack(input.getItem()), ManaHealingPotionPotion.potionType);
			}
			return ItemStack.EMPTY;
		}
	}
}
