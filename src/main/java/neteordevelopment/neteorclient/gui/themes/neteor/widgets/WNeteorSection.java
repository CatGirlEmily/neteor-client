/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.themes.neteor.widgets;

import neteordevelopment.neteorclient.gui.renderer.GuiRenderer;
import neteordevelopment.neteorclient.gui.themes.neteor.NeteorWidget;
import neteordevelopment.neteorclient.gui.widgets.WWidget;
import neteordevelopment.neteorclient.gui.widgets.containers.WSection;
import neteordevelopment.neteorclient.gui.widgets.pressable.WTriangle;

public class WNeteorSection extends WSection {
    public WNeteorSection(String title, boolean expanded, WWidget headerWidget) {
        super(title, expanded, headerWidget);
    }

    @Override
    protected WHeader createHeader() {
        return new WNeteorHeader(title);
    }

    protected class WNeteorHeader extends WHeader {
        private WTriangle triangle;

        public WNeteorHeader(String title) {
            super(title);
        }

        @Override
        public void init() {
            add(theme.horizontalSeparator(title)).expandX();

            if (headerWidget != null) add(headerWidget);

            triangle = new WHeaderTriangle();
            triangle.theme = theme;
            triangle.action = this::onClick;

            add(triangle);
        }

        @Override
        protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
            triangle.rotation = (1 - animProgress) * -90;
        }
    }

    protected static class WHeaderTriangle extends WTriangle implements NeteorWidget {
        @Override
        protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
            renderer.rotatedQuad(x, y, width, height, rotation, GuiRenderer.TRIANGLE, theme().textColor.get());
        }
    }
}
