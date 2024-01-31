package net.starlight.potato_core.util;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public class FluidStack {
    // FluidVariant 类包含了流体的类型和NBT标签
    public FluidVariant fluidVariant;
    // 容积
    public long litre;

    /**
     * @param fluidVariant 类包含了流体的类型和NBT标签
     * @param litre 容积
     */
    public FluidStack(FluidVariant fluidVariant, long litre) {
        this.fluidVariant = fluidVariant;
        this.litre = litre;
    }

    /**
     * @return 返回流体的类型
     */
    public FluidVariant getFluidVariant() {
        return fluidVariant;
    }

    /**
     * @return 返回容积
     */
    public long getLitre() {
        return litre;
    }
    public long getAmount() {
        return litre;
    }

    /**
     * 将给定的droplets转换为MB
     */
    public static long convertDropletsToMb(long droplets) {
        return droplets / 81;
    }
}
