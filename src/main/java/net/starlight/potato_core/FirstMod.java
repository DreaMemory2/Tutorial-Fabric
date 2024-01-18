package net.starlight.potato_core;

import net.fabricmc.api.ModInitializer;
import net.starlight.potato_core.mod.ModManager;
import net.starlight.potato_core.register.ModBlocks;
import net.starlight.potato_core.register.*;
import net.starlight.potato_core.screen.ModScreenHandlers;
import net.starlight.potato_core.sound.Sounds;
import net.starlight.potato_core.world.feature.TreeConfiguredFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 主要的登记处
public class FirstMod implements ModInitializer {
	public static final String MOD_ID = "potato_core";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ModManager modManager;

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModPaintings.registerPaintings();
		ModScreenHandlers.registerAllScreenHandles();

		ModFluids.registerFluids();
		ModBiome.registerBiome();

		Sounds.registerSounds();
		TreeConfiguredFeatures.registerTree();

		ModPortal.registerPortal();
	}

	/**
	 * <p>需要将manager在客户端主类中创建</p>
	 * <p>游戏启动时调用客户端主页中的run来创建</p>
	 * <p>在run方法和stop方法被调用时，打印日志</p>
	 */
	public static void run() {
		modManager = new ModManager();
		modManager.load();
		FirstMod.LOGGER.info("FirstMod runing...");
	}
	/**
	 * 停止
	 */
	public static void stop() {
		FirstMod.LOGGER.info("FirstMod stopping...");
	}
}
