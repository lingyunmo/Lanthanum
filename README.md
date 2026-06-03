# Lanthanum

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1-green?style=flat-square)
![Architectury](https://img.shields.io/badge/Built%20with-Architectury-blue?style=flat-square)
![CI Status](https://img.shields.io/github/actions/workflow/status/lingyunmo/Lanthanum/Build_master.yml?style=flat-square&label=Build)
![License](https://img.shields.io/github/license/lingyunmo/Lanthanum?style=flat-square)

**Lanthanum** is an industrial-scale ore processing mod for Minecraft 1.20.1, built with Architectury API for both **Fabric** and **Forge**. Its goal is to push ore multiplication far beyond the conventional 5× limit, reaching **16× or higher** through a two-stage processing chain: a Mekanism-style step-by-step pipeline, followed by a quantum-level multiplication system powered by particle accelerator technology.

> 🌏 **Language / 语言选择**:
> [English](README.md) | [简体中文](README_ZH.md)

---

## 🎯 Design Philosophy

| Aspect | Choice |
|--------|--------|
| Ore Multiplication Target | **16×+** (surpassing Mekanism's 5×) |
| Processing Style | Clean pipeline (early) → Quantum amplification (late) |
| Machine Upgrades | **Upgrade Cards** (speed / efficiency / output) |
| Machine Count | ~14 machines total |
| Texture Strategy | **ItemColor tinting** — 2 grayscale base textures × material colors = all dust/crystal variants |
| Complexity Philosophy | Complexity front-loaded into quantum chip production; no GregTech-style chemistry |

### Two-Stage Processing

```
Stage 1: Mekanism-Style Pipeline (1× → 8×)
┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐
│ Crusher  │──→│Ore Washer│──→│Crystalliz│──→│Centrifuge│──→│Purificat │──→│Compressor│──→│Induction │
│   2×     │   │   3×     │   │   4×     │   │   5×     │   │   6×     │   │   7×     │   │   8×     │
└──────────┘   └──────────┘   └──────────┘   └──────────┘   └──────────┘   └──────────┘   └──────────┘

Stage 2: Quantum Multiplication (8× → 16×+)
┌─────────────────────────────────────────────────────────────┐
│  Particle Accelerator → Isotope Separator → Neutron Irradiator  │
│              ↓                                                  │
│  Wafer Etcher → Quantum Entangler → Chip Encapsulator          │
│              ↓                                                  │
│     Quantum Chip (64 durability) → Quantum Multiplier           │
└─────────────────────────────────────────────────────────────┘
```

---

## 🚧 Development Status & Warning

**⚠️ STOP — READ THIS BEFORE DOWNLOADING**

**Current version: `0.0.2-alpha` (ALPHA — DO NOT PLAY)**

Versions starting with **`0.x.x`** are **ALPHA / EXPERIMENTAL** and **NOT INTENDED FOR PLAY**.
* These builds have **known and unknown bugs** that may crash your game or **corrupt your world saves**.
* Machines, recipes, and world generation are incomplete and subject to breaking changes between versions.
* **No backward compatibility is guaranteed** for 0.x.x worlds — future versions may break everything.
* There is **no survival balance** — energy costs, processing times, and recipes are arbitrary.

**DO NOT use any 0.x.x version in:**
- Survival worlds
- Multiplayer servers
- Modpacks
- Any world you care about

**Wait for `1.0.0`** for a stable, balanced, and tested release.

---

## 🗺️ Development Roadmap

### Phase 1: Foundation (MVP) — `0.0.x` ✅ COMPLETE

| System | Details |
|--------|---------|
| **Energy** | Botarium `SimpleEnergyContainer` — capacity 20k–40k FE per machine, redstone as fuel |
| **Recipe Framework** | `MachineRecipe` type — single input → single output, JSON-defined, processing time + energy cost |
| **Upgrade Cards** | Speed (halves processing time), Efficiency (40% less energy), Output (25% bonus item) |
| **Lanthanum Ore** | Stone + deepslate variants, world generation Y=-32~64 via Forge tags and `BiomeModifications` |
| **Crusher** | Ore → 2× dust, 200t/32FE |
| **Ore Washer** | Dust → washed dust, 150t/16FE |
| **Crystallizer** | Washed dust → 2× crystals, 300t/64FE |
| **Ore Compatibility** | Tag-based recipes (`forge:ores/iron` etc.) — any mod's iron/gold/copper ore → 2× dust |
| **Material System** | ItemColor tinting: 2 grayscale base textures tinted for iron/gold/copper/lanthanum (12 items) |
| **Forge Tags** | Full tag suite: `forge:ores/*`, `forge:dusts/*`, `forge:crystals/*`, `forge:ingots/*`, `forge:raw_materials/*` |
| **GUI** | 176×166 vanilla furnace-style, energy bar + progress arrow |
| **Architecture** | `BaseMachineBlockEntity` — shared abstract base, new machines add ~40 lines each |
| **Cross-Platform** | Architectury: Fabric + Forge, Mojang Mappings, Java 17 |
| **Languages** | en_us, zh_cn |

### Phase 2: Complete Pipeline — `0.1.x`
Complete all 7 pipeline machines to achieve the full 8× processing chain.

- [ ] **Centrifuge** — crystals → 5× enriched fragments
- [ ] **Purification Furnace** — fragments → 6× high-purity powder
- [ ] **Compressor** — powder → 7× compressed billet
- [ ] **Induction Smelter** — billet → 8× ingot
- [ ] Fluid integration for water-requiring machines (Botarium Fluid API)
- [ ] JEI/REI recipe viewer compatibility
- [ ] Jade/WTHIT tooltip compatibility
- [ ] Cloth Config integration
- [ ] Forge energy capability registration

### Phase 3: Quantum Age — `0.2.x`
Introduce the particle accelerator chain and quantum chip system for 16×+ multiplication.

- [ ] **Particle Accelerator** (linear radiation zone)
- [ ] **Isotope Separator**
- [ ] **Neutron Irradiator** (spherical radiation zone)
- [ ] **Wafer Etcher**
- [ ] **Quantum Entangler** (weak radiation zone)
- [ ] **Chip Encapsulator**
- [ ] **Quantum Multiplier** — consumes Quantum Chip to amplify 8× → 16×+
- [ ] Radiation system — area damage near specific accelerator machines
- [ ] Radiation protection gear
- [ ] Uranium ore + world generation (for neutron source material)

---

## 📥 Installation

This mod requires the following dependencies:

### For Fabric Users
1. [Fabric Loader](https://fabricmc.net/)
2. [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3. [Architectury API (Fabric)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)
4. [Botarium](https://modrinth.com/mod/botarium)

### For Forge Users
1. [Minecraft Forge](https://files.minecraftforge.net/)
2. [Architectury API (Forge)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)
3. [Botarium](https://modrinth.com/mod/botarium)

---

## ✨ Current Features (Phase 1)

### Ores & Materials
* **Lanthanum Ore** — Rare-earth mineral spawning at Y=-32~64, the foundation of all mod technology
* **4-Material Dust/Crystal System** — Iron, Gold, Copper, and Lanthanum each have dust, washed dust, and crystal forms
* **ItemColor Tinting** — 2 shared grayscale base textures, tinted per material on the client (saves ~10 textures)

### Machines
* **Crusher** — Doubles ore output (ore → 2× dust). Processes ANY ore using Forge tags
* **Ore Washer** — Refines dust into washed dust, preparing it for crystal growth
* **Crystallizer** — Grows washed dust into 2× crystals, achieving 4× total ore multiplication
* **Standard GUI** — 176×166 vanilla furnace-style interface with energy bar + progress arrow

### Upgrade Cards
* **Speed Card** — Halves processing time
* **Efficiency Card** — Reduces energy consumption by 40%
* **Output Card** — 25% chance of bonus output per operation

### Cross-Mod Compatibility
* **Forge Tags** — Full material tag coverage for lanthanum ores, ingots, dusts, and crystals
* **Tag-Based Recipes** — Vanilla/modded iron, gold, and copper ores can all be processed through the Crusher
* **Hopper I/O** — Directional: sides insert to input, bottom extracts from output
* **Energy** — Botarium `SimpleEnergyContainer` internally; Forge capability registration planned for Phase 2

---

## 🤝 Contributing

We welcome contributions! Whether you are a developer, artist, or translator, feel free to help improve Lanthanum.

### Branching Strategy
We follow the **GitHub Flow**.
* All Pull Requests should be directed to the **`master`** branch.
* Our CI/CD pipeline will automatically test your PR. **Do not merge until checks pass.**

### Submission Guidelines

#### 1. Issues
Before submitting an issue, please check if it already exists. Use the provided **Issue Templates** and apply correct labels:
* `type: bug` — For crashes or logic errors.
* `type: feature` — For new ideas.
* `platform: fabric` / `platform: forge` — If the issue is specific to one loader.

#### 2. Pull Requests (PRs)
* **Naming Convention**: We follow [Conventional Commits](https://www.conventionalcommits.org/). Your PR title must follow this format:
    * `feat(common): add crusher machine`
    * `fix(forge): resolve rendering crash`
    * `docs: update readme`
    * `assets: add texture for ore washer`
* **Scope (Optional)**: `common`, `fabric`, `forge`, `assets`, `data`.
* **Description**: Briefly explain your changes. If it fixes a bug, link the issue (e.g., `Closes #12`).

### Setup Development Environment
1. Clone the repository.
2. Run `./gradlew genSources` (or the specific task for your IDE).
3. Import the project into IntelliJ IDEA.

---

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.
