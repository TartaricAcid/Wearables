package net.gegy1000.wearables.server.wearable.component.feet;

import net.gegy1000.wearables.server.core.WearablesHooks;
import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FlippersComponent extends WearableComponentType {
    private static final ItemStack[] INGREDIENTS = new ItemStack[] { new ItemStack(Items.LEATHER, 8), new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Items.STRING, 2) };

    @Override
    public String getIdentifier() {
        return "flippers";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.FEET_GENERIC;
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
    public float getSpeedModifier(EntityPlayer player) {
        return player.isInWater() ? 1.0F : 0.8F;
    }

    @Override
    public void tick(EntityPlayer player) {
        super.tick(player);
        if (player.isInWater() && (Math.abs(player.moveForward) > 0.0F || !player.onGround)) {
            WearablesHooks.setFlag(player, 7, true);
        }
    }

    @Override
    public int getDepthStrideModifier(EntityPlayer player) {
        int modifier = 8;
        if (!player.onGround) {
            modifier *= 2;
        }
        return modifier;
    }
}
