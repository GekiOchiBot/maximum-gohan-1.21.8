package net.ochibo.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ochibo.MaximumGohan;
import net.ochibo.block.ModBlocks;
import net.ochibo.block.entity.custom.CampfireModifierRackEntity;
import net.ochibo.block.entity.custom.CampfireModifierSmokerRackEntity;

public class ModBlockEntities {
    public static final BlockEntityType<CampfireModifierRackEntity> RACK_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MaximumGohan.MOD_ID,"rack_be"),
                    FabricBlockEntityTypeBuilder.create(CampfireModifierRackEntity::new, ModBlocks.MODIFIER_RACK).build());

    public static final BlockEntityType<CampfireModifierSmokerRackEntity> SMOKER_RACK_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MaximumGohan.MOD_ID,"smoker_rack_be"),
                    FabricBlockEntityTypeBuilder.create(CampfireModifierSmokerRackEntity::new, ModBlocks.MODIFIER_RACK).build());

    public static void registerModBlockEntities(){
        MaximumGohan.LOGGER.info("Registering Mod Block Entities for " + MaximumGohan.MOD_ID);
    }
}
