package pers.lb.mymod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class CitrineBlock extends Block {

    public CitrineBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops());
    }
}
