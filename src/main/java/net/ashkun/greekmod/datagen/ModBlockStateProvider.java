package net.ashkun.greekmod.datagen;


import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import net.ashkun.greekmod.block.custom.BarleyCropBlock;
import net.ashkun.greekmod.block.custom.GrapeVineBlock;
import net.minecraft.Util;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.block.ModBlocks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.data.models.BlockModelGenerators.MULTIFACE_GENERATOR;
import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GreekMod.MOD_ID, exFileHelper);

    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BRONZE_BLOCK);
        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.SALT_ORE);



        makeBarleyCrop((CropBlock) ModBlocks.BARLEY_CROP.get(), "barleygrowth","barleygrowth");
       // makeGrapeVine((GrapeVineBlock) ModBlocks.GRAPEVINEBLOCK.get(), "grapevinegrown","grapevinegrown");

        logBlock(((RotatedPillarBlock) ModBlocks.PINE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.PINE_WOOD.get()), blockTexture(ModBlocks.PINE_LOG.get()), blockTexture(ModBlocks.PINE_LOG.get()));

//        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_LOG.get()), blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()),
//                new ResourceLocation(GreekMod.MOD_ID, "block/stripped_pine_log_top"));
//        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_WOOD.get()), blockTexture(ModBlocks.PINE_LOG.get()),
//                blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()));

        blockItem(ModBlocks.PINE_LOG);
        blockItem(ModBlocks.PINE_WOOD);
       // blockItem(ModBlocks.STRIPPED_PINE_LOG);
      //  blockItem(ModBlocks.STRIPPED_PINE_WOOD);

        //blockWithItem(ModBlocks.PINE_PLANKS);

       // leavesBlock(ModBlocks.PINE_LEAVES);

        //blockItem(ModBlocks.GRAPEVINEBLOCK);


        //this.blockItem(ModBlocks.GRAPEVINEBLOCK);
       // this.createMultiface(ModBlocks.GRAPEVINEBLOCK.get());
        logBlock(((RotatedPillarBlock) ModBlocks.FIG_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.FIG_WOOD.get()), blockTexture(ModBlocks.FIG_LOG.get()), blockTexture(ModBlocks.FIG_LOG.get()));


        blockItem(ModBlocks.FIG_LOG);
        blockItem(ModBlocks.FIG_WOOD);

        leavesBlock(ModBlocks.FIG_LEAVES);

        saplingBlock(ModBlocks.FIG_SAPLING);

    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject){

        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
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

        Function<BlockState, ConfiguredModel[]> function = state -> {
            try {
                return GrapeStates(state, block, modelName, textureName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        getVariantBuilder(block).forAllStates(function);



    }


    private ConfiguredModel[] GrapeStates(BlockState state, GrapeVineBlock block, String modelName, String textureName) throws IOException {
        ConfiguredModel[] models = new ConfiguredModel[1];
       // ModelFile vinesModel = models().("vines", modLoc("block/vines"));

        ResourceLocation RL = new ResourceLocation(GreekMod.MOD_ID, ("block/" + textureName + state.getValue(block.getAgeProperty())));
       // models().singleTexture(modelName, ResourceLocation.tryParse(("block" + "/crop")), "crop", RL);



       models[0] = new ConfiguredModel(
//                // models().crop(modelName + state.getValue((block.getAgeProperty())),RL));
//               // models(). crop(modelName + state.getValue((block.getAgeProperty())),RL));
//
                models().singleTexture(modelName + state.getValue((block.getAgeProperty())), ResourceLocation.tryParse("block" + "/vine"), "vine", RL)
                );
               // models().singleTexture(modelName, ResourceLocation.tryParse(("block" + "/crop")), "crop", RL));
//models = ConfiguredModel.allRotations(models().singleTexture(modelName + state.getValue((block.getAgeProperty())), ResourceLocation.tryParse("block" + "/vine"), "vine", RL),
   //     true, 1);
     //   System.out.println(Paths.get(RL.getPath()));
       // Files.delete(Path.of(RL.getPath() + ".json"));
        //RL.
       // Files.delete(Paths.get( RL. + ".json"));
        return models;




    }


    private void blockWithItem(RegistryObject<Block> blockRegistryObject){

        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));

    }

    private void createMultiface(Block pMultifaceBlock) {

        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(pMultifaceBlock);
        MultiPartGenerator multipartgenerator = MultiPartGenerator.multiPart(pMultifaceBlock);
        Condition.TerminalCondition condition$terminalcondition = Util.make(Condition.condition(), (p_236295_) -> {
            MULTIFACE_GENERATOR.stream().map(Pair::getFirst).forEach((p_236299_) -> {
                if (pMultifaceBlock.defaultBlockState().hasProperty(p_236299_)) {
                    p_236295_.term(p_236299_, false);
                }

            });
        });
    }


}





