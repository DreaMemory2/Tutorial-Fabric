package net.starlight.potato_core.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;

@Environment(EnvType.CLIENT)
public class DiamondSpearEntityModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final EntityModelLayer DIAMOND_SPEAR = new EntityModelLayer(new Identifier(FirstMod.MOD_ID, "diamond_spear"), "main");
    public static final Identifier TEXTURE = new Identifier(FirstMod.MOD_ID, "textures/entity/diamond_spear.png");
    private final ModelPart root;

    public DiamondSpearEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.root = root.getChild("main");
    }

    // 你可以使用 BlockBench，制作你的模型并为你的实体模型导出以得到这个方法
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("main", ModelPartBuilder.create()
                        .uv(4, 0)
                        .cuboid(0.0F, -4.0F, -1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                        .uv(8, 0)
                        .cuboid(0.0F, -5.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                        .uv(4, 7)
                        .cuboid(0.0F, -5.0F, -2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                        .uv(0, 0)
                        .cuboid(0.0F, -20.0F, -1.0F, 1.0F, 16.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.root).forEach((modelRenderer) -> modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha));
    }
}
