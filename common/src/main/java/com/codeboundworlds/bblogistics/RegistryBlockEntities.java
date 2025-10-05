package com.codeboundworlds.bblogistics;

import com.codeboundworlds.bblogistics.blocks.BrightbronzeTerminalEntity;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeConnectorEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class RegistryBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(BrightbronzeMod.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<BrightbronzeTerminalEntity>> BRIGHTBRONZE_TERMINAL_ENTITY = BLOCK_ENTITIES
            .register(
                    ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_terminal"),
                    () -> BlockEntityType.Builder
                            .of(BrightbronzeTerminalEntity::new, RegistryBlocks.BRIGHTBRONZE_TERMINAL.get())
                            .build(null));

    public static final RegistrySupplier<BlockEntityType<BrightbronzeConnectorEntity>> BRIGHTBRONZE_CONNECTOR_ENTITY = BLOCK_ENTITIES
            .register(
                    ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_connector"),
                    () -> BlockEntityType.Builder
                            .of(BrightbronzeConnectorEntity::new, RegistryBlocks.BRIGHTBRONZE_CONNECTOR.get())
                            .build(null));

    public static void register() {
        BLOCK_ENTITIES.register();
    }
}
