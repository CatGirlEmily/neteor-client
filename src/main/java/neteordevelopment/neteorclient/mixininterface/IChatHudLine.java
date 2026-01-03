/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import com.mojang.authlib.GameProfile;

public interface IChatHudLine {
    String neteor$getText();

    int neteor$getId();

    void neteor$setId(int id);

    GameProfile neteor$getSender();

    void neteor$setSender(GameProfile profile);
}
