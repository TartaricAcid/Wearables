package net.gegy1000.wearables.client.render.item;

import net.gegy1000.wearables.client.render.ComponentInventoryRenderer;
import net.gegy1000.wearables.server.block.entity.WearableItemEntity;
import net.gegy1000.wearables.server.core.WearablesClientHooks;
import net.gegy1000.wearables.server.item.WearableItem;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class WearableItemRenderer extends TileEntitySpecialRenderer<WearableItemEntity> {
    @Override
    public void renderTileEntityAt(WearableItemEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        float scale = 1.0F;
        GlStateManager.scale(-scale, -scale, scale);
        Wearable wearable = WearableItem.getWearable(WearablesClientHooks.getRenderedStack());
        for (WearableComponent component : wearable.getComponents()) {
            ComponentInventoryRenderer.renderComponent(component, false);
        }
        GlStateManager.popMatrix();
    }
}
