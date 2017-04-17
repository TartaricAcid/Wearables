package net.gegy1000.wearables.server.wearable.component.head;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RoundGlassesComponent extends WearableComponentType {
    private static final ItemStack[] INGREDIENTS = new ItemStack[] { new ItemStack(Items.IRON_INGOT, 4), new ItemStack(Blocks.GLASS_PANE, 2) };

    @Override
    public String getIdentifier() {
        return "round_glasses";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.HEAD_FACE;
    }

    @Override
    public ItemStack[] getIngredients() {
        return INGREDIENTS;
    }
}
