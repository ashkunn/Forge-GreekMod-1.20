package net.ashkun.greekmod.datagen;

import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ashkun.greekmod.GreekMod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {


    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, GreekMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PINE_LOG.get().asItem())
                .add(ModBlocks.PINE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_PINE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_PINE_WOOD.get().asItem());


        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.PINE_PLANKS.get().asItem());

    }


}
