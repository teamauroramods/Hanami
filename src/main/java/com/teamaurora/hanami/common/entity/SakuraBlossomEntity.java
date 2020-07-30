package com.teamaurora.hanami.common.entity;

import com.teamaurora.hanami.core.registry.HanamiEntities;
import com.teamaurora.hanami.core.registry.HanamiItems;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SakuraBlossomEntity extends LivingEntity {
    private static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(SakuraBlossomEntity.class, DataSerializers.BLOCK_POS);

    public SakuraBlossomEntity(EntityType<? extends SakuraBlossomEntity> blossom, World worldIn) {
        super(HanamiEntities.SAKURA_BLOSSOM.get(), worldIn);
    }

    public SakuraBlossomEntity(World worldIn, BlockPos origin, double x, double y, double z) {
        this(HanamiEntities.SAKURA_BLOSSOM.get(), worldIn);
        this.setHealth(1);
        this.setOrigin(new BlockPos(origin));
        this.setPosition(x + 0.5D, y, z + 0.5D);
        this.setNoGravity(true);
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;
        this.rotationYaw = 180.0F;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ORIGIN, BlockPos.ZERO);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putLong("OriginPos", this.getOrigin().toLong());
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        this.setOrigin(BlockPos.fromLong(nbt.getLong("OriginPos")));
    }

    @Override
    public void tick() {
        //TODO
    }

    @Override
    public boolean hitByEntity(Entity entityIn) {
        if(!this.world.isRemote) {
            Block.spawnAsEntity(this.world, this.func_233580_cy_(), new ItemStack(HanamiItems.SAKURA_BLOSSOM.get()));
        }
        this.remove();
        return true;
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        if (damageSrc.isProjectile()) {
            if(!this.getEntityWorld().isRemote) {
                Block.spawnAsEntity(this.world, this.func_233580_cy_(), new ItemStack(HanamiItems.SAKURA_BLOSSOM.get()));
            }
            this.remove();
        } else if (damageSrc == DamageSource.IN_WALL) {
            this.remove();
        }
        super.damageEntity(damageSrc, damageAmount);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_WOOL_BREAK;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
        return size.height;
    }

    public void setOrigin(BlockPos pos) {
        this.dataManager.set(ORIGIN, pos);
    }

    public BlockPos getOrigin() {
        return this.dataManager.get(ORIGIN);
    }

    @Override
    public void applyKnockback(float strengthIn, double ratioXIn, double ratioZIn) {}


}
