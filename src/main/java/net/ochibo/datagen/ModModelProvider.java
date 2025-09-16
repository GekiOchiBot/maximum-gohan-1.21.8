package net.ochibo.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.ochibo.block.ModBlocks;
import net.ochibo.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MODIFIER_RACK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUST_PORTAL_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUSTED_IRON_BLOCK);
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUSTY_COBBLESTONE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PACIFIC_SAURY, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SAURY, Models.GENERATED);
    }
}
