/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.themes.neteor.widgets;

import neteordevelopment.neteorclient.gui.WidgetScreen;
import neteordevelopment.neteorclient.gui.themes.neteor.NeteorWidget;
import neteordevelopment.neteorclient.gui.widgets.WAccount;
import neteordevelopment.neteorclient.systems.accounts.Account;
import neteordevelopment.neteorclient.utils.render.color.Color;

public class WNeteorAccount extends WAccount implements NeteorWidget {
    public WNeteorAccount(WidgetScreen screen, Account<?> account) {
        super(screen, account);
    }

    @Override
    protected Color loggedInColor() {
        return theme().loggedInColor.get();
    }

    @Override
    protected Color accountTypeColor() {
        return theme().textSecondaryColor.get();
    }
}
