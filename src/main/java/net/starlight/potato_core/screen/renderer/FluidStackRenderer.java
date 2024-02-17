package net.starlight.potato_core.screen.renderer;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;
import net.starlight.potato_core.util.FluidStack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Test(Result.UNKNOWN)
public class FluidStackRenderer implements IIngredientRenderer<FluidStack> {
    private static final NumberFormat nf = NumberFormat.getIntegerInstance();
    // 容积
    public final long capacityMb;
    private final TooltipMode tooltipMode;
    private final int width;
    private final int height;

    enum TooltipMode {
        SHOW_AMOUNT,
        SHOW_AMOUNT_AND_CAPACITY,
        ITEM_LIST
    }

    public FluidStackRenderer() {
        this(FluidStack.convertDropletsToMb(FluidConstants.BUCKET), TooltipMode.SHOW_AMOUNT_AND_CAPACITY, 16, 16);
    }

    public FluidStackRenderer(long capacityMb, boolean showCapacity, int width, int height) {
        this(capacityMb, showCapacity ? TooltipMode.SHOW_AMOUNT_AND_CAPACITY : TooltipMode.SHOW_AMOUNT, width, height);
    }

    public FluidStackRenderer(long capacityMb, TooltipMode tooltipMode, int width, int height) {
        Preconditions.checkArgument(capacityMb > 0, "capacity must be > 0");
        Preconditions.checkArgument(width > 0, "width must be > 0");
        Preconditions.checkArgument(height > 0, "height must be > 0");
        this.capacityMb = capacityMb;
        this.tooltipMode = tooltipMode;
        this.width = width;
        this.height = height;
    }

    // 绘制流体
    public void drawFluid(MatrixStack context, FluidStack fluidStack, int x, int y, int width, int height, long maxCapacity) {
        // 判断液体是否为空
        if (fluidStack.getFluidVariant().getFluid() == Fluids.EMPTY) {
            return;
        }
        // 累加
        y += height;
        final Sprite sprite = FluidVariantRendering.getSprite(fluidStack.getFluidVariant());
        int color = FluidVariantRendering.getColor(fluidStack.getFluidVariant());

        final int drawHeight = (int) (fluidStack.getAmount() / (maxCapacity * 1f) * height);
        final int iconHeight = sprite.getContents().getHeight();
        int offsetHeight = drawHeight;

        RenderSystem.setShaderColor((color >> 16 & 255) / 255.0F, (float) (color >> 8 & 255) / 255.0F, (float) (color & 255) / 255.0F, 1F);

        int iteration = 0;
        while (offsetHeight != 0) {
            final int curHeight = offsetHeight < iconHeight ? offsetHeight : iconHeight;

            DrawableHelper.drawSprite(context, x, y - offsetHeight, 0, width, curHeight, sprite);
            offsetHeight -= curHeight;
            iteration++;
            if (iteration > 50) {
                break;
            }
        }

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);

        RenderSystem.setShaderTexture(0, FluidRenderHandlerRegistry.INSTANCE.get(fluidStack.getFluidVariant().getFluid())
                .getFluidSprites(MinecraftClient.getInstance().world, null, fluidStack.getFluidVariant().getFluid().getDefaultState())
                [0].getAtlasId());
    }

    @Override
    public List<Text> getTooltip(FluidStack fluidStack, TooltipContext tooltipContext) {
        // 返回的提示信息
        List<Text> tooltip = new ArrayList<>();
        FluidVariant fluidVariant = fluidStack.getFluidVariant();
        if (fluidVariant == null) {
            return tooltip;
        }
        // 构建第一个信息是显示的流体的类型是什么
        // MutableText displayName = Text.translatable("block." + Registries.FLUID.getId(fluidStack.fluidVariant.getFluid()));
        MutableText displayName = Text.translatable("水");
        tooltip.add(displayName);

        long amount = fluidStack.getAmount();
        // 显示流体的数据
        if (tooltipMode == TooltipMode.SHOW_AMOUNT_AND_CAPACITY) {
            MutableText amountString = Text.translatable("potato_core.tooltip.liquid.amount.with.capacity", nf.format(amount),
                    nf.format(capacityMb));
            tooltip.add(amountString.fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        } else {
            MutableText amountString = Text.translatable("potato_core.tootip.liquid.amount", nf.format(amount));
            tooltip.add(amountString.fillStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
        }

        return tooltip;
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}