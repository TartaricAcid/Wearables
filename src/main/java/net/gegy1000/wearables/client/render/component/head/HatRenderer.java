package net.gegy1000.wearables.client.render.component.head;

import net.gegy1000.wearables.client.WearableColourUtils;
import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.hat.HatModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.util.ResourceLocation;

public class HatRenderer extends ComponentRenderer {
    private static final HatModel MODEL = new HatModel();

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }

    @Override
    public float getInventoryOffsetY() {
        return 0.8F;
    }

    @Override
    public float getInventoryScale() {
        return 1.5F;
    }

    @Override
    public float[] adjustColour(float[] colour, int layer) {
        return WearableColourUtils.blend(colour, new float[] { 0.6F, 0.35F, 0.0F }, 0.5F);
    }
}
