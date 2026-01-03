/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.mixininterface;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;

public interface IMessageHandler {
    /** Only valid inside of {@link net.minecraft.client.gui.hud.ChatHud#addMessage(Text, MessageSignatureData, MessageIndicator)} call */
    GameProfile neteor$getSender();
}
