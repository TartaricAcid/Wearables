package net.gegy1000.wearables.client.render.component.head;

import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.head.Glasses1Model;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.util.ResourceLocation;

public class Glasses1Renderer extends ComponentRenderer {
    private static final Glasses1Model MODEL = new Glasses1Model();

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }

    @Override
    public float getInventoryScale() {
        return 2.5F;
    }

    @Override
    public float getInventoryOffsetX() {
        return -0.125F;
    }

    @Override
    public float getInventoryOffsetY() {
        return 0.25F;
    }

    @Override
    public float getInventoryOffsetZ() {
        return 0.0F;
    }

    @Override
    public float getFabricatorOffsetY() {
        return 0.25F;
    }

    @Override
    public float getFabricatorOffsetZ() {
        return -0.5F;
    }

    @Override
    public float getFabricatorScale() {
        return 0.9F;
    }
}
