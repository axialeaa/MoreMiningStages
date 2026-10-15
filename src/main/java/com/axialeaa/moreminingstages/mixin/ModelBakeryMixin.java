package com.axialeaa.moreminingstages.mixin;

import com.axialeaa.moreminingstages.MoreMiningStages;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.resources.model.ModelBakery;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {

    @Redirect(
		method = "<clinit>",
		slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;BREAKING_LOCATIONS_OIT:Ljava/util/List;", opcode = Opcodes.PUTSTATIC)),
		at = @At(value = "INVOKE",
			target = "Ljava/util/stream/Stream;collect(Ljava/util/stream/Collector;)Ljava/lang/Object;",
			ordinal = 0
		)
	)
    private static Object overrideDestroyTypes(Stream<RenderType> instance, Collector<?, ?, ?> arCollector) {
        List<RenderType> customDestroyTypes = MoreMiningStages.getDestroyTypes();

        customDestroyTypes.clear();
        instance.forEach(customDestroyTypes::add);

        return customDestroyTypes;
    }

	@Redirect(
		method = "<clinit>",
		slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;BREAKING_LOCATIONS_OIT:Ljava/util/List;", opcode = Opcodes.PUTSTATIC)),
		at = @At(value = "INVOKE",
			target = "Ljava/util/stream/Stream;collect(Ljava/util/stream/Collector;)Ljava/lang/Object;",
			ordinal = 1
		)
	)
	private static Object overrideDestroyTypesOit(Stream<RenderType> instance, Collector<?, ?, ?> arCollector) {
		List<RenderType> customDestroyTypes = MoreMiningStages.getDestroyTypesOit();

		customDestroyTypes.clear();
		instance.forEach(customDestroyTypes::add);

		return customDestroyTypes;
	}

}
