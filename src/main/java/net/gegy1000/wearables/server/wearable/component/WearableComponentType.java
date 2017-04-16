package net.gegy1000.wearables.server.wearable.component;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.minecraft.item.ItemStack;

public abstract class WearableComponentType {
    public abstract String getIdentifier();

    public abstract WearableCategory getCategory();

    public abstract ItemStack[] getIngredients();

    public int getLayerCount() {
        return 1;
    }

    public boolean canColour(int layer) {
        return true;
    }

    public float getColourX(int layer) {
        return 0.0F;
    }

    public float getColourY(int layer) {
        return 0.0F;
    }

    public boolean compatibleWith(WearableComponentType component) {
        return component.getCategory() != this.getCategory();
    }
}
