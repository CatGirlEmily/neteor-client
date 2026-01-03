/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.systems.modules.player;

import neteordevelopment.neteorclient.settings.BoolSetting;
import neteordevelopment.neteorclient.settings.Setting;
import neteordevelopment.neteorclient.settings.SettingGroup;
import neteordevelopment.neteorclient.systems.modules.Categories;
import neteordevelopment.neteorclient.systems.modules.Module;

public class Multitask extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> attackingEntities = sgGeneral.add(new BoolSetting.Builder()
        .name("attacking-entities")
        .description("Lets you attack entities while using an item.")
        .defaultValue(true)
        .build()
    );

    public Multitask() {
        super(Categories.Player, "multitask", "Lets you use items and attack at the same time.");
    }

    public boolean attackingEntities() {
        return isActive() && attackingEntities.get();
    }
}
