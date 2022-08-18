package pers.lb.mymod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CUCUMBER = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 3), 0.35f)
            .build();
}
