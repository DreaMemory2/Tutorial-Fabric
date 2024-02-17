package net.starlight.potato_core.screen.renderer;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.starlight.potato_core.item.entity.DiamondSpearEntity;
import net.starlight.potato_core.item.entity.model.DiamondSpearEntityModel;
import org.lwjgl.opengl.GL11;

public class DiamondSpearRenderer extends EntityRenderer<DiamondSpearEntity> {
    public static final Identifier TEXTURE = new Identifier("textures/entity/trident.png");
    public DiamondSpearEntityModel model;

    protected DiamondSpearRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new DiamondSpearEntityModel(context.getPart(EntityModelLayers.TRIDENT));
    }

    public void renderSpear(DiamondSpearEntity entityarrow, double d, double d1, double d2, float f, float f1) {
        // bindEntityTexture(entityarrow);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef((entityarrow.prevYaw + (entityarrow.getYaw() - entityarrow.prevYaw) * f1) - 90F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entityarrow.prevPitch + (entityarrow.getPitch() - entityarrow.prevPitch) * f1, 0.0F, 0.0F, 1.0F);
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();

        float[] color = entityarrow.getMaterialColor();
        int i = 0;
        float f2 = 0.0F;
        float f3 = 1.0F;
        float f4 = (0 + i * 10) / 32F;
        float f5 = (5 + i * 10) / 32F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (5 + i * 10) / 32F;
        float f9 = (10 + i * 10) / 32F;
        float f13 = 0.3125F;
        float f14 = 0.46875F;
        float f10 = 0.05625F;

        double length = 20D;

        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/ );
        float f11 = entityarrow.shake - f1;
        if (f11 > 0.0F) {
            float f12 = -MathHelper.sin(f11 * 3F) * f11;
            GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
        }
        GL11.glRotatef(45F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION);
        // tess.startDrawingQuads();
        {
            /*tess.addVertexWithUV(-length, -2D, -2D, f6, f8);
            tess.addVertexWithUV(-length, -2D, 2D, f7, f8);
            tess.addVertexWithUV(-length, 2D, 2D, f7, f9);
            tess.addVertexWithUV(-length, 2D, -2D, f6, f9);*/
        }
        tess.draw();

        GL11.glNormal3f(-f10, 0F, 0F);
        buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION);
        // tess.startDrawingQuads();
        {
            /* tess.addVertexWithUV(-length, 2D, -2D, f6, f8);
            tess.addVertexWithUV(-length, 2D, 2D, f7, f8);
            tess.addVertexWithUV(-length, -2D, 2D, f7, f9);
            tess.addVertexWithUV(-length, -2D, -2D, f6, f9);*/
        }
        tess.draw();
        for (int j = 0; j < 4; j++) {
            GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
            buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION);
            // tess.startDrawingQuads();
            {
                /*tess.setColorOpaque_F(1F, 1F, 1F);
                tess.addVertexWithUV(-length, -2D, 0.0D, f2, f4);
                tess.addVertexWithUV(length, -2D, 0.0D, f3, f4);
                tess.addVertexWithUV(length, 2D, 0.0D, f3, f5);
                tess.addVertexWithUV(-length, 2D, 0.0D, f2, f5);

                tess.setColorOpaque_F(color[0], color[1], color[2]);
                tess.addVertexWithUV(-length, -2D, 0.0D, f2, f13);
                tess.addVertexWithUV(length, -2D, 0.0D, f3, f13);
                tess.addVertexWithUV(length, 2D, 0.0D, f3, f14);
                tess.addVertexWithUV(-length, 2D, 0.0D, f2, f14);*/
            }
            tess.draw();
        }

        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    @Override
    public void render(DiamondSpearEntity tridentEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        // renderSpear((DiamondSpearEntity) entity, d, d1, d2, f, f1);
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, tridentEntity.prevYaw, tridentEntity.getYaw()) - 90.0f));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, tridentEntity.prevPitch, tridentEntity.getPitch()) + 90.0f));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(tridentEntity)), false, false);
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(tridentEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(DiamondSpearEntity entity) {
        return TEXTURE;
    }
}
