package net.starlight.potato_core.util;

import net.minecraft.util.Formatting;

public class ColorUtil {
    public static final Formatting[] colour = new Formatting[]{
            Formatting.RED, Formatting.YELLOW, Formatting.GREEN, Formatting.AQUA,
            Formatting.BLUE, Formatting.LIGHT_PURPLE, Formatting.DARK_AQUA, Formatting.DARK_BLUE,
            Formatting.DARK_GRAY, Formatting.DARK_GREEN, Formatting.DARK_RED
    };

    private static String formatting(String input, double delay) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0.0D) {
            delay = 0.001D;
        }
        int offset = (int) (Math.floor(System.currentTimeMillis() & 0x3FFFL) / delay) % ColorUtil.colour.length;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            sb.append(ColorUtil.colour[(ColorUtil.colour.length + i - offset) % ColorUtil.colour.length]);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String makeColour01(String input) {
        return formatting(input, 80.0D);
    }

    public static String makeColour02(String input) {
        return formatting(input, 59.5D);
    }
}
