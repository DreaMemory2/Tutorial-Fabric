package net.starlight.potato_core.api;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;

public class KeyboardClass extends Keyboard {
    public boolean sendRepeatsToGui;

    public KeyboardClass(MinecraftClient client) {
        super(client);
    }

    public void setSendRepeatsToGui(boolean sendRepeatsToGui){
        this.sendRepeatsToGui = sendRepeatsToGui;
    }
}
