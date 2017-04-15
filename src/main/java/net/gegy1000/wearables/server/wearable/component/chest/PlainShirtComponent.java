package net.gegy1000.wearables.server.wearable.component.chest;

import net.gegy1000.wearables.client.model.component.chest.PlainShirtModel;
import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlainShirtComponent extends WearableComponentType {
    @SideOnly(Side.CLIENT)
    private static final PlainShirtModel LARGE_ARMS = new PlainShirtModel(false);

    @SideOnly(Side.CLIENT)
    private static final PlainShirtModel SMALL_ARMS = new PlainShirtModel(true);

    @Override
    public String getIdentifier() {
        return "plain_shirt";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.CHEST_GENERIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getModel(boolean smallArms) {
        return smallArms ? SMALL_ARMS : LARGE_ARMS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(boolean smallArms, int layer) {
        return null;
    }
}
