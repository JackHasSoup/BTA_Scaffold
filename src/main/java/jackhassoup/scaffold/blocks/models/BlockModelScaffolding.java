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
                if (blockAccess.getBlockMaterial(x, y - 1, z).isSolid()) return super.getBlockTexture(blockAccess, x, y, z, side);
                return empty;
        
            default:
            {
                //check the side trying to be rendered, don't render support/hang/legs between blocks
                Block<?> b = blockAccess.getBlock(x+side.getOffsetX(), y, z+side.getOffsetZ());
                if(b != null && !b.getMaterial().isSolid()) return empty; //only hide face on a solid block

                //no air below block? no problem render the legs!
                b = blockAccess.getBlock(x, y - 1, z);
                if (b != null && (b.getMaterial().isSolid() || b.id() == this.block.id())) {
                 return sideReg;
                }

                //this block is hanging
                return sideHang;
                
            }
                
        }
    }

    
}
