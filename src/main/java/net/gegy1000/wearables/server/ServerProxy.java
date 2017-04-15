package net.gegy1000.wearables.server;

import net.gegy1000.wearables.server.block.BlockRegistry;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy {
    public void onPreInit() {
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());

        ComponentRegistry.register();
        ItemRegistry.register();
        BlockRegistry.register();
    }

    public void onInit() {
    }

    public void onPostInit() {
    }
}
