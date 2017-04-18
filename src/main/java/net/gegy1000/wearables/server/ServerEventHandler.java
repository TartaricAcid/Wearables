package net.gegy1000.wearables.server;

import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class ServerEventHandler {
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            List<WearableComponentType> components = WearableUtils.getActiveComponents(player);
            for (WearableComponentType component : components) {
                component.tick(player);
            }
            boolean hasSpeedModifier = false;
            float speedModifier = 1.0F;
            for (WearableComponentType component : components) {
                if (component.getSpeedModifier(player) >= 0.0F) {
                    speedModifier *= component.getSpeedModifier(player);
                    hasSpeedModifier = true;
                }
            }
            if (hasSpeedModifier) {
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(speedModifier * 0.1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            List<WearableComponentType> components = WearableUtils.getActiveComponents(player);
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
            List<WearableComponentType> components = WearableUtils.getActiveComponents(player);
            for (WearableComponentType component : components) {
                component.onFall(player);
            }
        }
    }
}
