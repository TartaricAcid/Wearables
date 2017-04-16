package net.gegy1000.wearables.server.item;

import net.gegy1000.wearables.server.api.item.RegisterBlockEntity;
import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.entity.WearableComponentEntity;
import net.gegy1000.wearables.server.tab.TabRegistry;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

public class WearableComponentItem extends Item implements RegisterItemModel, RegisterBlockEntity {
    public WearableComponentItem() {
        super();
        this.setUnlocalizedName("wearable_component");
        this.setCreativeTab(TabRegistry.COMPONENTS);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return WearableComponentEntity.class;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (WearableComponentType componentType : ComponentRegistry.COMPONENTS) {
            WearableComponent component = new WearableComponent(componentType);
            ItemStack stack = new ItemStack(this);
            stack.setTagCompound(component.serializeNBT());
            subItems.add(stack);
        }
    }

    public static WearableComponent getComponent(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        return WearableComponent.deserialize(compound);
    }
}
