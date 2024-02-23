package io.github.hiiragi283.mixin;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(GenerationSettings.Builder.class)
public abstract class GenerationSettingsBuilderMixin {

    @Unique
    private static final Logger LOGGER = LogManager.getLogger("LavaSweeper");
    @Unique
    private static final List<RegistryEntry<ConfiguredFeature<SpringFeatureConfig, ?>>> springFeatures = Arrays.asList(
            MiscConfiguredFeatures.SPRING_LAVA_FROZEN,
            MiscConfiguredFeatures.SPRING_LAVA_OVERWORLD,
            MiscConfiguredFeatures.SPRING_WATER,
            NetherConfiguredFeatures.SPRING_LAVA_NETHER,
            NetherConfiguredFeatures.SPRING_NETHER_CLOSED,
            NetherConfiguredFeatures.SPRING_NETHER_OPEN
    );

    @Unique
    private boolean isSpringFeature(RegistryEntry<PlacedFeature> featureEntry) {
        return springFeatures.contains(featureEntry.value().feature());
    }

    static {
        LOGGER.info("Removed SpringFeature!");
    }

    @Inject(method = "feature(Lnet/minecraft/world/gen/GenerationStep$Feature;Lnet/minecraft/util/registry/RegistryEntry;)Lnet/minecraft/world/biome/GenerationSettings$Builder;", at = @At("HEAD"), cancellable = true)
    private void ht_materials$feature(GenerationStep.Feature featureStep, RegistryEntry<PlacedFeature> feature, CallbackInfoReturnable<GenerationSettings.Builder> cir) {
        if (isSpringFeature(feature)) {
            cir.setReturnValue((GenerationSettings.Builder) (Object) this);
        }
    }

    @Inject(method = "feature(ILnet/minecraft/util/registry/RegistryEntry;)Lnet/minecraft/world/biome/GenerationSettings$Builder;", at = @At("HEAD"), cancellable = true)
    private void ht_materials$feature1(int stepIndex, RegistryEntry<PlacedFeature> featureEntry, CallbackInfoReturnable<GenerationSettings.Builder> cir) {
        if (isSpringFeature(featureEntry)) {
            cir.setReturnValue((GenerationSettings.Builder) (Object) this);
        }
    }
}