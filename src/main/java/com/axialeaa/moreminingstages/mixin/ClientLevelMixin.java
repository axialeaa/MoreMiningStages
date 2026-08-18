package com.axialeaa.moreminingstages.mixin;

import com.axialeaa.moreminingstages.MoreMiningStages;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

	@ModifyConstant(method = "destroyBlockProgress", constant = @Constant(intValue = 10))
	private int test(int constant) {
		return MoreMiningStages.getDestroyTypes().size();
	}

}
