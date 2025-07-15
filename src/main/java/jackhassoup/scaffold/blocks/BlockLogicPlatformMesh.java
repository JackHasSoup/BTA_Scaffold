package jackhassoup.scaffold.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.world.World;

public class BlockLogicPlatformMesh extends BlockLogicPlatform{

    public BlockLogicPlatformMesh(Block<?> block, Material material, int maxSpan, boolean expandVertical, boolean canDropThrough) {
        super(block, material, maxSpan, expandVertical, canDropThrough);
    }
    
    @Override
    public boolean collidesWithEntity(Entity entity, World world, int x, int y, int z) {
        if(entity instanceof EntityItem) return false;
        return super.collidesWithEntity(entity, world, x, y, z);
    }
}
