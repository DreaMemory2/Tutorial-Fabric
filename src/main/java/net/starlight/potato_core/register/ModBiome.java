package net.starlight.potato_core.register;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.starlight.potato_core.FirstMod;

public class ModBiome {
    // 注册维度
    // public static final RegistryKey<World> UP_NETHER = RegistryKey.of(RegistryKeys.WORLD, new Identifier(FirstMod.MOD_ID, "up_nether"));
    // 注册维度类型
    // public static final RegistryKey<DimensionType> UP_NETHER_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, UP_NETHER.getValue());
    public static void registerBiome(){
        FirstMod.LOGGER.debug("Register Biome" + FirstMod.MOD_ID);
    }
}
