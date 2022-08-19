package pers.lb.mymod.event.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pers.lb.mymod.References;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MOD_ID, value = Dist.CLIENT)
public class ClientChatEventHandler {
    @SubscribeEvent
    public static void handle(ClientChatEvent event) {
        String msg = event.getMessage();
        LocalPlayer player = Objects.requireNonNull(Minecraft.getInstance().player, "Player is null!");
        player.sendMessage(new TextComponent("§lYou said: §n" + msg), player.getUUID());
    }

}
