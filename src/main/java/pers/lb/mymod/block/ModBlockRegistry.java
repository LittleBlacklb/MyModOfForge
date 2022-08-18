package pers.lb.mymod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.lb.mymod.References;
import pers.lb.mymod.item.ModItemRegistry;
import pers.lb.mymod.tab.ModTab;

import java.util.function.Supplier;

public class ModBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.MOD_ID);

    public static final RegistryObject<Block> CITRINE_BLOCK = registerBlock("citrine_block", CitrineBlock::new, ModTab.CITRINE_TAB);
    public static final RegistryObject<Block> CITRINE_ORE = registerBlock("citrine_ore", CitrineOreBlock::new, ModTab.CITRINE_TAB);
    public static final RegistryObject<Block> RAW_CITRINE_BLOCK = registerBlock("raw_citrine_block",RawCitrineBlock::new, ModTab.CITRINE_TAB);

    public static final RegistryObject<Block> SPEEDY_BLOCK = registerBlock("speedy_block",SpeedyBlock::new, CreativeModeTab.TAB_MISC);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> supBlock, CreativeModeTab tab) {
        RegistryObject<T> rtn = BLOCKS.register(name, supBlock);
        registryBlockItem(name,rtn, tab);
        return rtn;
    }

    private static <T extends Block> RegistryObject<BlockItem> registryBlockItem(String name,RegistryObject<T> registeredBlock, CreativeModeTab tab) {
        return ModItemRegistry.ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties().tab(tab)));
    }


    public static void register(IEventBus iEventBus) {
        BLOCKS.register(iEventBus);
    }
}
