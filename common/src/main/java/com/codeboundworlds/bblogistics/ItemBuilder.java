package com.codeboundworlds.bblogistics;

import java.util.function.Supplier;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class ItemBuilder {
    private static DeferredRegister<Block> BLOCKS = DeferredRegister.create(BrightbronzeMod.MOD_ID, Registries.BLOCK);
    private static DeferredRegister<Item> ITEMS = DeferredRegister.create(BrightbronzeMod.MOD_ID, Registries.ITEM);

    public static void init() {
        RegistrySupplier<Block> bb_block = registerBlock("brightbronze_block", () -> new Block(
            Block.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));
        BLOCKS.register();

        registerItem("brightbronze_block", () -> new BlockItem(bb_block.get(),
            new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS)));
        registerItem("brightbronze_ingot", () -> new Item(
            new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));
        registerItem("brightbronze_nugget", () -> new Item(
            new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));
        ITEMS.register();
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, name), item);
    }

    public static RegistrySupplier<Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, name), block);
    }
}
