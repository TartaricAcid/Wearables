package net.gegy1000.wearables.client.render.component.head;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.head.Retro3DGlassesModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.util.ResourceLocation;

public class Retro3DGlassesRenderer extends ComponentRenderer {
    private static final Retro3DGlassesModel MODEL = new Retro3DGlassesModel();
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wearables.MODID, "textures/component/retro_3d_glasses.png");
    private static final ResourceLocation TEXTURE_COLOUR = new ResourceLocation(Wearables.MODID, "textures/component/retro_3d_glasses_colour.png");

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return layer == 0 ? TEXTURE : TEXTURE_COLOUR;
    }

    @Override
    public float getInventoryScale() {
        return 2.6F;
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
    public float getFabricatorOffsetY() {
        return 0.25F;
    }

    @Override
    public float getFabricatorOffsetZ() {
        return -0.5F;
    }

    @Override
    public float getFabricatorScale() {
        return 0.7F;
    }
}
