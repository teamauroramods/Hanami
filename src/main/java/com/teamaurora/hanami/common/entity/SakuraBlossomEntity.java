package com.teamaurora.hanami.common.entity;

import com.teamaurora.hanami.client.particle.HanamiParticles;
import com.teamaurora.hanami.core.registry.HanamiEntities;
import com.teamaurora.hanami.core.registry.HanamiItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;
import java.util.List;

public class SakuraBlossomEntity extends LivingEntity {
    private static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(SakuraBlossomEntity.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Boolean> WILD = EntityDataManager.createKey(SakuraBlossomEntity.class, DataSerializers.BOOLEAN);
    private boolean playedSound;
    private int age;
    private double movCheck;
    private double movCheck1;
    private double movCheck2;
    private double movCheck3;

    public SakuraBlossomEntity(EntityType<? extends SakuraBlossomEntity> blossom, World worldIn) {
        super(blossom, worldIn);
        age = 0;
    }

    public SakuraBlossomEntity(World worldIn, BlockPos origin, double x, double y, double z, boolean wild) {
        this(HanamiEntities.SAKURA_BLOSSOM.get(), worldIn);
        this.setHealth(1);
        this.setOrigin(new BlockPos(origin));
        this.setWild(wild);
        this.setPosition(x + 0.5D, y, z + 0.5D);
        this.setNoGravity(true);
        if (wild) {
            this.prevRenderYawOffset = rand.nextFloat() * 360.0F;
            this.renderYawOffset = rand.nextFloat() * 360.0F;
        } else {
            this.prevRenderYawOffset = 180.0F;
            this.renderYawOffset = 180.0F;
        }
        this.rotationYaw = 180.0F;
        this.movCheck = 0.0F;
        this.movCheck1 = 0.0F;
        this.movCheck2 = 0.0F;
        this.movCheck3 = 0.0F;
        this.playedSound = wild;
        age = 0;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ORIGIN, BlockPos.ZERO);
        this.dataManager.register(WILD, false);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putLong("OriginPos", this.getOrigin().toLong());
        nbt.putBoolean("Wild", this.getWild());
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        this.setOrigin(BlockPos.fromLong(nbt.getLong("OriginPos")));
        this.setWild(nbt.getBoolean("Wild"));
    }

    @Override
    public void tick() {
        super.tick();

        if (age >= 5 && this.movCheck3 == this.getPosY()) {
            if(!this.getWild()) this.playHurtSound(DamageSource.GENERIC);
            IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
            for (int i = 0; i < 16; ++i) {
                this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
            }
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }

        this.movCheck3 = this.movCheck2;
        this.movCheck2 = this.movCheck1;
        this.movCheck1 = this.movCheck;
        this.movCheck = this.getPosY();

        if (this.getWild()) {
            this.renderYawOffset = this.prevRenderYawOffset = this.prevRenderYawOffset + 3.0F;
        } else {
            this.renderYawOffset = this.prevRenderYawOffset = 180.0F;
        }
        this.rotationYaw = this.prevRotationYaw = 180.0F;

        age = age + 1;
        if (age >= 3600) {
            this.world.setEntityState(this, (byte)3);
            if(!this.getWild()) this.playHurtSound(DamageSource.GENERIC);
            this.remove();
        }

        if (this.getWild()) {
            if (this.world.getCurrentMoonPhaseFactor() == 1.0) {
                this.setMotion(0, Math.max(this.getBreeze(this.getPosX(), this.getPosZ()), this.getMotion().getY() - 0.1F), -0.15F);
            } else {
                this.setMotion(0, Math.max(this.getBreeze(this.getPosX(), this.getPosZ()), this.getMotion().getY() - 0.1F), -0.085F);
            }

        } else {
            this.setMotion(0, Math.max(-0.0375, this.getMotion().getY() - 0.1F), 0);
        }

        if(this.isBlockBlockingPath() || this.isEntityBlockingPath()) {
            if(!this.getWild()) this.playHurtSound(DamageSource.GENERIC);
            IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
            for (int i = 0; i < 16; ++i) {
                this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
            }
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }

        this.clearActivePotions();
        this.extinguish();

        if (!this.playedSound) {
            IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
            for (int i = 0; i < 16; ++i) {
                this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
            }
            this.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
            this.playedSound = true;
        }
    }

