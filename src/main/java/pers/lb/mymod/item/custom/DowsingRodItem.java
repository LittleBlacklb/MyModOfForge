package pers.lb.mymod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.lb.mymod.util.tag.BlockTags;

import java.util.List;
import java.util.Objects;

public class DowsingRodItem extends Item {
    public DowsingRodItem() {
        super(new Properties().tab(CreativeModeTab.TAB_MISC).durability(32).fireResistant());
    }


    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
//        System.out.printf("useOn(...) call: %s Thread: %s%n", pContext.toString(), Thread.currentThread().getName());
        if (pContext.getLevel().isClientSide()) {
            Player player = Objects.requireNonNull(pContext.getPlayer(), "Player is null!");
            BlockPos clickedPos = pContext.getClickedPos();
            boolean isFindBlock = false;
            for (int yOffset = 0, i = 1; yOffset < (clickedPos.getY() + 64); yOffset++) {
                BlockPos curPos = clickedPos.below(yOffset);
                Block block = pContext.getLevel().getBlockState(curPos).getBlock();
                if (isValuableBlock(block)) {
                    player.sendMessage(new TextComponent("§l" + (i++) + ". " + blockPositionFormat(block, curPos)), player.getUUID());
                    isFindBlock = !isFindBlock;
                }
            }
            if (!isFindBlock)
                player.sendMessage(new TranslatableComponent("item.mymod.dowsing_rod.nothing_found"), player.getUUID());
        }
        pContext.getItemInHand().hurtAndBreak(1, Objects.requireNonNull(pContext.getPlayer(), "Player is null!"),
                (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown())
            pTooltipComponents.add(new TranslatableComponent("tooltip.mymod.dowsing_rod.shift"));
        else
            pTooltipComponents.add(new TranslatableComponent("tooltip.mymod.dowsing_rod"));
//        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private String blockPositionFormat(Block block, BlockPos pos) {
        return "Find valuable block §a§l%s§r in §a§l(%d, %d, %d)§r.".formatted(block.getName().getString(), pos.getX(), pos.getY(), pos.getZ());
    }

    private boolean isValuableBlock(Block block) {
//        final Block[] valuableBlocks = {
//                Blocks.DIAMOND_ORE,
//                Blocks.IRON_ORE,
//                ModBlockRegistry.CITRINE_ORE.get(),
//                ModBlockRegistry.RAW_CITRINE_BLOCK.get()
//        };
//        for (Block valuableBlock : valuableBlocks) {
//            if (block.equals(valuableBlock)) {
//                return true;
//            }
//        }
//        return false;
        return Registry.BLOCK.getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get()).is(BlockTags.DOWSING_ROD_VALUABLES);
    }

}
