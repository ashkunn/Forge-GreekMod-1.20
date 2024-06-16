package net.ashkun.greekmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(12).saturationMod(0.8f).build();
    public static final FoodProperties GRAPE = new FoodProperties.Builder().nutrition(4).saturationMod(0.2f).build();

    //public static final FoodProperties BARLEY = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();
}
