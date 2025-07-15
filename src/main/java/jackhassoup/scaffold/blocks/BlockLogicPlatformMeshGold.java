package jackhassoup.scaffold.blocks;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityMeshGold;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicPlatformMeshGold extends BlockLogicPlatformMesh{

    public BlockLogicPlatformMeshGold(Block<?> block, Material material, int maxSpan, boolean expandVertical, boolean canDropThrough) {
        super(block, material, maxSpan, expandVertical, canDropThrough);
        block.withEntity(TileEntityMeshGold::new);
    }

    @Override
    public boolean isCubeShaped(){return true;} //to allow for the placement of non solids on top

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
        ItemStack heldItem = player.getHeldItem();

        //if hand empty or holding this platform, do the regular platform logic
        if(heldItem.getItem().id == this.block.asItem().id || heldItem == null || heldItem.stackSize <= 0) return super.onBlockRightClicked(world, x, y, z, player, side, xPlaced, yPlaced);

      TileEntityMeshGold meshGold = (TileEntityMeshGold)world.getTileEntity(x, y, z);
      boolean flag = meshGold.setFilterItem(player, heldItem);
      if (heldItem != null && heldItem.stackSize <= 0) {
         player.inventory.setItem(player.inventory.getCurrentItemIndex(), (ItemStack)null);
      }

      return flag;
   }

   @Override
   public boolean collidesWithEntity(Entity entity, World world, int x, int y, int z) {

        if(entity instanceof Player)
        {
            TileEntityMeshGold meshGold = (TileEntityMeshGold)world.getTileEntity(x, y, z);

            //if no filter is set, work as a regular mesh platform
            if(meshGold.filterItem == null) return super.collidesWithEntity(entity, world, x, y, z);

            ItemStack heldItem = ((Player)entity).getHeldItem();
            boolean match = heldItem != null && heldItem.isItemEqual(meshGold.filterItem);

            //if the player is holding the same item as the filter let them drop down
            if(canDrop(entity) && match) return false;
            return true;
        }else{
            return super.collidesWithEntity(entity, world, x, y, z);
        }
   }
}
