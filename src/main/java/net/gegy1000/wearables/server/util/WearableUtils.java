package net.gegy1000.wearables.server.util;

import net.gegy1000.wearables.client.render.RenderRegistry;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.gegy1000.wearables.server.item.WearableItem;
import net.gegy1000.wearables.server.movement.MovementHandler;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WearableUtils {
    private static final EntityEquipmentSlot[] ARMOUR_SLOTS = new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };

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

    public static List<WearableComponentType> getActiveComponents(EntityPlayer player) {
        List<WearableComponentType> componentTypes = new ArrayList<>();
        for (EntityEquipmentSlot slot : ARMOUR_SLOTS) {
            ItemStack stack = player.getItemStackFromSlot(slot);
            if (!stack.isEmpty() && stack.getItem() instanceof WearableItem) {
                Wearable wearable = WearableItem.getWearable(stack);
                componentTypes.addAll(wearable.getComponents().stream().map(WearableComponent::getType).collect(Collectors.toList()));
            }
        }
        return componentTypes;
    }

    public static List<MovementHandler> getMovementHandlers(EntityPlayer player) {
        List<MovementHandler> movementHandlers = new ArrayList<>();
        List<WearableComponentType> componentTypes = WearableUtils.getActiveComponents(player);
        for (WearableComponentType type : componentTypes) {
            MovementHandler movementHandler = type.getMovementHandler();
            if (movementHandler != null) {
                movementHandlers.add(movementHandler);
            }
        }
        return movementHandlers;
    }
}
