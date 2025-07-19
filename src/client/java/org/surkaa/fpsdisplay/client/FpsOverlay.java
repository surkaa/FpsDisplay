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
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class FpsOverlay {
    private static KeyBinding toggleHudKey;
    private static boolean hudVisible = true;

    private static final int updateInterval = 20; // 每 20 tick 更新一次
    private static int entityCount = 0;
    private static int tickCounter = 0;

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

            if (client.world != null) {
                tickCounter++;
                if (tickCounter >= updateInterval) {
                    tickCounter = 0;
                    int count = 0;
                    for (Entity entity : client.world.getEntities()) {
                        if (entity != null && entity.isAlive()) {
                            count++;
                        }
                    }
                    entityCount = count;
                }
            }
        });
    }

    private static void render(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (!hudVisible || client.player == null || client.textRenderer == null) return;

        TextRenderer textRenderer = client.textRenderer;

        int lineHeight = 10;
        int x = 5;
        int y = 5;

        // FPS
        int fps = client.getCurrentFps();
        drawContext.drawText(textRenderer, Text.literal("FPS: " + fps), x, y, 0xFFFFFF, true);

        // 实体数量
        drawContext.drawText(textRenderer, Text.literal("EntityCount: " + entityCount), x, y + lineHeight, 0xFFFFFF, true);

        // 玩家速度（blocks/sec）
        Vec3d velocity = client.player.getVelocity();
        double blocksPerSecond = velocity.length() * 20.0;
        drawContext.drawText(textRenderer, Text.literal(String.format("Velocity: %.1f", blocksPerSecond)), x, y + 2 * lineHeight, 0xFFFFFF, true);
    }
}
