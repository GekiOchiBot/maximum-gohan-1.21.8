package net.ochibo.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.ochibo.block.ModBlocks;

public class CampfireSmokingRecipe extends AbstractCookingRecipe {

    public CampfireSmokingRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return ModRecipes.CAMPFIRE_SMOKING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends AbstractCookingRecipe> getType() {
        return ModRecipes.CAMPFIRE_SMOKING_TYPE;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }

    @Override
    protected Item getCookerItem() {
        return ModBlocks.MODIFIER_RACK.asItem();
    }
}
