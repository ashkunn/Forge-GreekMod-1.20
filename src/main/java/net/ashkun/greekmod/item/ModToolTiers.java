package net.ashkun.greekmod.item;

import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.util.ModTags;
import net.minecraft.ResourceLocationException;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier BRONZE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.BRONZE.get())),
            new ResourceLocation(GreekMod.MOD_ID, "bronze"), List.of(Tiers.IRON), List.of());
}
