package arelse.unbound.client;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class HudConfig {
    public static boolean hudEnabled = true;
    public static boolean showFps = true;
    public static boolean showCps = true;
    public static boolean showCoords = true;
    public static boolean showEffects = true;
    public static boolean showDurability = true;

    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("arelse-unbound.properties");

    public static void load() {
        if (!Files.exists(FILE)) {
            return;
        }
        Properties p = new Properties();
        try (InputStream in = Files.newInputStream(FILE)) {
            p.load(in);
        } catch (IOException e) {
            return;
        }
        hudEnabled = read(p, "hudEnabled", hudEnabled);
        showFps = read(p, "showFps", showFps);
        showCps = read(p, "showCps", showCps);
        showCoords = read(p, "showCoords", showCoords);
        showEffects = read(p, "showEffects", showEffects);
        showDurability = read(p, "showDurability", showDurability);
    }

    public static void save() {
        Properties p = new Properties();
        p.setProperty("hudEnabled", String.valueOf(hudEnabled));
        p.setProperty("showFps", String.valueOf(showFps));
        p.setProperty("showCps", String.valueOf(showCps));
        p.setProperty("showCoords", String.valueOf(showCoords));
        p.setProperty("showEffects", String.valueOf(showEffects));
        p.setProperty("showDurability", String.valueOf(showDurability));
        try {
            Files.createDirectories(FILE.getParent());
            try (OutputStream out = Files.newOutputStream(FILE)) {
                p.store(out, "Arelse Unbound settings");
            }
        } catch (IOException ignored) {
        }
    }

    private static boolean read(Properties p, String key, boolean fallback) {
        String value = p.getProperty(key);
        return value == null ? fallback : Boolean.parseBoolean(value);
    }
}
