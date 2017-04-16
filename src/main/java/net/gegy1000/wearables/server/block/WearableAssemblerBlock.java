package net.gegy1000.wearables.server.block;

import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.entity.machine.WearableAssemblerEntity;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WearableAssemblerBlock extends MachineBlock implements RegisterItemModel {
    public WearableAssemblerBlock() {
        super(Material.WOOD);
        this.setUnlocalizedName("wearable_assembler");
        this.setHardness(0.5F);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return WearableAssemblerEntity.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new WearableAssemblerEntity();
    }
}
