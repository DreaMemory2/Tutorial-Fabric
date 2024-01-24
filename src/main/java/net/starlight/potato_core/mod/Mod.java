package net.starlight.potato_core.mod;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

/**
 * <p>将客户端的模块进行统一管理，让所以的模块继承父类，之后将所有的模块添加到列表中</p>
 * <p>基本的属性为模块名称和模块是否被启用的属性</p>
 * @author Stalight
 * @since 1.0
 */
public abstract class Mod {
    private final String name;
    private final Category category;
    /** 使用键盘按键来设置是否启用 */
    private boolean isEnable;
    /** 该模块添加按键代码属性 */
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
    public abstract void enable();

    public abstract void disable();

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
    public abstract void draw(MatrixStack matrices);

    /**
     * @return 模组资源路径
     */
    public static Identifier modLoc(String path) {
        return new Identifier(FirstMod.MOD_ID, path);
    }
}
