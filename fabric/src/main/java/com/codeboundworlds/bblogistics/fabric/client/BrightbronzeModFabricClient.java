package com.codeboundworlds.bblogistics.fabric.client;

import com.codeboundworlds.bblogistics.RegistryMenuTypes;
import com.codeboundworlds.bblogistics.client.BrightbronzeConnectorScreen;
import com.codeboundworlds.bblogistics.client.BrightbronzeTerminalScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public final class BrightbronzeModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register client-side screens for our menus
        MenuScreens.register(RegistryMenuTypes.BRIGHTBRONZE_CONNECTOR_MENU.get(), BrightbronzeConnectorScreen::new);
        MenuScreens.register(RegistryMenuTypes.BRIGHTBRONZE_TERMINAL_MENU.get(), BrightbronzeTerminalScreen::new);
    }
}
