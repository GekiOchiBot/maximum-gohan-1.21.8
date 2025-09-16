package net.ochibo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.pool.StructurePool;
import net.ochibo.item.ModItems;
import net.ochibo.recipe.CampfireSmokingRecipe;
import net.ochibo.recipe.ModRecipes;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup,recipeExporter) {
            @Override
            public void generate() {

                offerSmelting(List.of(ModItems.PACIFIC_SAURY), RecipeCategory.FOOD,ModItems.COOKED_SAURY,0.5f,200,"saury");
                offerFoodCookingRecipe("smoker",RecipeSerializer.SMOKING,SmokingRecipe::new,100,ModItems.PACIFIC_SAURY,ModItems.COOKED_SAURY,0.5f);
                offerFoodCookingRecipe("campfire",RecipeSerializer.CAMPFIRE_COOKING,CampfireCookingRecipe::new,600,ModItems.PACIFIC_SAURY,ModItems.COOKED_SAURY,0.5f);
                offerFoodCookingRecipe("campfire_smoking", ModRecipes.CAMPFIRE_SMOKING_SERIALIZER, CampfireSmokingRecipe::new,600,ModItems.CHEESE,ModItems.SMOKED_CHEESE,0.5f);
                offerFoodCookingRecipe("smoker",RecipeSerializer.SMOKING,SmokingRecipe::new,200,ModItems.CHEESE,ModItems.SMOKED_CHEESE,1f);
//                CookingRecipeJsonBuilder.create(Ingredient.ofItem(ModItems.PACIFIC_SAURY), RecipeCategory.FOOD, ModItems.COOKED_SAURY, 0.5f, 100, RecipeSerializer.SMOKING, SmokingRecipe::new);
//                offerBlasting(List.of(ModItems.PYRITE), RecipeCategory.MISC,ModItems.CHEESE,1,100,"test");
//                createShaped(RecipeCategory.MISC, ModItems.PYRITE, 2)
//                        .input('C', ModItems.PYRITE)
//                        .input('S', Items.ECHO_SHARD)
//                        .pattern("SSS")
//                        .pattern("SCS")
//                        .pattern("SSS")
//                        .criterion("has_echo_shard", this.conditionsFromItem(Items.ECHO_SHARD))
//                        .offerTo(exporter);
            }
        } ;
    }

    @Override
    public String getName() {
        return "";
    }


}
