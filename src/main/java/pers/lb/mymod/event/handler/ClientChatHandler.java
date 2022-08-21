package pers.lb.mymod.event.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pers.lb.mymod.References;
import pers.lb.mymod.event.task.ESPCommandsProcess;

@Mod.EventBusSubscriber(modid = References.MOD_ID, value = Dist.CLIENT)
public class ClientChatHandler {

    @SubscribeEvent
    public static void handle(ClientChatEvent event) {
        ESPCommandsProcess.handle(event);
    }
}
