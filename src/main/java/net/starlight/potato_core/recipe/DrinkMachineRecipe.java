package net.starlight.potato_core.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

/**
 * <p>自定义配方，自定义饮料制作机的合成表</p>
 */
@Test(Result.USELESS)
public class DrinkMachineRecipe implements Recipe<SimpleInventory> {
    /** 饮料制作机的标识符，例如：potato_core:drink_machine */
    private final Identifier id;
    /** 输出的物品 */
    private final ItemStack output;
    /** 输入的物品 */
    private final DefaultedList<Ingredient> recipesItem;

    /**
     * @param id 桦木工作台的Id
     * @param output 输出/传入的物品
     * @param recipesItem 输入/放入的物品
     */
    public DrinkMachineRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipesItem) {
        this.id = id;
        this.output = output;
        this.recipesItem = recipesItem;
    }

    /**
     * <p>我们需要判断给定的Inventory是否满足配方的输入要求</p>
     * <p>例如，第一个插槽匹配第一个输入，第二个插槽匹配第二个输入，然后返回true</p>
     * <p>当物品栏中的原料和配方中的排列顺序和物品一致时，返回 true</p>
     * @param inventory 物品栏
     * @return 是否满足配方的输入要求
     */
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) {
            return false;
        }
        // 下标为0插槽上的物品是否与下标为1插槽上的物品匹配
        return recipesItem.get(0).test(inventory.getStack(1));
    }

    /**
     * @param inventory 方块的库存
     * @return 合成之后的物品，输出物品
     */
    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return this.output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    /**
     * <p>获取输出物品</p>
     */
    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    /**
     * <p>获取饮料制作机的Id</p>
     */
    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<DrinkMachineRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * <p>获取饮料制作机的类型</p>
     */
    @Override
    public RecipeType<DrinkMachineRecipe> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<DrinkMachineRecipe>{
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "drink_machine";
    }

    /**
     * <p>Json文件的配置，Json文件的配方</p>
     * <pre>
     *     {
     *         "type": "potato_core:drink_machine",
     *         "ingredients": [
     *             {
     *              "item": "minecraft:apple"
     *             }
     *         ],
     *         "output": {
     *             "item": "potato_core:apple_juice"
     *         }
     *     }
     * </pre>
     * <p>配置自定义配方JSON文件处理[序列化]</p>
     */
    public static class Serializer implements RecipeSerializer<DrinkMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "drink_machine";

        /**
         * <p>从JSON文件中读取数据</p>
         * @param id 配方的ID
         * @param json 配方JSON文件
         * @return 返回一个DrinkMachineRecipe
         */
        @Override
        public DrinkMachineRecipe read(Identifier id, JsonObject json) {
            // 输入合成所需的物品
            JsonArray ingredinets = JsonHelper.getArray(json, "ingredinets");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);
            // 输出合成后的物品
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredinets.get(i)));
            }
            
            return new DrinkMachineRecipe(id, output, inputs);
        }

        /**
         * <p>从数据包中读取数据</p>
         * <p>读取数据包中的数据，并返回配方的实例</p>
         * @param id 配方的ID
         * @param buf 配方Buf
         * @return 配方的实例
         *
         */
        @Override
        public DrinkMachineRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new DrinkMachineRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, DrinkMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
