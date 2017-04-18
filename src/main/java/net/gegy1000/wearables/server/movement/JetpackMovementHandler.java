package net.gegy1000.wearables.server.movement;

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
}