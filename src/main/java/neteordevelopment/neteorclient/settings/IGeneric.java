/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.settings;

import neteordevelopment.neteorclient.gui.GuiTheme;
import neteordevelopment.neteorclient.gui.WidgetScreen;
import neteordevelopment.neteorclient.utils.misc.ICopyable;
import neteordevelopment.neteorclient.utils.misc.ISerializable;

public interface IGeneric<T extends IGeneric<T>> extends ICopyable<T>, ISerializable<T> {
    WidgetScreen createScreen(GuiTheme theme, GenericSetting<T> setting);
}
