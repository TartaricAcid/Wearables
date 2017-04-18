package net.gegy1000.wearables.server.wearable.component.chest;

import net.gegy1000.wearables.client.render.ComponentProperty;
import net.gegy1000.wearables.server.movement.JetpackMovementHandler;
import net.gegy1000.wearables.server.movement.MovementHandler;
import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class JetpackComponent extends WearableComponentType {
    private static final ItemStack[] INGREDIENTS = new ItemStack[] { new ItemStack(Items.IRON_INGOT, 20), new ItemStack(Items.BLAZE_POWDER, 4), new ItemStack(Items.LEATHER, 2), new ItemStack(Items.STRING, 2) };
    private static final JetpackMovementHandler MOVEMENT_HANDLER = new JetpackMovementHandler();

    @Override
    public String getIdentifier() {
        return "jetpack";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.CHEST_BACK;
    }

    @Override
    public ItemStack[] getIngredients() {
        return INGREDIENTS;
    }

    @Override
    public int getSupportedProperties() {
        return ComponentProperty.OFFSET_Y;
    }

    @Override
    public float getMinimum(int property) {
        if (property == ComponentProperty.OFFSET_Y) {
            return -0.4F;
        }
        return super.getMinimum(property);
    }

    @Override
    public float getMaximum(int property) {
        if (property == ComponentProperty.OFFSET_Y) {
            return 0.1F;
        }
        return super.getMaximum(property);
    }

    @Override
    public MovementHandler getMovementHandler() {
        return MOVEMENT_HANDLER;
    }

    @Override
    public void onFall(EntityPlayer player, LivingFallEvent event) {
        float distance = (float) (player.lastTickPosY - player.posY) * 7.0F;
        event.setDistance(distance);
    }
}
