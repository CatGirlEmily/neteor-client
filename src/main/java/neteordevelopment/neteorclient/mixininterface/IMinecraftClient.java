/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import net.minecraft.client.gl.Framebuffer;

public interface IMinecraftClient {
    void neteor$rightClick();

    void neteor$setFramebuffer(Framebuffer framebuffer);
}
