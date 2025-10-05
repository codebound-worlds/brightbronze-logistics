package com.codeboundworlds.bblogistics;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeTerminal;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeConnector;

public class RegistryBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BrightbronzeMod.MOD_ID,
            Registries.BLOCK);

    // Block of Brightbronze
    public static final RegistrySupplier<Block> BRIGHTBRONZE_BLOCK = BLOCKS.register(
            ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_block"),
            () -> new Block(Block.Properties.of().strength(3.0f).requiresCorrectToolForDrops()));

    // Brightbronze Terminal (container block)
    public static final RegistrySupplier<Block> BRIGHTBRONZE_TERMINAL = BLOCKS.register(
            ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_terminal"),
            BrightbronzeTerminal::new);

    // Brightbronze Connector (face-attached plate)
    public static final RegistrySupplier<Block> BRIGHTBRONZE_CONNECTOR = BLOCKS.register(
            ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_connector"),
            BrightbronzeConnector::new);

    public static void register() {
        BLOCKS.register();
        // Block entity builder now references BRIGHTBRONZE_TERMINAL supplier directly.
    }
}