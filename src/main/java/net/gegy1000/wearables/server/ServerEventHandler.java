package net.gegy1000.wearables.server;

import net.gegy1000.wearables.server.item.WearableItem;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerEventHandler {
    private static final EntityEquipmentSlot[] ARMOUR_SLOTS = new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            EntityPlayer player = event.player;
            List<WearableComponentType> components = this.getActiveComponents(player);
            for (WearableComponentType component : components) {
                component.tick(player);
            }
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            List<WearableComponentType> components = this.getActiveComponents(player);
            for (WearableComponentType component : components) {
                if (component.onJump(player)) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            List<WearableComponentType> components = this.getActiveComponents(player);
            for (WearableComponentType component : components) {
                component.onFall(player);
            }
        }
    }

    private List<WearableComponentType> getActiveComponents(EntityPlayer player) {
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
}
