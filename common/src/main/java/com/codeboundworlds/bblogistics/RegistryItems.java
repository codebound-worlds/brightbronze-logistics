package com.codeboundworlds.bblogistics;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;

public class RegistryItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BrightbronzeMod.MOD_ID, Registries.ITEM);

    // Block of Brightbronze
    public static final RegistrySupplier<Item> BRIGHTBRONZE_BLOCK_ITEM = ITEMS.register(
        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_block"),
        () -> new BlockItem(RegistryBlocks.BRIGHTBRONZE_BLOCK.get(),
            new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS))
    );

    // Brightbronze Ingot
    public static final RegistrySupplier<Item> BRIGHTBRONZE_INGOT = ITEMS.register(
        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_ingot"),
        () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS))
    );

    // Brightbronze Nugget
    public static final RegistrySupplier<Item> BRIGHTBRONZE_NUGGET = ITEMS.register(
        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_nugget"),
        () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS))
    );

    // Raw Brightbronze
    public static final RegistrySupplier<Item> RAW_BRIGHTBRONZE = ITEMS.register(
        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "raw_brightbronze"),
        () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS))
    );

    public static void register() {
        ITEMS.register();
    }
}