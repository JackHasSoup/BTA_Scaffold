package jackhassoup.scaffold.blocks;

import jackhassoup.scaffold.ScaffoldConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockPainted;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static jackhassoup.scaffold.ScaffoldMod.MOD_ID;

public class ScaffoldBlocks {
    public static int blockID = ScaffoldConfig.blockIDs;

    // SCAFFOLDING BLOCKS
    public static Block<?> SCAFFOLD_BAMBOO;
    public static Block<?> SCAFFOLD_PAPER;
    public static Block<?> SCAFFOLD_WOOD_PAINTED;
    public static Block<?> SCAFFOLD_WOOD;
    public static Block<?> SCAFFOLD_COBBLE;
    public static Block<?> SCAFFOLD_SLATE;
    public static Block<?> SCAFFOLD_IRON;
    public static Block<?> SCAFFOLD_STEEL;

    //PLATFORMS
    public  static Block<?> PLATFORM_CLOTH;
    public  static Block<?> PLATFORM_MESH;
    public  static Block<?> PLATFORM_MESH_GOLD;
    public  static Block<?> PLATFORM_BRICK_BASALT;
    public  static Block<?> PLATFORM_BRICK_CLAY;
    public  static Block<?> PLATFORM_BRICK_GRANITE;
    public  static Block<?> PLATFORM_BRICK_LIMESTONE;
    public  static Block<?> PLATFORM_BRICK_MARBLE;
    public  static Block<?> PLATFORM_BRICK_POLISHED_STONE;
    public  static Block<?> PLATFORM_BRICK_SLATE;
    public  static Block<?> PLATFORM_BRICK_STONE;

    public static void initBlocks() {
        BlockBuilder woodTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(1.0F)
                .setResistance(1.0F)
                .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS, BlockTags.CAN_HANG_OFF, BlockTags.INSTANT_PICKUP)
                .setFlammability(20, 35)
                .setBlockSound(BlockSounds.WOOD)
                .setVisualUpdateOnMetadata();

        BlockBuilder stoneTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.STONE)
                .setHardness(1.2F)
                .setResistance(1.2F)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.CAN_HANG_OFF, BlockTags.INSTANT_PICKUP)
                .setBlockSound(BlockSounds.STONE);

        BlockBuilder metalTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.METAL)
                .setHardness(1.2F)
                .setResistance(1.5F)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAN_HANG_OFF, BlockTags.INSTANT_PICKUP)
                .setBlockSound(BlockSounds.METAL);

        //-=-=-=-=-=-=-=- SCAFFOLDING -=-=-=-=-=-=-=-
        SCAFFOLD_BAMBOO = woodTier.build("scaffolding_bamboo", blockID+0,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.bambooScaffoldSpan));

        SCAFFOLD_PAPER = woodTier.build("scaffolding_paper", blockID+1,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.paperScaffoldSpan));

        SCAFFOLD_WOOD = woodTier.build("scaffolding_wood", blockID+2,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.woodScaffoldSpan));

        SCAFFOLD_WOOD_PAINTED = woodTier.build("scaffolding_wood_painted", blockID+3,
                (b) -> new BlockLogicColouredScaffolding(b, Material.decoration, ScaffoldConfig.woodScaffoldSpan))
                .setBlockItem((b) -> {return new ItemBlockPainted<>(b,false);});
        

        SCAFFOLD_COBBLE = stoneTier.build("scaffolding_cobble", blockID+4,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.cobbleScaffoldSpan));
                
        SCAFFOLD_SLATE = stoneTier.build("scaffolding_slate", blockID+5,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.slateScaffoldSpan));


         SCAFFOLD_IRON = metalTier.build("scaffolding_iron", blockID+6,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.ironScaffoldSpan));

        SCAFFOLD_STEEL = metalTier.build("scaffolding_steel", blockID+7,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, ScaffoldConfig.steelScaffoldSpan));

        //-=-=-=-=-=-=-=- PLATFORMS -=-=-=-=-=-=-=-
        PLATFORM_CLOTH = woodTier.build("platform_cloth", blockID+8,
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, true));

        PLATFORM_MESH = metalTier.build("platform_mesh", blockID+9, 
                (b) -> new BlockLogicPlatformMesh(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, true));
        
        PLATFORM_MESH_GOLD = metalTier.build("platform_mesh_gold", blockID+10, 
                (b) -> new BlockLogicPlatformMeshGold(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, true));

        PLATFORM_BRICK_BASALT = stoneTier.build("platform_brick_basalt", blockID+11, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_CLAY = stoneTier.build("platform_brick_clay", blockID+12, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_GRANITE = stoneTier.build("platform_brick_granite", blockID+13, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_LIMESTONE = stoneTier.build("platform_brick_limestone", blockID+14, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_MARBLE = stoneTier.build("platform_brick_marble", blockID+15, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_POLISHED_STONE = stoneTier.build("platform_brick_polished_stone", blockID+16, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_SLATE = stoneTier.build("platform_brick_slate", blockID+17, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));

        PLATFORM_BRICK_STONE = stoneTier.build("platform_brick_stone", blockID+18, 
                (b) -> new BlockLogicPlatform(b, Material.decoration, ScaffoldConfig.allPlatformSpan, false, false));
    }
}
