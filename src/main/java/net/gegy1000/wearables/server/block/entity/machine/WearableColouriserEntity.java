package net.gegy1000.wearables.server.block.entity.machine;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class WearableColouriserEntity extends MachineBlockEntity {
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("tile.wearable_colouriser.name");
    }

    @Override
    public int getSlotCount() {
        return 1;
    }
}
