package net.ashkun.greekmod.datagen;

import net.ashkun.greekmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GreekMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        simpleItem(ModItems.TIN);
//        simpleItem(ModItems.BRONZE);
//        simpleItem(ModItems.BARLEY_SEEDS);
//        simpleItem(ModItems.BARLEY);
//        evenSimplerBlockItem(ModBlocks.GRAPEVINEBLOCK);

        simpleItem(ModBlocks.PINE_SAPLING);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Block> item){

        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(GreekMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(GreekMod.MOD_ID,"block/" + item.getId().getPath()));
    }


    public void evenSimplerBlockItem(RegistryObject<Block> block){
        this.withExistingParent(GreekMod.MOD_ID  + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));

    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(GreekMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(GreekMod.MOD_ID,"block/" + item.getId().getPath()));
    }

}
