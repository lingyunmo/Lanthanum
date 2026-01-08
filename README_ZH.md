# Lanthanum (镧)

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1-green?style=flat-square)
![Architectury](https://img.shields.io/badge/Built%20with-Architectury-blue?style=flat-square)
![CI Status](https://img.shields.io/github/actions/workflow/status/lingyunmo/Lanthanum/Build_master.yml?style=flat-square&label=Build)
![License](https://img.shields.io/github/license/lingyunmo/Lanthanum?style=flat-square)

**Lanthanum** 是一个适用于 Minecraft 1.20.1 的现代科技模组，基于 Architectury API 构建，同时支持 **Fabric** 和 **Forge** 加载器。本模组引入了全新的材料、独特的矿物处理机制以及一个专属的维度，旨在拓展您游戏世界的科技边界。

> 🌏 **语言选择 / Language**:
> [English](README.md) | [简体中文](README_ZH.md)

---

## 🚧 开发状态与重要警告

**⚠️ 下载前请务必阅读**

所有以 **`0.x.x`** 开头（例如 `0.0.1`）的版本均属于 **ALPHA / 实验性版本**。
* 这些构建版本**未经完整测试**，仅供开发或尝鲜使用。
* 可能包含**严重的 Bug**，导致游戏崩溃或**损坏存档**。
* 可能缺少关键依赖或合成表。

**请勿在生存存档或整合包中使用 `0.x.x` 版本。** 请仅使用稳定正式版（版本号 `1.0.0` 起）。

---

## 📥 安装指南

本模组需要安装以下前置依赖才能运行：

### Fabric 用户
1.  [Fabric Loader](https://fabricmc.net/)
2.  [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3.  [Architectury API (Fabric)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)

### Forge 用户
1.  [Minecraft Forge](https://files.minecraftforge.net/)
2.  [Architectury API (Forge)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)

---

## ✨ 模组特性 (计划/施工中)

* **全新资源**：镧矿石 (Lanthanum Ore) 与各类宝石。
* **矿物处理**：高级精炼机制，可大幅提高矿物产出倍率。
* **跨平台支持**：完全兼容 Fabric 和 Forge 双平台。

---

## 🤝 贡献指南

我们非常欢迎社区贡献！无论您是开发者、美术设计还是翻译人员，都可以帮助完善 Lanthanum。

### 分支策略
我们遵循标准 **GitHub Flow** 工作流。
* 所有的 Pull Requests (PR) 请直接提交到 **`master`** 分支。
* 我们的 CI/CD 流水线会自动测试您的 PR。**在自动化检查通过之前，请勿合并代码。**

### 提交规范

#### 1. 提交 Issue (问题反馈)
在提交 Issue 之前，请先搜索是否已有相同问题。请使用我们将提供的 **Issue 模板** 并打上正确的标签：
* `type: bug` - 游戏崩溃、逻辑错误或功能异常。
* `type: feature` - 新功能建议。
* `platform: fabric` / `platform: forge` - 如果问题仅出现在特定平台。

#### 2. Pull Requests (PR 规范)
* **命名公约**：我们严格遵循 [约定式提交 (Conventional Commits)](https://www.conventionalcommits.org/zh-hans/)。您的 PR 标题必须符合以下格式：
    * `feat(common): add lanthanum ingot` (添加镧锭)
    * `fix(forge): resolve rendering crash` (修复渲染崩溃)
    * `docs: update readme` (更新文档)
    * `assets: add texture for machine` (添加机器材质)
* **范围 (Scope)** (可选): `common`, `fabric`, `forge`, `assets`, `data`.
* **描述**: 简要说明您的改动。如果是修复 Bug，请关联 Issue（例如 `Closes #12`）。

### 搭建开发环境
1.  克隆 (Clone) 本仓库。
2.  运行 `./gradlew genSources` (或您 IDE 对应的 Gradle 任务)。
3.  将项目导入 IntelliJ IDEA。

---

## 📄 许可证

本项目采用 **MIT License** 许可证开源。详情请参阅 [LICENSE](LICENSE) 文件。