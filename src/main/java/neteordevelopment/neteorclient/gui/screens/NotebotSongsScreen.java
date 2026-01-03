/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.screens;

import neteordevelopment.neteorclient.NeteorClient;
import neteordevelopment.neteorclient.gui.GuiTheme;
import neteordevelopment.neteorclient.gui.WindowScreen;
import neteordevelopment.neteorclient.gui.widgets.containers.WTable;
import neteordevelopment.neteorclient.gui.widgets.input.WTextBox;
import neteordevelopment.neteorclient.gui.widgets.pressable.WButton;
import neteordevelopment.neteorclient.systems.modules.Modules;
import neteordevelopment.neteorclient.systems.modules.misc.Notebot;
import neteordevelopment.neteorclient.utils.Utils;
import neteordevelopment.neteorclient.utils.notebot.decoder.SongDecoders;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

public class NotebotSongsScreen extends WindowScreen {
    private static final Notebot notebot = Modules.get().get(Notebot.class);

    private WTextBox filter;
    private String filterText = "";

    private WTable table;

    public NotebotSongsScreen(GuiTheme theme) {
        super(theme, "Notebot Songs");
    }

    @Override
    public void initWidgets() {
        // Random Song
        WButton randomSong = add(theme.button("Random Song")).minWidth(400).expandX().widget();
        randomSong.action = notebot::playRandomSong;

        // Filter
        filter = add(theme.textBox("", "Search for the songs...")).minWidth(400).expandX().widget();
        filter.setFocused(true);
        filter.action = () -> {
            filterText = filter.get().trim();

            table.clear();
            initSongsTable();
        };

        table = add(theme.table()).widget();

        initSongsTable();
    }

    private void initSongsTable() {
        AtomicBoolean noSongsFound = new AtomicBoolean(true);
        try {
            Files.list(NeteorClient.FOLDER.toPath().resolve("notebot")).forEach(path -> {
                if (SongDecoders.hasDecoder(path)) {
                    String name = path.getFileName().toString();

                    if (Utils.searchTextDefault(name, filterText, false)){
                        addPath(path);
                        noSongsFound.set(false);
                    }
                }
            });
        } catch (IOException e) {
            table.add(theme.label("Missing neteor-client/notebot folder.")).expandCellX();
            table.row();
        }

        if (noSongsFound.get()) {
            table.add(theme.label("No songs found.")).expandCellX().center();
        }
    }

    private void addPath(Path path) {
        table.add(theme.horizontalSeparator()).expandX().minWidth(400);
        table.row();

        table.add(theme.label(FilenameUtils.getBaseName(path.getFileName().toString()))).expandCellX();
        WButton load = table.add(theme.button("Load")).right().widget();
        load.action = () -> notebot.loadSong(path.toFile());
        WButton preview = table.add(theme.button("Preview")).right().widget();
        preview.action = () -> notebot.previewSong(path.toFile());

        table.row();
    }
}
