package cn.bzlom.lanthanum.recipe;

import cn.bzlom.lanthanum.Lanthanum;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.RECIPE_SERIALIZER);

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Lanthanum.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeSerializer<MachineRecipe>> MACHINE_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register(MachineRecipe.Serializer.ID,
                    () -> MachineRecipe.Serializer.INSTANCE);

    public static final RegistrySupplier<RecipeType<MachineRecipe>> MACHINE_RECIPE_TYPE =
            RECIPE_TYPES.register(MachineRecipe.Type.ID,
                    () -> MachineRecipe.Type.INSTANCE);
}
