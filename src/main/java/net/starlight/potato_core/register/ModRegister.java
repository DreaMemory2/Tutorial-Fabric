package net.starlight.potato_core.register;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;

/**
 * <p>其他注册方式</p>
 */
public class ModRegister {
    /**
     * <p>{@link Environment 相当于Forge中的SideOnly注解}</p>
     */
    @Environment(EnvType.CLIENT)
    public static void registerBlockColor() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
                ModBlocks.STONE);
    }
}
