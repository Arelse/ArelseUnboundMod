package arelse.unbound.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class ArelseUnboundClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementAfter(
            VanillaHudElements.MISC_OVERLAYS,
            Identifier.of("arelse-unbound", "fps_counter"),
            (context, tickCounter) -> {
                MinecraftClient client = MinecraftClient.getInstance();
                int fps = client.getCurrentFps();
                context.drawText(client.textRenderer, "FPS: " + fps, 4, 4, 0xFFFFFF, true);
            }
        );
    }
}
