package com.teamaurora.hanami.core.other;

import com.teamaurora.hanami.common.entity.SakuraBlossomEntity;
import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import com.teamaurora.hanami.core.registry.HanamiEffects;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
        if ((world.getMoonPhase() != 0 && world.rand.nextInt(10000) == 0) || (world.getMoonPhase() == 0 && world.rand.nextInt(500) == 0)) {
            for (BlockPos blockPos : BlockPos.getAllInBoxMutable(playerBlockPos.add(-10, -10, -10), playerBlockPos.add(10, 10, 10))) {
                if (blockPos.getY() > 1 && blockPos.getY() < world.getHeight()) {
                    if (world.getBlockState(blockPos).getBlock() == HanamiBlocks.SAKURA_LEAVES.get() && world.getBlockState(blockPos.down()).getBlock() == Blocks.AIR) {
                        // blockPos.down() can spawn a blossom!
                        // bound should be 80000 for wind event
                        if (world.rand.nextInt(8) == 0) {
                            if (!world.isRemote) {
                                SakuraBlossomEntity blossom = new SakuraBlossomEntity(world, blockPos, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ(), true);

                                world.addEntity(blossom);
                            }
                        }
                    }
                }
            }
        }
    }

}
