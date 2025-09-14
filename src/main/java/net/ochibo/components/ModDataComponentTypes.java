package net.ochibo.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ochibo.MaximumGohan;

import java.awt.event.ComponentEvent;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<String> FOOD_TEXT = register("food_text",stringBuilder -> stringBuilder.codec(Codec.STRING));


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MaximumGohan.MOD_ID,name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes(){
        MaximumGohan.LOGGER.info("Registering Data Component Types for " + MaximumGohan.MOD_ID);
    }
}
