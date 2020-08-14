package com.teamaurora.hanami.core.other;

import com.google.gson.JsonObject;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiLootModifiers {
    public static class FruitfulModifier extends LootModifier {
        private final Item cherry;

        public FruitfulModifier(ILootCondition[] conditionsIn, Item cherryItem) {
            super(conditionsIn);
            cherry = cherryItem;
        }

        @Nonnull
        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            if (ModList.get().isLoaded("fruitful")) {
                generatedLoot.removeIf(x -> x.getItem() == cherry);
            }
            return generatedLoot;
        }

        private static class Serializer extends GlobalLootModifierSerializer<com.teamaurora.hanami.core.other.HanamiLootModifiers.FruitfulModifier> {

            @Override
            public com.teamaurora.hanami.core.other.HanamiLootModifiers.FruitfulModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
                Item cherryItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getString(object, "cherryItem"))));
                return new com.teamaurora.hanami.core.other.HanamiLootModifiers.FruitfulModifier(conditionsIn, cherryItem);
            }
        }
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new FruitfulModifier.Serializer().setRegistryName(new ResourceLocation(Hanami.MODID,"fruitful")));
    }
}
