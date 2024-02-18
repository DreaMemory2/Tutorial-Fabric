package net.starlight.potato_core.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class ProjectileEntity extends Entity implements Ownable {
    @Nullable
    private UUID ownerUuid;
    @Nullable
    private Entity owner;
    private boolean leftOwner;
    private boolean shot;

    public ProjectileEntity(EntityType<? extends ProjectileEntity> type, World world) {
        super(type, world);
    }

    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUuid = entity.getUuid();
            this.owner = entity;
        }
    }

    @Nullable
    @Override
    public Entity getOwner() {
        World world;
        return null;
    }
}
