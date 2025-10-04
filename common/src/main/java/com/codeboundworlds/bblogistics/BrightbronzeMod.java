package com.codeboundworlds.bblogistics;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

public final class BrightbronzeMod {
    public static final String MOD_ID = "bblogistics";

    public static final TagKey<Item> BRIGHTBRONZE_TOOLS = TagKey.create(Registries.ITEM,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "brightbronze_tools"));
    public static final TagKey<Item> BRIGHTBRONZE_ARMOR = TagKey.create(Registries.ITEM,
        ResourceLocation.fromNamespaceAndPath(MOD_ID, "brightbronze_armor"));

    public static void init() {
        // Fires when a player joins (integrated or dedicated server)
        PlayerEvent.PLAYER_JOIN.register((ServerPlayer player) -> {
            player.displayClientMessage(Component.literal("Hello World"), false);
        });

        RegistryArmorMaterials.register();
        RegistryBlocks.register();
        RegistryItems.register();
    }
}
