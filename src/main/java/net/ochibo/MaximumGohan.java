package net.ochibo;

import net.fabricmc.api.ModInitializer;

import net.ochibo.block.ModBlocks;
import net.ochibo.components.ModDataComponentTypes;
import net.ochibo.item.ModItemGroups;
import net.ochibo.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumGohan implements ModInitializer {
	public static final String MOD_ID = "maximum-gohan";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModDataComponentTypes.registerDataComponentTypes();
		ModBlocks.registerModBlocks();
	}
}