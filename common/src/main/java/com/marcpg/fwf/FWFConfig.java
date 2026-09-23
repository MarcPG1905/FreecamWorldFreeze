package com.marcpg.fwf;

import com.marcpg.fwf.compat.FreecamImplManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public final class FWFConfig {
    private static final SystemToast.SystemToastId FWF_TOGGLE_TOAST_ID = new SystemToast.SystemToastId(3000L);

    // Ordered from oldest to newest added:
    public float tickSpeed = 0f;
    public boolean worldEffectEnabled = true;
    public boolean freezePlayer = true;

    public String getTickSpeedCategory() {
        int rounded = Math.round(tickSpeed);
        if (rounded > 20) return "Sped-Up";
        if (rounded == 20) return "No Effect";
        if (rounded > 0) return "Slow-Motion";
        return "Frozen";
    }

    public void toggleWorldEffect() {
        worldEffectEnabled = !worldEffectEnabled;
        FreecamWF.config().saveConfig();
        EffectManager.updateAll();

        notifyToggle(tickSpeed > 0f ? FZFFeature.SLOW_MOTION : FZFFeature.FREEZE, worldEffectEnabled);
    }

    public void toggleFreezePlayer() {
        // Ignore if the feature natively exists in the freecam supplier.
        if (FZFFeature.FREEZE_PLAYER.isNative())
            return;

        freezePlayer = !freezePlayer;
        FreecamWF.config().saveConfig();

        notifyToggle(FZFFeature.FREEZE_PLAYER, freezePlayer);
    }

    public void saveConfig() {
        try {
            Files.writeString(FreecamWF.configFile(), tickSpeed + "\n" + worldEffectEnabled + "\n" + freezePlayer);
        } catch (IOException e) {
            FreecamWF.logger().error("Could not save config file", e);
        }
    }

    public void loadConfig() {
        if (Files.notExists(FreecamWF.configFile()))
            return;

        try {
            List<String> lines = Files.readAllLines(FreecamWF.configFile());
            tickSpeed = Float.parseFloat(lines.get(0));
            worldEffectEnabled = Boolean.parseBoolean(lines.get(1));
            freezePlayer = Boolean.parseBoolean(lines.get(2));
        } catch (IndexOutOfBoundsException _) {
            // Ignore if a newer value does not exist in the config yet.
        } catch (IOException | NumberFormatException e) {
            FreecamWF.logger().error("Could not read config file", e);
        }
    }

    private void notifyToggle(FZFFeature feature, boolean newValue) {
        Component message = Component.translatable(
                "generic.freecam_wf.config.toggle",
                feature.translation,
                Component.translatable("generic.freecam_wf." + (newValue ? "on" : "off"))
        );

        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            player.sendOverlayMessage(message);
        } else {
            Minecraft.getInstance().gui.toastManager().addToast(new SystemToast(FWF_TOGGLE_TOAST_ID, Component.translatable("options.freecam_wf.title"), message));
        }
    }

    public static class ConfigScreen extends Screen {
        private static final Component TITLE = Component.translatable("options.freecam_wf.title");

        private final Screen parent;
        private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);

        public ConfigScreen(Screen parent) {
            super(Component.translatable("options.freecam_wf.title"));
            this.parent = parent;
        }

        @Override
        protected void init() {
            // Header
            LinearLayout header = this.layout.addToHeader(LinearLayout.vertical().spacing(8));
            header.addChild(new StringWidget(TITLE, this.font), LayoutSettings::alignHorizontallyCenter);

            GridLayout gridLayout = new GridLayout();
            gridLayout.defaultCellSetting().paddingHorizontal(4).paddingBottom(4).alignHorizontallyCenter();
            GridLayout.RowHelper helper = gridLayout.createRowHelper(2);

            // Contents
            helper.addChild(new StringWidget(Component.translatable("options.freecam_wf.compat_info", FreecamImplManager.integration().name).withColor(TextColor.GRAY), this.font), 2);

            helper.addChild(new TimeFactorSlider(
                    0, 0,
                    300, 20,
                    FreecamWF.config().tickSpeed
            ), 2);

            CycleButton<Boolean> button = CycleButton.onOffBuilder(FreecamWF.config().freezePlayer).create(Component.translatable("options.freecam_wf.freeze_player"), (_, newValue) -> {
                FreecamWF.config().freezePlayer = newValue;
                EffectManager.updateAll();
            });
            if (FZFFeature.FREEZE_PLAYER.isNative()) {
                button.active = false;
                button.setTooltip(Tooltip.create(Component.translatable("options.freecam_wf.freeze_player.disabled", FreecamImplManager.integration().name)));
            }
            helper.addChild(button);

            this.layout.addToContents(gridLayout);

            // Footer
            this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, _ -> this.onClose()).width(200).build());

            // Apply the layout
            this.layout.visitWidgets(this::addRenderableWidget);
            this.repositionElements();
        }

        @Override
        protected void repositionElements() {
            this.layout.arrangeElements();
        }

        @Override
        public void onClose() {
            minecraft.setScreenAndShow(parent);
        }

        public static final class TimeFactorSlider extends AbstractSliderButton {
            private static final Component CAPTION = Component.translatable("options.freecam_wf.factor");

            private static final float[] TPS_LIST = { 0f, 1f, 2f, 3f, 4f, 5f, 6.67f, 10f, 15f, 20f };

            public TimeFactorSlider(int x, int y, int width, int height, float initialTicks) {
                super(x, y, width, height, Component.empty(), indexOf(initialTicks) / (double) (TPS_LIST.length - 1));
                updateMessage();
            }

            private static int indexOf(float ticks) {
                int closest = 0;
                float closestDiff = Float.MAX_VALUE;
                for (int i = 0; i < TPS_LIST.length; i++) {
                    float diff = Math.abs(TPS_LIST[i] - ticks);
                    if (diff < closestDiff) {
                        closestDiff = diff;
                        closest = i;
                    }
                }
                return closest;
            }

            @Override
            protected void setValue(double newValue) {
                int index = (int) Math.round(newValue * (TPS_LIST.length - 1));
                super.setValue((double) index / (TPS_LIST.length - 1));
            }

            @Override
            protected void updateMessage() {
                float ticks = getTicks();
                setMessage(Options.genericValueLabel(CAPTION, switch ((int) ticks) {
                    case 0 -> Component.translatable("options.freecam_wf.factor.0");
                    case 20 -> Component.translatable("options.freecam_wf.factor.20");
                    default -> Component.literal(formatSpeed(ticks));
                }));
            }

            @Override
            protected void applyValue() {
                FreecamWF.config().tickSpeed = getTicks();
                EffectManager.updateAll();
            }

            @Override
            public void onRelease(@NonNull MouseButtonEvent event) {
                FreecamWF.config().saveConfig();
            }

            private float getTicks() {
                int index = (int) Math.round(value * (TPS_LIST.length - 1));
                return TPS_LIST[index];
            }

            @Contract(pure = true)
            private static @NonNull String formatSpeed(float ticks) {
                return ((float) Math.round(ticks / 20f * 100f * 10f) / 10f) + "%";
            }
        }
    }
}
