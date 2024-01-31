package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.block.entity.DrinkMachineEntity;

public class ModBlockEntities {
    // 注册方块实体
    public static BlockEntityType<DrinkMachineEntity> DRINK_MACHINE_ENTITY;

    public static void registerBlockEntities() {
        DRINK_MACHINE_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(FirstMod.MOD_ID, "drink_machine"),
                FabricBlockEntityTypeBuilder.create(DrinkMachineEntity::new, ModBlocks.DRINK_MACHINE).build(null));

        // 流体储存功能绑定到饮料制作机上，流体储存功能登记
        FluidStorage.SIDED.registerForBlockEntities((blockEntity, direction) -> ((DrinkMachineEntity)blockEntity).fluidStorage, DRINK_MACHINE_ENTITY);
    }
}
