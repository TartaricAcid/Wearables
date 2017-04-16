package net.gegy1000.wearables.server.wearable.component.chest;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PlainShirtComponent extends WearableComponentType {
    private static final ItemStack[] INGREDIENTS = new ItemStack[] { new ItemStack(Items.LEATHER, 2), new ItemStack(Items.STRING, 3) };

    @Override
    public String getIdentifier() {
        return "plain_shirt";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.CHEST_GENERIC;
    }

    @Override
    public ItemStack[] getIngredients() {
        return INGREDIENTS;
    }
}
