package net.seagullboi.originofspirits.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
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

    @Override
    public String getName() {
        return "TOOS Recipes";
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(OriginOfSpirits.MOD_ID, path);
    }
}