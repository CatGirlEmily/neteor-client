/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.themes.neteor.widgets;

import neteordevelopment.neteorclient.gui.renderer.GuiRenderer;
import neteordevelopment.neteorclient.gui.themes.neteor.NeteorWidget;
import neteordevelopment.neteorclient.gui.widgets.containers.WView;

public class WNeteorView extends WView implements NeteorWidget {
    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        if (canScroll && hasScrollBar) {
            renderer.quad(handleX(), handleY(), handleWidth(), handleHeight(), theme().scrollbarColor.get(handlePressed, handleMouseOver));
        }
    }
}
