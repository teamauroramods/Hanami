package com.teamaurora.hanami.common.entity;

import com.teamaurora.hanami.client.particle.HanamiParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ThrownSakuraBlossomEntity extends ProjectileItemEntity {
    private double distance;
    private int age;

    public ThrownSakuraBlossomEntity(EntityType<? extends ThrownSakuraBlossomEntity> entityType, World world) {
        super(entityType, world);
        distance = 0;
        age = 0;
    }

    public ThrownSakuraBlossomEntity(World world, LivingEntity thrower) {
        super(EntityType.SNOWBALL, thrower, world);
        distance = 0;
        age = 0;
    }

    public ThrownSakuraBlossomEntity(World world, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, world);
        distance = 0;
        age = 0;
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    @Override
    public void tick() {
        Vector3d prevPos = this.getPositionVec();
        super.tick();
        distance = distance + this.getPositionVec().distanceTo(prevPos);
        age = age + 1;
        if (distance >= 6) {
            if (!this.world.isRemote) {
                this.world.setEntityState(this, (byte) 4);
                BlockPos pos = this.getOnPosition();
                SakuraBlossomEntity blossom = new SakuraBlossomEntity(this.world, pos, pos.getX(), pos.getY(), pos.getZ(), false);

                world.addEntity(blossom);

                this.remove();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        ItemStack itemStack = this.func_213882_k();
        return itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemStack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            this.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);

            IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
            for (int i = 0; i < 16; ++i) {
                this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
            }
        }
        super.handleStatusUpdate(id);
    }

    private double getParticleOffset(double value) {
        return value + (this.world.rand.nextDouble() * 0.2F) - 0.1F;
    }

    private double getRandWithMagnitude(double mag) {
        return (this.world.rand.nextDouble() * 2 * mag) - mag;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }
}
