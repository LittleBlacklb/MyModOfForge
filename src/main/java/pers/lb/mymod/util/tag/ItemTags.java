package pers.lb.mymod.util.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import pers.lb.mymod.References;

public class ItemTags {

    public static final TagKey<Item> CITRINE_GEMS = tagForge("gems/citrine");

    private static TagKey<Item> tag(String name) {
        return net.minecraft.tags.ItemTags.create(new ResourceLocation(References.MOD_ID, name));
    }

    private static TagKey<Item> tagForge(String name) {
        return net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", name));
    }
}
