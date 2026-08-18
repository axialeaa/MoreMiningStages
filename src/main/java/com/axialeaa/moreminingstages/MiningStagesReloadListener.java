package com.axialeaa.moreminingstages;

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

	private static final Identifier ID = MoreMiningStages.id("mining_stages");
	private static final Codec<List<Identifier>> CODEC = Identifier.CODEC.listOf().fieldOf("textures").codec();

	@Override
	public void onResourceManagerReload(ResourceManager manager) {
		Identifier id = ID.withSuffix(".json");

		try (BufferedReader reader = manager.openAsReader(id)) {
			List<RenderType> destroyTypes = MoreMiningStages.getDestroyTypes();
			destroyTypes.clear();

			for (Identifier texture : readTextures(reader))
				destroyTypes.add(RenderTypes.crumbling(rectifyPath(texture)));
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

	private static List<Identifier> readTextures(BufferedReader reader) {
		return CODEC.parse(JsonOps.INSTANCE, StrictJsonParser.parse(reader)).getOrThrow();
	}

	private static Identifier rectifyPath(Identifier texture) {
		return texture.withPath(path -> "textures/" + path + ".png");
	}

}
