package com.teamaurora.hanami.core.other;

import com.teamabnormals.abnormals_core.core.utils.TradeUtils;
import com.teamaurora.hanami.core.Hanami;
import com.teamaurora.hanami.core.registry.HanamiBlocks;
import com.teamaurora.hanami.core.registry.HanamiItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID)
public class HanamiTrades {

    @SubscribeEvent
    public static void onWandererTradesEvent(WandererTradesEvent event) {
        event.getGenericTrades().add(new TradeUtils.ItemsForEmeraldsTrade(HanamiBlocks.SAKURA_SAPLING.get(), 5, 1, 8, 1));
    }

    @SubscribeEvent
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FARMER) {
            event.getTrades().get(2).add(new TradeUtils.ItemsForEmeraldsTrade(HanamiItems.CHERRY_PIE.get(), 1, 4, 12, 5));
            event.getTrades().get(4).add(new TradeUtils.ItemsForEmeraldsTrade(HanamiItems.BLACK_FOREST_GATEAU.get(), 2, 1, 12, 15));
        }
    }
}
