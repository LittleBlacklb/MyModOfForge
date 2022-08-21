package pers.lb.mymod.esp;

import pers.lb.mymod.util.RenderBlockProp;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SyncRenderList {
    private final Set<RenderBlockProp> SYNC_RENDER_SET = Collections.synchronizedSet(new HashSet<>());

    private static final SyncRenderList instance = new SyncRenderList();

    private SyncRenderList() {
    }


    public RenderBlockProp[] getPropsIndex() {
        int size = SYNC_RENDER_SET.size();
        RenderBlockProp[] res = new RenderBlockProp[size];
        int i = 0;
        for (RenderBlockProp prop : SYNC_RENDER_SET) {
            res[i++] = prop;
        }
        return res;
    }

    public static SyncRenderList getInstance() {
        return instance;
    }

    public Set<RenderBlockProp> getSet() {
        return SYNC_RENDER_SET;
    }
}
