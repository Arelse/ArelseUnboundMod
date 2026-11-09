package arelse.unbound.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class ArelseUnboundClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementAfter(
            VanillaHudElements.MISC_OVERLAYS,
            ResourceLocation.fromNamespaceAndPath("arelse-unbound", "fps_counter"),
            (graphics, deltaTracker) -> {
                Minecraft mc = Minecraft.getInstance();
                graphics.drawString(mc.font, "FPS: " + mc.getFps(), 4, 4, 0xFFFFFF, true);
            }
        );
    }
}
