package net.ashkun.greekmod.datagen;


import net.ashkun.greekmod.block.custom.BarleyCropBlock;
import net.ashkun.greekmod.block.custom.GrapeVineBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
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

