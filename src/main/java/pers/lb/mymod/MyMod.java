package pers.lb.mymod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import pers.lb.mymod.block.ModBlockRegistry;
import pers.lb.mymod.item.ModItemRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(References.MOD_ID)
public class MyMod {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MyMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlockRegistry.register(eventBus);
        ModItemRegistry.register(eventBus);
        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

}
