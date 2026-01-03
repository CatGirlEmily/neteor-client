/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.themes.neteor.widgets.pressable;

import neteordevelopment.neteorclient.gui.themes.neteor.NeteorWidget;
import neteordevelopment.neteorclient.gui.widgets.pressable.WFavorite;
import neteordevelopment.neteorclient.utils.render.color.Color;

public class WNeteorFavorite extends WFavorite implements NeteorWidget {
    public WNeteorFavorite(boolean checked) {
        super(checked);
    }

    @Override
    protected Color getColor() {
        return theme().favoriteColor.get();
    }
}
