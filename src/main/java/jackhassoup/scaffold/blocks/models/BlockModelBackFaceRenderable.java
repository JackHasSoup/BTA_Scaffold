package jackhassoup.scaffold.blocks.models;

import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelTransparent;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;

public class BlockModelBackFaceRenderable<T extends BlockLogic> extends BlockModelTransparent<T>  {

    public BlockModelBackFaceRenderable(Block<T> block, boolean renderInside) {
        super(block, renderInside);
    }
    

     @Override
    public boolean render(Tessellator tessellator, int x, int y, int z) 
    {
        renderStandardBlockWithBackfaces(tessellator, this, this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z), x, y, z, 255, 255, 255);
        return super.render(tessellator, x, y, z);
    }

    public boolean renderStandardBlockWithBackfaces(Tessellator tessellator, BlockModel<?> blockModel, AABB bounds, int x, int y, int z, float r, float g, float b) 
    {
        renderBlocks.enableAO = true;
        int meta = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
        renderBlocks.cache.setupCache(blockModel.block, renderBlocks.blockAccess, x, y, z);
        boolean somethingRendered = false;
        for (Side side : Side.sides) {

            somethingRendered |= renderBlocks.renderSide(tessellator, blockModel, bounds, x, y, z, r, g, b, side, meta);
            somethingRendered |= this.renderBackface(tessellator, blockModel, bounds, x, y, z, r, g, b, side, meta, side.getOffsetX(), side.getOffsetY(), side.getOffsetZ());
        }
        renderBlocks.enableAO = false;
        return somethingRendered;
    }

    private boolean renderBackface(Tessellator tessellator, BlockModel<?> blockModel, AABB bounds, int x, int y, int z, float r, float g, float b, Side side, int meta, int dirX, int dirY, int dirZ) {
        IconCoordinate tex = blockModel.getBlockTexture(renderBlocks.blockAccess, x, y, z, side);
        if (tex == null) return false;
        // Call a helper to render the quad with reversed winding for each face
        if(renderBlocks.renderAllFaces || blockModel.shouldSideBeRendered(renderBlocks.blockAccess, bounds, x + dirX, y + dirY, z + dirZ, side.getId(), meta))
        {
            switch (side) {
            case BOTTOM: renderBottomFaceReversed(tessellator, bounds, x, y, z, tex, r, g, b); break;
            case TOP: renderTopFaceReversed(tessellator, bounds, x, y, z, tex, r, g, b); break;
            case NORTH: renderNorthFaceReversed(tessellator, bounds, x, y, z, tex, r, g, b); break;
            case SOUTH: renderSouthFaceReversed(tessellator, bounds, x, y, z, tex, r, g, b); break;
            case WEST: renderWestFaceReversed(tessellator, bounds, x, y, z, tex, r, g, b); break;
            case EAST: renderEastFaceReversed(tessellator, bounds, x, y, z, tex, r, g, b); break;
            default: return false;
        }
        }
        return true;
    }
    // --- Reversed face helpers ---
    // These methods add the same quad as the normal face, but with the vertex order reversed and no AO/lighting.
    private void renderBottomFaceReversed(Tessellator tessellator, AABB bounds, double x, double y, double z, IconCoordinate tex, float r, float g, float b) {
        if (tex == null) return;
        double d3 = tex.getSubIconU(bounds.minX);
        double d4 = tex.getSubIconU(bounds.maxX);
        double d5 = tex.getSubIconV(bounds.minZ);
        double d6 = tex.getSubIconV(bounds.maxZ);
        double d11 = x + bounds.minX;
        double d12 = x + bounds.maxX;
        double d13 = y + bounds.minY;
        double d14 = z + bounds.minZ;
        double d15 = z + bounds.maxZ;
        tessellator.setColorOpaque_F(r, g, b);
        tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
        tessellator.addVertexWithUV(d12, d13, d14, d4, d5);
        tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
        tessellator.addVertexWithUV(d11, d13, d15, d3, d6);
    }

    private void renderTopFaceReversed(Tessellator tessellator, AABB bounds, double x, double y, double z, IconCoordinate tex, float r, float g, float b) {
        if (tex == null) return;
        double d3 = tex.getSubIconU(bounds.minX);
        double d4 = tex.getSubIconU(bounds.maxX);
        double d5 = tex.getSubIconV(bounds.minZ);
        double d6 = tex.getSubIconV(bounds.maxZ);
        double d11 = x + bounds.minX;
        double d12 = x + bounds.maxX;
        double d13 = y + bounds.maxY;
        double d14 = z + bounds.minZ;
        double d15 = z + bounds.maxZ;
        tessellator.setColorOpaque_F(r, g, b);
        tessellator.addVertexWithUV(d11, d13, d15, d3, d6);
        tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
        tessellator.addVertexWithUV(d12, d13, d14, d4, d5);
        tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
    }

    private void renderNorthFaceReversed(Tessellator tessellator, AABB bounds, double x, double y, double z, IconCoordinate tex, float r, float g, float b) {
        if (tex == null) return;
        double d3 = tex.getSubIconU(bounds.minX);
        double d4 = tex.getSubIconU(bounds.maxX);
        double d5 = tex.getSubIconV(1.0 - bounds.maxY);
        double d6 = tex.getSubIconV(1.0 - bounds.minY);
        double d12 = x + bounds.minX;
        double d13 = x + bounds.maxX;
        double d14 = y + bounds.minY;
        double d15 = y + bounds.maxY;
        double d16 = z + bounds.minZ;
        tessellator.setColorOpaque_F(r, g, b);
        tessellator.addVertexWithUV(d12, d14, d16, d4, d6);
        tessellator.addVertexWithUV(d13, d14, d16, d3, d6);
        tessellator.addVertexWithUV(d13, d15, d16, d3, d5);
        tessellator.addVertexWithUV(d12, d15, d16, d4, d5);
    }

    private void renderSouthFaceReversed(Tessellator tessellator, AABB bounds, double x, double y, double z, IconCoordinate tex, float r, float g, float b) {
        if (tex == null) return;
        double d3 = tex.getSubIconU(bounds.minX);
        double d4 = tex.getSubIconU(bounds.maxX);
        double d5 = tex.getSubIconV(1.0 - bounds.maxY);
        double d6 = tex.getSubIconV(1.0 - bounds.minY);
        double x0 = x + bounds.minX;
        double x1 = x + bounds.maxX;
        double y0 = y + bounds.minY;
        double y1 = y + bounds.maxY;
        double z0 = z + bounds.maxZ;
        tessellator.setColorOpaque_F(r, g, b);
        tessellator.addVertexWithUV(x1, y1, z0, d4, d5);
        tessellator.addVertexWithUV(x1, y0, z0, d4, d6);
        tessellator.addVertexWithUV(x0, y0, z0, d3, d6);
        tessellator.addVertexWithUV(x0, y1, z0, d3, d5);
    }

    private void renderWestFaceReversed(Tessellator tessellator, AABB bounds, double x, double y, double z, IconCoordinate tex, float r, float g, float b) {
        if (tex == null) return;
        double d3 = tex.getSubIconU(bounds.minZ);
        double d4 = tex.getSubIconU(bounds.maxZ);
        double d5 = tex.getSubIconV(1.0 - bounds.maxY);
        double d6 = tex.getSubIconV(1.0 - bounds.minY);
        double d12 = x + bounds.minX;
        double d13 = y + bounds.minY;
        double d14 = y + bounds.maxY;
        double d15 = z + bounds.minZ;
        double d16 = z + bounds.maxZ;
        tessellator.setColorOpaque_F(r, g, b);
        // Reverse winding compared to the front face:
        tessellator.addVertexWithUV(d12, d13, d15, d3, d6); // bottom-left
        tessellator.addVertexWithUV(d12, d14, d15, d3, d5); // top-left
        tessellator.addVertexWithUV(d12, d14, d16, d4, d5); // top-right
        tessellator.addVertexWithUV(d12, d13, d16, d4, d6); // bottom-right
    }

    private void renderEastFaceReversed(Tessellator tessellator, AABB bounds, double x, double y, double z, IconCoordinate tex, float r, float g, float b) {
        if (tex == null) return;
        double uMin = tex.getSubIconU(bounds.minZ);
        double uMax = tex.getSubIconU(bounds.maxZ);
        double vMin = tex.getSubIconV(1.0 - bounds.maxY);
        double vMax = tex.getSubIconV(1.0 - bounds.minY);
        double xMax = x + bounds.maxX;
        double yMin = y + bounds.minY;
        double yMax = y + bounds.maxY;
        double zMin = z + bounds.minZ;
        double zMax = z + bounds.maxZ;
        tessellator.setColorOpaque_F(r, g, b);
        tessellator.addVertexWithUV(xMax, yMax, zMax, uMin, vMin);
        tessellator.addVertexWithUV(xMax, yMax, zMin, uMax, vMin);
        tessellator.addVertexWithUV(xMax, yMin, zMin, uMax, vMax);
        tessellator.addVertexWithUV(xMax, yMin, zMax, uMin, vMax);
    }
}
