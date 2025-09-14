package net.ochibo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
//        addDrop(ModBlocks.RUSTED_IRON_BLOCK);
//        addDrop(ModBlocks.IRON_RUSTER_BLOCK);
//        addDrop(ModBlocks.RUSTY_COBBLESTONE);
//        addDrop(ModBlocks.PYRITE_ORE ,oreDrops(ModBlocks.PYRITE_ORE, ModItems.PYRITE));
    }
}
