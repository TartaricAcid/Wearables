package net.gegy1000.wearables.client.render.item;

import net.gegy1000.wearables.client.render.WearableRenderer;
import net.gegy1000.wearables.server.block.entity.WearableComponentEntity;
import net.gegy1000.wearables.server.core.WearablesClientHooks;
import net.gegy1000.wearables.server.item.WearableComponentItem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class WearableComponentRenderer extends TileEntitySpecialRenderer<WearableComponentEntity> {
    @Override
    public void renderTileEntityAt(WearableComponentEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        WearableRenderer.renderComponent(WearableComponentItem.getComponent(WearablesClientHooks.getRenderedStack()), false);
        GlStateManager.popMatrix();
    }
}
