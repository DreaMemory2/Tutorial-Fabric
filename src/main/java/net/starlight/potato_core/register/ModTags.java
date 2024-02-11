package net.starlight.potato_core.register;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

@Test(Result.UNKNOWN)
public class ModTags {
    /**
     * <p>通过Ore标签，筛选出所需要的矿石，通过矿石Nbt标签，储存在Data Tabet物品上</p>
     */
    public static final TagKey<Block> ORE_BLOCKS = createTag("ore_blocks");

    private static TagKey<Block> createTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(FirstMod.MOD_ID, name));
    }
}
