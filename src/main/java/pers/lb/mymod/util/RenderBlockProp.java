package pers.lb.mymod.util;

import com.google.common.base.Objects;
import net.minecraft.core.BlockPos;

import javax.annotation.concurrent.Immutable;

@Immutable
public class RenderBlockProp {
    private final int color;
    private final BlockPos pos;

    public RenderBlockProp(BlockPos pos, int color) {
        this.pos = pos;
        this.color = color;
    }

    public RenderBlockProp(int x, int y, int z, int color) {
        this(new BlockPos(x, y, z), color);
    }

    public int getColor() {
        return color;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenderBlockProp that = (RenderBlockProp) o;
        return Objects.equal(pos, that.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pos);
    }

}
