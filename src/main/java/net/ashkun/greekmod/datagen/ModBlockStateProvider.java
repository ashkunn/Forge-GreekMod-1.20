package net.ashkun.greekmod.datagen;


import net.ashkun.greekmod.block.custom.BarleyCropBlock;
import net.ashkun.greekmod.block.custom.GrapeVineBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.block.ModBlocks;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GreekMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BRONZE_BLOCK);
        blockWithItem(ModBlocks.TIN_ORE);


        makeBarleyCrop((CropBlock) ModBlocks.BARLEY_CROP.get(), "barleygrowth","barleygrowth");
        makeGrapeVine((GrapeVineBlock) ModBlocks.GRAPEVINEBLOCK.get(), "grapevinegrown","grapevinegrown");

        logBlock(((RotatedPillarBlock) ModBlocks.PINE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.PINE_WOOD.get()), blockTexture(ModBlocks.PINE_LOG.get()), blockTexture(ModBlocks.PINE_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_LOG.get()), blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()),
                new ResourceLocation(GreekMod.MOD_ID, "block/stripped_pine_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_WOOD.get()), blockTexture(ModBlocks.PINE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()));

        blockItem(ModBlocks.PINE_LOG);
        blockItem(ModBlocks.PINE_WOOD);
        blockItem(ModBlocks.STRIPPED_PINE_LOG);
        blockItem(ModBlocks.STRIPPED_PINE_WOOD);

        blockWithItem(ModBlocks.PINE_PLANKS);

        leavesBlock(ModBlocks.PINE_LEAVES);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(GreekMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }


    public void makeBarleyCrop(CropBlock block, String modelName, String textureName){

        Function<BlockState, ConfiguredModel[]> function = state -> BarleyStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] BarleyStates(BlockState state, CropBlock block, String modelName, String textureName){
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BarleyCropBlock)block).getAgeProperty()),
                new ResourceLocation(GreekMod.MOD_ID, "block/" + textureName + state.getValue(((BarleyCropBlock)block).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    public void makeGrapeVine(GrapeVineBlock block, String modelName, String textureName){

        Function<BlockState, ConfiguredModel[]> function = state -> GrapeStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);



    }

    private ConfiguredModel[] GrapeStates(BlockState state, GrapeVineBlock block, String modelName, String textureName){
        ConfiguredModel[] models = new ConfiguredModel[1];
        ResourceLocation RL = new ResourceLocation(GreekMod.MOD_ID, ("block/" + textureName + state.getValue(block.getAgeProperty())));
        models[0] = new ConfiguredModel(
                models().crop(modelName + state.getValue((block.getAgeProperty())),RL));
        return models;
    }


    private void blockWithItem(RegistryObject<Block> blockRegistryObject){

        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));

    }

}

