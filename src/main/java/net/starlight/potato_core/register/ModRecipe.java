package net.starlight.potato_core.register;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.recipe.DrinkMachineRecipe;

public class ModRecipe {
    /**
     * <p>自定义配方的登记处</p>
     */
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(FirstMod.MOD_ID, DrinkMachineRecipe.Serializer.ID),
                DrinkMachineRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(FirstMod.MOD_ID, DrinkMachineRecipe.Type.ID),
                DrinkMachineRecipe.Type.INSTANCE);
    }
}
