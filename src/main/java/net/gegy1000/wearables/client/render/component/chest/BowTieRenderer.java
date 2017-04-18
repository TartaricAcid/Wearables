package net.gegy1000.wearables.client.render.component.chest;

import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.gegy1000.wearables.client.model.component.head.BowTieModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class BowTieRenderer extends ComponentRenderer {
    private static final BowTieModel MODEL = new BowTieModel();

    @Override
    public WearableComponentModel getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }

    @Override
    public AxisAlignedBB getBounds() {
        return new AxisAlignedBB(-0.2F, 0.15F, 0.2F, 0.2F, -0.2F, 0.1F);
    }
}
