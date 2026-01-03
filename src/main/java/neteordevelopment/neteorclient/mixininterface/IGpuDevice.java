/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import com.mojang.blaze3d.systems.RenderPass;

public interface IGpuDevice {
    /**
     * Currently there can only be a single scissor pushed at once.
     */
    void neteor$pushScissor(int x, int y, int width, int height);

    void neteor$popScissor();

    /**
     * This is an *INTERNAL* method, it shouldn't be called.
     */
    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    void neteor$onCreateRenderPass(RenderPass pass);
}
