package net.ashkun.greekmod;

import com.mojang.logging.LogUtils;
import net.ashkun.greekmod.block.ModBlocks;
import net.ashkun.greekmod.block.custom.ModPortalBlock;
import net.ashkun.greekmod.item.ModItems;
import net.ashkun.greekmod.worldgen.ModBiomeModifiers;
import net.ashkun.greekmod.worldgen.ModConfiguredFeatures;
import net.ashkun.greekmod.worldgen.ModPlacedFeatures;
import net.ashkun.greekmod.worldgen.biome.ModTerrablender;
import net.ashkun.greekmod.worldgen.biome.surface.ModSurfaceRules;
import net.ashkun.greekmod.worldgen.tree.ModFoliagePlacers;
import net.ashkun.greekmod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Overwrite;
import terrablender.api.SurfaceRuleManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static net.ashkun.greekmod.block.ModBlocks.VANILLA_BLOCKS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GreekMod.MOD_ID)
public class GreekMod
{
    public static final String MOD_ID = "greekmod";
    private static final Logger LOGGER = LogUtils.getLogger();


    public void onClientSetup(FMLClientSetupEvent event) {
        // Register your resource pack here if necessary
    }


    public GreekMod()
    {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        VANILLA_BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);
        ModTerrablender.registerBiomes();
            MinecraftForge.EVENT_BUS.register(this);






        }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.BRONZE);
            event.accept(ModItems.TIN);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.TIN_ORE);
            event.accept(ModBlocks.SALT_ORE);

            event.accept(ModBlocks.PINE_LOG);
            event.accept(ModBlocks.PINE_WOOD);
            event.accept(ModBlocks.STRIPPED_PINE_LOG);
            event.accept(ModBlocks.STRIPPED_PINE_WOOD);

            event.accept(ModBlocks.PINE_PLANKS);
            event.accept(ModBlocks.PINE_LEAVES);

            event.accept(ModBlocks.PINE_SAPLING);

            event.accept(ModBlocks.GRASS_BLOCK);
            event.accept(ModBlocks.DIRT);
            event.accept(ModBlocks.STONE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.BRONZE_BLOCK);
            event.accept(ModBlocks.MOD_PORTAL);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.BRONZE_BOOTS);
            event.accept(ModItems.BRONZE_CHESTPLATE);
            event.accept(ModItems.BRONZE_HELMET);

            event.accept(ModItems.JAVELIN);
            event.accept(ModItems.XIPHOS);
            event.accept(ModItems.KOPIS);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.BARLEY);
            event.accept(ModItems.BARLEY_SEEDS);
            event.accept(ModBlocks.GRAPEVINEBLOCK);
        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

}
