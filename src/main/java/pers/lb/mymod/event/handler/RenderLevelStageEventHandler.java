package pers.lb.mymod.event.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pers.lb.mymod.References;
import pers.lb.mymod.event.task.ESPRenderProcess;

@Mod.EventBusSubscriber(modid = References.MOD_ID, value = Dist.CLIENT)
public class RenderLevelStageEventHandler {
    @SubscribeEvent
    static void handle(RenderLevelStageEvent event) {
        ESPRenderProcess.handle(event);
    }
}
