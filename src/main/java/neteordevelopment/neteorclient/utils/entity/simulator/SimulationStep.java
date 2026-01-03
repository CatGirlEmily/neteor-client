/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.utils.entity.simulator;

import net.minecraft.util.hit.HitResult;

public class SimulationStep {
    public static final SimulationStep MISS = new SimulationStep(true);

    public boolean shouldStop;
    public HitResult[] hitResults;

    public SimulationStep(boolean stop, HitResult... hitResults) {
        this.shouldStop = stop;
        this.hitResults = hitResults;
    }
}
