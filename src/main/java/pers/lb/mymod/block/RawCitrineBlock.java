package pers.lb.mymod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class RawCitrineBlock extends Block {

    public RawCitrineBlock() {
        super(Properties.of(Material.METAL).strength(7f).requiresCorrectToolForDrops());
    }
}
