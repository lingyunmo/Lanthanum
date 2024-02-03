package cn.bzlom.lanthanum.screen.renderer;

import net.minecraft.client.util.math.Rect2i;
import net.minecraft.text.Text;
import team.reborn.energy.api.EnergyStorage;

import java.util.List;

public class EnergyInfoArea extends InfoArea {
    private final EnergyStorage energyStorage;

    public EnergyInfoArea(int xMin, int yMin) {
        this(xMin, yMin, null, 8, 64);
    }

    public EnergyInfoArea(int xMin, int yMin, EnergyStorage energyStorage) {
        this(xMin, yMin, energyStorage, 8, 64);
    }

    public EnergyInfoArea(int xMin, int yMin, EnergyStorage energyStorage, int width, int height) {
        super(new Rect2i(xMin, yMin, width, height));
        this.energyStorage = energyStorage;
    }

    public List<Text> getTooltips() {
        return List.of(Text.literal(energyStorage.getAmount() + "/" + energyStorage.getCapacity() + "E"));
    }
}