package com.axialeaa.moreminingstages.mixin;

import com.axialeaa.moreminingstages.MoreMiningStages;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

	@ModifyConstant(method = "getDestroyStage", constant = @Constant(floatValue = 10.0F))
	private float modifyStageCount(float constant) {
		return (float) MoreMiningStages.getDestroyTypes().size();
	}

}
