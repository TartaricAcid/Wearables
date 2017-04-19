package net.gegy1000.wearables.server.movement;

import net.gegy1000.wearables.server.util.WearableUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class WingsMovementHandler extends MovementHandler {
    @Override
    public void updateMovement(EntityPlayer player, MovementState movementState) {
        if (movementState.shouldMoveUp()) {
            float angle = 0.0F;
            if (movementState.shouldMoveForward() || movementState.shouldMoveBackward()) {
                angle = 40.0F;
            }
            float pitchCos = -MathHelper.cos((float) -Math.toRadians(angle - 90.0F));
            float pitchSin = MathHelper.sin((float) -Math.toRadians(angle - 90.0F));
            float yawCos = MathHelper.cos((float) (-Math.toRadians(player.rotationYaw) - (float) Math.PI));
            float yawSin = MathHelper.sin((float) (-Math.toRadians(player.rotationYaw) - (float) Math.PI));
            float moveX = yawSin * pitchCos;
            float moveZ = yawCos * pitchCos;
            float speed = 0.06F;
            player.motionY += pitchSin * speed + 0.05F;
            player.motionX += moveX * speed;
            player.motionZ += moveZ * speed;
        } else if (player.motionY < 0.0) {
            player.motionY *= 0.9;
        }
    }

    @Override
    public void applyRotations(EntityPlayer player, float yaw, float bodyYaw, float partialTicks) {
        if (WearableUtils.onGround(player)) {
            return;
        }
        float limbSwingAmount = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * partialTicks;
        GlStateManager.rotate(-limbSwingAmount * 40.0F, 1.0F, 0.0F, 0.0F);
    }
}