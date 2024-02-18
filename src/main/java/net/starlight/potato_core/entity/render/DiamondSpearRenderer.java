package net.starlight.potato_core.entity.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.entity.DiamondSpearEntity;
import net.starlight.potato_core.entity.model.DiamondSpearEntityModel;

public class DiamondSpearRenderer extends EntityRenderer<DiamondSpearEntity> {
    // 钻石矛实体的材质
    public static final Identifier TEXTURE = new Identifier(FirstMod.MOD_ID, "textures/entity/diamond_spear.png");
    public DiamondSpearEntityModel model;

    public DiamondSpearRenderer(EntityRendererFactory.Context context) {
        super(context);
        // this.model = new DiamondSpearEntityModel(context.getPart(DiamondSpearEntityModel.DIAMOND_SPEAR));
    }

    public DiamondSpearRenderer(EntityRendererFactory.Context dispatcher, ItemRenderer itemRenderer) {
        this(dispatcher);
    }

    @Override
    public void render(DiamondSpearEntity diamondSpearEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {

        if (diamondSpearEntity.age >= 2 || this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(diamondSpearEntity) >= 12.25D) {
            matrixStack.push();
            matrixStack.scale(1.5F, 1.5F, 1.5F);

            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, diamondSpearEntity.prevYaw, diamondSpearEntity.getYaw()) - 90.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, diamondSpearEntity.prevPitch, diamondSpearEntity.getPitch()) + 90.0f));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));

            MinecraftClient.getInstance().getItemRenderer().renderItem(diamondSpearEntity.getStack(), ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, diamondSpearEntity.world, 0);

            matrixStack.pop();
            super.render(diamondSpearEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
        }
    }

    @Override
    public Identifier getTexture(DiamondSpearEntity entity) {
        return TEXTURE;
    }
}
