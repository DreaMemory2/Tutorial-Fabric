package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;
import net.starlight.potato_core.screen.handler.DrinkMachineScreenHandler;
import net.starlight.potato_core.screen.handler.KunBookScreenHandler;

/**
 * Forge与Fabric的关系
 */
public class ModScreenHandlers {
    // 实现了ExtendedFactory函数接口中的create
    // 方法一(Lambda表达式):
    // KunBookScreenHandler.ExtendedFactory factory = (syncId, inventory, buf) -> new KunBookScreenHandler(syncId, inventory, buf);
    // 方法二(精简方式):
    public static final ScreenHandlerType<KunBookScreenHandler> KUN_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(KunBookScreenHandler::new);
    public static ScreenHandlerType<DrinkMachineScreenHandler> DRINK_MACHINE_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(DrinkMachineScreenHandler::new);

    public static void registerAllScreenHandles() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(FirstMod.MOD_ID, "kun_book_screen"),
                KUN_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER,
                new Identifier(FirstMod.MOD_ID, "drink_machine_screen_handler"),
                DRINK_MACHINE_SCREEN_HANDLER);
    }
}
