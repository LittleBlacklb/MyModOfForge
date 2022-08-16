package pers.lb.mymod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class CitrineOre extends Block {

    public CitrineOre() {
        super(Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops());
    }
}
