package pers.lb.mymod.event.task;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.ClientChatEvent;
import pers.lb.mymod.esp.SyncRenderList;
import pers.lb.mymod.util.RenderBlockProp;

import java.util.Objects;

import static pers.lb.mymod.util.Utils.getCommandParams;
import static pers.lb.mymod.util.Utils.sendMsg;

public class ESPCommandsProcess {
    private static final SyncRenderList srlInstance = SyncRenderList.getInstance();

    private static RenderBlockProp[] propsIndex;

    private static boolean isListed = false;

    public static void handle(ClientChatEvent event) {
        String[] params = getCommandParams('$', event.getMessage());
        if (params == null) return;
        LocalPlayer player = Objects.requireNonNull(Minecraft.getInstance().player, "Suck my player");
        // params.length = [2, ...)
        switch (params[0]) {
            // add x,y,z color
            case "add" -> {
                if (params.length != 3) {
                    sendMsg("Error command parameters!", player);
                    break;
                }
                int rgbColor = Integer.parseInt(params[2]);
                rgbColor = rgbColor <= 0xFFFFFF ? rgbColor : 0;  // If out of color range, then set BLACK as default.

                String[] positionStr = params[1].split(",");
                int[] positionInt = new int[positionStr.length];
                if (positionStr.length != 3) {
                    sendMsg("Error position!", player);
                    break;
                }
                for (int i = 0; i < positionStr.length; i++) {
                    if (positionStr[i].equals("~")) {
                        switch (i) {
                            case 0 -> positionInt[i] = (int) player.getX();
                            case 1 -> positionInt[i] = (int) player.getY();
                            case 2 -> positionInt[i] = (int) player.getZ();
                        }
                    } else {
                        try {
                            positionInt[i] = Integer.parseInt(positionStr[i]);
                        } catch (NumberFormatException ignore) {
                            sendMsg("Error position format!", player);
                            break;
                        }
                    }
                }
                SyncRenderList.getInstance().getSet().add(new RenderBlockProp(
                        positionInt[0],
                        positionInt[1],
                        positionInt[2],
                        rgbColor));
                ESPRenderProcess.requestedRefresh = true;
            }
            // remove i
            case "remove" -> {
                if (params.length != 2) {
                    sendMsg("Error command parameters!", player);
                    break;
                }
                if (!isListed) {
                    sendMsg("List before use.", player);
                    break;
                }
                int i = Integer.parseInt(params[1]);
                if (i >= propsIndex.length) sendMsg("Out of range.", player);
                if (srlInstance.getSet().remove(propsIndex[i])) {
                    sendMsg("Successfully removed §l§e%s§r".formatted(propsIndex[i].getPos()), player);
                    // GC
                    propsIndex[i] = null;
                } else {
                    sendMsg("Fail to remove %s".formatted(propsIndex[i].getPos()), player);
                }
                isListed = false;
                ESPRenderProcess.requestedRefresh = true;
            }
            case "list" -> {
                isListed = true;
                propsIndex = srlInstance.getPropsIndex();
                for (int i = 0; i < propsIndex.length; i++) {
                    sendMsg("§l%d§r. %s".formatted(i, propsIndex[i].getPos().toShortString()), player);
                }
            }
            case "refresh" -> ESPRenderProcess.requestedRefresh = true;
        }
        event.setCanceled(true);  // Not to send msg.
    }
}
