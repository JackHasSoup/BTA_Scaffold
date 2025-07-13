package jackhassoup.scaffold.blocks;

import jackhassoup.scaffold.ScaffoldConfig;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static jackhassoup.scaffold.ScaffoldMod.MOD_ID;

public class ScaffoldBlocks {
    public static int blockID = ScaffoldConfig.blockIDs;

    // SCAFFOLDING BLOCKS
    public static Block<?> SCAFFOLD_BAMBOO;

    public static void initBlocks() {
        BlockBuilder woodTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(1.0F)
                .setResistance(1.0F)
                .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS,
                        BlockTags.CAN_HANG_OFF)
                .setFlammability(20, 35)
                .setBlockSound(BlockSounds.WOOD);

        SCAFFOLD_BAMBOO = woodTier.build("scaffolding_bamboo", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.wood, 5));
    }
}
