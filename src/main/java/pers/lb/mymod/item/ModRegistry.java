package pers.lb.mymod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.lb.mymod.References;

public class ModRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MOD_ID);

    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", Citrine::new);

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine", RawCitrine::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
