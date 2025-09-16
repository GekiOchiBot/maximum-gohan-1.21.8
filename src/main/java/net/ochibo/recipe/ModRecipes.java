package net.ochibo.recipe;

import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.ochibo.MaximumGohan;
import net.ochibo.MaximumGohanClient;

public class ModRecipes {
    public static final RecipeSerializer<CampfireSmokingRecipe> CAMPFIRE_SMOKING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MaximumGohan.MOD_ID, "campfire_smoking"), new AbstractCookingRecipe.Serializer<>(CampfireSmokingRecipe::new, 200));

    public static final RegistryKey<RecipePropertySet> CAMPFIRE_SMOKING_INPUT = RegistryKey.of(RecipePropertySet.REGISTRY, Identifier.of(MaximumGohan.MOD_ID,"campfire_smoke_input"));

    public static final RecipeType<CampfireSmokingRecipe> CAMPFIRE_SMOKING_TYPE = Registry.register(Registries.RECIPE_TYPE, Identifier.of(MaximumGohan.MOD_ID, "campfire_smoking"), new RecipeType<CampfireSmokingRecipe>() {
        @Override
        public String toString() {return "campfire_smoking";}
    });
    public static void registerRecipes(){
        MaximumGohan.LOGGER.info("Registering Recipes for" + MaximumGohan.MOD_ID);
    }
}
