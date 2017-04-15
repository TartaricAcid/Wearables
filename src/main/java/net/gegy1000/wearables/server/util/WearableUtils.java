package net.gegy1000.wearables.server.util;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.text.TextFormatting;

public class WearableUtils {
    public static final int[] DYE_TO_TEXT = new int[] { 15, 6, 5, 11, 14, 10, 13, 8, 7, 11, 13, 9, 0, 10, 12, 0 };

    public static int fromRGBFloatArray(float[] rgb) {
        int red = (int) (rgb[0] * 255);
        int green = (int) (rgb[1] * 255);
        int blue = (int) (rgb[2] * 255);
        return WearableUtils.fromRGB(red, green, blue);
    }

    public static int fromRGB(int red, int green, int blue) {
        return (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    }

    public static float[] toRGBFloatArray(int colour) {
        int red = (colour >> 16) & 0xFF;
        int green = (colour >> 8) & 0xFF;
        int blue = colour & 0xFF;
        return new float[] { red / 255.0F, green / 255.0F, blue / 255.0F };
    }

    public static int getDyeColour(EnumDyeColor color) {
        return WearableUtils.fromRGBFloatArray(EntitySheep.getDyeRgb(color));
    }

    public static TextFormatting getClosest(int colour) {
        return WearableUtils.getClosest(WearableUtils.toRGBFloatArray(colour));
    }

    public static TextFormatting getClosest(float[] colour) {
        TextFormatting closest = TextFormatting.WHITE;
        float closestDistance = Float.MAX_VALUE;
        for (EnumDyeColor dye : EnumDyeColor.values()) {
            float[] dyeColour = EntitySheep.getDyeRgb(dye);
            float distance = Math.abs(dyeColour[0] - colour[0]) + Math.abs(dyeColour[1] - colour[1]) + Math.abs(dyeColour[2] - colour[2]);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = TextFormatting.fromColorIndex(DYE_TO_TEXT[dye.getMetadata()]);
            }
        }
        return closest;
    }

    public static boolean hasSlimArms(Entity entity) {
        if (entity instanceof AbstractClientPlayer) {
            String skinType = ((AbstractClientPlayer) entity).getSkinType();
            return skinType != null && skinType.equals("slim");
        }
        return false;
    }
}
