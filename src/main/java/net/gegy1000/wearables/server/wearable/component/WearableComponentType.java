package net.gegy1000.wearables.server.wearable.component;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class WearableComponentType {
    public abstract String getIdentifier();

    public abstract WearableCategory getCategory();

    public abstract int getLayerCount();

    @SideOnly(Side.CLIENT)
    public abstract ModelBiped getModel(boolean smallArms);

    @SideOnly(Side.CLIENT)
    public abstract ResourceLocation getTexture(boolean smallArms, int layer);

    public float getInventoryOffsetX() {
        return 0.0F;
    }

    public float getInventoryOffsetY() {
        return 0.0F;
    }

    public float getInventoryOffsetZ() {
        return 0.0F;
    }

    public boolean compatibleWith(WearableComponentType component) {
        return component.getCategory() != this.getCategory();
    }
}
