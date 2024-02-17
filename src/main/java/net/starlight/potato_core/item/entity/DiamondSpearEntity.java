package net.starlight.potato_core.item.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.starlight.potato_core.register.ModEntities;

public class DiamondSpearEntity extends PersistentProjectileEntity {
    public ItemStack thrownItem;
    public static final int	PICKUP_ALL = 1;
    public static final int	PICKUP_OWNER = 3;
    private static final float[] NO_MATERIAL_COLOR = { 1F, 1F, 1F };
    private static final float[][] MATERIAL_COLORS = { { 0.6F, 0.4F, 0.1F }, { 0.5F, 0.5F, 0.5F }, { 1.0F, 1.0F, 1.0F }, { 0.0F, 0.8F, 0.7F }, { 1.0F, 0.9F, 0.0F } };

    public DiamondSpearEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public DiamondSpearEntity(World world, LivingEntity entityliving, ItemStack itemStack) {
        super(ModEntities.DIAMOND_SPEAR_ENTITY, entityliving, world);
        setThrownItemStack(itemStack);
        updatePositionAndAngles(entityliving.getX(), entityliving.getY() + entityliving.getStandingEyeHeight(), entityliving.getZ(), entityliving.prevYaw, entityliving.prevPitch);
        prevX -= MathHelper.cos((prevYaw / 180F) * 3.141593F) * 0.16F;
        prevY -= 0.1D;
        prevZ -= MathHelper.sin((prevYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(prevX, prevY, prevZ);
        // yOffset = 0.0F;
        prevX = -MathHelper.sin((prevYaw / 180F) * 3.141593F);
        lastRenderX = -MathHelper.sin((prevYaw / 180F) * 3.141593F) * MathHelper.cos((prevPitch / 180F) * 3.141593F);
        lastRenderY = -MathHelper.sin((prevPitch / 180F) * 3.141593F);
        lastRenderZ = MathHelper.cos((prevYaw / 180F) * 3.141593F) * MathHelper.cos((prevPitch / 180F) * 3.141593F);
        setVelocity(lastRenderX, lastRenderY, lastRenderZ, 0.8F, 3.0F);
    }

    public float getMeleeHitDamage(Entity entity) {
        if (entity instanceof LivingEntity) {
            return EnchantmentHelper.getSweepingMultiplier((LivingEntity) entity);
        }
        return 0F;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        // if (worldObj.isRemote) return;
        Entity entity = entityHitResult.getEntity();
        DamageSource damageSource = DamageSource.arrow(this, entity);
        if (entity.damage(damageSource, /*thrownItem.getItem().getMeleeComponent().getEntityDamage() + */ getMeleeHitDamage(entity))) {
            onHit((LivingEntity) entity);
            getHitSound();
            if (thrownItem.getDamage() + 1 > thrownItem.getMaxDamage()) {
                // thrownItem.stackSize--;
                // setDead();
            } else {
                if (entity instanceof LivingEntity) {
                    thrownItem.setDamage(1);
                } else {
                    // thrownItem.attemptDamageItem(1, rand);
                }
                setVelocity(0.0D, 0.0D, 0.0D);
            }
        } else {
            bounceBack();
        }
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
    }

    @Override
    protected ItemStack asItemStack() {
        return thrownItem;
    }

    public void setThrownItemStack(ItemStack itemstack) {
        thrownItem = itemstack;
        updateWeaponMaterial();
    }

    public final void updateWeaponMaterial() {
        if (thrownItem != null ) {
            // int material = MaterialRegistry.getMaterialID(thrownItem);
            // if (material < 0) material = ((IItemWeapon) thrownItem.getItem()).getMeleeComponent().weaponMaterial.ordinal();
            // dataWatcher.updateObject(25, Byte.valueOf((byte) (material & 0xFF)));
        }
    }



    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (thrownItem != null) {
            nbt.put("thrown.item", thrownItem.writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        setThrownItemStack(ItemStack.fromNbt(nbt.getCompound("thrown.item")));
    }

    protected void bounceBack() {
        lastRenderX *= -0.1D;
        lastRenderY *= -0.1D;
        lastRenderZ *= -0.1D;
        prevYaw += 180F;
        // prevRotationYaw += 180F;
        setAir(0);
    }

    public PickupPermission getMaxLifetime() {
        // return pickupMode == PICKUP_ALL || pickupMode == PICKUP_OWNER ? 0 : 1200;
        return pickupType = PickupPermission.ALLOWED;
    }

    public int getMaxArrowShake() {
        return 10;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    public final float[] getMaterialColor() {
        // int id = getWeaponMaterialId();
        int id = getMaxArrowShake();
        if (id < 5) return MATERIAL_COLORS[id];
        return getColorFromMaterialID(id);
    }

    public static float[] getColorFromMaterialID(int id) {
        return NO_MATERIAL_COLOR;
    }
}
