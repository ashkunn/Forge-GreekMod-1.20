package net.ashkun.greekmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.ashkun.greekmod.GreekMod;
import net.ashkun.greekmod.block.ModBlocks;

public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GreekMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NEGROMOD_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BRONZE.get()))
            .title(Component.translatable("creativetab.tutorial_tab"))
            .displayItems((pParameters, pOutput) -> {

                pOutput.accept(ModItems.BARLEY_SEEDS.get());
                pOutput.accept(ModItems.BARLEY.get());




            })
            .build());

    public static void register(IEventBus eventbus){
        CREATIVE_MODE_TABS.register(eventbus);

    }
}
