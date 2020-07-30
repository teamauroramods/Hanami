package com.teamaurora.hanami.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SakuraBlossomModel<T extends SakuraBlossomEntity> extends EntityModel<T> {
    public ModelRenderer square;

    public SakuraBlossomModel() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.square = new ModelRenderer(this, 0, 0);
        this.square.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.square.addBox(-8.0F, 8.0F, -8.0F, 16, 0, 16);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.square.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, 1.0F);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
