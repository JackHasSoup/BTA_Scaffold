package jackhassoup.scaffold.blocks.models;

import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BambooScaffoldModel<T extends BlockLogic> extends BlockModelBackFaceRenderable<T> {

    protected IconCoordinate sideReg;
    protected IconCoordinate sideHang;
    protected IconCoordinate empty;

    public BambooScaffoldModel(Block<T> block, boolean renderInside) {
        super(block, renderInside);
        this.sideReg = TextureRegistry.getTexture("scaffold:block/scaffolding/bamboo/side");
        this.sideHang = TextureRegistry.getTexture("scaffold:block/scaffolding/bamboo/side_hang");
        this.empty = TextureRegistry.getTexture("scaffold:block/empty");
    }

    @Override
    public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
        //check if hanging for side and bottom texture
        switch (side) {
            case TOP:
                return super.getBlockTexture(blockAccess, x, y, z, side);
            
            case BOTTOM:
                if (blockAccess.getBlockMaterial(x, y - 1, z) != Material.air) return super.getBlockTexture(blockAccess, x, y, z, side);
                return empty;
        
            default:
            {
                //no air below block? no problem render the legs!
                if (blockAccess.getBlockMaterial(x, y - 1, z) != Material.air) {
                 return sideReg;
                }

                //this block is not hanging, don't render support/hang/legs between blocks
                //check the side trying to be rendered
                Block<?> b = blockAccess.getBlock(x+side.getOffsetX(), y, z+side.getOffsetZ());
                if(b == null || b.getMaterial() == Material.air){return sideHang;}
                return empty;
            }
                
        }
    }

    
}
