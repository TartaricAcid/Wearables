package net.gegy1000.wearables.server.wearable.component;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class WearableComponent implements INBTSerializable<NBTTagCompound> {
    private WearableComponentType type;
    private int[] colours;

    public WearableComponent(WearableComponentType type) {
        this.type = type;
        this.colours = new int[type.getLayerCount()];
    }

    private WearableComponent() {
    }

    public void setColour(int layer, int colour) {
        this.colours[layer] = colour;
    }

    public WearableComponentType getType() {
        return this.type;
    }

    public int[] getColours() {
        return this.colours;
    }

    public int getColour(int layer) {
        return this.colours[layer];
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("identifier", this.type.getIdentifier());
        compound.setIntArray("colour_layers", this.colours);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        this.type = ComponentRegistry.get(compound.getString("identifier"));
        this.colours = compound.getIntArray("colour_layers");
    }

    public static WearableComponent deserialize(NBTTagCompound compound) {
        WearableComponent component = new WearableComponent();
        component.deserializeNBT(compound);
        return component;
    }
}
