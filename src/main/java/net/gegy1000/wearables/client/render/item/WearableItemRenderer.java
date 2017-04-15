package net.gegy1000.wearables.client.render.item;

import net.gegy1000.wearables.server.block.entity.WearableItemEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class WearableItemRenderer extends TileEntitySpecialRenderer<WearableItemEntity> {
    @Override
    public void renderTileEntityAt(WearableItemEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.popMatrix();
    }
}
