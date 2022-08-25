package net.seagullboi.originofspirits.datagen;

import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.function.Consumer;

public class TOOSRecipeProvider extends RecipeProvider implements IConditionBuilder {
        public TOOSRecipeProvider(DataGenerator pGenerator) {
            super(pGenerator);
        }

        @Override
        protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        }

    @Override
    public String getName() {
        return "TOOS Recipes";
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(OriginOfSpirits.MOD_ID, path);
    }
}