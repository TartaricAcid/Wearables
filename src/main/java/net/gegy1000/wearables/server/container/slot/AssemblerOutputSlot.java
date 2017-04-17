package net.gegy1000.wearables.server.container.slot;

import net.gegy1000.wearables.server.container.WearableAssemblerContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AssemblerOutputSlot extends SlotItemHandler {
    private final WearableAssemblerContainer container;

    public AssemblerOutputSlot(WearableAssemblerContainer container, IItemHandler inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.container = container;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return this.container.canBuild() && super.canTakeStack(player);
    }

    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack take) {
        this.container.consumeComponents();
        return super.onTake(player, take);
    }
}
