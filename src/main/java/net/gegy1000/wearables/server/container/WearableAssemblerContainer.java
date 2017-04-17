package net.gegy1000.wearables.server.container;

import net.gegy1000.wearables.server.block.entity.machine.WearableAssemblerEntity;
import net.gegy1000.wearables.server.container.slot.AssemblerInputSlot;
import net.gegy1000.wearables.server.container.slot.AssemblerOutputSlot;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.gegy1000.wearables.server.item.WearableComponentItem;
import net.gegy1000.wearables.server.wearable.Wearable;
import net.gegy1000.wearables.server.wearable.WearableCategory;
import net.gegy1000.wearables.server.wearable.component.WearableComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashSet;
import java.util.Set;

public class WearableAssemblerContainer extends AutoTransferContainer {
    private final WearableAssemblerEntity entity;
    private final ItemStackHandler components = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            WearableAssemblerContainer.this.onContentsChanged();
        }
    };
    private final ItemStackHandler result = new ItemStackHandler(1);

    public WearableAssemblerContainer(InventoryPlayer playerInventory, WearableAssemblerEntity entity) {
        this.entity = entity;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 2; column++) {
                this.addSlotToContainer(new AssemblerInputSlot(this, this.components, column + row * 2, 8 + column * 18, 18 + row * 18));
            }
        }

        this.addSlotToContainer(new AssemblerOutputSlot(this, this.result, 0, 144, 35));

        for (int column = 0; column < 9; column++) {
            this.addSlotToContainer(new Slot(playerInventory, column, 8 + column * 18, 142));
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlotToContainer(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.entity.isUsableByPlayer(player);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        if (!player.world.isRemote) {
            for (int slot = 0; slot < this.components.getSlots(); slot++) {
                ItemStack stack = this.components.getStackInSlot(slot);
                this.components.extractItem(slot, stack.getCount(), false);
                if (!stack.isEmpty()) {
                    player.dropItem(stack, false);
                }
            }
        }
    }

    protected void onContentsChanged() {
        this.result.setStackInSlot(0, this.buildResult());
    }

    protected ItemStack buildResult() {
        EntityEquipmentSlot slotType = this.getSlotType();
        if (slotType != null) {
            Set<WearableCategory> usedCategories = new HashSet<>();
            Wearable wearable = new Wearable();
            for (int i = 0; i < this.components.getSlots(); i++) {
                ItemStack stack = this.components.getStackInSlot(i);
                if (!stack.isEmpty() && stack.getItem() instanceof WearableComponentItem) {
                    WearableComponent component = WearableComponentItem.getComponent(stack);
                    WearableCategory category = component.getType().getCategory();
                    if (category.getSlot() == slotType && !usedCategories.contains(category)) {
                        usedCategories.add(category);
                        wearable.addComponent(component);
                    }
                }
            }
            if (wearable.getComponents().size() > 0) {
                ItemStack stack = new ItemStack(this.getWearableItem(slotType));
                stack.setTagCompound(wearable.serializeNBT());
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public void consumeComponents() {
        EntityEquipmentSlot slotType = this.getSlotType();
        Set<WearableCategory> usedCategories = new HashSet<>();
        for (int i = 0; i < this.components.getSlots(); i++) {
            ItemStack stack = this.components.getStackInSlot(i);
            if (stack.getItem() instanceof WearableComponentItem && stack.getCount() > 0) {
                WearableComponent component = WearableComponentItem.getComponent(stack);
                WearableCategory category = component.getType().getCategory();
                if (category.getSlot() == slotType && !usedCategories.contains(category)) {
                    stack.shrink(1);
                    usedCategories.add(category);
                }
            }
        }
        this.onContentsChanged();
    }

    public boolean canBuild() {
        return this.getSlotType() != null;
    }

    public boolean canAddComponent(WearableComponent checkComponent) {
        WearableCategory checkCategory = checkComponent.getType().getCategory();
        for (int i = 0; i < this.components.getSlots(); i++) {
            ItemStack stack = this.components.getStackInSlot(i);
            if (stack.getItem() instanceof WearableComponentItem && stack.getCount() > 0) {
                WearableComponent component = WearableComponentItem.getComponent(stack);
                WearableCategory category = component.getType().getCategory();
                if (checkCategory == category || checkCategory.getSlot() != category.getSlot()) {
                    return false;
                }
            }
        }
        return true;
    }

    private EntityEquipmentSlot getSlotType() {
        EntityEquipmentSlot slotType = null;
        for (int i = 0; i < this.components.getSlots(); i++) {
            ItemStack stack = this.components.getStackInSlot(i);
            if (stack.getItem() instanceof WearableComponentItem && stack.getCount() > 0) {
                WearableComponent component = WearableComponentItem.getComponent(stack);
                EntityEquipmentSlot slot = component.getType().getCategory().getSlot();
                if (slotType == null) {
                    slotType = slot;
                } else if (slotType != slot) {
                    return null;
                }
            }
        }
        return slotType;
    }

    private Item getWearableItem(EntityEquipmentSlot slot) {
        switch (slot) {
            case HEAD:
                return ItemRegistry.WEARABLE_HEAD;
            case CHEST:
                return ItemRegistry.WEARABLE_CHEST;
            case LEGS:
                return ItemRegistry.WEARABLE_LEGS;
            case FEET:
                return ItemRegistry.WEARABLE_FEET;
        }
        return ItemRegistry.WEARABLE_CHEST;
    }
}