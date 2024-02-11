package net.starlight.potato_core.register;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;

public class ModPaintings {
    public static final PaintingVariant KUN_HEAD = registerPainting("kun_head", new PaintingVariant(64, 64));
    public static final PaintingVariant KUN_NAME = registerPainting("kun_name", new PaintingVariant(64, 32));
    public static final PaintingVariant BLUEY = registerPainting("bluey", new PaintingVariant(64, 64));

    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant){
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(FirstMod.MOD_ID, name), paintingVariant);
    }

    public static void registerPaintings(){
        FirstMod.LOGGER.debug("Register Paintings for" + FirstMod.MOD_ID);
    }
}
