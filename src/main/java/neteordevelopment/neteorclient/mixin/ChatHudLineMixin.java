/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import com.mojang.authlib.GameProfile;
import neteordevelopment.neteorclient.mixininterface.IChatHudLine;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = ChatHudLine.class)
public abstract class ChatHudLineMixin implements IChatHudLine {
    @Shadow @Final private Text content;
    @Unique private int id;
    @Unique private GameProfile sender;

    @Override
    public String neteor$getText() {
        return content.getString();
    }

    @Override
    public int neteor$getId() {
        return id;
    }

    @Override
    public void neteor$setId(int id) {
        this.id = id;
    }

    @Override
    public GameProfile neteor$getSender() {
        return sender;
    }

    @Override
    public void neteor$setSender(GameProfile profile) {
        sender = profile;
    }
}
