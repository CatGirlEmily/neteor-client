/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.item.property.numeric.CompassState;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CompassState.class)
public abstract class CompassStateMixin {
    @ModifyExpressionValue(method = "getBodyYaw", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/HeldItemContext;getBodyYaw()F"))
    private static float callLivingEntityGetYaw(float original) {
        return original;
    }

    @ModifyReturnValue(method = "getAngleTo(Lnet/minecraft/util/HeldItemContext;Lnet/minecraft/util/math/BlockPos;)D", at = @At("RETURN"))
    private static double modifyGetAngleTo(double original, HeldItemContext from, BlockPos to) {
        return original;
    }
}
