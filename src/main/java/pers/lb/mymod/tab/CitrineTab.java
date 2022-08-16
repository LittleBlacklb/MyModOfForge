package pers.lb.mymod.tab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import pers.lb.mymod.item.ModItemRegistry;

public class CitrineTab extends CreativeModeTab {
    public CitrineTab() {
        super("citrine_tab");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return ModItemRegistry.CITRINE.get().getDefaultInstance();
    }
}
