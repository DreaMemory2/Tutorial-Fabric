package net.starlight.potato_core.mod;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.starlight.potato_core.FirstMod;

import java.util.List;

/**
 * <p>将客户端的模块进行统一管理，让所以的模块继承父类，之后将所有的模块添加到列表中</p>
 * <p>基本的属性为模块名称和模块是否被启用的属性</p>
 * <p></p>
 */
public class Mod {
    private final String name;
    private final Category category;
    private boolean isEnable;
    /** <p>该模块添加按键代码属性</p> */
    private int key;

    public Mod(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
        if (isEnable) {
            this.enable();
        } else {
            this.disable();
        }
    }

    /**
     * <p>用于执行模组启用或禁用</p>
     */
    public void enable() {

    }

    public void disable() {

    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    /**
     * <p>编写事件</p>
     * <p>创建draw方法</p>
     */
    public void draw(MatrixStack matrices) {

    }

    /**
     * @return 模组资源路径
     */
    public static Identifier modLoc(String path) {
        return new Identifier(FirstMod.MOD_ID, path);
    }

    /**
     * 在服务端世界生成掉落物
     * @param world 生成世界
     * @param pos 生成位置
     * @param drops 要掉落的物品
     * @param keepStack 是否保留物品，如果传入false，则原先的ItemStack会被销毁
     */
    public static void spawnDropItems(World world, BlockPos pos, List<ItemStack> drops, boolean keepStack) {

    }
}
