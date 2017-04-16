package net.gegy1000.wearables.client.render.component;

import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ComponentRenderer {
    public abstract WearableComponentModel getModel(boolean smallArms);

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

    public float getInventoryScale() {
        return 1.0F;
    }

    public float getFabricatorOffsetX() {
        return 0.0F;
    }

    public float getFabricatorOffsetY() {
        return 0.0F;
    }

    public float getFabricatorOffsetZ() {
        return 0.0F;
    }

    public float getFabricatorScale() {
        return 1.0F;
    }

    public float[] adjustColour(float[] colour, int layer) {
        return colour;
    }
}
