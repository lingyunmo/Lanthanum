package cn.bzlom.lanthanum.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpgradeCard extends Item {

    public enum Type {
        SPEED("speed", ChatFormatting.GOLD),
        EFFICIENCY("efficiency", ChatFormatting.GREEN),
        OUTPUT("output", ChatFormatting.BLUE);

        public final String id;
        public final ChatFormatting color;

        Type(String id, ChatFormatting color) {
            this.id = id;
            this.color = color;
        }
    }

    private final Type cardType;

    public UpgradeCard(Type cardType, Properties properties) {
        super(properties);
        this.cardType = cardType;
    }

    public Type getCardType() {
        return cardType;
    }

    /**
     * Get the speed multiplier from a speed upgrade card.
     * Returns 1.0 if no card is present (no bonus).
     * 0.5 means the machine runs at 2x speed (halves processing time).
     */
    public static float getSpeedBonus(ItemStack stack) {
        if (stack.getItem() instanceof UpgradeCard card && card.cardType == Type.SPEED) {
            return 0.5f;
        }
        return 1.0f;
    }

    /**
     * Get the energy efficiency multiplier. Lower = less energy used.
     */
    public static float getEfficiencyBonus(ItemStack stack) {
        if (stack.getItem() instanceof UpgradeCard card && card.cardType == Type.EFFICIENCY) {
            return 0.6f; // 40% less energy
        }
        return 1.0f;
    }

    /**
     * Get the output bonus multiplier. >1.0 means chance of extra output.
     */
    public static float getOutputBonus(ItemStack stack) {
        if (stack.getItem() instanceof UpgradeCard card && card.cardType == Type.OUTPUT) {
            return 0.25f; // 25% chance of bonus output
        }
        return 0.0f;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.lanthanum.upgrade_card." + cardType.id).withStyle(cardType.color));
    }
}
