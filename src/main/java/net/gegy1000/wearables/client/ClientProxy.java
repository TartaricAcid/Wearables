package net.gegy1000.wearables.client;

import net.gegy1000.wearables.client.model.BlankModel;
import net.gegy1000.wearables.client.render.RenderRegistry;
import net.gegy1000.wearables.client.render.layer.WearableRenderLayer;
import net.gegy1000.wearables.server.ServerProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

public class ClientProxy extends ServerProxy {
    public static final Minecraft MC = Minecraft.getMinecraft();
    public static final BlankModel BLANK_MODEL = new BlankModel();

    @Override
    public void onPreInit() {
        super.onPreInit();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        RenderRegistry.register();
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
    }
}
