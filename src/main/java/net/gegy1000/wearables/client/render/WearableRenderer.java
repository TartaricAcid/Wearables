package net.gegy1000.wearables.client.render;

import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WearableRenderer {
    public static final Minecraft MC = Minecraft.getMinecraft();

    public static void renderComponent(WearableComponent component, boolean smallArms) {
        WearableComponentType type = component.getType();
        for (int layer = 0; layer < type.getLayerCount(); layer++) {
            ResourceLocation texture = type.getTexture(smallArms, layer);
            if (texture == null) {
                GlStateManager.disableTexture2D();
            } else {
                GlStateManager.enableTexture2D();
                MC.getTextureManager().bindTexture(texture);
            }
            ModelBiped model = type.getModel(smallArms);
            float[] colour = WearableUtils.toRGBFloatArray(component.getColour(layer));
            GlStateManager.color(colour[0], colour[1], colour[2], 1.0F);
            GlStateManager.pushMatrix();
            GlStateManager.translate(type.getInventoryOffsetX(), type.getInventoryOffsetY(), type.getInventoryOffsetZ());
            model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GlStateManager.popMatrix();
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableTexture2D();
    }
}
