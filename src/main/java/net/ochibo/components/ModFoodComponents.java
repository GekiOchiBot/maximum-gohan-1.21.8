package net.ochibo.components;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CHEESE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f).build();
    public static final FoodComponent SMOKED_CHEESE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.75f).build();
}
