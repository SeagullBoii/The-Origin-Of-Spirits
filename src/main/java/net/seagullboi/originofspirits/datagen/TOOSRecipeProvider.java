package net.seagullboi.originofspirits.datagen;

import net.minecraft.data.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.function.Consumer;

public class TOOSRecipeProvider extends RecipeProvider implements IConditionBuilder {
        public TOOSRecipeProvider(DataGenerator pGenerator) {
            super(pGenerator);
        }

        @Override
        protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        }

    public static void ingotToBlock(Consumer<IFinishedRecipe> consumer, IItemProvider providedItem, IItemProvider requiredItem) {
        ShapedRecipeBuilder.shapedRecipe(providedItem, 1).key('#', requiredItem).patternLine("###").patternLine("###").patternLine("###")
                .addCriterion("has_item", hasItem(requiredItem)).build(consumer);
    }

    public static void blockToIngot(Consumer<IFinishedRecipe> consumer, IItemProvider providedItem, IItemProvider requiredItem) {
        ShapelessRecipeBuilder.shapelessRecipe(providedItem, 9).addIngredient(requiredItem)
                .addCriterion("has_item", hasItem(requiredItem)).build(consumer);
    }


    @Override
    public String getName() {
        return "TOOS Recipes";
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(OriginOfSpirits.MOD_ID, path);
    }
}