/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.screens.settings;

import neteordevelopment.neteorclient.gui.GuiTheme;
import neteordevelopment.neteorclient.gui.WindowScreen;
import neteordevelopment.neteorclient.gui.utils.Cell;
import neteordevelopment.neteorclient.gui.widgets.WLabel;
import neteordevelopment.neteorclient.gui.widgets.WWidget;
import neteordevelopment.neteorclient.gui.widgets.containers.WTable;
import neteordevelopment.neteorclient.gui.widgets.containers.WView;
import neteordevelopment.neteorclient.gui.widgets.input.WDropdown;
import neteordevelopment.neteorclient.gui.widgets.input.WTextBox;
import neteordevelopment.neteorclient.gui.widgets.pressable.WButton;
import neteordevelopment.neteorclient.renderer.Fonts;
import neteordevelopment.neteorclient.renderer.text.FontFamily;
import neteordevelopment.neteorclient.renderer.text.FontInfo;
import neteordevelopment.neteorclient.settings.FontFaceSetting;
import org.apache.commons.lang3.Strings;

import java.util.List;

public class FontFaceSettingScreen extends WindowScreen {
    private final FontFaceSetting setting;

    private WTable table;

    private WTextBox filter;
    private String filterText = "";

    public FontFaceSettingScreen(GuiTheme theme, FontFaceSetting setting) {
        super(theme, "Select Font");

        this.setting = setting;
    }

    @Override
    public void initWidgets() {
        filter = add(theme.textBox("")).expandX().widget();
        filter.setFocused(true);
        filter.action = () -> {
            filterText = filter.get().trim();

            table.clear();
            initTable();
        };

        window.view.hasScrollBar = false;

        enterAction = () -> {
            List<Cell<?>> row = table.getRow(0);
            if (row == null) return;

            WWidget widget = row.get(2).widget();
            if (widget instanceof WButton button) {
                button.action.run();
            }
        };

        WView view = add(theme.view()).expandX().widget();
        view.scrollOnlyWhenMouseOver = false;
        table = view.add(theme.table()).expandX().widget();

        initTable();
    }

    private void initTable() {
        for (FontFamily fontFamily : Fonts.FONT_FAMILIES) {
            String name = fontFamily.getName();

            WLabel item = theme.label(name);
            if (!filterText.isEmpty() && !Strings.CI.contains(name, filterText)) continue;
            table.add(item);

            WDropdown<FontInfo.Type> dropdown = table.add(theme.dropdown(FontInfo.Type.Regular)).right().widget();

            WButton select = table.add(theme.button("Select")).expandCellX().right().widget();
            select.action = () -> {
                setting.set(fontFamily.get(dropdown.get()));
                close();
            };

            table.row();
        }
    }
}
