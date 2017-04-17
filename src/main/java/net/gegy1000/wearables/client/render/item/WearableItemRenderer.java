package net.gegy1000.wearables.client.render.item;

import net.gegy1000.wearables.client.render.ComponentInventoryRenderer;
import net.gegy1000.wearables.client.render.RenderRegistry;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.gegy1000.wearables.server.block.entity.wearable.WearableItemEntity;
import net.gegy1000.wearables.server.core.WearablesClientHooks;
import net.gegy1000.wearables.server.item.WearableItem;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.AxisAlignedBB;

public class WearableItemRenderer extends TileEntitySpecialRenderer<WearableItemEntity> {
    @Override
    public void renderTileEntityAt(WearableItemEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        float scale = 1.0F;
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.translate(0.0F, 1.0F, 0.0F);
        Wearable wearable = WearableItem.getWearable(WearablesClientHooks.getRenderedStack());
        AxisAlignedBB union = null;
        for (WearableComponent component : wearable.getComponents()) {
            ComponentRenderer renderer = RenderRegistry.getRenderer(component.getType().getIdentifier());
            if (union == null) {
                union = renderer.getBounds();
            } else {
                union = union.union(renderer.getBounds());
            }
        }
        if (union != null && Minecraft.getMinecraft().getRenderManager().isDebugBoundingBox()) {
            GlStateManager.disableLighting();
            GlStateManager.disableTexture2D();
            RenderGlobal.drawSelectionBoundingBox(union, 1.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
        }
        for (WearableComponent component : wearable.getComponents()) {
            ComponentInventoryRenderer.renderSingleComponent(component);
        }
        GlStateManager.popMatrix();
    }
}
