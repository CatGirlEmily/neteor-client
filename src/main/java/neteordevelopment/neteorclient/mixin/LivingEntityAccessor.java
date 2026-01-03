/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Invoker("swimUpward")
    void neteor$swimUpwards(TagKey<Fluid> fluid);

    @Accessor("jumping")
    boolean neteor$isJumping();

    @Accessor("jumpingCooldown")
    int neteor$getJumpCooldown();

    @Accessor("jumpingCooldown")
    void neteor$setJumpCooldown(int cooldown);
}
