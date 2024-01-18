package net.starlight.potato_core.block;

import net.minecraft.block.Block;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class NineCompressedWool extends Block {
    public NineCompressedWool(Settings settings) {
        super(settings);
    }

    @Override
    public MutableText getName() {
        return Text.translatable("§c9重压缩毛巾").formatted(Formatting.DARK_RED);
    }
}
