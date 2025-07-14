package org.surkaa.fpsdisplay.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class FpsOverlay {
    public static void register() {
        HudRenderCallback.EVENT.register(FpsOverlay::render);
    }

    private static void render(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.textRenderer == null) return;

        int fps = client.getCurrentFps();
        String text = "FPS: " + fps;

        TextRenderer textRenderer = client.textRenderer;

        // 获取绘制参数
        MatrixStack matrixStack = drawContext.getMatrices();
        matrixStack.push();

        // 你也可以更换颜色或字体样式
        drawContext.drawText(
                textRenderer,
                text,
                5,
                5,
                0xFFFFFF, // 白色
                true      // 是否带阴影
        );

        matrixStack.pop();
    }
}
