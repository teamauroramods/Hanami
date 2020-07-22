package com.teamaurora.hanami.core.other;

import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class HanamiBlockData {
    public static void registerCompostables() {
        // TODO
    }

    public static void registerFlammables() {
        // TODO
    }

    public static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_DOOR.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_TRAPDOOR.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_LEAF_CARPET.get(), RenderType.getCutoutMipped());
    }
}
