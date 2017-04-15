package net.gegy1000.wearables.client.render.component.chest;

import net.gegy1000.wearables.client.model.component.chest.PlainShirtModel;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;

public class PlainShirtRenderer extends ComponentRenderer {
    private static final PlainShirtModel LARGE_ARMS = new PlainShirtModel(false);
    private static final PlainShirtModel SMALL_ARMS = new PlainShirtModel(true);

    @Override
    public ModelBiped getModel(boolean smallArms) {
        return smallArms ? SMALL_ARMS : LARGE_ARMS;
    }

    @Override
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }
}
