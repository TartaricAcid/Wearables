package net.gegy1000.wearables.server.util;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class WearableUtils {
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
}
