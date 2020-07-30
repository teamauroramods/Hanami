package com.teamaurora.hanami.common.entity;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ThrownSakuraBlossomEntity extends ProjectileItemEntity {
    public ThrownSakuraBlossomEntity(EntityType<? extends ThrownSakuraBlossomEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    public ThrownSakuraBlossomEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityType.SNOWBALL, throwerIn, worldIn);
    }

    public ThrownSakuraBlossomEntity(World worldIn, double x, double y, double z) {
        super(EntityType.SNOWBALL, x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }


    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        ItemStack itemstack = this.func_213882_k();
        return (IParticleData) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.makeParticle();

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @SuppressWarnings("deprecation")
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), 0);
        } else if (result.getType() == RayTraceResult.Type.BLOCK) {
            //TODO
            if (!this.world.isRemote) {
                BlockPos pos = this.getOnPosition();
                SakuraBlossomEntity blossom = new SakuraBlossomEntity(this.world, pos, pos.getX(), pos.getY(), pos.getZ());

                world.addEntity(blossom);
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }
}
