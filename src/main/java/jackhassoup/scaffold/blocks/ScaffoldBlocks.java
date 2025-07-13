package jackhassoup.scaffold.blocks;

import jackhassoup.scaffold.ScaffoldConfig;
import jackhassoup.scaffold.blocks.*;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicLog;
import net.minecraft.core.block.BlockLogicMushroom;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.BlockBuilder;

import static jackhassoup.scaffold.ScaffoldMod.MOD_ID;

public class ScaffoldBlocks {
    public static int blockID = ScaffoldConfig.blockIDs;

    // SCAFFOLDING BLOCKS
    public static Block<?> BAMBOO_SCAFFOLD;

    public static void initBlocks() {
        BlockBuilder woodTier = new BlockBuilder(MOD_ID)
                .setBlockSound(BlockSounds.WOOD)
                .setHardness(1.0F)
                .setResistance(1.0F)
                .setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_SWORD, BlockTags.BROKEN_BY_FLUIDS,
                        BlockTags.CAN_HANG_OFF)
                .setFlammability(20, 35)
                .setBlockSound(BlockSounds.WOOD);

        BAMBOO_SCAFFOLD = woodTier.build("scaffolding_bamboo", blockID++,
                (b) -> new BlockLogicScaffolding(b, Material.wood));
    }
}
