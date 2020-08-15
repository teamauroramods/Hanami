package com.teamaurora.hanami.core.other;

import com.teamaurora.hanami.client.particle.HanamiParticles;
import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.common.entity.ThrownSakuraBlossomEntity;
import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import com.teamaurora.hanami.core.registry.HanamiEffects;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Hanami.MODID)
public class HanamiEvents {

    @SubscribeEvent
    public static void livingKnockBack(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntityLiving();

        // INSTABILITY //
        if (entity.isPotionActive(HanamiEffects.INSTABILITY.get())) {
            int amplifier = entity.getActivePotionEffect(HanamiEffects.INSTABILITY.get()).getAmplifier();
            event.setStrength(event.getStrength() * (2 + amplifier));
        }
    }

    @SubscribeEvent
    public static void livingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        ItemStack item = event.getItem();

        LivingEntity entity = event.getEntityLiving();

        // NOURISHING //
        if (entity.isPotionActive(HanamiEffects.NOURISHING.get())) {
            int amplifier = entity.getActivePotionEffect(HanamiEffects.NOURISHING.get()).getAmplifier();
            if (item.isFood()) {
                int foodToAdd = 2 * (amplifier + 1);
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    player.getFoodStats().addStats(foodToAdd, 0);
                }
            }
        }

        // UNSALUTARY //
        if (entity.isPotionActive(HanamiEffects.UNSALUTARY.get())) {
            int amplifier = entity.getActivePotionEffect(HanamiEffects.UNSALUTARY.get()).getAmplifier();
            if (item.isFood()) {
                Food food = item.getItem().getFood();
                int foodToRemove = Math.min(2 * (amplifier + 1), food.getHealing());
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    player.getFoodStats().addStats(foodToRemove * -1, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        Vector3d playerPos = player.getPositionVec();
        BlockPos playerBlockPos = new BlockPos(Math.floor(playerPos.getX()), Math.floor(playerPos.getY()), Math.floor(playerPos.getZ()));
        World world = event.player.world;
        float spawnChance = 0.0015F;
        float modifiedSpawnChance = spawnChance;
        if (world.isRaining() || world.isThundering()) modifiedSpawnChance += spawnChance;
        if (world.getCurrentMoonPhaseFactor() == 1.0F) modifiedSpawnChance += 2 * spawnChance;
        if (world.rand.nextFloat() < modifiedSpawnChance) {
            for (BlockPos blockPos : BlockPos.getAllInBoxMutable(playerBlockPos.add(-10, -10, -10), playerBlockPos.add(10, 10, 10))) {
                if (blockPos.getY() > 1 && blockPos.getY() < world.getHeight()) {
                    if (world.getBlockState(blockPos).getBlock() == HanamiBlocks.SAKURA_LEAVES.get() && !world.getBlockState(blockPos).get(LeavesBlock.PERSISTENT) && world.getBlockState(blockPos.down()).getBlock() == Blocks.AIR && world.getBlockState(blockPos.down().north()).getBlock() == Blocks.AIR) {
                        // blockPos.down() can spawn a blossom!
                        // bound should be 80000 for wind event
                        if (world.rand.nextInt(40) == 0) {
                            if (world instanceof ServerWorld) {
                                SakuraBlossomEntity blossom = new SakuraBlossomEntity(world, blockPos, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ(), true);

                                world.addEntity(blossom);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void projectileImpact(ProjectileImpactEvent event) {
        Entity entity = event.getEntity();
        World world = entity.getEntityWorld();
        if (entity instanceof ThrownSakuraBlossomEntity && event.getRayTraceResult().getType() != RayTraceResult.Type.MISS) {
            if (!world.isRemote) {
                //event.getEntity().getEntityWorld().setEntityState(event.getEntity(), (byte) 3);
                //event.getEntity().remove();
                IParticleData blossoms = HanamiParticles.BLOSSOM_PETAL.get();
                Random rand = world.getRandom();
                for (int i = 0; i < 16; ++i) {
                    world.addParticle(blossoms, this.getParticleOffset(entity.getPosX(), rand), this.getParticleOffset(entity.getPosY(), rand), this.getParticleOffset(entity.getPosZ(), rand), this.getRandWithMagnitude(0.05, rand), this.getRandWithMagnitude(0.03, rand), this.getRandWithMagnitude(0.05, rand));
                }
            }
        }
    }

    private double getParticleOffset(double value, Random rand) {
        return value + (rand.nextDouble() * 0.2F) - 0.1F;
    }

    private double getRandWithMagnitude(double mag, Random rand) {
        return (rand.nextDouble() * 2 * mag) - mag;
    }

}
