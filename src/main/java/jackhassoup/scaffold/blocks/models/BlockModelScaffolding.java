package jackhassoup.scaffold.blocks.models;

import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelScaffolding<T extends BlockLogic> extends BlockModelBackFaceRenderable<T> {

    protected IconCoordinate sideReg;
    protected IconCoordinate sideHang;
    protected IconCoordinate empty;

    public BlockModelScaffolding(Block<T> block, boolean renderInside, String mat) {
        super(block, renderInside);
        this.sideReg = TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/side");
        this.sideHang = TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/side_hang");
        this.empty = TextureRegistry.getTexture("scaffold:block/empty");
    }

    @Override
    public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
        //check if hanging for side and bottom texture
        switch (side) {
            case TOP:
                return super.getBlockTexture(blockAccess, x, y, z, side);
            
            case BOTTOM:
                if (isSolidOrThis(blockAccess.getBlock(x, y - 1, z))) return super.getBlockTexture(blockAccess, x, y, z, side);
                return empty;
        
            default:
            {
                //check the side trying to be rendered, don't render support/hang/legs between blocks
                Block<?> b = blockAccess.getBlock(x+side.getOffsetX(), y, z+side.getOffsetZ());
                if(isSolidOrThis(b)) return empty; //only hide face on a solid block

                //no air below block? no problem render the legs!
                b = blockAccess.getBlock(x, y - 1, z);

                if (isSolidOrThis(b)) {
                    return sideReg;
                }

                //this block is hanging
                return sideHang;
                
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
