package com.axialeaa.moreminingstages;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.Optional;

public record MiningStage(Identifier texture, Optional<Identifier> textureOit) {

	private static final Codec<MiningStage> SIMPLE_CODEC = Identifier.CODEC.xmap(MiningStage::createWithDefaultOit, MiningStage::texture);
	private static final Codec<MiningStage> FULL_CODEC = RecordCodecBuilder.create(instance -> instance
		.group(
			Identifier.CODEC.fieldOf("texture").forGetter(MiningStage::texture),
			Identifier.CODEC.optionalFieldOf("texture_oit").forGetter(MiningStage::textureOit)
		)
		.apply(instance, MiningStage::new)
	);

	public static final Codec<MiningStage> CODEC = Codec.either(SIMPLE_CODEC, FULL_CODEC)
		.xmap(
			Either::unwrap,
			miningStage -> miningStage.textureOit.isPresent() ? Either.right(miningStage) : Either.left(miningStage)
		);

	public static final Codec<List<MiningStage>> LIST_CODEC = CODEC.listOf().fieldOf("textures").codec();

	public static MiningStage createWithDefaultOit(Identifier texture) {
		return new MiningStage(texture, Optional.empty());
	}

	public Identifier getOrCreateTextureOit() {
		return this.textureOit.orElse(this.texture.withSuffix("_oit"));
	}

}