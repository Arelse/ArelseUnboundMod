package arelse.unbound.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ArelseSettingsScreen extends Screen {
    private static final int ACCENT = 0xFF5AA9E6;
    private static final int BACKGROUND = 0xF00B0B0F;

    private final Screen parent;

    public ArelseSettingsScreen(Screen parent) {
        super(Component.literal("Arelse Unbound Client"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;
        int startY = this.height / 2 - 90;
        int gap = 24;

        this.addRenderableWidget(toggle(x, startY, "HUD", () -> HudConfig.hudEnabled, v -> HudConfig.hudEnabled = v));
        this.addRenderableWidget(toggle(x, startY + gap, "FPS", () -> HudConfig.showFps, v -> HudConfig.showFps = v));
        this.addRenderableWidget(toggle(x, startY + gap * 2, "CPS", () -> HudConfig.showCps, v -> HudConfig.showCps = v));
        this.addRenderableWidget(toggle(x, startY + gap * 3, "Coordinates", () -> HudConfig.showCoords, v -> HudConfig.showCoords = v));
        this.addRenderableWidget(toggle(x, startY + gap * 4, "Potion effects", () -> HudConfig.showEffects, v -> HudConfig.showEffects = v));
        this.addRenderableWidget(toggle(x, startY + gap * 5, "Durability", () -> HudConfig.showDurability, v -> HudConfig.showDurability = v));

        this.addRenderableWidget(new DarkButton(x, startY + gap * 6 + 12, 200, 20,
            Component.literal("Done"), this::onClose));
    }

    private DarkButton toggle(int x, int y, String name, BooleanSupplier getter, Consumer<Boolean> setter) {
        return new DarkButton(x, y, 200, 20, label(name, getter.getAsBoolean()), () -> {
            setter.accept(!getter.getAsBoolean());
            HudConfig.save();
            this.clearWidgets();
            this.init();
        });
    }

    private static Component label(String name, boolean on) {
        return Component.literal(name + ": " + (on ? "ON" : "OFF"));
    }

    @Override
    protected void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fill(0, 0, this.width, this.height, BACKGROUND);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 110, ACCENT);
    }

    @Override
    public void onClose() {
        HudConfig.save();
        this.minecraft.setScreen(parent);
    }
}
