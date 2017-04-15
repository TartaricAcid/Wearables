package net.gegy1000.wearables.client.render.component;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ComponentRenderer {
    public abstract ModelBiped getModel(boolean smallArms);

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

    public float[] adjustColour(float[] colour, int layer) {
        return colour;
    }
}
