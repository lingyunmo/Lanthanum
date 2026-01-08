# Lanthanum

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20.1-green?style=flat-square)
![Architectury](https://img.shields.io/badge/Built%20with-Architectury-blue?style=flat-square)
![CI Status](https://img.shields.io/github/actions/workflow/status/lingyunmo/Lanthanum/Build_master.yml?style=flat-square&label=Build)
![License](https://img.shields.io/github/license/lingyunmo/Lanthanum?style=flat-square)

**Lanthanum** is a modern tech mod for Minecraft 1.20.1, built using the Architectury API to support both **Fabric** and **Forge**. It introduces new materials, processing mechanics, and a unique dimension, aiming to expand the technological horizon of your world.

> 🌏 **Language / 语言选择**:
> [English](README.md) | [简体中文](README_ZH.md)

---

## 🚧 Development Status & Warning

**⚠️ PLEASE READ BEFORE DOWNLOADING**

Versions starting with **`0.x.x`** (e.g., `0.0.1`) are considered **ALPHA / EXPERIMENTAL**.
* These builds are **untested** and meant for development purposes only.
* They may contain severe bugs, cause game crashes, or corrupt world saves.
* They may lack essential dependencies or recipes.

**Do not use `0.x.x` versions in survival worlds or modpacks.** Only use stable releases (starting from `1.0.0`).

---

## 📥 Installation

This mod requires the following dependencies to run:

### For Fabric Users
1.  [Fabric Loader](https://fabricmc.net/)
2.  [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3.  [Architectury API (Fabric)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)

### For Forge Users
1.  [Minecraft Forge](https://files.minecraftforge.net/)
2.  [Architectury API (Forge)](https://www.curseforge.com/minecraft/mc-mods/architectury-api)

---

## ✨ Features (Planned/WIP)

* **New Resources**: Lanthanum Ore and Gemstones.
* **Processing**: Advanced refining mechanics to multiply ore outputs.
* **Cross-Platform**: Fully compatible with both Fabric and Forge loaders.

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
* `type: bug` - For crashes or logic errors.
* `type: feature` - For new ideas.
* `platform: fabric` / `platform: forge` - If the issue is specific to one loader.

#### 2. Pull Requests (PRs)
* **Naming Convention**: We follow [Conventional Commits](https://www.conventionalcommits.org/). Your PR title must follow this format:
    * `feat(common): add lanthanum ingot`
    * `fix(forge): resolve rendering crash`
    * `docs: update readme`
    * `assets: add texture for machine`
* **Scope (Optional)**: `common`, `fabric`, `forge`, `assets`, `data`.
* **Description**: Briefly explain your changes. If it fixes a bug, link the issue (e.g., `Closes #12`).

### Setup Development Environment
1.  Clone the repository.
2.  Run `./gradlew genSources` (or the specific task for your IDE).
3.  Import the project into IntelliJ IDEA.

---

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.