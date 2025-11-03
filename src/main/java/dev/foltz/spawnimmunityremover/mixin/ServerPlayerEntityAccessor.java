package dev.foltz.spawnimmunityremover.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Access the private join-invulnerability counter on the server player.
 */
@Mixin(ServerPlayerEntity.class)
public interface ServerPlayerEntityAccessor {
    @Accessor("joinInvulnerabilityTicks")
    int getJoinInvulnerabilityTicks();

    @Accessor("joinInvulnerabilityTicks")
    void setJoinInvulnerabilityTicks(int ticks);
}
