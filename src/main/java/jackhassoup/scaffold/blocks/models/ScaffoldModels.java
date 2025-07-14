package jackhassoup.scaffold.blocks.models;

import jackhassoup.scaffold.blocks.ScaffoldBlocks;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;


public class ScaffoldModels implements ModelEntrypoint {

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {

        ModelHelper.setBlockModel(
                ScaffoldBlocks.SCAFFOLD_BAMBOO,
                () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_BAMBOO, true, "bamboo")
                        .setTex(0, "scaffold:block/scaffolding/bamboo/side", Side.sides)
                        .setTex(0, "scaffold:block/scaffolding/bamboo/top", Side.TOP)
                        .setTex(0, "scaffold:block/scaffolding/bamboo/bottom", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_PAPER,
            () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_PAPER, true, "paper")
                    .setTex(0, "scaffold:block/scaffolding/paper/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/paper/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/paper/bottom", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,
            () -> new BlockModelColouredScaffolding<>(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED, true, "wood")
                    .setTex(0, "scaffold:block/scaffolding/wood/oak/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/wood/oak/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/wood/oak/bottom", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_WOOD,
            () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_WOOD, true, "wood/oak")
                    .setTex(0, "scaffold:block/scaffolding/wood/oak/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/wood/oak/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/wood/oak/bottom", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_COBBLE,
            () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_COBBLE, true, "cobble")
                    .setTex(0, "scaffold:block/scaffolding/cobble/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/cobble/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/cobble/top", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_SLATE,
            () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_SLATE, true, "slate")
                    .setTex(0, "scaffold:block/scaffolding/slate/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/slate/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/slate/bottom", Side.BOTTOM));
    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
    }

    @Override
    public void initEntityModels(EntityRenderDispatcher dispatcher) {
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {
    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {
    }

}
