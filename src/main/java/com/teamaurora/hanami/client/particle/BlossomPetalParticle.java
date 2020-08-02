package com.teamaurora.hanami.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;

public class BlossomPetalParticle extends SpriteTexturedParticle {
    protected final IAnimatedSprite animatedSprite;
    private float angle;

    public BlossomPetalParticle(IAnimatedSprite animatedSprite, ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        //this.angle = (float) Math.random() * ((float)Math.PI * 2F);
        this.particleAngle = (float) Math.random() * ((float)Math.PI * 2F);
        this.animatedSprite = animatedSprite;
        this.maxAge = rand.nextInt(20) + 20;
        this.particleScale *= 1.0F;
        this.selectSpriteWithAge(animatedSprite);
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevParticleAngle = this.particleAngle;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            //this.motionY -= 0.04D * this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionY -= (double)0.006F;
            this.motionY = Math.max(this.motionY, (double)-0.25F);
            //this.motionX += Math.cos(this.angle) * 0.0001;
            //this.motionZ += Math.sin(this.angle) * 0.0001;
            //this.motionY *= 0.98D;
        }
        this.selectSpriteWithAge(this.animatedSprite);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private IAnimatedSprite animatedSprite;

        public Factory(IAnimatedSprite animatedSprite) {
            this.animatedSprite = animatedSprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BlossomPetalParticle(this.animatedSprite, world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
