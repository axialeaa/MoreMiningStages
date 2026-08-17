package com.axialeaa.modid;

import com.axialeaa.modid.network.ExampleS2CPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID = "mod-id";
	public static final String MOD_NAME = "Example Mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} initialized! Insert funny joke here.", MOD_NAME);
		PayloadTypeRegistry.clientboundPlay().register(ExampleS2CPayload.TYPE, ExampleS2CPayload.STREAM_CODEC);
	}

	public static void meow() {
		LOGGER.info("Meow :3");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static <T> ResourceKey<T> resourceKey(ResourceKey<? extends Registry<T>> registryKey, String path) {
		return ResourceKey.create(registryKey, id(path));
	}

	public static <T, V extends T> V register(Registry<T> registry, String path, V value) {
		return Registry.register(registry, id(path), value);
	}

	public static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> payloadType(String path) {
		return new CustomPacketPayload.Type<>(id(path));
	}

}