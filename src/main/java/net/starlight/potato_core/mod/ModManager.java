package net.starlight.potato_core.mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>将模块全部放入列表里</p>
 */
public class ModManager {
    private final List<Mod> mods = new ArrayList<>();

    public void load() {
        mods.add(new Logo());
    }

    /**
     * <p>创建一个方法，从manager中获取到已经启用的模块</p>
     */
    public List<Mod> getIsEnables() {
        return mods.stream().filter(Mod::isEnable).collect(Collectors.toList());
    }

    /**
     * <p>在ModManager类中创建方法</p>
     * <p>当方法被调用时，模块列表判断模块的按键代码来启用或禁用模块</p>
     * <p>该模块添加按键代码属性</p>
     */
    public void onKey(int code) {
        for (Mod mod: mods) {
            // 如果代码按键相等，就设置模块启用或禁用
            if (mod.getKey() == code) {
                mod.setEnable(!mod.isEnable());
            }
        }

    }
}
