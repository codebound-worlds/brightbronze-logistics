package com.codeboundworlds.bblogistics;

import com.codeboundworlds.bblogistics.menu.BrightbronzeConnectorMenu;
import com.codeboundworlds.bblogistics.menu.BrightbronzeTerminalMenu;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class RegistryMenuTypes {
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister
            .create(BrightbronzeMod.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<BrightbronzeConnectorMenu>> BRIGHTBRONZE_CONNECTOR_MENU = MENU_TYPES
            .register(ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_connector"),
                    () -> new MenuType<>(BrightbronzeConnectorMenu::new, FeatureFlags.VANILLA_SET));

    public static final RegistrySupplier<MenuType<BrightbronzeTerminalMenu>> BRIGHTBRONZE_TERMINAL_MENU = MENU_TYPES
            .register(ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_terminal"),
                    () -> new MenuType<>(BrightbronzeTerminalMenu::new, FeatureFlags.VANILLA_SET));

    public static void register() {
        MENU_TYPES.register();
    }
}