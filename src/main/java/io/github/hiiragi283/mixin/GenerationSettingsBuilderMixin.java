package io.github.hiiragi283.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Mixin(GenerationSettings.Builder.class)
public abstract class GenerationSettingsBuilderMixin {

    @Unique
    private static final Logger LOGGER = LogManager.getLogger("LavaSweeper");
    @Unique
    private static final List<ConfiguredFeature<?, ?>> springFeatures = Arrays.asList(
            ConfiguredFeatures.SPRING_LAVA_DOUBLE,
            ConfiguredFeatures.SPRING_LAVA,
            ConfiguredFeatures.SPRING_DELTA,
            ConfiguredFeatures.SPRING_CLOSED,
            ConfiguredFeatures.SPRING_CLOSED_DOUBLE,
            ConfiguredFeatures.SPRING_OPEN,
            ConfiguredFeatures.SPRING_WATER
    );

    @Unique
    private boolean isSpringFeature(ConfiguredFeature<?, ?> feature) {
        return springFeatures.contains(feature);
    }

    static {
        LOGGER.info("Removed SpringFeature!");
    }

    @Inject(method = "feature(Lnet/minecraft/world/gen/GenerationStep$Feature;Lnet/minecraft/world/gen/feature/ConfiguredFeature;)Lnet/minecraft/world/biome/GenerationSettings$Builder;", at = @At("HEAD"), cancellable = true)
    private void ht_materials$feature(GenerationStep.Feature featureStep, ConfiguredFeature<?, ?> feature, CallbackInfoReturnable<GenerationSettings.Builder> cir) {
        if (isSpringFeature(feature)) {
            cir.setReturnValue((GenerationSettings.Builder) (Object) this);
        }
    }

    @Inject(method = "feature(ILjava/util/function/Supplier;)Lnet/minecraft/world/biome/GenerationSettings$Builder;", at = @At("HEAD"), cancellable = true)
    private void ht_materials$feature1(int stepIndex, Supplier<ConfiguredFeature<?, ?>> featureSupplier, CallbackInfoReturnable<GenerationSettings.Builder> cir) {
        ConfiguredFeature<?, ?> feature = featureSupplier.get();
        if (isSpringFeature(feature)) {
            cir.setReturnValue((GenerationSettings.Builder) (Object) this);
        }
    }
}