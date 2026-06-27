package net.mruevich.siege_evolved.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static FoodProperties TEA_MUSHROOM = new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20*200), 1).build();

    public static FoodProperties TEMPE = new FoodProperties.Builder().fast().nutrition(3).build();
}
