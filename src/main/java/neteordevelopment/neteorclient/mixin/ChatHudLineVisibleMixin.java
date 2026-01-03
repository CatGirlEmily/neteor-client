/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixin;

import com.mojang.authlib.GameProfile;
import neteordevelopment.neteorclient.mixininterface.IChatHudLineVisible;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatHudLine.Visible.class)
public abstract class ChatHudLineVisibleMixin implements IChatHudLineVisible {
    @Shadow @Final private OrderedText content;
    @Unique private int id;
    @Unique private GameProfile sender;
    @Unique private boolean startOfEntry;

    @Override
    public String neteor$getText() {
        StringBuilder sb = new StringBuilder();

        content.accept((index, style, codePoint) -> {
            sb.appendCodePoint(codePoint);
            return true;
        });

        return sb.toString();
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

    @Override
    public boolean neteor$isStartOfEntry() {
        return startOfEntry;
    }

    @Override
    public void neteor$setStartOfEntry(boolean start) {
        startOfEntry = start;
    }
}
