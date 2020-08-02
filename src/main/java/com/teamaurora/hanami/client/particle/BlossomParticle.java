package com.teamaurora.hanami.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;

public class BlossomParticle extends SpriteTexturedParticle {
    protected final IAnimatedSprite animatedSprite;
    private final float rotSpeed;

    public BlossomParticle(IAnimatedSprite animatedSprite, ClientWorld world, double posX, double posY, double posZ) {
        super(world, posX, posY, posZ);
        //this.angle = (float) Math.random() * ((float)Math.PI * 2F);
        this.particleAngle = (float) Math.random() * ((float)Math.PI * 2F);
        this.animatedSprite = animatedSprite;
        this.maxAge = rand.nextInt(30) + 60;
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.05F;
        this.particleScale *= 2.0F;
        this.selectSpriteRandomly(animatedSprite);
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionY -= (double)0.002F;
            this.motionY = Math.max(this.motionY, (double)-0.1F);
            this.prevParticleAngle = this.particleAngle;
            if(!this.onGround) {
                this.particleAngle += (float)Math.PI * this.rotSpeed * 1.6F;
            } else {
                this.motionY = 0.0D;
            }
        }
        this.particleAlpha = this.age >= this.maxAge - 30 ? ((float)this.maxAge - (float)this.age) / 30 : 1.0F;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite animatedSprite) {
            this.spriteSet = animatedSprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BlossomParticle(this.spriteSet, world, x, y, z);
        }
    }
}
