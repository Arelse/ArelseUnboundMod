package arelse.unbound.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class DarkButton extends AbstractButton {
    private final Runnable action;

    public DarkButton(int x, int y, int width, int height, Component message, Runnable action) {
        super(x, y, width, height, message);
        this.action = action;
    }

    @Override
    public void onPress() {
        action.run();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        boolean hovered = this.isHovered();
        int bg = hovered ? 0xFF2A2A33 : 0xFF17171C;
        int accent = hovered ? 0xFF5AA9E6 : 0xFF2A2A33;
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();

        graphics.fill(x, y, x + w, y + h, bg);
        graphics.fill(x, y + h - 1, x + w, y + h, accent);
        graphics.drawCenteredString(
            Minecraft.getInstance().font, getMessage(), x + w / 2, y + (h - 8) / 2, 0xFFE6E6E6);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
    }
}