    @Override
    public boolean hitByEntity(Entity entity) {
        IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
        for (int i = 0; i < 16; ++i) {
            this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
        }
        if(!this.world.isRemote) {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                if (!player.abilities.isCreativeMode) {
                    Block.spawnAsEntity(this.world, this.func_233580_cy_(), new ItemStack(HanamiItems.SAKURA_FLOWER.get()));
                }
            } else {
                Block.spawnAsEntity(this.world, this.func_233580_cy_(), new ItemStack(HanamiItems.SAKURA_FLOWER.get()));
            }
        }
        this.playHurtSound(DamageSource.GENERIC);
        this.remove();
        return true;
    }

    @Override
    protected void damageEntity(DamageSource damageSource, float damageAmount) {
        if (damageSource.getImmediateSource() instanceof ThrownSakuraBlossomEntity || damageSource.getTrueSource() instanceof ThrownSakuraBlossomEntity) return;
        IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
        for (int i = 0; i < 16; ++i) {
            this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
        }
        if (damageSource.isProjectile()) {
            if(!this.getEntityWorld().isRemote) {
                Block.spawnAsEntity(this.world, this.func_233580_cy_(), new ItemStack(HanamiItems.SAKURA_FLOWER.get()));
            }
            this.remove();
        } else if (damageSource == DamageSource.IN_WALL) {
            this.remove();
        }
        super.damageEntity(damageSource, damageAmount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
        for (int i = 0; i < 16; ++i) {
            this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
        }
        this.remove();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float amount) {
        if (damageSource.getTrueSource() instanceof ArrowEntity || damageSource.getImmediateSource() instanceof ArrowEntity) {
            damageEntity(damageSource, amount);
            if (damageSource.getTrueSource() instanceof ArrowEntity) {
                damageSource.getTrueSource().remove();
            }
            if (damageSource.getImmediateSource() instanceof ArrowEntity) {
                damageSource.getImmediateSource().remove();
            }
        }
        return super.attackEntityFrom(damageSource, amount);
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        ItemStack itemStack = new ItemStack(HanamiItems.SAKURA_FLOWER.get());
        return itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemStack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 1) {
            this.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        } else if (id == 3) {
            IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
            for (int i = 0; i < 16; ++i) {
                this.world.addParticle(blossoms, this.getParticleOffset(this.getPosX()), this.getParticleOffset(this.getPosY()), this.getParticleOffset(this.getPosZ()), this.getRandWithMagnitude(0.05), this.getRandWithMagnitude(0.03), this.getRandWithMagnitude(0.05));
            }
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.BLOCK_WOOL_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_WOOL_BREAK;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return size.height;
    }

    @Override
    public void move(MoverType type, Vector3d pos) {
        super.move(type, pos);
        if (type == MoverType.PISTON) {
            this.playHurtSound(DamageSource.GENERIC);
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(HanamiItems.SAKURA_FLOWER.get());
    }

    private double getParticleOffset(double value) {
        return value + (this.world.rand.nextDouble() * 0.2F) - 0.1F;
    }

    private double getRandWithMagnitude(double mag) {
        return (this.world.rand.nextDouble() * 2 * mag) - mag;
    }

    private double getBreeze(double x, double z) {
        //return 0.0625F * Math.cos((x + z) * 0.05) - 0.0125F;
        return -0.025F;
    }

    private boolean isBlockBlockingPath() {
        Vector3d eyePos = this.getPositionVec().add(0, 0, -0.5F);
        return this.world.rayTraceBlocks(new RayTraceContext(
                eyePos,
                eyePos.add(this.getMotion()),
                RayTraceContext.BlockMode.OUTLINE,
                RayTraceContext.FluidMode.ANY,
                this
        )).getType() != RayTraceResult.Type.MISS;
    }

    private boolean isSpecialBlockBlockingPath() {
        BlockPos pos = this.getPositionUnderneath();
        return this.world.getBlockState(pos).getBlock() == Blocks.END_PORTAL_FRAME || this.world.getBlockState(pos.up()).getBlock() == Blocks.LILY_PAD;
    }

    private boolean isEntityBlockingPath() {
        AxisAlignedBB clusterBB = this.getBoundingBox().offset(0.0F, -0.01F, 0.0F);
        List<Entity> entitiesAbove = this.world.getEntitiesWithinAABBExcludingEntity(null, clusterBB);
        if(!entitiesAbove.isEmpty()) {
            for (Entity entity : entitiesAbove) {
                if (!entity.isPassenger() && !(entity instanceof SakuraBlossomEntity || entity instanceof ItemEntity || entity instanceof PotionEntity || entity instanceof ThrownSakuraBlossomEntity) && entity.getPushReaction() != PushReaction.IGNORE) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setOrigin(BlockPos pos) {
        this.dataManager.set(ORIGIN, pos);
    }

    public BlockPos getOrigin() {
        return this.dataManager.get(ORIGIN);
    }

    public void setWild(boolean wild) {
        this.dataManager.set(WILD, wild);
    }

    public boolean getWild() {
        return this.dataManager.get(WILD);
    }

    @Override
    public void applyKnockback(float strengthIn, double ratioXIn, double ratioZIn) {}

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ILLAGER;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entity) {
        return entity.canBePushed() ? entity.getBoundingBox() : null;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.getBoundingBox();
    }

    @Override
    protected float getWaterSlowDown() {
        return 0;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean startRiding(Entity entity, boolean force) {
        return false;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return NonNullList.withSize(4, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemStackFromSlot(EquipmentSlotType slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slot, ItemStack stack) {}

    @Override
    public HandSide getPrimaryHand() {
        return HandSide.RIGHT;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().func_233815_a_(Attributes.MAX_HEALTH, 1.0D);
    }
}
