package net.starlight.potato_core.register;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;

public class ModPortal {
    public static void registerPortal() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.GOLD_BLOCK)
                .lightWithFluid(Fluids.WATER)
                .destDimID(new Identifier("the_nether"))
                .tintColor(234, 183,8)
                .registerPortal();
    }
}
