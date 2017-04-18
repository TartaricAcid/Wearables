package net.gegy1000.wearables.server.network;

import io.netty.buffer.ByteBuf;
import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.movement.MovementHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class StopTrackingMovementMessage implements IMessage {
    private int playerId;

    public StopTrackingMovementMessage() {
    }

    public StopTrackingMovementMessage(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.playerId);
    }

    public static class Handler implements IMessageHandler<StopTrackingMovementMessage, IMessage> {
        @Override
        public IMessage onMessage(StopTrackingMovementMessage message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Wearables.PROXY.schedule(() -> {
                    EntityPlayer player = Wearables.PROXY.getPlayer(ctx);
                    Entity entity = player.world.getEntityByID(message.playerId);
                    if (entity instanceof EntityPlayer) {
                        MovementHandler.removeState((EntityPlayer) entity);
                    }
                }, ctx);
            }
            return null;
        }
    }
}
