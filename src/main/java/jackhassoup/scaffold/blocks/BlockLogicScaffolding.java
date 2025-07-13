package jackhassoup.scaffold.blocks;

import java.util.List;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFullyRotatable;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

import static jackhassoup.scaffold.ScaffoldMod.LOGGER;

public class BlockLogicScaffolding extends BlockLogicTransparent {

    public BlockLogicScaffolding(Block<?> block, Material material) {
        super(block, material);
    }

    @Override
    public boolean isClimbable(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isCubeShaped() {
        return false;
    }

    @Override
    public boolean isSolidRender() {
        return false;
    }

    @Override
    public boolean blocksLight() {
        return false;
    }

    @Override
    public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
        if (world.getBlockMaterial(x, y + 1, z) != Material.air && world.getBlockMaterial(x, y - 1, z) != Material.air) {
            // block above and below, do not allow stand so player can fall through
            return null;
        }
        // default, top face collision
        return AABB.getTemporaryBB(x, y + 0.99F, z, x + 1, y + 1, z + 1);
        // return AABB.getTemporaryBB(x + 0.3F, y, z + 0.3F, x + 0.7F, y + 1, z + 0.7F);
    }

    @Override
    public boolean collidesWithEntity(Entity entity, World world, int x, int y, int z) {
        if(entity.isSneaking() && world.getBlockMaterial(x, y - 1, z) == Material.air) return true;

        return !entity.isSneaking() && entity.y - entity.heightOffset > y;
    }

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
        ItemStack heldItem = player.getHeldItem();

        if(heldItem == null || player.isSneaking() || heldItem.getItem() != world.getBlock(x,y,z).asItem() ) return false;

        if(side != Side.TOP && side != Side.BOTTOM)
        {
            boolean reachedTop = false;
            int ytop = y;

            //keep going up a block untill either a scaffold can or can't be placed
            while(!reachedTop)
            {
                Block b = world.getBlock(x, ytop, z);
                if(b == null || b.getMaterial() == Material.air){reachedTop = true; break;}
                if(b.id() == this.block.id())
                {
                     ytop++;
                }else{
                    return false;
                }

            }

            
            world.setBlockAndMetadataWithNotify(x, ytop, z, this.block.id(), 0);
        }else{
            Direction d = player.getHorizontalPlacementDirection(side);
            int xo = d.getOffsetX();
            int zo = d.getOffsetZ();

            if(world.getBlock(x + xo, y, z+zo ) == null){world.setBlockAndMetadataWithNotify(x + xo, y, z+zo , this.id(), 0);}//if its air
            else if(world.getBlock(x + xo, y, z+zo ).getMaterial() == Material.air){world.setBlockAndMetadataWithNotify(x + xo, y, z+zo , this.id(), 0);}//also if its air
            else if (world.getBlock(x + xo, y, z+zo ).id() != this.id()) {return false;} //if its a different block exit
            else{
                int X = xo;
                int Z = zo;

                boolean placed = false;
                //run along placement axis untill either a solid block is hit and the function exits, or air is hit and the block gets placed
                while(!placed)
                {
                    Block b = world.getBlock(x+X, y, z+Z);
                    if(b == null || b.getMaterial() == Material.air)
                    {
                        placed = true;
                        break;
                    }
                    if(b.id() == this.id()){X+=xo; Z+=zo;}else{return false;}
                }
                world.setBlockAndMetadataWithNotify(x + X ,y, z+Z , this.id(), 0);

            }
        }
        
            //use item
            if(player.gamemode != Gamemode.creative) heldItem.stackSize -= 1;
            world.playBlockSoundEffect(player, x, y, z, this.block, EnumBlockSoundEffectType.PLACE);
            return true;
    }

}
