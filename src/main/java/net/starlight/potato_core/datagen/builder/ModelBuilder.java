package net.starlight.potato_core.datagen.builder;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModelBuilder {
    public final Map<String, Identifier> textures = new HashMap<>();
    public final Identifier id;
    public final String pathPrefix;
    private Identifier parent;
    public ModelBuilder(String pathPrefix, Identifier id) {
        this.id = id;
        this.pathPrefix = pathPrefix;
    }

    public ModelBuilder parent(Identifier id) {
        this.parent = ModelBuilder.expandLoc(pathPrefix, id);
        return this;
    }

    public ModelBuilder texture(String key, Identifier id) {
        id = ModelBuilder.expandLoc(pathPrefix, id);
        textures.put(key, id);
        return this;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public static Identifier expandLoc(String prefix, Identifier id) {
        String path = id.getPath();
        if (path.indexOf('/') > 0) {
            return id;
        }
        return new Identifier(id.getNamespace(), prefix + "/" + path);
    }
}
