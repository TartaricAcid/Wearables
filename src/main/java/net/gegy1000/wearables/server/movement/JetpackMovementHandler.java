package net.gegy1000.wearables.server.movement;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;

public class JetpackMovementHandler extends MovementHandler{
    @Override
    public void updateMovement(EntityPlayer player, MovementState movementState) {
        if (movementState.shouldMoveUp()) {
            player.motionY += 0.1;
            player.world.spawnParticle(EnumParticleTypes.FLAME, player.posX, player.posY, player.posZ, 0.0, -1.0, 0.0);
        }
    }

    @Override
    public void applyRotations(EntityPlayer player, float yaw, float bodyYaw, float partialTicks) {
        GlStateManager.rotate(-player.moveForward * 10.0F, 1.0F, 0.0F, 0.0F);
    }
}
