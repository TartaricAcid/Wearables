package net.gegy1000.wearables.server.block.entity.machine;

import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class WearableFabricatorEntity extends MachineBlockEntity implements ITickable {
    private WearableComponentType selectedComponent;

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("tile.wearable_fabricator.name");
    }

    @Override
    public int getSlotCount() {
        return 5;
    }

    @Override
    public void update() {
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        if (this.selectedComponent != null) {
            compound.setString("selected_component", this.selectedComponent.getIdentifier());
        }
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("selected_component")) {
            this.selectedComponent = ComponentRegistry.get(compound.getString("selected_component"));
        } else {
            this.selectedComponent = null;
        }
    }

    public void setSelectedComponent(WearableComponentType selectedComponent) {
        this.selectedComponent = selectedComponent;
    }

    public WearableComponentType getSelectedComponent() {
        return this.selectedComponent;
    }
}
