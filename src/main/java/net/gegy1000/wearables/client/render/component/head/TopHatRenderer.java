package net.gegy1000.wearables.client.render.component.head;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.head.TopHatModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

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
    public float getInventoryScale(ItemCameraTransforms.TransformType type) {
        return 1.5F;
    }

    @Override
    public Vec3d getInventoryOffset(ItemCameraTransforms.TransformType type) {
        if (type == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || type == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
            return new Vec3d(0.0F, 0.9F, -0.3F);
        }
        return new Vec3d(0.0F, 0.9F, 0.0F);
    }

    @Override
    public float getFabricatorOffsetY() {
        return 0.8F;
    }
}
