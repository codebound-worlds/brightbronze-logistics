package com.codeboundworlds.bblogistics.fabric;

import net.fabricmc.api.ModInitializer;

import com.codeboundworlds.bblogistics.BrightbronzeMod;

public final class BrightbronzeModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BrightbronzeMod.init();
    }
}
