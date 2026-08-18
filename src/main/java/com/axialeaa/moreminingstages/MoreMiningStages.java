package com.axialeaa.moreminingstages;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MoreMiningStages implements ClientModInitializer {

	public static final String MOD_ID = "moreminingstages";
	public static final String MOD_NAME = "More Mining Stages";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	private static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();

	private static final List<RenderType> DESTROY_TYPES = new ArrayList<>();

	@Override
	public void onInitializeClient() {
		MiningStagesReloadListener.register();
		registerPack("double_destruction", PackActivationType.DEFAULT_ENABLED);
	}

	private static void registerPack(String path, PackActivationType activationType) {
		Identifier id = id(path);
		ResourceLoader.registerBuiltinPack(id, MOD_CONTAINER, Component.translatable(id.toLanguageKey("resourcePack", "name")), activationType);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static List<RenderType> getDestroyTypes() {
		return DESTROY_TYPES;
	}

}