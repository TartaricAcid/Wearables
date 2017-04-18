package net.gegy1000.wearables.server.network;

import io.netty.buffer.ByteBuf;
import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.movement.MovementHandler;
import net.gegy1000.wearables.server.movement.MovementState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateMovementMessage implements IMessage {
    private int playerId;
    private boolean hasPlayer;
    private boolean moveUp;

    public UpdateMovementMessage() {
    }

    public UpdateMovementMessage(MovementState state, boolean hasPlayer) {
        this.playerId = state.getPlayer().getEntityId();
        this.hasPlayer = hasPlayer;
        this.moveUp = state.shouldMoveUp();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.hasPlayer = buf.readBoolean();
        if (this.hasPlayer) {
            this.playerId = buf.readInt();
        }
        this.moveUp = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.hasPlayer);
        if (this.hasPlayer) {
            buf.writeInt(this.playerId);
        }
        buf.writeBoolean(this.moveUp);
    }

    public static class Handler implements IMessageHandler<UpdateMovementMessage, IMessage> {
        @Override
        public IMessage onMessage(UpdateMovementMessage message, MessageContext ctx) {
            Wearables.PROXY.schedule(() -> {
                EntityPlayer player = Wearables.PROXY.getPlayer(ctx);
                if (ctx.side.isServer()) {
                    MovementState state = MovementHandler.MOVEMENT_STATES.computeIfAbsent(player.getUniqueID(), uuid -> new MovementState(player));
                    state.setMoveUp(message.moveUp);
                } else {
                    Entity senderEntity = player.world.getEntityByID(message.playerId);
                    if (senderEntity instanceof EntityPlayer) {
                        EntityPlayer sender = (EntityPlayer) senderEntity;
                        MovementState state = MovementHandler.MOVEMENT_STATES.computeIfAbsent(sender.getUniqueID(), uuid -> new MovementState(player));
                        state.setMoveUp(message.moveUp);
                    }
                }
            }, ctx);
            return null;
        }
    }
}
