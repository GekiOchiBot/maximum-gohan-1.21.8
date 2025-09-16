package net.ochibo.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.ochibo.block.ModBlocks;

public class CampfireSmokingRecipe extends AbstractCookingRecipe {
    public CampfireSmokingRecipe(String string, CookingRecipeCategory cookingRecipeCategory, Ingredient ingredient, ItemStack itemStack, float f, int i) {
        super(string, cookingRecipeCategory, ingredient, itemStack, f, i);
    }

    protected Item getCookerItem() {
        return ModBlocks.MODIFIER_RACK.asItem();
    }

    public RecipeSerializer<CampfireSmokingRecipe> getSerializer() {
        return ModRecipes.CAMPFIRE_SMOKING_SERIALIZER;
    }

    public RecipeType<CampfireSmokingRecipe> getType() {
        return ModRecipes.CAMPFIRE_SMOKING_TYPE;
    }

    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CAMPFIRE;
    }
}
