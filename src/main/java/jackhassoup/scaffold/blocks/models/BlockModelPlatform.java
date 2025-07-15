package jackhassoup.scaffold.blocks.models;

import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;

public class BlockModelPlatform<T extends BlockLogic> extends BlockModelBackFaceRenderable<T> {

    protected IconCoordinate sideBottom;
    protected IconCoordinate sideTop;
    protected IconCoordinate empty;

    public BlockModelPlatform(Block<T> block, boolean renderInside, String mat) {
        super(block, renderInside);
        
        this.sideBottom = TextureRegistry.getTexture("scaffold:block/platform/"+mat+"/side");
        this.sideTop = TextureRegistry.getTexture("scaffold:block/platform/"+mat+"/side_top");
        this.empty = TextureRegistry.getTexture("scaffold:block/empty");
    }

    @Override
    public boolean render(Tessellator tessellator, int x, int y, int z) {
        AABB bounds = this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z);
        this.setRenderSide(Side.BOTTOM, false);
        this.renderStandardBlockWithBackfaces(tessellator, this, bounds, x,y,z, 255, 255, 255);
        this.renderStandardBlock(tessellator, bounds, x, y, z);
        renderBlocks.renderBitMask = 63;
        this.setRenderSide(Side.BOTTOM, true);
        bounds.minY += 0.1875;
        this.renderStandardBlockWithBackfaces(tessellator, this, bounds, x,y,z, 255, 255, 255);
        this.renderStandardBlock(tessellator, bounds, x, y, z);
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
                //if the side is covered by something other than air/null AND if that cover is this block then are they on the same level
                if(isSolidOrThis(b)){
                    if (b.id() == this.block.id() && blockAccess.getBlockMetadata(x+side.getOffsetX(), y, z+side.getOffsetZ()) == blockAccess.getBlockMetadata(x, y, z)) return empty;
                } 

                if(blockAccess.getBlockMetadata(x, y, z) == 1){return sideBottom;}
                return sideTop;
            }
        }
    }

    protected boolean isActuallySolid(Block<?> block)
    {
        if(block==null) return false;
        return block.getMaterial().isSolid()  && block.isCubeShaped() && block.isSolidRender();
    }

    protected boolean isSolidOrThis(Block<?> b)
    {
        if(b ==null) return false;
        return isActuallySolid(b) || b.id() == this.block.id();
    }
}