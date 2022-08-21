package pers.lb.mymod.util;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.Nullable;


public class Utils {
    /**
     * @param prefix The command prefix
     * @param msg    The message sent by player
     * @return null if the first character is not the prefix else return parameter array
     * <br>
     * Exp: prefix='$'
     * <br>
     * "$test p1" --> ["test", "p1"]
     * <br>
     * "test p1" --> null
     */
    public static String @Nullable [] getCommandParams(char prefix, String msg) {
        if (msg.charAt(0) != prefix) return null;
        msg = msg.substring(1);
        return msg.split(" ");
    }

    public static void sendMsg(String msg, LocalPlayer player) {
        player.sendMessage(new TextComponent(msg), player.getUUID());
    }
}
