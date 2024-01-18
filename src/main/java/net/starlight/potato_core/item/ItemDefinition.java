package net.starlight.potato_core.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

public class ItemDefinition<I extends Item> implements ItemConvertible {
    private final Identifier id;

    public String defaultName;

    public ItemDefinition(Identifier id, String name, I item) {
        this.id = id;
    }

    public void ClientRegister() {

    }

    /**
     * 数据生成环境下的物品模型注册逻辑
     */
    protected void provideModel() {

        /*DataProviders.MODEL_ITEM.model(loc)
                .parent("minecraft:item/generated")
                .texture("layer0", loc);*/
    }

    protected String getDefaultName() {
        return defaultName;
    }

    @Override
    public Item asItem() {
        return null;
    }
}
