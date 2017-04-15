package net.gegy1000.wearables.server.wearable;

import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class Wearable implements INBTSerializable<NBTTagCompound> {
    private List<WearableComponent> components = new ArrayList<>();

    public void addComponent(WearableComponent component) {
        this.components.add(component);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList componentList = new NBTTagList();
        for (WearableComponent component : this.components) {
            componentList.appendTag(component.serializeNBT());
        }
        compound.setTag("components", componentList);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        NBTTagList componentList = compound.getTagList("components", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < componentList.tagCount(); i++) {
            this.components.add(WearableComponent.deserialize(componentList.getCompoundTagAt(i)));
        }
    }

    public static Wearable deserialize(NBTTagCompound compound) {
        Wearable wearable = new Wearable();
        wearable.deserializeNBT(compound);
        return wearable;
    }
}
