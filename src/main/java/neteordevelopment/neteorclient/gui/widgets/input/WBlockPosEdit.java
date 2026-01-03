/*
 * This file is part of the Neteor Client distribution (https://github.com/NeteorDevelopment/neteor-client).
 * Copyright (c) Neteor Development.
 */

package neteordevelopment.neteorclient.gui.widgets.input;

import neteordevelopment.neteorclient.NeteorClient;
import neteordevelopment.neteorclient.events.entity.player.InteractBlockEvent;
import neteordevelopment.neteorclient.events.entity.player.StartBreakingBlockEvent;
import neteordevelopment.neteorclient.gui.widgets.containers.WHorizontalList;
import neteordevelopment.neteorclient.gui.widgets.pressable.WButton;
import neteordevelopment.neteorclient.systems.modules.Modules;
import neteordevelopment.neteorclient.systems.modules.render.marker.Marker;
import neteordevelopment.neteorclient.utils.Utils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import static neteordevelopment.neteorclient.NeteorClient.mc;

public class WBlockPosEdit extends WHorizontalList {
    public Runnable action;
    public Runnable actionOnRelease;

    private WTextBox textBoxX, textBoxY, textBoxZ;

    private Screen previousScreen;

    private BlockPos value;
    private BlockPos lastValue;

    private boolean clicking;

    public WBlockPosEdit(BlockPos value) {
        this.value = value;
    }

    @Override
    public void init() {
        addTextBox();

        if (Utils.canUpdate()) {
            WButton click = add(theme.button("Click")).expandX().widget();
            click.action = () -> {
                String sb = "Click!\nRight click to pick a new position.\nLeft click to cancel.";
                Modules.get().get(Marker.class).info(sb);

                clicking = true;
                NeteorClient.EVENT_BUS.subscribe(this);
                previousScreen = mc.currentScreen;
                mc.setScreen(null);
            };

            WButton here = add(theme.button("Set Here")).expandX().widget();
            here.action = () -> {
                lastValue = value;
                set(new BlockPos(mc.player.getBlockPos()));
                newValueCheck();

                clear();
                init();
            };
        }
    }

    @EventHandler
    private void onStartBreakingBlock(StartBreakingBlockEvent event) {
        if (clicking) {
            clicking = false;
            event.cancel();
            NeteorClient.EVENT_BUS.unsubscribe(this);
            mc.setScreen(previousScreen);
        }
    }

    @EventHandler
    private void onInteractBlock(InteractBlockEvent event) {
        if (clicking) {
            if (event.result.getType() == HitResult.Type.MISS) return;
            lastValue = value;
            set(event.result.getBlockPos());
            newValueCheck();

            clear();
            init();

            clicking = false;
            event.cancel();
            NeteorClient.EVENT_BUS.unsubscribe(this);
            mc.setScreen(previousScreen);
        }
    }

    private boolean filter(String text, char c) {
        boolean good;
        boolean validate = true;

        if (c == '-' && text.isEmpty()) {
            good = true;
            validate = false;
        }
        else good = Character.isDigit(c);

        if (good && validate) {
            try {
                Integer.parseInt(text + c);
            } catch (NumberFormatException ignored) {
                good = false;
            }
        }

        return good;
    }

    public BlockPos get() {
        return value;
    }

    public void set(BlockPos value) {
        this.value = value;
    }

    private void addTextBox() {
        textBoxX = add(theme.textBox(Integer.toString(value.getX()), this::filter)).minWidth(75).widget();
        textBoxY = add(theme.textBox(Integer.toString(value.getY()), this::filter)).minWidth(75).widget();
        textBoxZ = add(theme.textBox(Integer.toString(value.getZ()), this::filter)).minWidth(75).widget();

        textBoxX.actionOnUnfocused = () -> {
            lastValue = value;
            if (textBoxX.get().isEmpty()) set(new BlockPos(0, 0, 0));
            else {
                try {
                    set(new BlockPos(Integer.parseInt(textBoxX.get()), value.getY(), value.getZ()));
                } catch (NumberFormatException ignored) {}
            }
            newValueCheck();
        };

        textBoxY.actionOnUnfocused = () -> {
            lastValue = value;
            if (textBoxY.get().isEmpty()) set(new BlockPos(0, 0, 0));
            else {
                try {
                    set(new BlockPos(value.getX(), Integer.parseInt(textBoxY.get()), value.getZ()));
                } catch (NumberFormatException ignored) {}
            }
            newValueCheck();
        };

        textBoxZ.actionOnUnfocused = () -> {
            lastValue = value;
            if (textBoxZ.get().isEmpty()) set(new BlockPos(0, 0, 0));
            else {
                try {
                    set(new BlockPos(value.getX(), value.getY(), Integer.parseInt(textBoxZ.get())));
                } catch (NumberFormatException ignored) {}
            }
            newValueCheck();
        };
    }

    private void newValueCheck() {
        if (value != lastValue) {
            if (action != null) action.run();
            if (actionOnRelease != null) actionOnRelease.run();
        }
    }
}
