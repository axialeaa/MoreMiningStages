package com.axialeaa.modid.data.registry.content;

import com.axialeaa.modid.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class References {

	private static BlockItemId blockItem(String path) {
		return new BlockItemId(block(path), item(path));
	}

	private static ResourceKey<Block> block(String path) {
		return ExampleMod.resourceKey(Registries.BLOCK, path);
	}

	private static ResourceKey<Item> item(String path) {
		return ExampleMod.resourceKey(Registries.ITEM, path);
	}

}
