package pers.lb.mymod.event.handler;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;
import pers.lb.mymod.References;
import pers.lb.mymod.esp.SyncRenderList;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MOD_ID, value = Dist.CLIENT)
public class RenderLevelStageEventHandler {
    private static VertexBuffer vertexBuffer;
    public static boolean requestedRefresh = false;

    @SubscribeEvent
    static void renderBlocks(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_CUTOUT_BLOCKS || Minecraft.getInstance().player == null) {
            return;
        }
        if (vertexBuffer == null || requestedRefresh) {
            System.out.println("TEMP renderBlocks Called");
            requestedRefresh = false;
            vertexBuffer = new VertexBuffer();

            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder buffer = tesselator.getBuilder();

            var opacity = 1F;

            buffer.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);

            SyncRenderList.getInstance().getSet().forEach(blockProps -> {
                if (blockProps == null) {
                    return;
                }
                final float size = 1.0f;
                final double x = blockProps.getPos().getX(), y = blockProps.getPos().getY(), z = blockProps.getPos().getZ();

                final float red = (blockProps.getColor() >> 16 & 0xff) / 255f;
                final float green = (blockProps.getColor() >> 8 & 0xff) / 255f;
                final float blue = (blockProps.getColor() & 0xff) / 255f;

                // TOP
                // AB
                buffer.vertex(x, y + size, z).color(red, green, blue, opacity).endVertex();  // A
                buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).endVertex();  // B

                // BC
                buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).endVertex();  // B
                buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();  // C

                // CD
                buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();  // C
                buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).endVertex();  // D

                // DA
                buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).endVertex();  // D
                buffer.vertex(x, y + size, z).color(red, green, blue, opacity).endVertex();  // A

                // BOTTOM
                buffer.vertex(x + size, y, z).color(red, green, blue, opacity).endVertex();  // A'
                buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).endVertex();  // B'

                buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).endVertex();  //B'
                buffer.vertex(x, y, z + size).color(red, green, blue, opacity).endVertex();  // C'

                buffer.vertex(x, y, z + size).color(red, green, blue, opacity).endVertex();  // C'
                buffer.vertex(x, y, z).color(red, green, blue, opacity).endVertex();  // D'

                buffer.vertex(x, y, z).color(red, green, blue, opacity).endVertex();  // D'
                buffer.vertex(x + size, y, z).color(red, green, blue, opacity).endVertex();  //A'

                // Edge 1
                buffer.vertex(x + size, y, z + size).color(red, green, blue, opacity).endVertex();  // B'
                buffer.vertex(x + size, y + size, z + size).color(red, green, blue, opacity).endVertex();  // C

                // Edge 2
                buffer.vertex(x + size, y, z).color(red, green, blue, opacity).endVertex();  // A'
                buffer.vertex(x + size, y + size, z).color(red, green, blue, opacity).endVertex();  // B

                // Edge 3
                buffer.vertex(x, y, z + size).color(red, green, blue, opacity).endVertex();  // C'
                buffer.vertex(x, y + size, z + size).color(red, green, blue, opacity).endVertex();  // D

                // Edge 4
                buffer.vertex(x, y, z).color(red, green, blue, opacity).endVertex();  // D'
                buffer.vertex(x, y + size, z).color(red, green, blue, opacity).endVertex(); // A
            });

            vertexBuffer.bind();
            buffer.end();
            vertexBuffer.upload(buffer);
            VertexBuffer.unbind();
        }

        if (vertexBuffer != null) {
            Vec3 view = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition();

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL11.GL_DEPTH_TEST);

            RenderSystem.setShader(GameRenderer::getPositionColorShader);

            PoseStack matrix = event.getPoseStack();
            matrix.pushPose();
            matrix.translate(-view.x, -view.y, -view.z);

            vertexBuffer.bind();
            vertexBuffer.drawWithShader(matrix.last().pose(), event.getProjectionMatrix().copy(), Objects.requireNonNull(RenderSystem.getShader(), "Get null MotherFucker!"));
            VertexBuffer.unbind();
            matrix.popPose();

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }
    }
}
