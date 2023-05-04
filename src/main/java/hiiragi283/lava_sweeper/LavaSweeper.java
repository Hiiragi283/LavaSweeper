package hiiragi283.lava_sweeper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "lava_sweeper", name = "LavaSweeper", version = "v1.0.0-1.7.10", acceptedMinecraftVersions = "[1.7.10]")
public class LavaSweeper {

    public static Logger LOG = LogManager.getLogger("lava_sweeper");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //イベントを登録する
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
        //ログを出力する
        LOG.info("LavaSweeper has been activated!");
    }

    @SubscribeEvent
    public void removeLavaTrap(PopulateChunkEvent.Populate event) {
        //ネザーの1マス溶岩を消す
        if (event.type == PopulateChunkEvent.Populate.EventType.NETHER_LAVA) {
            event.setResult(Event.Result.DENY);
        }
    }
}
