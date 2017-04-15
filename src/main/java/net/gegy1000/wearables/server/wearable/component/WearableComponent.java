package net.gegy1000.wearables.server.wearable.component;

import net.gegy1000.wearables.client.WearableColourUtils;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class WearableComponent implements INBTSerializable<NBTTagCompound> {
    private WearableComponentType type;
    private int[] colours;

    public WearableComponent(WearableComponentType type) {
        this.type = type;
        this.colours = new int[type.getLayerCount()];
        int colour = WearableColourUtils.fromRGBFloatArray(EntitySheep.getDyeRgb(EnumDyeColor.WHITE));
        for (int layer = 0; layer < this.colours.length; layer++) {
            this.colours[layer] = colour;
        }
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
        if (compound.hasKey("identifier")) {
            this.type = ComponentRegistry.get(compound.getString("identifier"));
        } else {
            this.type = ComponentRegistry.getDefault();
        }
        if (compound.hasKey("colour_layers")) {
            this.colours = compound.getIntArray("colour_layers");
        } else {
            this.colours = new int[this.type.getLayerCount()];
            int colour = WearableColourUtils.fromRGBFloatArray(EntitySheep.getDyeRgb(EnumDyeColor.WHITE));
            for (int layer = 0; layer < this.colours.length; layer++) {
                this.colours[layer] = colour;
            }
        }
    }

    public static WearableComponent deserialize(NBTTagCompound compound) {
        WearableComponent component = new WearableComponent();
        component.deserializeNBT(compound);
        return component;
    }
}
