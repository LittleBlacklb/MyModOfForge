package pers.lb.mymod.event.handler;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import pers.lb.mymod.References;

@Mod.EventBusSubscriber(modid = References.MOD_ID, value = Dist.CLIENT)
public class PlayerItemPickupEventHandler {
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void handle(PlayerEvent.ItemPickupEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = event.getStack();
        String itemDisplayName = stack.getDisplayName().getString();
        int itemCounts = stack.getCount();
        LOGGER.debug("[Actual: %s/Display: %s] picked up %d %s".formatted(player.getName().getString(), player.getDisplayName().getString(), itemCounts, itemDisplayName));
    }
}
