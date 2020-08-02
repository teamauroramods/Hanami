package com.teamaurora.hanami.common.entity.block;

import com.teamaurora.hanami.client.particle.HanamiParticles;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.registry.HanamiEffects;
import com.teamaurora.hanami.core.registry.HanamiEntities;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class BlombEntity extends TNTEntity {
    private static final DataParameter<Integer> FUSE = EntityDataManager.createKey(BlombEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Float> POWER = EntityDataManager.createKey(BlombEntity.class, DataSerializers.FLOAT);
    @Nullable
    private LivingEntity tntPlacedBy;
    private int fuse = 20;
    private boolean toYeet = false;

    public BlombEntity(EntityType<? extends BlombEntity> type, World worldIn) {
        super(type, worldIn);
        this.preventEntitySpawning = true;
    }

    public BlombEntity(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(HanamiEntities.BLOMB.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(20);
        this.setPower(1.0F);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(FUSE, 20);
        this.dataManager.register(POWER, 1.0F);
    }

    @Override
    public void tick() {
        toYeet = false;
        if (!this.hasNoGravity()) {
            this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getMotion());
        this.setMotion(this.getMotion().scale(0.98D));
        if (this.onGround) {
            this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
        }

        --this.fuse;
        if (this.fuse <= 0) {
            this.remove();
            if (!this.world.isRemote) {
                //this.yeet();
                this.toYeet = true;
                this.world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        } else {
            this.func_233566_aG_();
            if (this.world.isRemote) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
        if (toYeet) {
            this.yeet();
        }
    }

    private Vector3d vecSub (Vector3d a, Vector3d b) {
        return new Vector3d(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    private Vector3d vecScale (float a, Vector3d b) {
        return new Vector3d(a * b.getX(), a * b.getY(), a * b.getZ());
    }

    public void yeet() {
        if (this.world.isRemote) {
            this.world.playSound(this.getPosX(), this.getPosYHeight(0.0625), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
        } else {
            AxisAlignedBB explosionBB = this.getBoundingBox().grow(5);
            List<Entity> entitiesAbove = this.world.getEntitiesWithinAABBExcludingEntity(null, explosionBB);
            if (!entitiesAbove.isEmpty()) {
                for (Entity entity : entitiesAbove) {
                    Vector3d offsetVector = vecSub(entity.getPositionVec(), this.getPositionVec()).normalize();
                    float yeetPower = this.getPower() * (float)(Math.sqrt(18)/Math.sqrt(11));
                    if (entity instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity) entity;
                        if (living.isPotionActive(HanamiEffects.INSTABILITY.get())) {
                            yeetPower = yeetPower * (float)Math.sqrt(living.getActivePotionEffect(HanamiEffects.INSTABILITY.get()).getAmplifier() + 2);
                        }
                    }
                    entity.addVelocity(yeetPower * offsetVector.getX(), yeetPower * offsetVector.getY(), yeetPower * offsetVector.getZ());
                    if (entity instanceof ServerPlayerEntity) {
                        ServerPlayerEntity player = (ServerPlayerEntity) entity;
                        player.connection.sendPacket(new SEntityVelocityPacket(entity));
                    }
                }
                // this is *very* hacky but it works
            }
        }
        //Minecraft.getInstance().world.addParticle(ParticleTypes.EXPLOSION_EMITTER,false, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        for (int i = 0; i < 128; ++i) {
            Minecraft.getInstance().world.addParticle(HanamiParticles.BLOSSOM_PETAL.get(), this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.25), this.getRandWithMagnitude(0.25), this.getRandWithMagnitude(0.25));
        }
    }

    private double getParticleOffset(double value) {
        return value + (this.world.rand.nextDouble()) - 0.5F;
    }

    private double getRandWithMagnitude(double mag) {
        return (this.world.rand.nextDouble() * 2 * mag) - mag;
    }

    protected void writeAdditional(CompoundNBT compound) {
        compound.putShort("Fuse", (short)this.getFuse());
        compound.putFloat("Power", this.getPower());
    }

    protected void readAdditional(CompoundNBT compound) {
        this.setFuse(compound.getShort("Fuse"));
        this.setPower(compound.getFloat("Power"));
    }

    @Nullable
    public LivingEntity getTntPlacedBy() {
        return this.tntPlacedBy;
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.0F;
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (FUSE.equals(key)) {
            this.fuse = this.getFuseDataManager();
        }
    }

    public int getFuseDataManager() {
        return this.dataManager.get(FUSE);
    }

    public int getFuse() {
        return this.fuse;
    }

    public void setPower(float power) {
        this.dataManager.set(POWER, power);
    }

    public float getPower() {
        return this.dataManager.get(POWER);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}