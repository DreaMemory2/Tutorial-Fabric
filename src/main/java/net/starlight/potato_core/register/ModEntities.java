package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.starlight.potato_core.item.entity.DiamondSpearEntity;

public class ModEntities {

    /**
     * Registries.ENTITY_TYPE,
     *             new Identifier("entitytesting", "cube"),
     *             FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
     */
    public static /* final */ EntityType<DiamondSpearEntity> DIAMOND_SPEAR_ENTITY; /*= Registry.register(
            Registries.ENTITY_TYPE, "diamond_spear",
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, DiamondSpearEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());*/


    private static <T extends DiamondSpearEntity> EntityType<T> registerEntity(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }

    public static void registerEntity() {

    }
}
