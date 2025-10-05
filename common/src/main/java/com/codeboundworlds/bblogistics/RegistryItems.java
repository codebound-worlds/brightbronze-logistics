package com.codeboundworlds.bblogistics;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.block.Block;

public class RegistryItems {
        private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BrightbronzeMod.MOD_ID,
                        Registries.ITEM);

        // Block of Brightbronze
        public static final RegistrySupplier<Item> BRIGHTBRONZE_BLOCK_ITEM = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_block"),
                        () -> new BlockItem(RegistryBlocks.BRIGHTBRONZE_BLOCK.get(),
                                        new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS)));

        // Brightbronze Terminal BlockItem
        public static final RegistrySupplier<Item> BRIGHTBRONZE_TERMINAL_ITEM = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_terminal"),
                        () -> new BlockItem(RegistryBlocks.BRIGHTBRONZE_TERMINAL.get(),
                                        new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS)));

        // Brightbronze Ingot
        public static final RegistrySupplier<Item> BRIGHTBRONZE_INGOT = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_ingot"),
                        () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));

        // Brightbronze Nugget
        public static final RegistrySupplier<Item> BRIGHTBRONZE_NUGGET = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_nugget"),
                        () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));

        // Raw Brightbronze
        public static final RegistrySupplier<Item> RAW_BRIGHTBRONZE = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "raw_brightbronze"),
                        () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));

        // Brightbronze Tool Material
        public static final Tier BRIGHTBRONZE_TIER = new Tier() {
                @Override
                public int getUses() {
                        return 250;
                }

                @Override
                public float getSpeed() {
                        return 6.0f;
                }

                @Override
                public float getAttackDamageBonus() {
                        return 2.0f;
                }

                @Override
                public TagKey<Block> getIncorrectBlocksForDrops() {
                        return BlockTags.INCORRECT_FOR_IRON_TOOL;
                }

                @Override
                public int getEnchantmentValue() {
                        return 22;
                }

                @Override
                public Ingredient getRepairIngredient() {
                        return Ingredient.of(BRIGHTBRONZE_INGOT.get());
                }
        };

        // Brightbronze Sword
        public static final RegistrySupplier<Item> BRIGHTBRONZE_SWORD = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_sword"),
                        () -> new SwordItem(BRIGHTBRONZE_TIER, new Item.Properties()
                                        .arch$tab(CreativeModeTabs.COMBAT)
                                        .attributes(SwordItem.createAttributes(BRIGHTBRONZE_TIER, 3, -2.4f))));

        // Brightbronze Pickaxe
        public static final RegistrySupplier<Item> BRIGHTBRONZE_PICKAXE = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_pickaxe"),
                        () -> new PickaxeItem(BRIGHTBRONZE_TIER, new Item.Properties()
                                        .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                                        .attributes(PickaxeItem.createAttributes(BRIGHTBRONZE_TIER, 1, -2.8f))));

        // Brightbronze Axe
        public static final RegistrySupplier<Item> BRIGHTBRONZE_AXE = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_axe"),
                        () -> new AxeItem(BRIGHTBRONZE_TIER, new Item.Properties()
                                        .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                                        .attributes(AxeItem.createAttributes(BRIGHTBRONZE_TIER, 5.0f, -3.1f))));

        // Brightbronze Shovel
        public static final RegistrySupplier<Item> BRIGHTBRONZE_SHOVEL = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_shovel"),
                        () -> new ShovelItem(BRIGHTBRONZE_TIER, new Item.Properties()
                                        .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                                        .attributes(ShovelItem.createAttributes(BRIGHTBRONZE_TIER, 1.5f, -3.0f))));

        // Brightbronze Hoe
        public static final RegistrySupplier<Item> BRIGHTBRONZE_HOE = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_hoe"),
                        () -> new HoeItem(BRIGHTBRONZE_TIER, new Item.Properties()
                                        .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                                        .attributes(HoeItem.createAttributes(BRIGHTBRONZE_TIER, -2, -1.0f))));

        // Brightbronze Helmet
        public static final RegistrySupplier<Item> BRIGHTBRONZE_HELMET = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_helmet"),
                        () -> new ArmorItem(RegistryArmorMaterials.BRIGHTBRONZE, ArmorItem.Type.HELMET,
                                        new Item.Properties()
                                                        .arch$tab(CreativeModeTabs.COMBAT)
                                                        .durability(165)));

        // Brightbronze Chestplate
        public static final RegistrySupplier<Item> BRIGHTBRONZE_CHESTPLATE = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_chestplate"),
                        () -> new ArmorItem(RegistryArmorMaterials.BRIGHTBRONZE, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()
                                                        .arch$tab(CreativeModeTabs.COMBAT)
                                                        .durability(240)));

        // Brightbronze Leggings
        public static final RegistrySupplier<Item> BRIGHTBRONZE_LEGGINGS = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_leggings"),
                        () -> new ArmorItem(RegistryArmorMaterials.BRIGHTBRONZE, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()
                                                        .arch$tab(CreativeModeTabs.COMBAT)
                                                        .durability(225)));

        // Brightbronze Boots
        public static final RegistrySupplier<Item> BRIGHTBRONZE_BOOTS = ITEMS.register(
                        ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze_boots"),
                        () -> new ArmorItem(RegistryArmorMaterials.BRIGHTBRONZE, ArmorItem.Type.BOOTS,
                                        new Item.Properties()
                                                        .arch$tab(CreativeModeTabs.COMBAT)
                                                        .durability(195)));

        public static void register() {
                ITEMS.register();
        }
}
