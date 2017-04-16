package net.gegy1000.wearables.client.render.layer;

import net.gegy1000.wearables.client.WearableColourUtils;
import net.gegy1000.wearables.client.render.RenderRegistry;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.gegy1000.wearables.server.item.WearableItem;
import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WearableRenderLayer implements LayerRenderer<EntityLivingBase> {
    private static final Minecraft MC = Minecraft.getMinecraft();

    private RenderLivingBase renderer;

    public WearableRenderLayer(RenderLivingBase renderer) {
        this.renderer = renderer;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale) {
        GlStateManager.enableRescaleNormal();
        this.renderPiece(EntityEquipmentSlot.HEAD, entity, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
        this.renderPiece(EntityEquipmentSlot.CHEST, entity, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
        this.renderPiece(EntityEquipmentSlot.LEGS, entity, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
        this.renderPiece(EntityEquipmentSlot.FEET, entity, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
    }

    private void renderPiece(EntityEquipmentSlot slot, EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale) {
        ItemStack stack = entity.getItemStackFromSlot(slot);
        if (!stack.isEmpty() && stack.getItem() instanceof WearableItem) {
            WearableItem item = (WearableItem) stack.getItem();
            if (item.getEquipmentSlot() == slot) {
                Wearable wearable = WearableItem.getWearable(stack);
                for (WearableComponent component : wearable.getComponents()) {
                    WearableComponentType componentType = component.getType();
                    ComponentRenderer renderer = RenderRegistry.getRenderer(componentType.getIdentifier());
                    boolean smallArms = WearableUtils.hasSlimArms(entity);
                    ModelBiped model = renderer.getModel(smallArms);
                    model.setModelAttributes(this.renderer.getMainModel());
                    model.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
                    for (int layer = 0; layer < componentType.getLayerCount(); layer++) {
                        ResourceLocation texture = renderer.getTexture(smallArms, layer);
                        if (texture == null) {
                            GlStateManager.disableTexture2D();
                        } else {
                            GlStateManager.enableTexture2D();
                            MC.getTextureManager().bindTexture(texture);
                        }
                        float[] colour = renderer.adjustColour(WearableColourUtils.toRGBFloatArray(component.getColour(layer)), layer);
                        GlStateManager.color(colour[0], colour[1], colour[2], 1.0F);
                        model.render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
                    }
                }
                GlStateManager.enableTexture2D();
            }
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
