package cn.bzlom.lanthanum.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

/**
 * Generic machine recipe with one input and one output.
 * Processing time and energy cost are configured per recipe.
 */
public class MachineRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final int processingTime;
    private final int energyCost;

    public MachineRecipe(ResourceLocation id, Ingredient input, ItemStack output,
                         int processingTime, int energyCost) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.processingTime = processingTime;
        this.energyCost = energyCost;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return input.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(input);
        return list;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    // ── Recipe Type ───────────────────────────────────────
    public static class Type implements RecipeType<MachineRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "machine_recipe";
    }

    // ── Serializer ────────────────────────────────────────
    public static class Serializer implements RecipeSerializer<MachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "machine_recipe";

        @Override
        public MachineRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.get("ingredient"));
            ItemStack output = ShapedRecipe.itemStackFromJson(
                    GsonHelper.getAsJsonObject(json, "result"));
            int processingTime = GsonHelper.getAsInt(json, "processingTime", 200);
            int energyCost = GsonHelper.getAsInt(json, "energyCost", 6400);
            return new MachineRecipe(id, input, output, processingTime, energyCost);
        }

        @Override
        public MachineRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            int processingTime = buf.readVarInt();
            int energyCost = buf.readVarInt();
            return new MachineRecipe(id, input, output, processingTime, energyCost);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MachineRecipe recipe) {
            recipe.input.toNetwork(buf);
            buf.writeItem(recipe.output);
            buf.writeVarInt(recipe.processingTime);
            buf.writeVarInt(recipe.energyCost);
        }
    }
}
