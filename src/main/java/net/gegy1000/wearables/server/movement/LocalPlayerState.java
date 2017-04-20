package net.gegy1000.wearables.server.movement;

import net.gegy1000.wearables.server.util.WearableUtils;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalPlayerState {
    private static final Map<EntityPlayer, LocalPlayerState> CLIENT_PLAYER_STATES = new HashMap<>();
    private static final Map<EntityPlayer, LocalPlayerState> SERVER_PLAYER_STATES = new HashMap<>();

    private final EntityPlayer player;
    private List<WearableComponentType> lastEquipment;

    private int swimAnimation;
    private boolean swimming;

    private int flyAnimation;
    private boolean flying;

    public LocalPlayerState(EntityPlayer player) {
        this.player = player;
    }

    public void updateEquipment(List<WearableComponentType> equipment) {
        if (this.lastEquipment != null) {
            for (WearableComponentType component : this.lastEquipment) {
                if (!equipment.contains(component)) {
                    component.onRemoved(this.player);
                }
            }
        }
        this.lastEquipment = equipment;
    }

    public void update() {
        if (this.player.world.isRemote) {
            this.swimAnimation = WearableUtils.updateAnimation(this.swimAnimation, this.swimming, 10);
            this.flyAnimation = WearableUtils.updateAnimation(this.flyAnimation, this.flying, 5);
        }
    }

    public void setSwimming(boolean swimming) {
        this.swimming = swimming;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public float getRenderSwimTimer(float partialTicks) {
        return WearableUtils.scaleTimer(this.swimAnimation, this.swimming, partialTicks, 10.0F);
    }

    public float getRenderFlyTimer(float partialTicks) {
        return WearableUtils.scaleTimer(this.flyAnimation, this.flying, partialTicks, 5.0F);
    }

    public static LocalPlayerState getState(EntityPlayer player) {
        return LocalPlayerState.getStates(player).computeIfAbsent(player, LocalPlayerState::new);
    }

    public static void removeState(EntityPlayer player) {
        LocalPlayerState.getStates(player).remove(player);
    }

    private static Map<EntityPlayer, LocalPlayerState> getStates(EntityPlayer player) {
        return player.world.isRemote ? CLIENT_PLAYER_STATES : SERVER_PLAYER_STATES;
    }
}
