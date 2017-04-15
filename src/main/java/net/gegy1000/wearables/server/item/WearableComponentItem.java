package net.gegy1000.wearables.server.item;

import net.gegy1000.wearables.server.api.item.RegisterBlockEntity;
import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.entity.WearableComponentEntity;
import net.gegy1000.wearables.server.tab.TabRegistry;
import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

public class WearableComponentItem extends Item implements RegisterItemModel, RegisterBlockEntity {
    public WearableComponentItem() {
        super();
        this.setUnlocalizedName("wearable_component");
        this.setCreativeTab(TabRegistry.ITEMS);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return WearableComponentEntity.class;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (WearableComponentType componentType : ComponentRegistry.COMPONENTS) {
            for (int colourIndex = 0; colourIndex < 16; colourIndex++) {
                int colour = WearableUtils.fromRGBFloatArray(EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(colourIndex)));
                WearableComponent component = new WearableComponent(componentType);
                for (int i = 0; i < componentType.getLayerCount(); i++) {
                    component.setColour(i, colour);
                }
                ItemStack stack = new ItemStack(this);
                stack.setTagCompound(component.serializeNBT());
                subItems.add(stack);
            }
        }
    }

    public static WearableComponent getComponent(ItemStack stack) {
        return WearableComponent.deserialize(stack.getTagCompound());
    }
}
