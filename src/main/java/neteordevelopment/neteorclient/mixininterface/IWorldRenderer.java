/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import net.minecraft.client.gl.Framebuffer;

public interface IWorldRenderer {
    void neteor$pushEntityOutlineFramebuffer(Framebuffer framebuffer);

    void neteor$popEntityOutlineFramebuffer();
}
