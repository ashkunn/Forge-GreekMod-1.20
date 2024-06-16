package net.ashkun.greekmod.worldgen;

import net.ashkun.greekmod.GreekMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_tin_ore");

    public static final ResourceKey<BiomeModifier> ADD_SALT_ORE = registerKey("add_salt_ore");
    //public static final ResourceKey<BiomeModifier> ADD_END_TIN_ORE = registerKey("add_end_sapphire_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context){
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TIN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(ADD_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


//        context.register(ADD_TIN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
//                biomes.getOrThrow(BiomeTags.IS_END),
//                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY)),
//                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(GreekMod.MOD_ID, name));
    }
}
