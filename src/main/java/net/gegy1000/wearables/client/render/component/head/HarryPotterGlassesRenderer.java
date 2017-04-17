package net.gegy1000.wearables.client.render.component.head;

import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.head.HarryPotterGlassesModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class HarryPotterGlassesRenderer extends ComponentRenderer {
    private static final HarryPotterGlassesModel MODEL = new HarryPotterGlassesModel();

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }

    @Override
    public float getInventoryScale(ItemCameraTransforms.TransformType type) {
        return 2.5F;
    }

    @Override
    public Vec3d getInventoryOffset(ItemCameraTransforms.TransformType type) {
        if (type == ItemCameraTransforms.TransformType.GUI) {
            return new Vec3d(-0.125F, 0.25F, 0.0F);
        }
        return new Vec3d(0.0F, 0.25F, 0.0F);
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