package net.ashkun.greekmod.worldgen.biome;

import net.ashkun.greekmod.GreekMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(new ResourceLocation(GreekMod.MOD_ID, "overworld"), 5));
    }
}
