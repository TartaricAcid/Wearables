package net.gegy1000.wearables.server.block;

import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.entity.machine.WearableColouriserEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WearableColouriserBlock extends MachineBlock implements RegisterItemModel {
    public WearableColouriserBlock() {
        super(Material.WOOD);
        this.setUnlocalizedName("wearable_colouriser");
        this.setHardness(0.5F);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return WearableColouriserEntity.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new WearableColouriserEntity();
    }
}
