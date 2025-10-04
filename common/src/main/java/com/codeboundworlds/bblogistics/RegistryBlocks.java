package com.codeboundworlds.bblogistics;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class RegistryBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BrightbronzeMod.MOD_ID, Registries.BLOCK);

    // Block of Brightbronze
    public static final RegistrySupplier<Block> BRIGHTBRONZE_BLOCK = BLOCKS.register(
        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_block"),
        () -> new Block(Block.Properties.of().strength(3.0f).requiresCorrectToolForDrops())
    );

    public static void register() {
        BLOCKS.register();
    }
}