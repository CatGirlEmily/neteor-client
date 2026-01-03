/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import neteordevelopment.neteorclient.renderer.MeshUniforms;
import neteordevelopment.neteorclient.utils.render.postprocess.OutlineUniforms;
import neteordevelopment.neteorclient.utils.render.postprocess.PostProcessShader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public abstract class RenderSystemMixin {
    @Inject(method = "flipFrame", at = @At("TAIL"))
    private static void neteor$flipFrame(CallbackInfo info) {
        MeshUniforms.flipFrame();
        PostProcessShader.flipFrame();
        OutlineUniforms.flipFrame();
    }
}
