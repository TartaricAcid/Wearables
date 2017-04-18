package net.gegy1000.wearables.server.core;

import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class WearablesHooks {
    private static final Method SET_FLAG = ReflectionHelper.findMethod(Entity.class, "setFlag", "func_70052_a", int.class, boolean.class);

    public static void setFlag(Entity entity, int flag, boolean value) {
        try {
            SET_FLAG.invoke(entity, flag, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static int getDepthStriderModifier(EntityLivingBase entity, int strider) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            List<WearableComponentType> components = WearableUtils.getActiveComponents(player);
            int modifier = 1;
            for (WearableComponentType component : components) {
                if (component.getDepthStrideModifier(player) >= 0) {
                    modifier *= component.getDepthStrideModifier(player);
                }
            }
            return strider + modifier;
        }
        return strider;
    }
}
