package jackhassoup.scaffold.blocks.models;

import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;

public class BlockModelColouredScaffolding <T extends BlockLogic> extends BlockModelScaffolding<T>{

     private final IconCoordinate[] SideTextures;
     private final IconCoordinate[] HangTextures;
     private final IconCoordinate[] BottomTextures;
     private final IconCoordinate[] TopTextures;

    public BlockModelColouredScaffolding(Block<T> block, boolean renderInside, String mat) {
        super(block, renderInside, mat);

        // Having to init blocks like this makes me sad
        SideTextures  = new IconCoordinate[]{
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/white/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/orange/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/magenta/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lightblue/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/yellow/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lime/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/pink/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/gray/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/silver/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/cyan/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/purple/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/blue/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/brown/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/green/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/red/side"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/black/side"),
        };

        HangTextures  = new IconCoordinate[]{
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/white/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/orange/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/magenta/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lightblue/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/yellow/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lime/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/pink/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/gray/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/silver/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/cyan/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/purple/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/blue/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/brown/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/green/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/red/side_hang"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/black/side_hang"),
        };

        BottomTextures  = new IconCoordinate[]{
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/white/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/orange/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/magenta/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lightblue/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/yellow/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lime/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/pink/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/gray/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/silver/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/cyan/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/purple/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/blue/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/brown/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/green/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/red/bottom"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/black/bottom"),
        };

        TopTextures  = new IconCoordinate[]{
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/white/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/orange/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/magenta/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lightblue/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/yellow/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/lime/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/pink/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/gray/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/silver/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/cyan/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/purple/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/blue/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/brown/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/green/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/red/top"),
        TextureRegistry.getTexture("scaffold:block/scaffolding/"+mat+"/black/top"),
        };
    }
    
    @Override
    public IconCoordinate getBlockTexture(WorldSource blockAccess, int x, int y, int z, Side side) {
        //get metadata
        int data = blockAccess.getBlockMetadata(x, y, z);

        //check if hanging for side and bottom texture
        switch (side) {
            case TOP:
                return TopTextures[data&15];
            
            case BOTTOM:
                if (isSolidOrThis(blockAccess.getBlock(x, y - 1, z))) return BottomTextures[data&15];
                return empty;
        
            default:
            {
                //check the side trying to be rendered, don't render support/hang/legs between blocks
                Block<?> b = blockAccess.getBlock(x+side.getOffsetX(), y, z+side.getOffsetZ());
                if(isSolidOrThis(b)) return empty;

                //no air below block? no problem render the legs!
                b = blockAccess.getBlock(x, y - 1, z);
                
                if (isSolidOrThis(b)) {
                 return SideTextures[data&15];
                }

                //this block is hanging
                return HangTextures[data&15];
                
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

    @Override
    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
        switch (side) {
            case TOP:
                return TopTextures[data & 15];

            case BOTTOM:
                return BottomTextures[data & 15];
        
            default:
                return SideTextures[data & 15];
        }
		
	}
}
