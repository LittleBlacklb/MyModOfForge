package pers.lb.mymod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class CitrineOreBlock extends Block {

    public CitrineOreBlock() {
        super(Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops());
    }
}
