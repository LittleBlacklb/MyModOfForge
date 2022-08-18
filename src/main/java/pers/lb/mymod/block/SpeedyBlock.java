package pers.lb.mymod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

public class SpeedyBlock extends Block {
    public SpeedyBlock() {
        super(Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops());
    }

    @Override
    public void stepOn(Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        if (!pLevel.isClientSide()) {
            LivingEntity livingEntity = ((LivingEntity) pEntity);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 3, false, true), livingEntity);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
