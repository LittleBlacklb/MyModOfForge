package pers.lb.mymod.event.handler;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import pers.lb.mymod.References;

@Mod.EventBusSubscriber(modid = References.MOD_ID)
public class PlayerTickEventHandler {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static Vec3 position = null;

    @SubscribeEvent
    public static void handle(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Vec3 cur_pos = player.position();
        if (position == null || (int) cur_pos.x == (int) position.x && (int) cur_pos.y == (int) position.y && (int) cur_pos.z == (int) position.z) {
            position = cur_pos;
            return;
        }
        position = cur_pos;
        String blockName = player.getLevel().getBlockState(new BlockPos(cur_pos).below()).getBlock().getName().getString();
        LOGGER.debug("The block under player: %s. Current position: (%f, %f, %f)".formatted(blockName, cur_pos.x, cur_pos.y, cur_pos.z));
    }
}
