package hiiragi283.sweeper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class LavaSweeper {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //イベントを登録する
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void removeLavaTrap(PopulateChunkEvent.Populate event) {
        //ネザーの1マス溶岩を消す
        if (event.getType() == PopulateChunkEvent.Populate.EventType.NETHER_LAVA || event.getType() == PopulateChunkEvent.Populate.EventType.NETHER_LAVA2) {
            event.setResult(Event.Result.DENY);
        }
    }
}
