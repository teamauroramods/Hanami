package com.teamaurora.hanami.client.render.entity;

import com.teamaurora.hanami.client.model.SakuraBlossomModel;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class SakuraBlossomRender extends LivingRenderer<SakuraBlossomEntity, SakuraBlossomModel<SakuraBlossomEntity>> {
    public SakuraBlossomRender(EntityRendererManager renderManager) {
        super(renderManager, new SakuraBlossomModel<>(), 0.0F);
    }

    @Override
    protected RenderType func_230496_a_(SakuraBlossomEntity blossom, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        return RenderType.getEntityCutout(this.getEntityTexture(blossom));
    }

    @Override
    public ResourceLocation getEntityTexture(SakuraBlossomEntity entity) {
        return new ResourceLocation(Hanami.MODID, "textures/entity/sakura_blossom.png");
    }

    @Override
    protected boolean canRenderName(SakuraBlossomEntity entity) {
        return false;
    }
}
