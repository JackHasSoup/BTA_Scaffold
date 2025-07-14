package jackhassoup.scaffold.blocks;

import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockLogicPlatform extends BlockLogicRightClickExpandable{

    public BlockLogicPlatform(Block<?> block, Material material, int maxSpan, boolean expandVertical) {
        super(block, material, maxSpan, expandVertical, true);
        this.setBlockBounds(0.0,0.0,0.0,1.0,0.375,1.0);
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
        // if (world.getBlockMaterial(x, y + 1, z) != Material.air && world.getBlockMaterial(x, y - 1, z) != Material.air) {
        //     // block above and below, do not allow stand so player can fall through
        //     return null;
        // }
        // default, top face collision
        return AABB.getTemporaryBB(x, y + 0.3749F, z, x + 1, y + 0.375, z + 1);
        // return AABB.getTemporaryBB(x + 0.3F, y, z + 0.3F, x + 0.7F, y + 1, z + 0.7F);
    }

    @Override
    public boolean collidesWithEntity(Entity entity, World world, int x, int y, int z) {
        if(entity instanceof PlayerLocal && ((PlayerLocal)entity).input == null) return false;
        if(entity.isSneaking() && world.getBlockMaterial(x, y - 1, z) == Material.air) return true;

        return !entity.isSneaking() && entity.y - entity.heightOffset > y;
    }

}
