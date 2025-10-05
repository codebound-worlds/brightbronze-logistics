package com.codeboundworlds.bblogistics.util;

import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class ChannelUtils {
    
    private static final String[] CHANNEL_KEYS = {
        "bblogistics.channel.white",
        "bblogistics.channel.orange", 
        "bblogistics.channel.magenta",
        "bblogistics.channel.light_blue",
        "bblogistics.channel.yellow",
        "bblogistics.channel.lime",
        "bblogistics.channel.pink",
        "bblogistics.channel.gray",
        "bblogistics.channel.light_gray",
        "bblogistics.channel.cyan",
        "bblogistics.channel.purple",
        "bblogistics.channel.blue",
        "bblogistics.channel.brown",
        "bblogistics.channel.green",
        "bblogistics.channel.red",
        "bblogistics.channel.black"
    };
    
    private static final ChatFormatting[] CHANNEL_COLORS = {
        ChatFormatting.WHITE,
        ChatFormatting.GOLD,
        ChatFormatting.LIGHT_PURPLE,
        ChatFormatting.AQUA,
        ChatFormatting.YELLOW,
        ChatFormatting.GREEN,
        ChatFormatting.LIGHT_PURPLE,
        ChatFormatting.DARK_GRAY,
        ChatFormatting.GRAY,
        ChatFormatting.DARK_AQUA,
        ChatFormatting.DARK_PURPLE,
        ChatFormatting.BLUE,
        ChatFormatting.DARK_RED,
        ChatFormatting.DARK_GREEN,
        ChatFormatting.RED,
        ChatFormatting.BLACK
    };
    
    public static Component getChannelName(int channel) {
        if (channel >= 0 && channel < CHANNEL_KEYS.length) {
            return Component.translatable(CHANNEL_KEYS[channel])
                    .withStyle(CHANNEL_COLORS[channel]);
        }
        return Component.literal("Unknown Channel").withStyle(ChatFormatting.GRAY);
    }
    
    public static String getChannelKey(int channel) {
        if (channel >= 0 && channel < CHANNEL_KEYS.length) {
            return CHANNEL_KEYS[channel];
        }
        return "unknown";
    }
}