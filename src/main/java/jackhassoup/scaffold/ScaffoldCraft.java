package jackhassoup.scaffold;

import jackhassoup.scaffold.blocks.ScaffoldBlocks;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryUndyeing;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ScaffoldCraft implements RecipeEntrypoint{

    public static  final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = ((RecipeGroup<RecipeEntryCrafting<?, ?>>) RecipeBuilder.getRecipeGroup(ScaffoldMod.MOD_ID, "workbench", new RecipeSymbol(Blocks.WORKBENCH.getDefaultStack())));

    @Override
    public void onRecipesReady() {
        WORKBENCH.register("woodscaffolddying", new RecipeEntryDyeing(new RecipeSymbol("scaffold:scaffold_woods"), new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED), false, false));
        WORKBENCH.register("woodscaffoldundying", new RecipeEntryUndyeing(new RecipeSymbol("scaffold:scaffold_woods"), new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD)));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BLOCK_SUGARCANE_BAKED)
        .create("scaffoldBambooCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_BAMBOO, ScaffoldConfig.scaffoldCraftAmount * 9));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.PAPER_WALL)
        .create("scaffoldPaperCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_PAPER, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.PLANKS_OAK)
        .create("scaffoldWoodCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD, ScaffoldConfig.scaffoldCraftAmount ));

        for(int i =0; i<16; i++)
        {
            RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
            .setShape("SBS")
            .addInput('S', Items.STICK)
            .addInput('B', Blocks.PLANKS_OAK_PAINTED, 15-i)
            .create("scaffoldWoodCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED, ScaffoldConfig.scaffoldCraftAmount, (15-i)*16));
        }

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', "minecraft:cobblestones")
        .create("scaffoldCobbleCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_COBBLE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.SLATE)
        .create("scaffoldSlateCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_SLATE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', Items.INGOT_IRON)
        .create("scaffoldIronCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_IRON, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("SBS")
        .addInput('S', Items.STICK)
        .addInput('B', Items.INGOT_STEEL_CRUDE)
        .create("scaffoldSteelCraft", new ItemStack(ScaffoldBlocks.SCAFFOLD_STEEL, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Items.CLOTH)
        .create("platformClothCraft", new ItemStack(ScaffoldBlocks.PLATFORM_CLOTH, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.MESH)
        .create("platformMeshCraft", new ItemStack(ScaffoldBlocks.PLATFORM_MESH, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.MESH_GOLD)
        .create("platformMeshGoldCraft", new ItemStack(ScaffoldBlocks.PLATFORM_MESH_GOLD, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_BASALT)
        .create("platformBasaltCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_BASALT, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_CLAY)
        .create("platformBrickCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_CLAY, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_GRANITE)
        .create("platformGraniteCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_GRANITE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_LIMESTONE)
        .create("platformLimestoneCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_LIMESTONE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_MARBLE)
        .create("platformMarbleCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_MARBLE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_STONE_POLISHED)
        .create("platformPolishedStoneCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_POLISHED_STONE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_SLATE)
        .create("platformSlateCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_SLATE, ScaffoldConfig.scaffoldCraftAmount ));

        RecipeBuilder.Shaped(ScaffoldMod.MOD_ID)
        .setShape("S")
        .setShape("B")
        .setShape("S")
        .addInput('S', Items.STICK)
        .addInput('B', Blocks.BRICK_STONE)
        .create("platformStoneCraft", new ItemStack(ScaffoldBlocks.PLATFORM_BRICK_STONE, ScaffoldConfig.scaffoldCraftAmount ));
    }

    @Override
    public void initNamespaces() {
        RecipeBuilder.initNameSpace(ScaffoldMod.MOD_ID);
        RecipeBuilder.getRecipeNamespace(ScaffoldMod.MOD_ID);

        Registries.ITEM_GROUPS.register("scaffold:scaffold_woods", Registries.stackListOf(
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD,1,0),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,0),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,1),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,2),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,3),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,4),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,5),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,6),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,7),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,8),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,9),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,10),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,11),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,12),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,13),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,14),
            new ItemStack(ScaffoldBlocks.SCAFFOLD_WOOD_PAINTED,1,15)
        ));
    }
    
}
