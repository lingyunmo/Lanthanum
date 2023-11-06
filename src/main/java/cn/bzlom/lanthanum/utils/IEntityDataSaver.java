package cn.bzlom.lanthanum.utils;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
    NbtCompound getPersistentData();
}
