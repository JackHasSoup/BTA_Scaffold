package jackhassoup.scaffold.blocks.models;

import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;

public class BlockModelPlatform<T extends BlockLogic> extends BlockModelBackFaceRenderable<T> {

    protected IconCoordinate sideReg;
    protected IconCoordinate empty;

    public BlockModelPlatform(Block<T> block, boolean renderInside, String mat) {
        super(block, renderInside);
        
        this.sideReg = TextureRegistry.getTexture("scaffold:block/platform/"+mat+"/side");
        this.empty = TextureRegistry.getTexture("scaffold:block/empty");
    }

    @Override
    public boolean render(Tessellator tessellator, int x, int y, int z) {
        AABB bounds = this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z);
        this.setRenderSide(Side.BOTTOM, false);
        this.renderStandardBlockWithBackfaces(tessellator, this, bounds, x,y,z, 255, 255, 255);
        renderBlocks.renderBitMask = 63;
        this.setRenderSide(Side.BOTTOM, true);
        bounds.minY += 0.1875;
        this.renderStandardBlockWithBackfaces(tessellator, this, bounds, x,y,z, 255, 255, 255);
        renderBlocks.renderBitMask = 0;
        return true;
    }

    @Override
    public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
        //check if hanging for side and bottom texture
        switch (side) {
            case TOP:
            case BOTTOM:
                return super.getBlockTexture(blockAccess, x, y, z, side);
        
            default:
            {
                //check the side trying to be rendered, don't render support/hang/legs between blocks
                Block<?> b = blockAccess.getBlock(x+side.getOffsetX(), y, z+side.getOffsetZ());
                if(b != null && b.getMaterial() != Material.air) return sideReg; //only hide face on a solid block

                return sideReg;
            }
        }
    }
}