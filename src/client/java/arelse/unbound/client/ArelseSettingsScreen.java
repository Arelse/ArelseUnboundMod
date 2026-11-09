package arelse.unbound.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ArelseSettingsScreen extends Screen {
    private final Screen parent;

    public ArelseSettingsScreen(Screen parent) {
        super(Component.literal("Arelse Unbound"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 50;
        int gap = 24;

        this.addRenderableWidget(toggle(x, y, "HUD", () -> HudConfig.hudEnabled, v -> HudConfig.hudEnabled = v));
        this.addRenderableWidget(toggle(x, y + gap, "Coordinates", () -> HudConfig.showCoords, v -> HudConfig.showCoords = v));
        this.addRenderableWidget(toggle(x, y + gap * 2, "Potion effects", () -> HudConfig.showEffects, v -> HudConfig.showEffects = v));
        this.addRenderableWidget(toggle(x, y + gap * 3, "Durability", () -> HudConfig.showDurability, v -> HudConfig.showDurability = v));

        this.addRenderableWidget(Button.builder(Component.literal("Done"), b -> this.onClose())
            .bounds(x, y + gap * 4 + 10, 200, 20)
            .build());
    }

    private Button toggle(int x, int y, String name, BooleanSupplier getter, Consumer<Boolean> setter) {
        return Button.builder(label(name, getter.getAsBoolean()), b -> {
                setter.accept(!getter.getAsBoolean());
                b.setMessage(label(name, getter.getAsBoolean()));
            })
            .bounds(x, y, 200, 20)
            .build();
    }

    private static Component label(String name, boolean on) {
        return Component.literal(name + ": " + (on ? "ON" : "OFF"));
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fill(0, 0, this.width, this.height, 0xE00B0B0F);
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 80, 0xFF5AA9E6);
    }
}
