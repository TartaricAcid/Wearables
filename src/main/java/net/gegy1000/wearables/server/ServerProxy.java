package net.gegy1000.wearables.server;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.block.BlockRegistry;
import net.gegy1000.wearables.server.block.entity.DisplayMannequinEntity;
import net.gegy1000.wearables.server.block.entity.machine.WearableFabricatorEntity;
import net.gegy1000.wearables.server.container.DisplayMannequinContainer;
import net.gegy1000.wearables.server.container.WearableFabricatorContainer;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ServerProxy implements IGuiHandler {
    public static final int DISPLAY_MANNEQUIN_GUI = 0;
    public static final int WEARABLE_FABRICATOR_GUI = 1;

    public void onPreInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(Wearables.INSTANCE, this);

        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());

        ComponentRegistry.register();
        ItemRegistry.register();
        BlockRegistry.register();
    }

    public void onInit() {
    }

    public void onPostInit() {
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        IBlockState state = world.getBlockState(pos);
        TileEntity entity = world.getTileEntity(pos);
        if (id == DISPLAY_MANNEQUIN_GUI && entity instanceof DisplayMannequinEntity) {
            return new DisplayMannequinContainer(player.inventory, (DisplayMannequinEntity) entity);
        } else if (id == WEARABLE_FABRICATOR_GUI && entity instanceof WearableFabricatorEntity) {
            return new WearableFabricatorContainer(player.inventory, (WearableFabricatorEntity) entity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
