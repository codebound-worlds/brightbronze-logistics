package com.codeboundworlds.bblogistics;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

public final class BrightbronzeMod {
    public static final String MOD_ID = "bblogistics";

    public static void init() {
        // Fires when a player joins (integrated or dedicated server)
        PlayerEvent.PLAYER_JOIN.register((ServerPlayer player) -> {
            player.displayClientMessage(Component.literal("Hello World"), false);
        });
    }
}
