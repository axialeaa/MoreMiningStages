package com.axialeaa.moreminingstages.mixin;

import com.axialeaa.moreminingstages.MoreMiningStages;
import net.minecraft.server.level.BlockDestructionProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockDestructionProgress.class)
public class BlockDestructionProgressMixin {

	@ModifyConstant(method = "setProgress", constant = @Constant(intValue = 10))
	private int modifyStageCount(int constant) {
		return MoreMiningStages.getDestroyTypes().size();
	}

}
