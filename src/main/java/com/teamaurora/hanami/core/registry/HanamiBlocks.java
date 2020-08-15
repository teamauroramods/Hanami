package com.teamaurora.hanami.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.abnormals_core.common.blocks.*;
import com.teamabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.teamabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.teamabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.teamabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchBlock;
import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchSlabBlock;
import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchStairsBlock;
import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchVerticalSlabBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.*;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.common.block.*;
import com.teamaurora.hanami.common.block.trees.SakuraTree;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiBlocks {
    public static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    // sakura wood set
    public static final RegistryObject<Block> STRIPPED_SAKURA_LOG = HELPER.createBlock("stripped_sakura_log", ()->new StrippedLogBlock(Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_LOG = HELPER.createBlock("sakura_log", ()->new AbnormalsLogBlock(STRIPPED_SAKURA_LOG, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_SAKURA_WOOD = HELPER.createBlock("stripped_sakura_wood", ()->new StrippedWoodBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_WOOD = HELPER.createBlock("sakura_wood", ()->new WoodBlock(STRIPPED_SAKURA_WOOD, Block.Properties.from(Blocks.OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_PLANKS = HELPER.createBlock("sakura_planks", ()->new PlanksBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_SLAB = HELPER.createBlock("sakura_slab", ()->new WoodSlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_STAIRS = HELPER.createBlock("sakura_stairs", ()->new WoodStairsBlock(SAKURA_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_PRESSURE_PLATE = HELPER.createBlock("sakura_pressure_plate", ()->new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)), ItemGroup.REDSTONE);
    public static final RegistryObject<Block> SAKURA_FENCE = HELPER.createBlock("sakura_fence", ()->new WoodFenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> SAKURA_FENCE_GATE = HELPER.createBlock("sakura_fence_gate", ()->new WoodFenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), ItemGroup.REDSTONE);
    public static final RegistryObject<Block> SAKURA_BUTTON = HELPER.createBlock("sakura_button", ()->new AbnormalsWoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), ItemGroup.REDSTONE);
    public static final RegistryObject<Block> SAKURA_TRAPDOOR = HELPER.createBlock("sakura_trapdoor", ()->new WoodTrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), ItemGroup.REDSTONE);
    public static final RegistryObject<Block> SAKURA_DOOR = HELPER.createBlock("sakura_door", ()->new WoodDoorBlock(Block.Properties.from(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
    public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> SAKURA_SIGNS = HELPER.createSignBlock("sakura", MaterialColor.PINK);
    public static final RegistryObject<Block> SAKURA_LEAVES = HELPER.createBlock("sakura_leaves", ()->new SakuraLeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES).harvestTool(ToolType.HOE)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> SAKURA_LEAF_CARPET = HELPER.createBlock("sakura_leaf_carpet", ()->new LeafCarpetBlock(Block.Properties.create(Material.CARPET).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> SAKURA_SAPLING = HELPER.createBlock("sakura_sapling", () -> new AbnormalsSaplingBlock(new SakuraTree(), Block.Properties.create(Material.PLANTS, MaterialColor.RED).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), ItemGroup.DECORATIONS); // temporarily grows oak tree
    public static final RegistryObject<Block> POTTED_SAKURA_SAPLING = HELPER.createBlockNoItem("potted_sakura_sapling", () -> new FlowerPotBlock(SAKURA_SAPLING.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));

    public static final RegistryObject<Block> VERTICAL_SAKURA_PLANKS = HELPER.createCompatBlock("quark","vertical_sakura_planks", ()->new Block(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_VERTICAL_SLAB = HELPER.createCompatBlock("quark","sakura_vertical_slab", ()->new VerticalSlabBlock(Block.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_BOOKSHELF = HELPER.createCompatBlock("quark","sakura_bookshelf", ()->new BookshelfBlock(Block.Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> SAKURA_LADDER = HELPER.createCompatBlock("quark","sakura_ladder", ()->new AbnormalsLadderBlock(Block.Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
    public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> SAKURA_CHESTS = HELPER.createCompatChestBlocks("sakura", MaterialColor.ADOBE);

    public static final RegistryObject<Block> CHERRY_SAKURA_LEAVES = HELPER.createCompatBlock("fruitful", "cherry_sakura_leaves", ()->new AbnormalsLeavesBlock(Block.Properties.from(SAKURA_LEAVES.get()).notSolid().harvestTool(ToolType.HOE)), ItemGroup.DECORATIONS);

    public static final RegistryObject<Block> SAKURA_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "sakura_beehive", ()->new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);

    // plants
    public static final RegistryObject<Block> AZALEA_BUSH = HELPER.createBlock("azalea_bush", ()->new AzaleaBushBlock(()->HanamiEffects.INSTABILITY.get(), 8, Block.Properties.from(Blocks.POPPY)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> POTTED_AZALEA_BUSH = HELPER.createBlockNoItem("potted_azalea_bush", ()->new FlowerPotBlock(AZALEA_BUSH.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));

    // misc
    public static final RegistryObject<Block> BLACK_FOREST_GATEAU = HELPER.createBlockNoItem("black_forest_gateau", ()->new BlackForestGateauBlock(Block.Properties.from(Blocks.CAKE)));
    public static final RegistryObject<Block> BLOMB = HELPER.createBlock("blomb", ()->new BlombBlock(Block.Properties.from(Blocks.TNT)), ItemGroup.REDSTONE);
    public static final RegistryObject<Block> SAKURA_CHERRY_SACK = HELPER.createCompatBlock("quark", "sakura_cherry_sack", ()->new Block(Block.Properties.create(Material.WOOL, MaterialColor.RED).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)), ItemGroup.DECORATIONS);
}
