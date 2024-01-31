package net.starlight.potato_core.register;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

@Test(Result.SUCCESS)
public class ModPortal {
    /**
     * 注册传送门登记处
     */
    public static void registerPortal() {
        CustomPortalBuilder.beginPortal()
                // 传送门框架方块：雪块
                .frameBlock(Blocks.SNOW_BLOCK)
                // 启动传送门的液体
                .lightWithFluid(Fluids.WATER)
                // 传送维度
                .destDimID(new Identifier("the_nether"))
                // 传送门方块的颜色
                .tintColor(234, 183,8)
                .registerPortal();
    }
}
