/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.screens.settings;

import neteordevelopment.neteorclient.gui.GuiTheme;
import neteordevelopment.neteorclient.gui.screens.settings.base.CollectionListSettingScreen;
import neteordevelopment.neteorclient.gui.widgets.WWidget;
import neteordevelopment.neteorclient.settings.PacketListSetting;
import neteordevelopment.neteorclient.settings.Setting;
import neteordevelopment.neteorclient.utils.network.PacketUtils;
import net.minecraft.network.packet.Packet;

import java.util.Set;
import java.util.function.Predicate;

public class PacketBoolSettingScreen extends CollectionListSettingScreen<Class<? extends Packet<?>>> {
    public PacketBoolSettingScreen(GuiTheme theme, Setting<Set<Class<? extends Packet<?>>>> setting) {
        super(theme, "Select Packets", setting, setting.get(), PacketUtils.PACKETS);
    }

    @Override
    protected boolean includeValue(Class<? extends Packet<?>> value) {
        Predicate<Class<? extends Packet<?>>> filter = ((PacketListSetting) setting).filter;

        if (filter == null) return true;
        return filter.test(value);
    }

    @Override
    protected WWidget getValueWidget(Class<? extends Packet<?>> value) {
        return theme.label(PacketUtils.getName(value));
    }

    @Override
    protected String[] getValueNames(Class<? extends Packet<?>> value) {
        return new String[]{
            PacketUtils.getName(value),
            value.getSimpleName()
        };
    }
}
