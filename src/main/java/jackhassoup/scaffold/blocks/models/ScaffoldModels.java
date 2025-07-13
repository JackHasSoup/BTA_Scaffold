package jackhassoup.scaffold.blocks.models;

import jackhassoup.scaffold.blocks.ScaffoldBlocks;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

import static jackhassoup.scaffold.ScaffoldMod.MOD_ID;
import static jackhassoup.scaffold.ScaffoldMod.LOGGER;;

public class ScaffoldModels implements ModelEntrypoint {

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {

        ModelHelper.setBlockModel(
                ScaffoldBlocks.SCAFFOLD_BAMBOO,
                () -> new BambooScaffoldModel<>(ScaffoldBlocks.SCAFFOLD_BAMBOO, true)
                        .setTex(0, "scaffold:block/scaffolding/bamboo/side", Side.sides)
                        .setTex(0, "scaffold:block/scaffolding/bamboo/top", Side.TOP)
                        .setTex(0, "scaffold:block/scaffolding/bamboo/bottom", Side.BOTTOM));
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
