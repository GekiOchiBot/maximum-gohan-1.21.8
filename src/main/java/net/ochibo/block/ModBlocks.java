package net.ochibo.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.ochibo.MaximumGohan;
import net.ochibo.block.custom.CampfireModSmokerBlock;

import java.util.function.Function;

public class ModBlocks {

//    public static final Block RUSTY_COBBLESTONE = registerBlock("rusty_cobblestone", properties -> new Block(properties.mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 6.0F)));
//    public static final Block IRON_RUSTER_BLOCK = registerBlock("iron_ruster_block",properties -> new IronRusterBlock(properties.mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.IRON)));

    public static final Block MOD_SMOKER = registerBlock("iron_ruster_block",properties -> new CampfireModSmokerBlock(properties.nonOpaque()));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MaximumGohan.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(MaximumGohan.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(MaximumGohan.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MaximumGohan.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MaximumGohan.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MaximumGohan.MOD_ID, name)))));
    }

    public static void registerModBlocks(){
        MaximumGohan.LOGGER.info("Registering Mod Blocks for " + MaximumGohan.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
        //    fabricItemGroupEntries.add(ModBlocks.RUSTY_COBBLESTONE);
        //});
    }
}
