package net.starlight.potato_core.datagen.provider;

import net.minecraft.data.DataOutput;
import net.minecraft.data.DataWriter;
import net.minecraft.data.client.ModelProvider;

import java.util.concurrent.CompletableFuture;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(DataOutput output) {
        super(output);
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {

        return super.run(writer);
    }
}
