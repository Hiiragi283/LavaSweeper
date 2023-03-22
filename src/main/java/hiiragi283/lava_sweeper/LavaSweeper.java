package hiiragi283.lava_sweeper;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.SpringFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod("lava_sweeper")
public class LavaSweeper {

    private static final Logger LOGGER = LogManager.getLogger();

    public LavaSweeper() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class LavaRemovingEvent {

        @SubscribeEvent(priority = EventPriority.NORMAL)
        public static void onBiomeLoading(BiomeLoadingEvent event) {
            //BiomeのBuilderを取得
            BiomeGenerationSettingsBuilder generation = event.getGeneration();
            //Biomeのカテゴリがネザー系の場合
            if (event.getCategory() == Biome.Category.NETHER) {
                //ConfiguredFeaturesの一覧から条件に合致するものを削除
                generation.getFeatures(GenerationStage.Decoration.UNDERGROUND_DECORATION).removeIf((supplier) -> {
                    //ConfiguredFeaturesを取得
                    ConfiguredFeature<?, ?> configuredFeature = supplier.get();
                    //ConfiguredFeaturesの一覧に対して実行
                    for (ConfiguredFeature<?, ?> feature : configuredFeature.getConfiguredFeatures().collect(Collectors.toList())) {
                        //SpringFeatureクラスの場合
                        if (feature.feature instanceof SpringFeature) {
                            LOGGER.info("The feature" + feature + "was removed!");
                            return true;
                        }
                    }
                    return false;
                });
            }
        }
    }
}