/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import net.minecraft.util.math.BlockPos;

public interface IBox {
    void neteor$expand(double v);

    void neteor$set(double x1, double y1, double z1, double x2, double y2, double z2);

    default void neteor$set(BlockPos pos) {
        neteor$set(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }
}
