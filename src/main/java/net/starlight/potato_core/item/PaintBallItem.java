package net.starlight.potato_core.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;
import net.starlight.potato_core.util.AEColor;

import java.util.EnumMap;
import java.util.Map;

/**
 * <p>染色球</p>
 * <p>每个染色物品都拥有不同的id, 所以需要根据颜色和是否发光，来获取每个染色球的资源路径。</p>
 */
@Test(Result.FAIL)
public class PaintBallItem implements ItemConvertible {
    public final boolean isLight;
    public final AEColor color;
    public final String defaultName;

    public Item item;
    public Identifier loc;

    /**
     * @param name    染色球的名称
     * @param isLight 是否发光
     * @param color   颜色
     */
    private PaintBallItem(String name, boolean isLight, AEColor color) {
        this(getId(isLight, color), name, new Item(new Item.Settings()), isLight, color);
    }

    private PaintBallItem(Identifier loc, String name, Item item, boolean isLight, AEColor color) {
        this.isLight = isLight;
        this.color = color;
        this.defaultName = name;
        this.item = item;
        this.loc = loc;
        /* 注册入口 */
        // registerItem(loc, item);
    }

    private static Identifier getId(boolean isLight, AEColor color){
        // 获取染色球的名称
        String name = color.id;
        // 如果染色球带闪光，加上_lumen后缀
        if (isLight) name += "_lumen";
        // 否则加上_paint_ball后缀
        name += "_paint_ball";
        return new Identifier(FirstMod.MOD_ID, name);
    }

    public static Map<AEColor, PaintBallItem> create(boolean isLight, String name) {
        Map<AEColor, PaintBallItem> balls = new EnumMap<>(AEColor.class);
        for (AEColor color : AEColor.values()) {
            balls.put(color, new PaintBallItem(name, isLight, color));
        }
        return balls;
    }

    /**
     * <p>注册滤镜</p>
     */
    @Environment(EnvType.CLIENT)
    public static void clientRegister() {
        // ColorProviderRegistry.ITEM.register((stack, tintIndex) -> calculateColor(), this);
    }

    /**
     * <p>计算滤镜的颜色</p>
     */
    public int calculateColor() {
        int rgb = color.rgb;
        // 获取染色球的颜色
        if (isLight) {
            int r = rgb >> 16 & 0xff;
            int g = rgb >> 8 & 0xff;
            int b = rgb & 0xff;
            // 略微提升亮度
            float fail = 0.7f;
            float full = 0xff * 0.3f;
            rgb = (int) (full + r + fail) << 16
                    | (int) (full + g + fail) << 8
                    | (int) (full + b + fail);
        }
        return rgb;
    }

    /**
     * <p>获取染色球的默认名称</p>
     */
    public String getDefaultName() {
        return color.name + defaultName;
    }

    @Override
    public Item asItem() {
        return new Item(new Item.Settings());
    }

    public void registerItem(Identifier loc, Item item) {
        Registry.register(Registries.ITEM, loc, item);
    }
}
