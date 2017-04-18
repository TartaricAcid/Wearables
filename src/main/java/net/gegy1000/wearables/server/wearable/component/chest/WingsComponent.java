package net.gegy1000.wearables.server.wearable.component.chest;

import net.gegy1000.wearables.client.render.ComponentProperty;
import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class WingsComponent extends WearableComponentType {
    private static final ItemStack[] INGREDIENTS = new ItemStack[] { new ItemStack(Items.IRON_INGOT, 30), new ItemStack(Items.FEATHER, 50), new ItemStack(Items.LEATHER, 10), new ItemStack(Items.BONE, 10) };

    @Override
    public String getIdentifier() {
        return "wings";
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
    public int getLayerCount() {
        return 2;
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
    public void onFall(EntityPlayer player, LivingFallEvent event) {
        float distance = (float) (player.lastTickPosY - player.posY) * 7.0F;
        event.setDistance(distance);
    }
}
