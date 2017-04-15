package net.gegy1000.wearables.client;

import net.gegy1000.wearables.server.ServerProxy;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy {
    @Override
    public void onPreInit() {
        super.onPreInit();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @Override
    public void onInit() {
        super.onInit();
    }

    @Override
    public void onPostInit() {
        super.onPostInit();
    }
}
