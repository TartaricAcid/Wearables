package net.gegy1000.wearables.server.wearable.component.head;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HatComponent extends WearableComponentType {
    private static final ItemStack[] INGREDIENTS = new ItemStack[] { new ItemStack(Items.LEATHER, 3), new ItemStack(Items.STRING) };

    @Override
    public String getIdentifier() {
        return "hat";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.HEAD_TOP;
    }

    @Override
    public ItemStack[] getIngredients() {
        return INGREDIENTS;
    }
}
