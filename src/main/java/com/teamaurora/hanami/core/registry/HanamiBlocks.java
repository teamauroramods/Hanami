package com.teamaurora.hanami.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.abnormals_core.common.blocks.AbnormalsBeehiveBlock;
import com.teamabnormals.abnormals_core.common.blocks.LeafCarpetBlock;
import com.teamabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.teamabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.teamabnormals.abnormals_core.common.blocks.wood.*;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.hanami.core.Hanami;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hanami.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HanamiBlocks {
    public static final RegistryHelper HELPER = Hanami.REGISTRY_HELPER;

    // sakura wood set
    // Put Here: Stripped Sakura Log
    // Put Here: Sakura Log
    // Put Here: Stripped Sakura Wood
    // Put Here: Sakura Wood
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
    public static final RegistryObject<Block> SAKURA_LEAVES = HELPER.createBlock("sakura_leaves", ()->new AbnormalsLeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> SAKURA_LEAF_CARPET = HELPER.createBlock("sakura_leaf_carpet", ()->new LeafCarpetBlock(Block.Properties.from(SAKURA_LEAVES.get())), ItemGroup.DECORATIONS);
    // Put Here: Sakura Sapling
    // Put Here: Potted Sakura Sapling

    // Put Here: Vertical Sakura Planks
    // Put Here: Sakura Vertical Slab
    // Put Here: Sakura Bookshelf
    // Put Here: Sakura Ladder
    // Put Here: Sakura Chests

    public static final RegistryObject<Block> SAKURA_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "sakura_beehive", ()->new AbnormalsBeehiveBlock(Block.Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);

}
