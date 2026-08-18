package com.axialeaa.moreminingstages.data;

import com.axialeaa.moreminingstages.MoreMiningStages;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.StrictJsonParser;
import org.jspecify.annotations.NullMarked;

import java.io.BufferedReader;

@NullMarked
public class MiningStagesReloadListener implements ResourceManagerReloadListener {

	public static final Identifier ID = MoreMiningStages.id("mining_stages");

	@Override
	public void onResourceManagerReload(ResourceManager manager) {
		Identifier id = ID.withSuffix(".json");
		MoreMiningStages.miningStages = null;

		try (BufferedReader reader = manager.openAsReader(id)) {
			MoreMiningStages.miningStages = new MiningStagesDefinition(StrictJsonParser.parse(reader).getAsJsonObject());
		}
		catch (Exception e) {
			MoreMiningStages.LOGGER.error("Error occurred while loading {}", id, e);
		}
	}

	public static void register() {
		ResourceLoader resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);

		resourceLoader.addListenerOrdering(ResourceReloaderKeys.AFTER_VANILLA, ID);
		resourceLoader.registerReloadListener(ID, new MiningStagesReloadListener());
	}

}
