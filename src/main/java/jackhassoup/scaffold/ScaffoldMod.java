package jackhassoup.scaffold;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jackhassoup.scaffold.blocks.ScaffoldBlocks;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ItemInitEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ScaffoldMod implements ModInitializer, RecipeEntrypoint, GameStartEntrypoint, ItemInitEntrypoint {
	public static final String MOD_ID = "scaffold";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Scaffold initialized.");
	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void afterItemInit() {
		ScaffoldBlocks.initBlocks();
	}
}
