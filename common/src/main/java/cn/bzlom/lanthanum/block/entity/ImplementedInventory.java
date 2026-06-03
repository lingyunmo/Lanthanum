package cn.bzlom.lanthanum.block.entity;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

/**
 * Simplified inventory implementation for machine block entities.
 * Implementing classes must provide {@link #getItems()}.
 */
public interface ImplementedInventory extends WorldlyContainer {

    /**
     * Return the backing item list for this inventory.
     */
    NonNullList<ItemStack> getItems();

    @Override
    default int[] getSlotsForFace(Direction side) {
        int size = getContainerSize();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i;
        }
        return result;
    }

    @Override
    default boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
        return canPlaceItem(slot, stack);
    }

    @Override
    default boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return true;
    }

    @Override
    default int getContainerSize() {
        return getItems().size();
    }

    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            if (!getItems().get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    default ItemStack getItem(int slot) {
        return getItems().get(slot);
    }

    @Override
    default ItemStack removeItem(int slot, int count) {
        NonNullList<ItemStack> items = getItems();
        ItemStack stack = items.get(slot);
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack result;
        if (stack.getCount() <= count) {
            result = stack.copy();
            items.set(slot, ItemStack.EMPTY);
        } else {
            result = stack.split(count);
        }
        setChanged();
        return result;
    }

    @Override
    default ItemStack removeItemNoUpdate(int slot) {
        NonNullList<ItemStack> items = getItems();
        ItemStack stack = items.get(slot);
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        items.set(slot, ItemStack.EMPTY);
        return stack;
    }

    @Override
    default void setItem(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > stack.getMaxStackSize()) {
            stack.setCount(stack.getMaxStackSize());
        }
        setChanged();
    }

    @Override
    default void clearContent() {
        getItems().clear();
    }

    @Override
    default boolean stillValid(Player player) {
        if (this instanceof BlockEntity be) {
            if (be.getLevel() == null) return false;
            return be.getLevel().getBlockEntity(be.getBlockPos()) == be
                    && player.distanceToSqr(
                        be.getBlockPos().getX() + 0.5,
                        be.getBlockPos().getY() + 0.5,
                        be.getBlockPos().getZ() + 0.5) <= 64.0;
        }
        return true;
    }

    @Override
    default void setChanged() {
        if (this instanceof BlockEntity be && be.getLevel() != null) {
            be.getLevel().sendBlockUpdated(be.getBlockPos(), be.getBlockState(), be.getBlockState(), 3);
            be.setChanged();
        }
    }
}
