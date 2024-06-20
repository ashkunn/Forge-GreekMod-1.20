package net.ashkun.greekmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.ashkun.greekmod.worldgen.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PineFoliagePlacer extends FoliagePlacer {
    public static final Codec<PineFoliagePlacer> CODEC = RecordCodecBuilder.create(pineFoliagePlacerInstance
            -> foliagePlacerParts(pineFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(pineFoliagePlacerInstance, PineFoliagePlacer::new));
    private final int height;

    public PineFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.PINE_FOLIAGE_PLACER.get();
    }

    @Override
//    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
//                                 int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
////        int j = 0;
////        for(int i = pOffset; i >= pOffset - pFoliageHeight; --i){
////            this.placeLeavesRow(pLevel,pBlockSetter,pRandom,pConfig,pAttachment.pos(),j,i, pAttachment.doubleTrunk());
////            if(j >= 1 && i == pOffset - pFoliageHeight + 1){
////                --j;
////            } else if (j < pFoliageRadius + pAttachment.radiusOffset()){
////                ++j;
////            }
////        }
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 0, 0, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 1, -1, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 2, -2, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 1, -3, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 2, -4, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 1, -5, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 2, -6, pAttachment.doubleTrunk());
//        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 1, -7, pAttachment.doubleTrunk());
//
//
//
//    }
    protected void createFoliage(LevelSimulatedReader pLevel, FoliagePlacer.FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliagePlacer.FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos $$9 = pAttachment.pos();
        int $$10 = pRandom.nextInt(2);
        int $$11 = 1;
        int $$12 = 0;

        for(int $$13 = pOffset; $$13 >= -pFoliageHeight; --$$13) {
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, $$9, $$10, $$13, pAttachment.doubleTrunk());
            if ($$10 >= $$11) {
                $$10 = $$12;
                $$12 = 1;
                $$11 = Math.min($$11 + 1, pFoliageRadius + pAttachment.radiusOffset());
            } else {
                ++$$10;
            }
        }

    }


//    @Override
//    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
//        return this.height;
//    }
//
//    @Override
//    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
//        return false;
//    }

    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {

        return Math.max(4, pHeight - this.height);
    }

    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return pLocalX == pRange && pLocalZ == pRange && pRange > 0;
    }
}
