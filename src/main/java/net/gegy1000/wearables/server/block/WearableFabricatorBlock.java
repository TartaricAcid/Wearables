package net.gegy1000.wearables.server.block;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.ServerProxy;
import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.entity.machine.WearableFabricatorEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WearableFabricatorBlock extends MachineBlock implements RegisterItemModel {
    public WearableFabricatorBlock() {
        super(Material.WOOD);
        this.setUnlocalizedName("wearable_fabricator");
        this.setHardness(0.5F);
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return WearableFabricatorEntity.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new WearableFabricatorEntity();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.getTileEntity(pos);
        if (!world.isRemote && tile instanceof WearableFabricatorEntity) {
            player.openGui(Wearables.INSTANCE, ServerProxy.WEARABLE_FABRICATOR_GUI, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return false;
    }
}
