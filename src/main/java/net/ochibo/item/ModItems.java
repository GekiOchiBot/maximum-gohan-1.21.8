package net.ochibo.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.ochibo.MaximumGohan;
import net.ochibo.components.ModFoodComponents;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(GetDefaultSettings("ruby")));
    public static final Item CHEESE = registerItem("cheese", new Item(GetDefaultSettings("cheese").food(ModFoodComponents.CHEESE)));
    public static final Item SMOKED_CHEESE = registerItem("smoked_cheese", new Item(GetDefaultSettings("smoked_cheese").food(ModFoodComponents.SMOKED_CHEESE)));
    public static final Item PACIFIC_SAURY = registerItem("pacific_saury", new Item(GetDefaultSettings("pacific_saury").food(FoodComponents.TROPICAL_FISH)));
    public static final Item COOKED_SAURY = registerItem("cooked_saury", new Item(GetDefaultSettings("cooked_saury").food(FoodComponents.COOKED_SALMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MaximumGohan.MOD_ID,name), item);
    }

    private static Item.Settings GetDefaultSettings(String name){
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MaximumGohan.MOD_ID, name));
        return new Item.Settings().registryKey(key);
    }

    public static void registerModItems(){
        MaximumGohan.LOGGER.info("Registering Mod Items for " + MaximumGohan.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(RUBY.asItem());
        });
    }
}
