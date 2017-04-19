package net.gegy1000.wearables.client;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.movement.MovementState;
import net.gegy1000.wearables.server.network.UpdateMovementMessage;
import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {
    private static final Minecraft MC = Minecraft.getMinecraft();

    public static int ticks = 0;

    private MovementState movementState;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        ticks++;
        EntityPlayer player = MC.player;
        if (player != null) {
            if (this.movementState == null || this.movementState.getPlayer() != player) {
                this.movementState = new MovementState(player);
            }
            this.movementState.unmarkDirty();
            if (WearableUtils.getMovementHandlers(MC.player).isEmpty()) {
                this.movementState.setMoveUp(false);
            } else {
                this.movementState.setMoveUp(MC.gameSettings.keyBindJump.isKeyDown() && !MC.player.capabilities.isFlying);
            }
            if (this.movementState.isDirty()) {
                Wearables.NETWORK_WRAPPER.sendToServer(new UpdateMovementMessage(this.movementState, false));
            }
        }
    }

    @SubscribeEvent
    public void setFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (WearableUtils.hasComponent(MC.player, ComponentRegistry.NIGHT_VISION_GOGGLES)) {
            GlStateManager.setFog(GlStateManager.FogMode.EXP);
            float brightnessFactor = MC.world.getSunBrightnessFactor(1.0F);
            float inverseFactor = 1.0F - brightnessFactor;
            event.setDensity(0.01F * inverseFactor + event.getDensity() * brightnessFactor);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void setFogColors(EntityViewRenderEvent.FogColors event) {
        if (WearableUtils.hasComponent(MC.player, ComponentRegistry.NIGHT_VISION_GOGGLES)) {
            float brightnessFactor = MC.world.getSunBrightnessFactor(1.0F);
            float inverseFactor = 1.0F - brightnessFactor;
            event.setRed(0.2F * inverseFactor + event.getRed() * brightnessFactor);
            event.setGreen(1.0F * inverseFactor + event.getGreen() * brightnessFactor);
            event.setBlue(0.4F * inverseFactor + event.getBlue() * brightnessFactor);
        }
    }
}
