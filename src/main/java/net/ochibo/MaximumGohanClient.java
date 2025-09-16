package net.ochibo;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.ochibo.block.ModBlocks;
import net.ochibo.block.entity.ModBlockEntities;
import net.ochibo.block.entity.renderer.CampfireModifierRackEntityRenderer;
import net.ochibo.block.entity.renderer.CampfireModifierSmokerRackEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumGohanClient implements ClientModInitializer {
    private static final Logger log = LoggerFactory.getLogger(MaximumGohanClient.class);

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.RACK_BE, CampfireModifierRackEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SMOKER_RACK_BE, CampfireModifierSmokerRackEntityRenderer::new);
        BlockRenderLayerMap. putBlock ( ModBlocks.MODIFIER_RACK, BlockRenderLayer.CUTOUT ) ;
    }
}
