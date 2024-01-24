package net.starlight.potato_core.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

/**
 * <p>模组物品容器，负责物品的各种注册、数据生成和其他操作</p>
 */
@Test(Result.FAIL)
public class ItemDefinition implements ItemConvertible {
    private final Identifier id;
    public Item item;

    public String defaultName;

    /**
     *
     * @param id ID
     * @param name name
     * @param item 物品
     */
    public ItemDefinition(Identifier id, String name, Item item) {
        this.id = id;
    }

    protected String getDefaultName() {
        return defaultName;
    }

    /**
     * 以物品形式获取Object对象
     */
    @Override
    public Item asItem() {
        return item;
    }
}
