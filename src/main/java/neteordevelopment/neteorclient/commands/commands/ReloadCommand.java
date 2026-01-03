/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import neteordevelopment.neteorclient.commands.Command;
import neteordevelopment.neteorclient.renderer.Fonts;
import neteordevelopment.neteorclient.systems.Systems;
import neteordevelopment.neteorclient.systems.friends.Friend;
import neteordevelopment.neteorclient.systems.friends.Friends;
import neteordevelopment.neteorclient.utils.network.Capes;
import neteordevelopment.neteorclient.utils.network.NeteorExecutor;
import net.minecraft.command.CommandSource;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("reload", "Reloads many systems.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            warning("Reloading systems, this may take a while.");

            Systems.load();
            Capes.init();
            Fonts.refresh();
            NeteorExecutor.execute(() -> Friends.get().forEach(Friend::updateInfo));

            return SINGLE_SUCCESS;
        });
    }
}
