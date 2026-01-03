/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import neteordevelopment.neteorclient.systems.modules.Modules;
import neteordevelopment.neteorclient.systems.modules.render.Fullbright;
import neteordevelopment.neteorclient.systems.modules.render.NoRender;
import neteordevelopment.neteorclient.utils.entity.EntityUtils;
import neteordevelopment.neteorclient.utils.render.color.Color;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.LightType;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {

    @Unique private NoRender noRender;

    // neteor is already initialised at this point
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(EntityRendererFactory.Context context, CallbackInfo ci) {
        noRender = Modules.get().get(NoRender.class);
    }

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void onRenderLabel(T entity, CallbackInfoReturnable<Text> cir) {
        if (noRender.noNametags()) cir.setReturnValue(null);
        if (!(entity instanceof PlayerEntity player)) return;
    }

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void shouldRender(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (noRender.noEntity(entity)) cir.setReturnValue(false);
        if (noRender.noFallingBlocks() && entity instanceof FallingBlockEntity) cir.setReturnValue(false);
    }


    @ModifyReturnValue(method = "getSkyLight", at = @At("RETURN"))
    private int onGetSkyLight(int original) {
        return Math.max(Modules.get().get(Fullbright.class).getLuminance(LightType.SKY), original);
    }

    @ModifyReturnValue(method = "getBlockLight", at = @At("RETURN"))
    private int onGetBlockLight(int original) {
        return Math.max(Modules.get().get(Fullbright.class).getLuminance(LightType.BLOCK), original);
    }

    @ModifyExpressionValue(method = "updateRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getLightLevel(Lnet/minecraft/world/LightType;Lnet/minecraft/util/math/BlockPos;)I"))
    private int onGetLightLevel(int original) {
        return Math.max(Modules.get().get(Fullbright.class).getLuminance(LightType.BLOCK), original);
    }

    @Inject(method = "updateShadow(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/render/entity/state/EntityRenderState;)V", at = @At("HEAD"), cancellable = true)
    private void updateShadow(Entity entity, EntityRenderState renderState, CallbackInfo ci) {
        if (noRender.noDeadEntities() &&
            entity instanceof LivingEntity &&
            renderState instanceof LivingEntityRenderState livingEntityRenderState &&
            livingEntityRenderState.deathTime > 0) {
            ci.cancel();
        }
    }
}
