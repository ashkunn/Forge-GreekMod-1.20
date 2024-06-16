package net.ashkun.greekmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.block.ModBlocks;
import net.ashkun.greekmod.item.ModItems;

import net.minecraft.data.recipes.*;
import net.minecraftforge.common.extensions.IForgeItem;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    //static final List<ItemLike> SAPPGIRE_SMETABLES = List.of(ModItems.TIN.get(), ModBlocks.BRONZE_BLOCK.get());
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }



    @Override
    protected void buildRecipes(RecipeOutput pWriter) {


//        oreSmelting(pWriter, SAPPGIRE_SMETABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");
 //       oreBlasting(pWriter, SAPPGIRE_SMETABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 50, "sapphire");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BRONZE_BLOCK.get())
              .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.BRONZE.get())
                .unlockedBy(getHasName(ModItems.BRONZE.get()), has(ModItems.BRONZE.get()))
                .save(pWriter);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRONZE.get(),9)
                .requires(ModBlocks.BRONZE_BLOCK.get())
               .unlockedBy(getHasName(ModBlocks.BRONZE_BLOCK.get()),has(ModBlocks.BRONZE_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHEESE.get(),1).requires(ModItems.SALT.get())
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.JUNGLE_WOOD,4).requires(ModBlocks.FIG_WOOD.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.JUNGLE_WOOD,4).requires(ModBlocks.FIG_LOG.get());

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }


    ///MIGHT BE A MAJOR PROBLEM BE CAREFULL
    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_)
                    .group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike)).save((RecipeOutput) p_250791_,GreekMod.MOD_ID + ":" + p_249236_ + "_" + getItemName(itemlike));
        }

    }

}
