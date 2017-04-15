package net.gegy1000.wearables.client.render.layer;

import net.gegy1000.wearables.client.WearableColourUtils;
import net.gegy1000.wearables.server.item.WearableItem;
import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WearableRenderLayer implements LayerRenderer<EntityPlayer> {
    private static final Minecraft MC = Minecraft.getMinecraft();

    private RenderPlayer renderer;

    public WearableRenderLayer(RenderPlayer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale) {
        this.renderPiece(EntityEquipmentSlot.HEAD, player, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
        this.renderPiece(EntityEquipmentSlot.CHEST, player, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
        this.renderPiece(EntityEquipmentSlot.LEGS, player, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
        this.renderPiece(EntityEquipmentSlot.FEET, player, limbSwing, limbSwingAmount, partialTicks, age, yaw, pitch, scale);
    }

    private void renderPiece(EntityEquipmentSlot slot, EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale) {
        ItemStack stack = player.getItemStackFromSlot(slot);
        if (!stack.isEmpty() && stack.getItem() instanceof WearableItem) {
            WearableItem item = (WearableItem) stack.getItem();
            if (item.getEquipmentSlot() == slot) {
                Wearable wearable = WearableItem.getWearable(stack);
                for (WearableComponent component : wearable.getComponents()) {
                    WearableComponentType componentType = component.getType();
                    boolean smallArms = WearableUtils.hasSlimArms(player);
                    ModelBiped model = componentType.getModel(smallArms);
                    model.setModelAttributes(this.renderer.getMainModel());
                    model.setLivingAnimations(player, limbSwing, limbSwingAmount, partialTicks);
                    ModelBiped.copyModelAngles(this.renderer.getMainModel().bipedRightArm, model.bipedRightArm);
                    for (int layer = 0; layer < componentType.getLayerCount(); layer++) {
                        ResourceLocation texture = componentType.getTexture(smallArms, layer);
                        if (texture == null) {
                            GlStateManager.disableTexture2D();
                        } else {
                            GlStateManager.enableTexture2D();
                            MC.getTextureManager().bindTexture(texture);
                        }
                        WearableColourUtils.color(component.getColour(layer));
                        model.render(player, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
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
