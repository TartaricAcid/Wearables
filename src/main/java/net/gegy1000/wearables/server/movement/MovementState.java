package net.gegy1000.wearables.server.movement;

import net.minecraft.entity.player.EntityPlayer;

public class MovementState {
    private final EntityPlayer player;
    private boolean moveUp;

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

    public boolean shouldMoveUp() {
        return this.moveUp;
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
