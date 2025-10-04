package com.codeboundworlds.bblogistics;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public class RegistryArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BrightbronzeMod.MOD_ID,
            Registries.ARMOR_MATERIAL);

    public static final RegistrySupplier<ArmorMaterial> BRIGHTBRONZE = ARMOR_MATERIALS.register(
            ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze"),
            () -> new ArmorMaterial(
                    Map.of(
                            ArmorItem.Type.BOOTS, 2,
                            ArmorItem.Type.LEGGINGS, 5,
                            ArmorItem.Type.CHESTPLATE, 6,
                            ArmorItem.Type.HELMET, 2
                    ),
                    22,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(RegistryItems.BRIGHTBRONZE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(BrightbronzeMod.MOD_ID, "brightbronze"))),
                    0.0f,
                    0.0f
            ));

    public static void register() {
        ARMOR_MATERIALS.register();
    }
}
