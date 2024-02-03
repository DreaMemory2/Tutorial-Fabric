package net.starlight.potato_core.util;

import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

@Test(Result.USELESS)
public enum AEColor {
    WHITE("白色", "white", 0xDBDBDB),
    ORANGE("橙色","orange",0xFAAE44),
    MAGENTA("品红色", "magenta",0xB82AB8),
    LIGHT_BLUE("淡蓝色","light_blue",0x82ACE7),
    YELLOW("黄色", "yellow", 0xF8FF4A),
    LIME("黄绿色", "lime", 0xBBFF51),
    PINK("粉色", "pink",0xF8B5D7),
    GRAY("灰色", "gray", 0xA0A0A0),
    LIGHT_GRAY("淡灰色", "light_gray", 0xCDCDCD),
    CYAN("青色", "cyan", 0x51AAC6),
    PURPLE("紫色", "purple", 0xA453CE),
    BLUE("蓝色","blue", 0x514AFF),
    BROWN("褐色", "brown", 0xB7967F),
    GREEN("绿色", "green", 0x60E32E),
    RED("红色", "red", 0xFF003C),
    BLACK("黑色", "black", 0x565656);
    public final String id;
    public final int rgb;
    public final String name;
    /**
     * @param name 颜色名称
     * @param id 颜色英文id
     * @param rgb RGB颜色
     */
    AEColor(String name, String id, int rgb) {
        this.id = id;
        this.rgb = rgb;
        this.name = name;
    }
}
