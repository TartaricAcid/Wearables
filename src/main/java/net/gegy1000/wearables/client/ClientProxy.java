package net.gegy1000.wearables.client;

import net.gegy1000.wearables.client.gui.DisplayMannequinGui;
import net.gegy1000.wearables.client.gui.WearableFabricatorGui;
import net.gegy1000.wearables.client.model.BlankModel;
import net.gegy1000.wearables.client.render.RenderRegistry;
import net.gegy1000.wearables.client.render.layer.WearableRenderLayer;
import net.gegy1000.wearables.server.ServerProxy;
import net.gegy1000.wearables.server.block.entity.DisplayMannequinEntity;
import net.gegy1000.wearables.server.block.entity.machine.WearableFabricatorEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Map;

public class ClientProxy extends ServerProxy {
    public static final Minecraft MC = Minecraft.getMinecraft();
    public static final BlankModel BLANK_MODEL = new BlankModel();

    @Override
    public void onPreInit() {
        super.onPreInit();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        RenderRegistry.onPreInit();
    }

    @Override
    public void onInit() {
        super.onInit();
    }

    @Override
    public void onPostInit() {
        super.onPostInit();
        Map<String, RenderPlayer> skinMap = MC.getRenderManager().getSkinMap();
        for (Map.Entry<String, RenderPlayer> entry : skinMap.entrySet()) {
            RenderPlayer renderer = entry.getValue();
            renderer.addLayer(new WearableRenderLayer(renderer));
        }
        for (Map.Entry<Class<? extends Entity>, Render<? extends Entity>> entry : MC.getRenderManager().entityRenderMap.entrySet()) {
            Render<? extends Entity> renderer = entry.getValue();
            if (renderer instanceof RenderLivingBase && !skinMap.containsValue(renderer)) {
                RenderLivingBase renderLiving = (RenderLivingBase) renderer;
                renderLiving.addLayer(new WearableRenderLayer(renderLiving));
            }
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        IBlockState state = world.getBlockState(pos);
        TileEntity entity = world.getTileEntity(pos);
        if (id == DISPLAY_MANNEQUIN_GUI && entity instanceof DisplayMannequinEntity) {
            return new DisplayMannequinGui(player.inventory, (DisplayMannequinEntity) entity);
        } else if (id == WEARABLE_FABRICATOR_GUI && entity instanceof WearableFabricatorEntity) {
            return new WearableFabricatorGui(player.inventory, (WearableFabricatorEntity) entity);
        }
        return null;
    }

    @Override
    public void schedule(Runnable runnable, MessageContext ctx) {
        if (ctx.side.isClient()) {
            MC.addScheduledTask(runnable);
        } else {
            super.schedule(runnable, ctx);
        }
    }
}
