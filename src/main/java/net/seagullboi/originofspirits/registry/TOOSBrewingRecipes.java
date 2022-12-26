package net.seagullboi.originofspirits.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.seagullboi.originofspirits.item.*;
import net.seagullboi.originofspirits.potion.ManaHealingPotionPotion;

public class TOOSBrewingRecipes {

    public static void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new AlcoholBottleRecipe());
        BrewingRecipeRegistry.addRecipe(new BeerBottleRecipe());
        BrewingRecipeRegistry.addRecipe(new EnlightenedVodkaRecipe());
        BrewingRecipeRegistry.addRecipe(new FairyBeerRecipe());
        BrewingRecipeRegistry.addRecipe(new ManaRegenerationPotionRecipe());
    }

    public static class AlcoholBottleRecipe implements IBrewingRecipe {
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

    public static class BeerBottleRecipe implements IBrewingRecipe {
        @Override
        public boolean isInput(ItemStack input) {
            return input.getItem() == AlcoholBottleItem.block;
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem() == TOOSItems.BARLEY_STACK.get();
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)) {
                return new ItemStack(BeerItem.block);
            }
            return ItemStack.EMPTY;
        }
    }

    public static class EnlightenedVodkaRecipe implements IBrewingRecipe {
        @Override
        public boolean isInput(ItemStack input) {
            return input.getItem() == AlcoholBottleItem.block;
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem() == TOOSItems.BUDDHAS_HAND.get();
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)) {
                return new ItemStack(EnlightenedVodkaItem.block);
            }
            return ItemStack.EMPTY;
        }
    }

    public static class FairyBeerRecipe implements IBrewingRecipe {
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

    public static class ManaRegenerationPotionRecipe implements IBrewingRecipe {
        @Override
        public boolean isInput(ItemStack input) {
            Item inputItem = input.getItem();
            return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils.getPotionFromItem(input) == Potions.AWKWARD;
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
