package net.gegy1000.wearables.server.wearable.component.head;

import net.gegy1000.wearables.client.model.component.hat.HatModel;
import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HatComponent extends WearableComponentType {
    @SideOnly(Side.CLIENT)
    private static final HatModel MODEL = new HatModel();

    @Override
    public String getIdentifier() {
        return "hat";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.HEAD_TOP;
    }

    @Override
    public int getLayerCount() {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getModel(boolean smallArms) {
        return MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }

    @Override
    public float getInventoryOffsetY() {
        return 0.8F;
    }
}
