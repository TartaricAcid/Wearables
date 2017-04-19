package net.gegy1000.wearables.server.movement;

import net.minecraft.entity.player.EntityPlayer;

public class MovementState {
    private final EntityPlayer player;
    private boolean moveUp;
    private boolean moveForward;
    private boolean moveBackward;

    private boolean dirty;

    public MovementState(EntityPlayer player) {
        this.player = player;
    }

    public void setMoveUp(boolean moveUp) {
        if (this.moveUp != moveUp) {
            this.dirty = true;
        }
        this.moveUp = moveUp;
    }

    public void setMoveForward(boolean moveForward) {
        if (this.moveForward != moveForward) {
            this.dirty = true;
        }
        this.moveForward = moveForward;
    }

    public void setMoveBackward(boolean moveBackward) {
        if (this.moveBackward != moveBackward) {
            this.dirty = true;
        }
        this.moveBackward = moveBackward;
    }

    public boolean shouldMoveUp() {
        return this.moveUp;
    }

    public boolean shouldMoveForward() {
        return this.moveForward;
    }

    public boolean shouldMoveBackward() {
        return this.moveBackward;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public void unmarkDirty() {
        this.dirty = false;
    }

    public boolean isDirty() {
        return this.dirty;
    }
}
