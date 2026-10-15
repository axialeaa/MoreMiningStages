package com.axialeaa.moreminingstages;

import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.StrictJsonParser;
import org.jspecify.annotations.NullMarked;

import java.io.BufferedReader;
import java.util.List;

@NullMarked
public class MiningStagesReloadListener implements ResourceManagerReloadListener {

	private static final Identifier ID = MoreMiningStages.id("mining_stages");

	@Override
	public void onResourceManagerReload(ResourceManager manager) {
		Identifier id = ID.withSuffix(".json");

		try (BufferedReader reader = manager.openAsReader(id)) {
			List<RenderType> destroyTypes = MoreMiningStages.getDestroyTypes();
			List<RenderType> destroyTypesOit = MoreMiningStages.getDestroyTypesOit();

			destroyTypes.clear();
			destroyTypesOit.clear();

			for (MiningStage miningStage : readMiningStages(reader)) {
				destroyTypes.add(RenderTypes.crumbling(rectifyPath(miningStage.texture())));
				destroyTypesOit.add(RenderTypes.crumbling(rectifyPath(miningStage.getOrCreateTextureOit())));
			}
		}
		catch (Exception e) {
			MoreMiningStages.LOGGER.error("Error occurred while loading {}", id, e);
		}
	}

	static void register() {
		ResourceLoader resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);

		resourceLoader.addListenerOrdering(ResourceReloaderKeys.AFTER_VANILLA, ID);
		resourceLoader.registerReloadListener(ID, new MiningStagesReloadListener());
	}

	private static List<MiningStage> readMiningStages(BufferedReader reader) {
		return MiningStage.LIST_CODEC.parse(JsonOps.INSTANCE, StrictJsonParser.parse(reader)).getOrThrow();
	}

	private static Identifier rectifyPath(Identifier texture) {
		return texture.withPath(path -> "textures/" + path + ".png");
	}

}
