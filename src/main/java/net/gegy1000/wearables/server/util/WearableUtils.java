package net.gegy1000.wearables.server.util;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;

public class WearableUtils {
    public static boolean hasSlimArms(Entity entity) {
        if (entity instanceof AbstractClientPlayer) {
            String skinType = ((AbstractClientPlayer) entity).getSkinType();
            return skinType != null && skinType.equals("slim");
        }
        return false;
    }
}
