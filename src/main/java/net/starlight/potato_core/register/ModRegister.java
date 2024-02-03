package net.starlight.potato_core.register;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.ItemConvertible;
import net.starlight.potato_core.item.PaintBallItem;
import net.starlight.potato_core.util.AEColor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>其他注册方式</p>
 */
public class ModRegister {
    /**
     * <p>{@link Environment 相当于Forge中的SideOnly注解}</p>
     * <p>方块的染色，基于生态群系的染色</p>
     * <p>例如：同样的方块在Plains生态群系和Taiga生态群系，它们颜色各不相同</p>
     * <p>在过渡地带，颜色有些梯度的变化，而不是泾渭分明</p>
     * <p>原因是，每个方块在选择颜色是，会考虑以它为中心3*3的范围取一个平均数</p>
     * <hr>
     * <p>注册颜色处理器，然后实现颜色处理器的类来判断颜色的方法，最后应用具体的方块上即可</p>
     * <p>在这个方块的Json文件上必须接受染色的，必须有tintindex的编号</p>
     * <p>作用，例如草方块，草方块不是完全染色的，主要以Top为主，不需要泥土部分发生改变，
     * 所以根据不同的染色编号tintIndex分别处理不同面的颜色样式
     * </p>
     */
    @Environment(EnvType.CLIENT)
    public static void registerBlockColor() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
                ModBlocks.STONE);
    }
}
