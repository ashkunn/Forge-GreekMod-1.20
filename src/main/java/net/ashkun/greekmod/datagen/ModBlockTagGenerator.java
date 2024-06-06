package net.ashkun.greekmod.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.block.ModBlocks;
import net.ashkun.greekmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {


    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GreekMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

//        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.SAPPHIRE_BLOCK.get()).addTag(Tags.Blocks.ORES);
//
//        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.SAPPHIRE_BLOCK.get(),
//                ModBlock.RAW_SAPPHIRE_BLOCK.get(),ModBlocks.SAPPHIRE_ORE.get(),ModBlock.SOUND_BLOCK.get());
//
//        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SAPPHIRE_BLOCK.get());
//
//        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SAPPHIRE_ORE.get());
//
//        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
//
//        //this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(ModBlock.RAW_SAPPHIRE_BLOCK.get());

    this.tag(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.PINE_LOG.get())
            .add(ModBlocks.PINE_WOOD.get())
            .add(ModBlocks.STRIPPED_PINE_LOG.get())
            .add(ModBlocks.STRIPPED_PINE_WOOD.get());

    this.tag(BlockTags.PLANKS)
            .add(ModBlocks.PINE_PLANKS.get());

    }
}
