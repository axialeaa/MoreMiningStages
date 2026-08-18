package com.axialeaa.moreminingstages.mixin;

import com.axialeaa.moreminingstages.MoreMiningStages;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.resources.model.ModelBakery;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;collect(Ljava/util/stream/Collector;)Ljava/lang/Object;", ordinal = 2))
    private static Object overrideDestroyTypes(Stream<RenderType> instance, Collector<?, ?, ?> arCollector) {
        List<RenderType> moddedDestroyTypes = MoreMiningStages.getDestroyTypes();
        moddedDestroyTypes.clear();

        instance.forEach(moddedDestroyTypes::add);

        return moddedDestroyTypes;
    }

}
