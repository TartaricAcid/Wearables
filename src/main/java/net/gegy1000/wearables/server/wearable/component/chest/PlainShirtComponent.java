package net.gegy1000.wearables.server.wearable.component.chest;

import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;

public class PlainShirtComponent extends WearableComponentType {
    @Override
    public String getIdentifier() {
        return "plain_shirt";
    }

    @Override
    public WearableCategory getCategory() {
        return WearableCategory.CHEST_GENERIC;
    }
}
