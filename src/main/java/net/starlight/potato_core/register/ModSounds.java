package net.starlight.potato_core.register;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.starlight.potato_core.FirstMod;

public class ModSounds {
    /**
     * galaxy item Galaxy唱片
     */
    public static final SoundEvent GALAXY = registerSoundEvent("galaxy");
    public static final SoundEvent BEAUTIFUL_CHICKEN = registerSoundEvent("beautiful_chicken");
    private static final MusicDiscItem GALAXY_MUSIC_DISC_ITEM = new MusicDiscItem(2, GALAXY, new FabricItemSettings(), 5);
    public static final Item GALAXY_ITEM = registerMusicDiscItem("galaxy_item", GALAXY_MUSIC_DISC_ITEM);
    private static SoundEvent registerSoundEvent(String name){
        return Registry.register(Registries.SOUND_EVENT, new Identifier("potato_core:" + name), SoundEvent.of(new Identifier("potato_core:" + name)));
    }
    private static Item registerMusicDiscItem(String item, MusicDiscItem musicDiscItem){
        return Registry.register(Registries.ITEM, new Identifier(FirstMod.MOD_ID, item), musicDiscItem);
    }
    public static void registerSounds(){
        FirstMod.LOGGER.debug("Sounds Event Register!");
    }
}
