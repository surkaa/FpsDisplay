# FpsDisplay

一个用于在屏幕上实时显示游戏帧率的我的世界（Minecraft）模组。

## 简介

FpsDisplay 是一个轻量级的 Fabric 客户端模组，可以在游戏界面上实时显示重要的性能和状态信息，包括：

- **FPS（帧率）**：实时显示游戏的帧率
- **实体数量**：显示当前世界中的实体总数
- **玩家速度**：显示玩家的移动速度（方块/秒）

## 功能特性

- 🎮 实时显示游戏帧率（FPS）
- 📊 显示当前世界实体数量
- 🏃 显示玩家移动速度
- ⌨️ 支持快捷键切换显示（默认 F12）
- 💡 轻量级，对性能影响极小
- 🌐 支持中文和英文界面

## 安装方法

1. 确保已安装 [Fabric Loader](https://fabricmc.net/)
2. 下载 [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3. 下载本模组的最新版本
4. 将模组文件放入 `.minecraft/mods` 文件夹
5. 启动游戏

## 使用说明

- 启动游戏后，HUD 信息会自动显示在屏幕左上角
- 按 **F12** 键可以切换显示/隐藏 HUD
- 显示的信息包括：
  - FPS: 当前帧率
  - EntityCount: 实体数量
  - Velocity: 玩家速度（方块/秒）

## 兼容性

- **Minecraft 版本**：1.20.1
- **模组加载器**：Fabric Loader 0.16.14+
- **依赖**：Fabric API 0.92.6+

## 开发信息

- **作者**：SurKaa
- **许可证**：MIT
- **源代码**：[GitHub](https://github.com/surkaa/FpsDisplay)

## 构建

```bash
./gradlew build
```

构建完成后，模组文件将位于 `build/libs` 目录中。

## 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件。
