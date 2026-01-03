/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.ProfileResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.SocialInteractionsManager;
import net.minecraft.client.resource.ResourceReloadLogger;
import net.minecraft.client.session.ProfileKeys;
import net.minecraft.client.session.Session;
import net.minecraft.client.session.report.AbuseReportContext;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.util.ApiServices;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAccessor {
    @Accessor("currentFps")
    static int neteor$getFps() {
        return 0;
    }

    @Mutable
    @Accessor("session")
    void neteor$setSession(Session session);

    @Accessor("resourceReloadLogger")
    ResourceReloadLogger neteor$getResourceReloadLogger();

    @Accessor("attackCooldown")
    int neteor$getAttackCooldown();

    @Accessor("attackCooldown")
    void neteor$setAttackCooldown(int attackCooldown);

    @Invoker("doAttack")
    boolean neteor$leftClick();

    @Mutable
    @Accessor("profileKeys")
    void neteor$setProfileKeys(ProfileKeys keys);

    @Mutable
    @Accessor("userApiService")
    void neteor$setUserApiService(UserApiService apiService);

    @Mutable
    @Accessor("skinProvider")
    void neteor$setSkinProvider(PlayerSkinProvider skinProvider);

    @Mutable
    @Accessor("socialInteractionsManager")
    void neteor$setSocialInteractionsManager(SocialInteractionsManager socialInteractionsManager);

    @Mutable
    @Accessor("abuseReportContext")
    void neteor$setAbuseReportContext(AbuseReportContext abuseReportContext);

    @Mutable
    @Accessor("gameProfileFuture")
    void neteor$setGameProfileFuture(CompletableFuture<ProfileResult> future);

    @Mutable
    @Accessor("apiServices")
    void neteor$setApiServices(ApiServices apiServices);
}
