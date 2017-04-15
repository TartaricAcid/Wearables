package net.gegy1000.wearables.server.item;

import net.gegy1000.wearables.server.api.item.RegisterBlockEntity;
import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.tab.TabRegistry;
import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.EnumHelper;

public class WearableItem extends ItemArmor implements RegisterItemModel, RegisterBlockEntity {
    public static final ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("wearable", "", -1, new int[4], 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    private Class<? extends TileEntity> entity;

    public WearableItem(Class<? extends TileEntity> entity, EntityEquipmentSlot slot) {
        super(MATERIAL, 0, slot);
        this.entity = entity;
        this.setUnlocalizedName("wearable_" + slot.getName());
        this.setCreativeTab(TabRegistry.ITEMS);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return this.entity;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (WearableComponentType componentType : ComponentRegistry.COMPONENTS) {
            for (int colourIndex = 0; colourIndex < 16; colourIndex++) {
                int colour = WearableUtils.fromRGBFloatArray(EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(colourIndex)));
                Wearable wearable = new Wearable();
                WearableComponent component = new WearableComponent(componentType);
                wearable.addComponent(component);
                for (int i = 0; i < componentType.getLayerCount(); i++) {
                    component.setColour(i, colour);
                }
                ItemStack stack = new ItemStack(this);
                stack.setTagCompound(wearable.serializeNBT());
                subItems.add(stack);
            }
        }
    }

    public static Wearable getWearable(ItemStack stack) {
        return Wearable.deserialize(stack.getTagCompound());
    }
}
