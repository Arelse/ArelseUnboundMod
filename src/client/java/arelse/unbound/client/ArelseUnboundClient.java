package arelse.unbound.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class ArelseUnboundClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudConfig.load();

        ClientTickEvents.END_CLIENT_TICK.register(HudRenderer::tick);

        HudElementRegistry.attachElementAfter(
            VanillaHudElements.MISC_OVERLAYS,
            Identifier.fromNamespaceAndPath("arelse-unbound", "hud"),
            (graphics, deltaTracker) -> HudRenderer.render(graphics)
        );
    }
}
