package net.gegy1000.wearables.server.item;

import net.gegy1000.wearables.client.ClientProxy;
import net.gegy1000.wearables.client.WearableColourUtils;
import net.gegy1000.wearables.server.api.item.RegisterBlockEntity;
import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.DisplayMannequinBlock;
import net.gegy1000.wearables.server.tab.TabRegistry;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class WearableItem extends ItemArmor implements RegisterItemModel, RegisterBlockEntity {
    public static final ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("wearable", "leather", -1, new int[4], 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    private Class<? extends TileEntity> entity;

    public WearableItem(Class<? extends TileEntity> entity, EntityEquipmentSlot slot) {
        super(MATERIAL, 0, slot);
        this.entity = entity;
        this.setUnlocalizedName("wearable_" + slot.getName());
        this.setCreativeTab(TabRegistry.TEMPLATES);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return this.entity;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (WearableComponentType componentType : ComponentRegistry.COMPONENTS) {
            if (componentType.getCategory().getSlot() == this.armorType) {
                for (int colourIndex = 0; colourIndex < 16; colourIndex++) {
                    int colour = WearableColourUtils.fromRGBFloatArray(EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(colourIndex)));
                    Wearable wearable = new Wearable();
                    WearableComponent component = new WearableComponent(componentType);
                    wearable.addComponent(component);
                    for (int i = 0; i < componentType.getLayerCount(); i++) {
                        if (componentType.canColour(i)) {
                            component.setColour(i, colour);
                        }
                    }
                    ItemStack stack = new ItemStack(this);
                    stack.setTagCompound(wearable.serializeNBT());
                    subItems.add(stack);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return ClientProxy.BLANK_MODEL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        Wearable wearable = WearableItem.getWearable(stack);
        if (!wearable.getComponents().isEmpty()) {
            tooltip.add(I18n.translateToLocal("label.wearable_components.name"));
            for (WearableComponent component : wearable.getComponents()) {
                WearableComponentType type = component.getType();
                TextFormatting textColour = WearableColourUtils.getClosest(component.getColour(0));
                tooltip.add(" - " + textColour + I18n.translateToLocal("component." + type.getIdentifier() + ".name"));
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!player.isSneaking()) {
            return super.onItemRightClick(world, player, hand);
        }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return world.getBlockState(pos).getBlock() instanceof DisplayMannequinBlock;
    }

    public static Wearable getWearable(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        return Wearable.deserialize(compound);
    }
}
