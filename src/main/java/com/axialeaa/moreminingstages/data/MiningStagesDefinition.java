package com.axialeaa.moreminingstages.data;

import com.google.common.collect.Streams;
import com.google.gson.JsonObject;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.GsonHelper;

import java.util.List;
import java.util.stream.Collectors;

public final class MiningStagesDefinition {

	private final List<Identifier> textures;
	public final List<RenderType> renderTypes;

	public MiningStagesDefinition(List<Identifier> textures) {
		this.textures = textures;
		this.renderTypes = textures.stream().map(RenderTypes::crumbling).collect(Collectors.toList());
	}

	public MiningStagesDefinition(JsonObject data) {
		this(Streams.stream(GsonHelper.getAsJsonArray(data, "textures"))
			.map(element -> GsonHelper.convertToString(element, "texture"))
			.map(MiningStagesDefinition::rectifyPath)
			.collect(Collectors.toList()));
	}

	private static Identifier rectifyPath(String string) {
		return Identifier.parse(string).withPath(path -> "textures/" + path + ".png");
	}

	public int getFrameCount() {
		return this.textures.size();
	}

}
