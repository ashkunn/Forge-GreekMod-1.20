package net.ashkun.greekmod.datagen.loot;

import net.ashkun.greekmod.block.custom.BarleyCropBlock;
import net.ashkun.greekmod.block.custom.GrapeVineBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.ashkun.greekmod.block.ModBlocks;
import net.ashkun.greekmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables(){

        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate(){
        this.dropSelf(ModBlocks.BRONZE_BLOCK.get());
        this.dropSelf(ModBlocks.TIN_ORE.get());


        //this.add(ModBlocks.SAPPHIRE_ORE.get(), block -> createCopperLikeOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));



        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE,4));

        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.GRAPEVINEBLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrapeVineBlock.AGE,4));

        this.add(ModBlocks.BARLEY_CROP.get(),createCropDrops(ModBlocks.BARLEY_CROP.get(),ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), lootitemcondition$builder));

        this.add(ModBlocks.GRAPEVINEBLOCK.get(),createCropDrops(ModBlocks.GRAPEVINEBLOCK.get(),ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), lootitemcondition$builder));

        this.dropSelf(ModBlocks.PINE_LOG.get());
        this.dropSelf(ModBlocks.PINE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_PINE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_PINE_WOOD.get());
        this.dropSelf(ModBlocks.PINE_PLANKS.get());

        this.add(ModBlocks.PINE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BRONZE_BLOCK.get(), NORMAL_LEAVES_SAPLING_CHANCES));




    }

    protected  LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item){
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(
                        UniformGenerator.between(2.0F, 5.0F))).apply(
                        ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));

    }


    @Override
    protected Iterable<Block> getKnownBlocks() {

        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}



