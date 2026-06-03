package cn.bzlom.lanthanum.registry;

import cn.bzlom.lanthanum.Lanthanum;
import cn.bzlom.lanthanum.block.CrusherBlock;
import cn.bzlom.lanthanum.block.CrystallizerBlock;
import cn.bzlom.lanthanum.block.OreWasherBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.BLOCK);

    // ── Lanthanum ores ──────────────────────────────────
    public static final RegistrySupplier<Block> LANTHANUM_ORE = BLOCKS.register("lanthanum_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(3.0f, 3.0f)
                            .sound(SoundType.STONE),
                    UniformInt.of(3, 7)
            ));

    public static final RegistrySupplier<Block> DEEPSLATE_LANTHANUM_ORE = BLOCKS.register("deepslate_lanthanum_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.DEEPSLATE)
                            .requiresCorrectToolForDrops()
                            .strength(4.5f, 3.0f)
                            .sound(SoundType.DEEPSLATE),
                    UniformInt.of(3, 7)
            ));

    // ── Lanthanum storage block ─────────────────────────
    public static final RegistrySupplier<Block> LANTHANUM_BLOCK = BLOCKS.register("lanthanum_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops()
                            .strength(5.0f, 6.0f)
                            .sound(SoundType.METAL)
            ));

    // ── Machines ─────────────────────────────────────────
    public static final RegistrySupplier<Block> CRUSHER = BLOCKS.register("crusher",
            () -> new CrusherBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops()
                            .strength(3.5f, 3.5f)
                            .sound(SoundType.METAL)
                            .noOcclusion()
            ));

    public static final RegistrySupplier<Block> ORE_WASHER = BLOCKS.register("ore_washer",
            () -> new OreWasherBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops()
                            .strength(3.5f, 3.5f)
                            .sound(SoundType.METAL)
                            .noOcclusion()
            ));

    public static final RegistrySupplier<Block> CRYSTALLIZER = BLOCKS.register("crystallizer",
            () -> new CrystallizerBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops()
                            .strength(3.5f, 3.5f)
                            .sound(SoundType.METAL)
                            .noOcclusion()
            ));
}
