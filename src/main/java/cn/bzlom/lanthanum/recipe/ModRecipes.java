package cn.bzlom.lanthanum.recipe;


import cn.bzlom.lanthanum.Lanthanum;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Lanthanum.MOD_ID,
                LanthanumRefinerRecipe.Serializer.ID), LanthanumRefinerRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Lanthanum.MOD_ID, LanthanumRefinerRecipe.Type.ID),
                LanthanumRefinerRecipe.Type.INSTANCE);
    }
}
