package dev.foltz.spawnimmunityremover.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerNoSpawnInvulMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void simr$clearJoinInvul(CallbackInfo ci) {
        ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;

        ((ServerPlayerEntityAccessor) self).setJoinInvulnerabilityTicks(0);

        self.setInvulnerable(false);
        self.timeUntilRegen = 0;
    }
}
