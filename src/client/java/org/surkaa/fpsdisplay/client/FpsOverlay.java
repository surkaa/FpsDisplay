package org.surkaa.fpsdisplay.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class FpsOverlay {

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static KeyBinding toggleHudKey;
    private static boolean hudVisible = true;

    public static void register() {
        // 注册渲染事件
        HudRenderCallback.EVENT.register(FpsOverlay::render);
        // 注册按键
        toggleHudKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.fpsdisplay.toggle_hud",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_F12,
                        "key.categories.misc"
                )
        );
        // 每帧监听是否按下切换键
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleHudKey.wasPressed()) {
                hudVisible = !hudVisible;
            }
        });
    }

    private static void render(DrawContext drawContext, float v) {
        if (!hudVisible || client.player == null || client.textRenderer == null) return;

        TextRenderer textRenderer = client.textRenderer;

        int lineHeight = 10;
        int x = 5;
        int y = 5;

        // FPS
        int fps = client.getCurrentFps();
        drawContext.drawText(textRenderer, Text.literal("FPS: " + fps), x, y, 0xFFFFFF, true);

        // 实体数量（当前维度）
        if (client.world == null) return;
        Iterable<Entity> entityIterable = client.world.getEntities();
        if (entityIterable == null) return;
        int entityCount = 0;
        for (Entity entity : entityIterable) {
            if (entity != null && entity.isAlive()) {
                entityCount++;
            }
        }
        drawContext.drawText(textRenderer, Text.literal("EntityCount: " + entityCount), x, y + lineHeight, 0xFFFFFF, true);
    }
}
