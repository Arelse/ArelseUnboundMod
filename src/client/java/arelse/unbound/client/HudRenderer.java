package arelse.unbound.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class HudRenderer {
    private static final int ACCENT = 0xFF5AA9E6;
    private static final int BACKGROUND = 0xAA0E0E12;
    private static final int TEXT = 0xFFE6E6E6;

    public static void render(GuiGraphics graphics) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (!HudConfig.hudEnabled || player == null || mc.options.hideGui) {
            return;
        }

        List<String> lines = new ArrayList<>();
        lines.add("FPS " + mc.getFps());

        if (HudConfig.showCoords) {
            BlockPos pos = player.blockPosition();
            lines.add("XYZ " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
        }

        if (HudConfig.showDurability) {
            ItemStack stack = player.getMainHandItem();
            if (stack.isDamageableItem()) {
                int max = stack.getMaxDamage();
                lines.add("Durability " + (max - stack.getDamageValue()) + "/" + max);
            }
        }

        if (HudConfig.showEffects) {
            for (MobEffectInstance effect : player.getActiveEffects()) {
                String name = effect.getEffect().value().getDisplayName().getString();
                lines.add(name + " " + (effect.getDuration() / 20) + "s");
            }
        }

        int width = 0;
        for (String line : lines) {
            width = Math.max(width, mc.font.width(line));
        }

        int x = 6;
        int y = 6;
        int lineHeight = 11;
        int boxWidth = width + 14;
        int boxHeight = lines.size() * lineHeight + 8;

        graphics.fill(x, y, x + boxWidth, y + boxHeight, BACKGROUND);
        graphics.fill(x, y, x + 2, y + boxHeight, ACCENT);
        for (int i = 0; i < lines.size(); i++) {
            graphics.drawString(mc.font, lines.get(i), x + 8, y + 4 + i * lineHeight, TEXT, true);
        }
    }
}
