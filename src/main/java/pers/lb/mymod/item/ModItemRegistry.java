package pers.lb.mymod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.lb.mymod.References;
import pers.lb.mymod.item.custom.DowsingRodItem;

public class ModItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MOD_ID);

    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine", CitrineItem::new);

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine", RawCitrineItem::new);

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod", DowsingRodItem::new);

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber", () -> new Item(
            new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.CUCUMBER)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
