package com.teamaurora.hanami.core.other;

import com.teamaurora.hanami.core.registry.HanamiBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class HanamiRender {
    public static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_DOOR.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_TRAPDOOR.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_LADDER.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.SAKURA_SAPLING.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.POTTED_SAKURA_SAPLING.get(), RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(HanamiBlocks.AZALEA_BUSH.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HanamiBlocks.POTTED_AZALEA_BUSH.get(), RenderType.getCutoutMipped());
    }
}
