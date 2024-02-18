package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.entity.DiamondSpearEntity;

public class ModEntities {

    public static final EntityType<DiamondSpearEntity> DIAMOND_SPEAR_ENTITY = registerEntity("diamond_spear",
            FabricEntityTypeBuilder.<DiamondSpearEntity>create(SpawnGroup.MISC, (DiamondSpearEntity::new))
                    .trackable(4, 10)
                    .dimensions(EntityDimensions.fixed(.5f, .5f)).build());

    private static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> entity) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(FirstMod.MOD_ID, id), entity);
    }

    public static void registerEntity() {
        FirstMod.LOGGER.info("Registering entity");
    }
}
