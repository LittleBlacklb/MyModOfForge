package pers.lb.mymod.util.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import pers.lb.mymod.References;

public class BlockTags {

    public static final TagKey<Block> DOWSING_ROD_VALUABLES = tag("dowsing_rod_valuables");

    private static TagKey<Block> tag(String name) {
        return net.minecraft.tags.BlockTags.create(new ResourceLocation(References.MOD_ID, name));
    }

    private static TagKey<Block> tagForge(String name) {
        return net.minecraft.tags.BlockTags.create(new ResourceLocation("forge", name));
    }
}
