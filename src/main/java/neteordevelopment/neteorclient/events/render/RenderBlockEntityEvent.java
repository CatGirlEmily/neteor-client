/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.events.render;

import neteordevelopment.neteorclient.events.Cancellable;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;

public class RenderBlockEntityEvent extends Cancellable {
    private static final RenderBlockEntityEvent INSTANCE = new RenderBlockEntityEvent();

    public BlockEntityRenderState blockEntityState;

    public static RenderBlockEntityEvent get(BlockEntityRenderState blockEntityState) {
        INSTANCE.setCancelled(false);
        INSTANCE.blockEntityState = blockEntityState;
        return INSTANCE;
    }
}
