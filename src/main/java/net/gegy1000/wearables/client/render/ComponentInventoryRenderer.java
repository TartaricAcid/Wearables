package net.gegy1000.wearables.client.render;

import net.gegy1000.wearables.client.WearableColourUtils;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ComponentInventoryRenderer {
    public static final Minecraft MC = Minecraft.getMinecraft();

    public static void renderComponent(WearableComponent component, boolean smallArms) {
        WearableComponentType type = component.getType();
        ComponentRenderer renderer = RenderRegistry.getRenderer(type.getIdentifier());
        for (int layer = 0; layer < type.getLayerCount(); layer++) {
            ResourceLocation texture = renderer.getTexture(smallArms, layer);
            if (texture == null) {
                GlStateManager.disableTexture2D();
            } else {
                GlStateManager.enableTexture2D();
                MC.getTextureManager().bindTexture(texture);
            }
            ModelBiped model = renderer.getModel(smallArms);
            float[] colour = renderer.adjustColour(WearableColourUtils.toRGBFloatArray(component.getColour(layer)), layer);
            GlStateManager.pushMatrix();
            GlStateManager.color(colour[0], colour[1], colour[2], 1.0F);
            float scale = renderer.getInventoryScale();
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.translate(renderer.getInventoryOffsetX(), renderer.getInventoryOffsetY(), renderer.getInventoryOffsetZ());
            model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GlStateManager.popMatrix();
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableTexture2D();
    }
}
