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
    //public static Block<?> SCAFFOLD_cloth; //dyeable???

    public static void initBlocks() {
        BlockBuilder woodTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(1.0F)
                .setResistance(1.0F)
                .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS, BlockTags.CAN_HANG_OFF)
                .setFlammability(20, 35)
                .setBlockSound(BlockSounds.WOOD)
                .setVisualUpdateOnMetadata();

            BlockBuilder stoneTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.STONE)
                .setHardness(1.2F)
                .setResistance(1.2F)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.CAN_HANG_OFF)
                .setBlockSound(BlockSounds.STONE);

                BlockBuilder metalTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.METAL)
                .setHardness(1.2F)
                .setResistance(1.5F)
                .setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.CAN_HANG_OFF)
                .setBlockSound(BlockSounds.METAL);

        SCAFFOLD_BAMBOO = woodTier.build("scaffolding_bamboo", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, 5));

        SCAFFOLD_PAPER = woodTier.build("scaffolding_paper", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, 4));

        SCAFFOLD_WOOD = woodTier.build("scaffolding_wood", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, 6));

        SCAFFOLD_WOOD_PAINTED = woodTier.build("scaffolding_wood_painted", blockID++,
                (b) -> new BlockLogicColouredScaffolding(b, Material.decoration, 6))
                .setBlockItem((b) -> {return new ItemBlockPainted<>(b,false);});
        

        SCAFFOLD_COBBLE = stoneTier.build("scaffolding_cobble", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, 8));
        SCAFFOLD_COBBLE = stoneTier.build("scaffolding_slate", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.decoration, 12));
    }
}
