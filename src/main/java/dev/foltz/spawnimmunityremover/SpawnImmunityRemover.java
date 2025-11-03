package dev.foltz.spawnimmunityremover;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Server-only: clears join invulnerability + hit i-frames each tick.
 * Players rejoining mid-fall take full damage on impact.
 */
public final class SpawnImmunityRemover implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity p : server.getPlayerManager().getPlayerList()) {
                // kill generic invulnerability flags/i-frames, every tick
                p.setInvulnerable(false);
                p.timeUntilRegen = 0; // remove post-hit cooldown (i-frames)
            }
        });
    }
}
