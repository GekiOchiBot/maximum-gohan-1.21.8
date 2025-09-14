package net.ochibo.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ochibo.MaximumGohan;
import net.ochibo.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MaximumGohan.MOD_ID,"mod_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.CHEESE))
                    .displayName(Text.translatable("itemgroup.maximum-gohan.mod_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CHEESE);
                        entries.add(ModItems.SMOKED_CHEESE);
                        entries.add(ModItems.PACIFIC_SAURY);
                        entries.add(ModItems.COOKED_SAURY);
                    })
                    .build());

    public static final ItemGroup BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MaximumGohan.MOD_ID,"mod_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.MOD_SMOKER))
                    .displayName(Text.translatable("itemgroup.maximum-gohan.mod_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.MOD_SMOKER);
                    })
                    .build());


    public static void registerItemGroups(){
        MaximumGohan.LOGGER.info("Registering Item Groups for " + MaximumGohan.MOD_ID);
    }
}
