/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.utils.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;

public class NoopImmediateVertexConsumerProvider extends VertexConsumerProvider.Immediate {
    public static final NoopImmediateVertexConsumerProvider INSTANCE = new NoopImmediateVertexConsumerProvider();

    private NoopImmediateVertexConsumerProvider() {
        super(null, null);
    }

    @Override
    public VertexConsumer getBuffer(RenderLayer layer) {
        return NoopVertexConsumer.INSTANCE;
    }

    @Override
    public void draw() {
    }

    @Override
    public void draw(RenderLayer layer) {
    }
}
