package net.starlight.potato_core.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.starlight.potato_core.ident.Result;
import net.starlight.potato_core.ident.Test;

/**
 * <p>自定义合成表</p>
 * <p>{@link CraftingInventory 处理合成界面九宫格上的物品的类}</p>
 */
@Test(Result.USELESS)
public class BirchCraftingTableRecipe implements Recipe<CraftingInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipesItem;

    /**
     * @param id 桦木工作台的Id
     * @param output 输出/传入的物品
     * @param recipesItem 输入/放入的物品
     */
    public BirchCraftingTableRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipesItem) {
        this.id = id;
        this.output = output;
        this.recipesItem = recipesItem;
    }

    /**
     * <p>我们需要判断给定的Inventory是否满足配方的输入要求</p>
     * <p>例如，第一个插槽匹配第一个输入，第二个插槽匹配第二个输入，然后返回true</p>
     * <p>当物品栏中的原料和配方中的排列顺序和物品一致时，返回 true</p>
     */
    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        if (inventory.size() < 8) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            return recipesItem.get(i).test(inventory.getStack(++i));
        }
        return false;
    }

    /**
     *
     */
    @Override
    public ItemStack craft(CraftingInventory inventory) {
        return this.output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return null;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
