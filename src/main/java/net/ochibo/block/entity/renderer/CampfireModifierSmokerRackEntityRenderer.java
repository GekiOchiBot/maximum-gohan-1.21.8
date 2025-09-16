package net.ochibo.block.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.ochibo.block.entity.custom.CampfireModifierSmokerRackEntity;

@Environment(EnvType.CLIENT)
public class CampfireModifierSmokerRackEntityRenderer implements BlockEntityRenderer<CampfireModifierSmokerRackEntity> {
    private static final float itemSize = 0.335f;

    public CampfireModifierSmokerRackEntityRenderer(BlockEntityRendererFactory.Context context){
    }

    @Override
    public void render(CampfireModifierSmokerRackEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        DefaultedList<ItemStack> defaultedList = entity.getItems();
        int k = (int)entity.getPos().asLong();

        for(int l = 0; l < defaultedList.size(); ++l) {
            ItemStack itemStack = defaultedList.get(l);
            if (itemStack != ItemStack.EMPTY) {
                matrices.push();
                matrices.translate(0.5F, 1f/16f*3.5F + 1f/16f*itemSize/2, 0.5F);
                matrices.translate(0.225F, 0.225F, 0.0F);
                matrices.translate(-0.15F*l, -0.15F*l, 0.0F);
                matrices.scale(itemSize, itemSize, itemSize);
                itemRenderer.renderItem(itemStack, ItemDisplayContext.FIXED, light, overlay, matrices, vertexConsumers, entity.getWorld(), k + l);
                matrices.pop();
            }
        }
    }
}
