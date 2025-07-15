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

        //-=-=-=-=-=-=-=- SCAFFOLDING -=-=-=-=-=-=-=-

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
        
        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_IRON,
            () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_IRON, true, "iron")
                    .setTex(0, "scaffold:block/scaffolding/iron/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/iron/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/iron/bottom", Side.BOTTOM));
        
        ModelHelper.setBlockModel(
            ScaffoldBlocks.SCAFFOLD_STEEL,
            () -> new BlockModelScaffolding<>(ScaffoldBlocks.SCAFFOLD_STEEL, true, "steel")
                    .setTex(0, "scaffold:block/scaffolding/steel/side", Side.sides)
                    .setTex(0, "scaffold:block/scaffolding/steel/top", Side.TOP)
                    .setTex(0, "scaffold:block/scaffolding/steel/bottom", Side.BOTTOM));

        //-=-=-=-=-=-=-=- PLATFORMS -=-=-=-=-=-=-=-
        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_CLOTH,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_CLOTH, true, "cloth")
                    .setTex(0, "scaffold:block/platform/cloth/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/cloth/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_MESH,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_MESH, true, "mesh")
                    .setTex(0, "scaffold:block/platform/mesh/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/mesh/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_MESH_GOLD,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_MESH_GOLD, true, "mesh_gold")
                    .setTex(0, "scaffold:block/platform/mesh_gold/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/mesh_gold/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_BASALT,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_BASALT, true, "brick/basalt")
                    .setTex(0, "scaffold:block/platform/brick/basalt/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/basalt/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_CLAY,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_CLAY, true, "brick/clay")
                    .setTex(0, "scaffold:block/platform/brick/clay/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/clay/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_GRANITE,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_GRANITE, true, "brick/granite")
                    .setTex(0, "scaffold:block/platform/brick/granite/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/granite/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_LIMESTONE,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_LIMESTONE, true, "brick/limestone")
                    .setTex(0, "scaffold:block/platform/brick/limestone/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/limestone/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_MARBLE,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_MARBLE, true, "brick/marble")
                    .setTex(0, "scaffold:block/platform/brick/marble/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/marble/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_POLISHED_STONE,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_POLISHED_STONE, true, "brick/polished_stone")
                    .setTex(0, "scaffold:block/platform/brick/polished_stone/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/polished_stone/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_SLATE,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_SLATE, true, "brick/slate")
                    .setTex(0, "scaffold:block/platform/brick/slate/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/slate/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));

        ModelHelper.setBlockModel(
            ScaffoldBlocks.PLATFORM_BRICK_STONE,
            () -> new BlockModelPlatform<>(ScaffoldBlocks.PLATFORM_BRICK_STONE, true, "brick/stone")
                    .setTex(0, "scaffold:block/platform/brick/stone/side", Side.sides)
                    .setTex(0, "scaffold:block/platform/brick/stone/top", Side.TOP)
                    .setTex(0, "scaffold:block/empty", Side.BOTTOM));
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
