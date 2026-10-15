package com.axialeaa.moreminingstages;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

import java.util.List;

public record MiningStage(Identifier texture, Identifier textureOit) {

	private static final Codec<MiningStage> CODEC = RecordCodecBuilder.create(instance -> instance
		.group(
			Identifier.CODEC.fieldOf("texture").forGetter(MiningStage::texture),
			Identifier.CODEC.fieldOf("texture_oit").forGetter(MiningStage::textureOit)
		)
		.apply(instance, MiningStage::new)
	);

	static final Codec<List<MiningStage>> LIST_CODEC = CODEC.listOf();

}