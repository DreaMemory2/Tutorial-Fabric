package net.starlight.potato_core.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.api.Result;
import net.starlight.potato_core.api.Test;
import net.starlight.potato_core.mod.Mod;
import net.starlight.potato_core.util.MyColor;

/**
 * <p>每个染色物品都拥有不同的id, 所以需要根据颜色和是否发光</p>
 * <p>来获取每个染色球的资源路径，</p>
 */
@Test(Result.FAIL)
public class PaintBallItemDefinition extends ItemDefinition<Item> {
    public final boolean isLight;
    public final MyColor color;


    /**
     * @param name 染色球的名称
     * @param isLight 是否发光
     * @param color 颜色
     */
    private PaintBallItemDefinition(String name, boolean isLight, MyColor color) {
        super(getLoc(isLight, color), name, new Item(new Item.Settings()));
        this.isLight = isLight;
        this.color = color;
    }

    private static Identifier getLoc(boolean isLight, MyColor color) {
        String name = color.name();
        if (isLight) name += "_lumen";
        name += "_paint_ball";
        return Mod.modLoc(name);
    }

    /**
     * <p>注册滤镜</p>
     */
    @Override
    @Environment(EnvType.CLIENT)
    public void ClientRegister() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> calculateColor(), this);
    }

    /**
     * <p>计算滤镜的颜色</p>
     */
    private int calculateColor() {
        int rgb = color.rgbNormal;
        if(this.isLight) {
            int r = rgb >> 16 & 0xff;
            int g = rgb >> 8 & 0xff;
            int b = rgb & 0xff;
            // 略微提升亮度
            float fail = 0.7f;
            float full = 0xff * 0.3f;
            rgb = (int)(full + r + fail) << 16
                    | (int)(full + g + fail) << 8
                    | (int)(full + b + fail);
        }
        return rgb;
    }

    @Override
    protected void provideModel() {

    }

    @Override
    protected String getDefaultName() {
        return color.defaultName + this.defaultName;
    }
}
