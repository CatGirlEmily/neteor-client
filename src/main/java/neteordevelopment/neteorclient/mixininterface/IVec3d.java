/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.joml.Vector3d;

@SuppressWarnings("UnusedReturnValue")
public interface IVec3d {
    Vec3d neteor$set(double x, double y, double z);

    default Vec3d neteor$set(Vec3i vec) {
        return neteor$set(vec.getX(), vec.getY(), vec.getZ());
    }

    default Vec3d neteor$set(Vector3d vec) {
        return neteor$set(vec.x, vec.y, vec.z);
    }

    default Vec3d neteor$set(Vec3d pos) {
        return neteor$set(pos.x, pos.y, pos.z);
    }

    Vec3d neteor$setXZ(double x, double z);

    Vec3d neteor$setY(double y);
}
