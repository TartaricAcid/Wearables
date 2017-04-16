package net.gegy1000.wearables.client.render.component.head;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.head.TopHatModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.util.ResourceLocation;

public class TopHatRenderer extends ComponentRenderer {
    private static final TopHatModel MODEL = new TopHatModel();
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wearables.MODID, "textures/component/top_hat.png");
    private static final ResourceLocation TEXTURE_LINE = new ResourceLocation(Wearables.MODID, "textures/component/top_hat_line.png");

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return layer == 0 ? TEXTURE : TEXTURE_LINE;
    }

    @Override
    public float getInventoryOffsetY() {
        return 0.9F;
    }

    @Override
    public float getInventoryScale() {
        return 1.5F;
    }

    @Override
    public float getFabricatorOffsetY() {
        return 0.8F;
    }
}
