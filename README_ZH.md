# Lanthanum (镧)

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1-green?style=flat-square)
![Architectury](https://img.shields.io/badge/Built%20with-Architectury-blue?style=flat-square)
![CI Status](https://img.shields.io/github/actions/workflow/status/lingyunmo/Lanthanum/Build_master.yml?style=flat-square&label=Build)
![License](https://img.shields.io/github/license/lingyunmo/Lanthanum?style=flat-square)

**Lanthanum** 是一个面向 Minecraft 1.20.1 的工业化矿物处理模组，基于 Architectury API 构建，同时支持 **Fabric** 和 **Forge** 加载器。本模组的目标是将矿石倍率推至远超传统 5 倍的极限，通过两阶段加工链达到 **16 倍甚至更高**：前期采用 Mekanism 式的清晰流水线，后期通过粒子加速器技术实现量子级别的矿石增值。

> 🌏 **语言选择 / Language**:
> [English](README.md) | [简体中文](README_ZH.md)

---

## 🎯 设计理念

| 方面 | 选择 |
|------|------|
| 矿石倍率目标 | **16×+**（超越通用机械的 5×） |
| 加工风格 | 前期清晰流水线 → 后期量子增值 |
| 机器升级 | **升级卡槽制**（速度 / 能效 / 产出） |
| 机器总数 | 约 14 台 |
| 贴图策略 | **ItemColor 染色**：2 张灰度底图 × 材质颜色 = 全部粉尘/晶体变种 |
| 复杂度哲学 | 复杂度集中在量子芯片生产环节；不搞 GregTech 式化工 |

### 两阶段加工体系

```
第一阶段：Mekanism 式流水线（1× → 8×）
┌────────┐   ┌────────┐   ┌────────┐   ┌────────┐   ┌────────┐   ┌────────┐   ┌────────┐
│ 粉碎机  │──→│ 洗矿机  │──→│ 结晶器  │──→│ 离心机  │──→│ 净化炉  │──→│ 压缩机  │──→│感应熔炉 │
│  2×    │   │  3×    │   │  4×    │   │  5×    │   │  6×    │   │  7×    │   │  8×    │
└────────┘   └────────┘   └────────┘   └────────┘   └────────┘   └────────┘   └────────┘

第二阶段：量子增值（8× → 16×+）
┌──────────────────────────────────────────────────────────────┐
│  粒子加速器 → 同位素分离器 → 中子辐照器                          │
│              ↓                                                  │
│  晶圆蚀刻机 → 量子缠绕机 → 芯片封装机                            │
│              ↓                                                  │
│     量子芯片（耐久 64 次）→ 量子增值机                           │
└──────────────────────────────────────────────────────────────┘
```

---

## 🚧 开发状态与重要警告

**⚠️ 下载前请务必阅读**

**当前版本：`0.0.2-alpha`（ALPHA 测试版 — 请勿游玩）**

所有以 **`0.x.x`** 开头的版本均属于 **ALPHA / 实验性版本**，**不适用于正常游戏**。
* 这些构建版本包含**已知和未知的 Bug**，可能导致游戏崩溃或**永久损坏存档**。
* 机器、配方、世界生成均未完成，版本间可能发生**不兼容的破坏性变更**。
* **0.x.x 版本不保证向后兼容** — 未来版本可能导致旧存档完全无法加载。
* 没有任何**生存平衡性** — 能量消耗、加工时间、配方比例均为临时数值。

**请勿在任何以下场景使用 0.x.x 版本：**
- 生存模式存档
- 多人服务器
- 整合包
- 任何你在意的世界

**请等待 `1.0.0`** 稳定版本发布后再进行游玩。

---

## 🗺️ 开发路线图

### Phase 1：基础框架（MVP）— `0.0.x` ✅ 已完成

| 系统 | 详情 |
|------|------|
| **能量** | Botarium `SimpleEnergyContainer` — 单机 20k–40k FE 容量，红石作为燃料 |
| **配方框架** | `MachineRecipe` 类型 — 单输入 → 单输出，JSON 定义，含加工时间和能耗 |
| **升级卡** | 速度（加工时间减半）、节能（能耗降 40%）、增产（25% 额外产出） |
| **镧矿石** | 石头 + 深层变种，Y=-32~64 世界生成，通过 Forge 标签和 `BiomeModifications` 注册 |
| **粉碎机** | 矿石 → 2× 粉尘，200t/32FE |
| **洗矿机** | 粉尘 → 洗净粉尘，150t/16FE |
| **结晶器** | 洗净粉尘 → 2× 晶体，300t/64FE |
| **矿石兼容** | 标签配方（`forge:ores/iron` 等）— 任何模组的铁/金/铜矿均可粉碎为粉尘 |
| **材质系统** | ItemColor 染色：2 张灰度底图，按材质（铁/金/铜/镧）染色，覆盖 12 种物品 |
| **Forge 标签** | 完整标签覆盖：`forge:ores/*`、`forge:dusts/*`、`forge:crystals/*`、`forge:ingots/*`、`forge:raw_materials/*` |
| **GUI** | 176×166 原版熔炉式界面，含能量条和进度箭头 |
| **架构** | `BaseMachineBlockEntity` — 共享抽象基类，新增机器仅需 ~40 行代码 |
| **跨平台** | Architectury：Fabric + Forge，Mojang Mappings，Java 17 |
| **语言** | en_us, zh_cn |

### Phase 2：完整流水线 — `0.1.x`
完成全部 7 台流水线机器，实现完整的 8 倍加工链。

- [ ] **离心机 (Centrifuge)** — 晶体 → 5× 富集碎片
- [ ] **净化炉 (Purification Furnace)** — 碎片 → 6× 高纯粉末
- [ ] **压缩机 (Compressor)** — 粉末 → 7× 压缩坯料
- [ ] **感应熔炉 (Induction Smelter)** — 坯料 → 8× 成品锭
- [ ] 需水机器的流体集成（Botarium Fluid API）
- [ ] JEI/REI 配方查看器兼容
- [ ] Jade/WTHIT 悬浮信息兼容
- [ ] Cloth Config 配置界面集成
- [ ] Forge 能量 Capability 注册

### Phase 3：量子时代 — `0.2.x`
引入粒子加速器链和量子芯片系统，实现 16 倍以上的极致产出。

- [ ] **粒子加速器 (Particle Accelerator)** — 线型辐射区
- [ ] **同位素分离器 (Isotope Separator)**
- [ ] **中子辐照器 (Neutron Irradiator)** — 球形辐射区
- [ ] **晶圆蚀刻机 (Wafer Etcher)**
- [ ] **量子缠绕机 (Quantum Entangler)** — 微弱辐射区
- [ ] **芯片封装机 (Chip Encapsulator)**
- [ ] **量子增值机 (Quantum Multiplier)** — 消耗量子芯片将 8× 放大至 16×+
- [ ] 辐射系统 — 粒子加速器/中子辐照器附近对实体造成持续伤害
- [ ] 防辐射装备
- [ ] 铀矿 + 世界生成（中子源材料）

---

## 📥 安装指南

本模组需要安装以下前置依赖：

### Fabric 用户
1. [Fabric Loader](https://fabricmc.net/)
2. [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3. [Architectury API (Fabric)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)
4. [Botarium](https://modrinth.com/mod/botarium)

### Forge 用户
1. [Minecraft Forge](https://files.minecraftforge.net/)
2. [Architectury API (Forge)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)
3. [Botarium](https://modrinth.com/mod/botarium)

---

## ✨ 当前功能（Phase 1）

### 矿石与材料
* **镧矿石 (Lanthanum Ore)** — 稀土矿物，Y=-32~64 自然生成，本模组所有技术的基石
* **四材质粉尘/晶体系统** — 铁、金、铜、镧各有粉尘/洗净粉尘/晶体三种形态
* **ItemColor 染色** — 2 张共享灰度底图，客户端按材质染色（节省约 10 张贴图）

### 机器
* **粉碎机 (Crusher)** — 矿石加倍（矿石 → 2× 粉尘）。通过 Forge 标签兼容任何模组的矿石
* **洗矿机 (Ore Washer)** — 粉尘精炼为洗净粉尘，为结晶做准备
* **结晶器 (Crystallizer)** — 洗净粉尘生长为 2× 晶体，总计实现 4 倍矿石倍率
* **标准 GUI** — 176×166 原版熔炉式界面，含能量条和进度箭头

### 升级卡
* **速度升级卡** — 加工时间减半
* **节能升级卡** — 能耗降低 40%
* **增产升级卡** — 每次加工 25% 概率额外产出

### 跨模组兼容
* **Forge 标签** — 镧的矿石/锭/粉尘/晶体全部注册为标准 Forge 标签
* **标签配方** — 原版和模组的铁/金/铜矿均可通过粉碎机加工
* **漏斗 I/O** — 方向感知：侧面插入输入槽，底部从输出槽抽取
* **能量** — 内部使用 Botarium `SimpleEnergyContainer`；Forge Capability 注册计划 Phase 2 完成

---

## 🤝 贡献指南

我们非常欢迎社区贡献！无论您是开发者、美术设计还是翻译人员，都可以帮助完善 Lanthanum。

### 分支策略
我们遵循标准 **GitHub Flow** 工作流。
* 所有的 Pull Requests (PR) 请直接提交到 **`master`** 分支。
* 我们的 CI/CD 流水线会自动测试您的 PR。**在自动化检查通过之前，请勿合并代码。**

### 提交规范

#### 1. 提交 Issue（问题反馈）
在提交 Issue 之前，请先搜索是否已有相同问题。请使用我们将提供的 **Issue 模板** 并打上正确的标签：
* `type: bug` — 游戏崩溃、逻辑错误或功能异常。
* `type: feature` — 新功能建议。
* `platform: fabric` / `platform: forge` — 如果问题仅出现在特定平台。

#### 2. Pull Requests（PR 规范）
* **命名公约**：我们严格遵循 [约定式提交 (Conventional Commits)](https://www.conventionalcommits.org/zh-hans/)。您的 PR 标题必须符合以下格式：
    * `feat(common): add crusher machine`（添加粉碎机）
    * `fix(forge): resolve rendering crash`（修复渲染崩溃）
    * `docs: update readme`（更新文档）
    * `assets: add texture for ore washer`（添加洗矿机材质）
* **范围 (Scope)**（可选）：`common`、`fabric`、`forge`、`assets`、`data`。
* **描述**：简要说明您的改动。如果是修复 Bug，请关联 Issue（例如 `Closes #12`）。

### 搭建开发环境
1. 克隆 (Clone) 本仓库。
2. 运行 `./gradlew genSources`（或您 IDE 对应的 Gradle 任务）。
3. 将项目导入 IntelliJ IDEA。

---

## 📄 许可证

本项目采用 **MIT License** 许可证开源。详情请参阅 [LICENSE](LICENSE) 文件。
