package jackhassoup.scaffold.blocks;

import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;


public class BlockLogicScaffolding extends BlockLogicTransparent {

    protected final int maxSpan;
    public BlockLogicScaffolding(Block<?> block, Material material, int maxSpan) {
        super(block, material);
        this.maxSpan = maxSpan;
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
        if(entity instanceof PlayerLocal && ((PlayerLocal)entity).input == null) return false;
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

            // Check for support: taxicab distance to nearest support column or ground <= 8
            boolean hasSupport = false;
            // BFS to find nearest support within maxSpan
            java.util.Queue<int[]> queue = new java.util.LinkedList<>();
            java.util.Set<String> visited = new java.util.HashSet<>();
            queue.add(new int[]{placeX, placeY, placeZ, 0});
            visited.add(placeX + "," + placeY + "," + placeZ);
            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                int cx = node[0];
                int cy = node[1];
                int cz = node[2];
                int dist = node[3];
                if (dist > maxSpan) continue;
                // Check if directly supported by non-air block below
                if (world.getBlockMaterial(cx, cy - 1, cz) != Material.air) {
                    hasSupport = true;
                    break;
                }
                // Add neighbors (horizontal and down)
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
            if (!hasSupport) return false;

            world.setBlockWithNotify(placeX, placeY, placeZ, this.id());
        }
        
            //use item
            if(player.gamemode != Gamemode.creative) heldItem.stackSize -= 1;
            world.playBlockSoundEffect(player, x, y, z, this.block, EnumBlockSoundEffectType.PLACE);
            return true;
    }

}
