package com.axialeaa.moreminingstages.data;

import com.axialeaa.moreminingstages.MoreMiningStages;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
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

	public static final Identifier ID = MoreMiningStages.id("mining_stages");
	private static final Codec<List<Identifier>> CODEC = Identifier.CODEC.listOf().fieldOf("textures").codec();

	@Override
	public void onResourceManagerReload(ResourceManager manager) {
		Identifier id = ID.withSuffix(".json");

		try (BufferedReader reader = manager.openAsReader(id)) {
			JsonElement json = StrictJsonParser.parse(reader);
			List<Identifier> textures = CODEC.parse(JsonOps.INSTANCE, json).getOrThrow();

			MoreMiningStages.getDestroyTypes().clear();
			for (Identifier texture : textures) {
				Identifier textureLocation = texture.withPath(path -> "textures/" + path + ".png");

				MoreMiningStages.getDestroyTypes().add(RenderTypes.crumbling(textureLocation));
			}
		} catch (Exception e) {
			MoreMiningStages.LOGGER.error("Error occurred while loading {}", id, e);
		}
	}

	public static void register() {
		ResourceLoader resourceLoader = ResourceLoader.get(PackType.CLIENT_RESOURCES);

		resourceLoader.addListenerOrdering(ResourceReloaderKeys.AFTER_VANILLA, ID);
		resourceLoader.registerReloadListener(ID, new MiningStagesReloadListener());
	}

}
