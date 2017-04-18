package net.gegy1000.wearables.client.render.component.chest;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.chest.Shirt1Model;
import net.gegy1000.wearables.client.model.component.chest.Shirt1ThinModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class Shirt1Renderer extends ComponentRenderer {
    private static final Shirt1Model MODEL = new Shirt1Model();
    private static final Shirt1ThinModel THIN_MODEL = new Shirt1ThinModel();
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wearables.MODID, "textures/component/shirt.png");

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return smallArms ? THIN_MODEL : MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return TEXTURE;
    }

    @Override
    public AxisAlignedBB getBounds() {
        return new AxisAlignedBB(-0.6F, 0.8F, 0.4F, 0.6F, -0.2F, -0.4F);
    }
}
