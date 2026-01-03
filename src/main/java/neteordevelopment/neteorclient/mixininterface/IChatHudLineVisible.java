/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

public interface IChatHudLineVisible extends IChatHudLine {
    boolean neteor$isStartOfEntry();
    void neteor$setStartOfEntry(boolean start);
}
