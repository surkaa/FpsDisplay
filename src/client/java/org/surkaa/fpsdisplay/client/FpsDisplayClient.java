package org.surkaa.fpsdisplay.client;

import net.fabricmc.api.ClientModInitializer;

public class FpsDisplayClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FpsOverlay.register(); // 注册 HUD 渲染器
    }
}
