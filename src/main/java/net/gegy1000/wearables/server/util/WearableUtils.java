package net.gegy1000.wearables.server.util;

import net.gegy1000.wearables.client.render.RenderRegistry;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.Random;

public class WearableUtils {
    public static boolean hasSlimArms(Entity entity) {
        if (entity instanceof AbstractClientPlayer) {
            String skinType = ((AbstractClientPlayer) entity).getSkinType();
            return skinType != null && skinType.equals("slim");
        }
        return false;
    }

    public static void dropInventory(World world, BlockPos pos, IItemHandler inventory) {
        WearableUtils.dropInventory(world, pos, inventory, inventory.getSlots());
    }

    public static void dropInventory(World world, BlockPos pos, IItemHandler inventory, int slotCount) {
        float motionScale = 0.05F;
        Random random = world.rand;
        for (int i = 0; i < slotCount; i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                float offsetX = random.nextFloat() * 0.8F + 0.1F;
                float offsetY = random.nextFloat() * 0.8F + 0.1F;
                float offsetZ = random.nextFloat() * 0.8F + 0.1F;
                EntityItem entity = new EntityItem(world, pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ, stack.copy());
                entity.motionX = random.nextGaussian() * motionScale;
                entity.motionY = random.nextGaussian() * motionScale + 0.2;
                entity.motionZ = random.nextGaussian() * motionScale;
                world.spawnEntity(entity);
            }
        }
    }

    public static AxisAlignedBB calculateUnion(Wearable wearable) {
        AxisAlignedBB union = null;
        for (WearableComponent component : wearable.getComponents()) {
            ComponentRenderer renderer = WearableUtils.getRenderer(component);
            if (union == null) {
                union = renderer.getBounds();
            } else {
                union = union.union(renderer.getBounds());
            }
        }
        if (union == null) {
            union = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
        }
        return union;
    }

    public static ComponentRenderer getRenderer(WearableComponent component) {
        return RenderRegistry.getRenderer(component.getType().getIdentifier());
    }
}
