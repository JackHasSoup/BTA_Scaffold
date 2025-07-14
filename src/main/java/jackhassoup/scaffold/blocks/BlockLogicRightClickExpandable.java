package jackhassoup.scaffold.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicRightClickExpandable extends BlockLogicTransparent{

    protected final int maxSpan;
    protected final boolean expandVertical, supportHorizontal;
    public BlockLogicRightClickExpandable(Block<?> block, Material material, int maxSpan, boolean expandVertical, boolean supportHorizontal) {
        super(block, material);

        this.maxSpan = maxSpan;
        this.expandVertical = expandVertical; //when the side of this block is right clicked will it expand upwards
        this.supportHorizontal = supportHorizontal; //when checking for support can this block be supported by a block on the side
    }
    
    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
        ItemStack heldItem = player.getHeldItem();
        
        if(player.isSneaking() && heldItem == null)
        {
            this.harvestBlock(world, player, x, y, z, z, null);
            world.setBlockWithNotify(x, y, z, 0);
            return false;
        }

        if(heldItem == null || heldItem.getItem() != world.getBlock(x,y,z).asItem() || heldItem.getMetadata() != world.getBlockMetadata(x, y, z)) return false;

        if(expandVertical && side != Side.TOP && side != Side.BOTTOM)
        {
            boolean reachedTop = false;
            int ytop = y;

            //keep going up a block untill either a scaffold can or can't be placed
            while(!reachedTop)
            {
                Block<?> b = world.getBlock(x, ytop, z);
                if(b == null || b.getMaterial() == Material.air){reachedTop = true; break;}
                if(b.id() == this.block.id())
                {
                     ytop++;
                }else{
                    return false;
                }

            }

            world.setBlockWithNotify(x, ytop, z, this.block.id());
        }else{
            Direction d = player.getHorizontalPlacementDirection(side);
            int xo = d.getOffsetX();
            int zo = d.getOffsetZ();

            // Find where the new block will be placed
            int X = xo;
            int Z = zo;
            while (true) {
                Block<?> b = world.getBlock(x + X, y, z + Z);
                if (b == null || b.getMaterial() == Material.air) {
                    break;
                }
                if (b.id() == this.id()) {
                    X += xo;
                    Z += zo;
                } else {
                    return false;
                }
            }
            int placeX = x + X;
            int placeY = y;
            int placeZ = z + Z;

            //check if this block would be supported
            if (!this.hasSupport(world, placeX, placeY, placeZ)) return false;

            world.setBlockWithNotify(placeX, placeY, placeZ, this.id());
        }

        //use item
        if(player.gamemode != Gamemode.creative) heldItem.stackSize -= 1;
        world.playBlockSoundEffect(player, x, y, z, this.block, EnumBlockSoundEffectType.PLACE);
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return hasSupport(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        if(!hasSupport(world, x, y, z))
        {
            Player p = world.getClosestPlayer(x, y, z, maxSpan * 3);
            if(p == null){
                this.dropBlockWithCause(world, EnumDropCause.WORLD, x, y, z, blockId, null, null);
            }else{
                this.harvestBlock(world, world.getClosestPlayer(x, y, z, maxSpan * 3), x, y, z, blockId, null);
            }
            
            world.setBlockWithNotify(x, y, z, 0);
        }
   }

    /**
     * Checks if the given position has support within maxSpan (taxicab distance to ground or support column).
     */
    private boolean hasSupport(World world, int x, int y, int z) {
        //BFS to find nearest support within maxSpan
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        java.util.Set<String> visited = new java.util.HashSet<>();
        queue.add(new int[]{x, y, z, 0});
        visited.add(x + "," + y + "," + z);
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int cx = node[0];
            int cy = node[1];
            int cz = node[2];
            int dist = node[3];
            if (dist > maxSpan) continue;
            //check if directly supported by non-air block below
            if (!supportHorizontal && world.getBlockMaterial(cx, cy - 1, cz).isSolid()) {
                return true;
            }else if(supportHorizontal){
                //check surrounding blocks
                for(int X = -1; X < 1; X++)
                {
                    for(int Z = -1; Z < 1; Z++)
                    {
                        if(X==0&&Z==0) continue;//if both 0, that is this block. it can't support itself

                        if(world.getBlockMaterial(X, cy, Z).isSolid() && world.getBlockId(X, cy, Z) != id()) return true;
                    }
                }
            }
            //add neighbors (horizontal and down)
            int[][] offsets = { {1,0,0}, {-1,0,0}, {0,0,1}, {0,0,-1}, {0,-1,0} };
            for (int[] off : offsets) {
                int nx = cx + off[0];
                int ny = cy + off[1];
                int nz = cz + off[2];
                String key = nx + "," + ny + "," + nz;
                if (ny < 0 || visited.contains(key)) continue;
                Block<?> b = world.getBlock(nx, ny, nz);
                if (b != null && b.id() == this.id()) {
                    queue.add(new int[]{nx, ny, nz, dist + 1});
                    visited.add(key);
                }
            }
        }
        return false;
    }
}
