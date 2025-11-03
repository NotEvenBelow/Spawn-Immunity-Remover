package dev.foltz.spawnimmunityremover.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Force no spawn/join invulnerability for players.
 * Runs every tick on the server player.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerNoSpawnInvulMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void simr$clearJoinInvul(CallbackInfo ci) {
        ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;

        // zero the "recently joined" invulnerability window
        ((ServerPlayerEntityAccessor) self).setJoinInvulnerabilityTicks(0);

        // just in case: ensure flags/cooldowns are off right now
        self.setInvulnerable(false);
        self.timeUntilRegen = 0;
        // NOTE: don't touch fallDistance — letting it accumulate is what we want.
    }
}
