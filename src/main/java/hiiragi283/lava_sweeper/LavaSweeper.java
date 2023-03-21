package hiiragi283.lava_sweeper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "lava_sweeper", name = "LavaSweeper", version = "v0.1.0")
public class LavaSweeper {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //イベントを登録する
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @SubscribeEvent
    public void removeLavaTrap(PopulateChunkEvent.Populate event) {
        //ネザーの1マス溶岩を消す
        if (event.getType() == PopulateChunkEvent.Populate.EventType.NETHER_LAVA || event.getType() == PopulateChunkEvent.Populate.EventType.NETHER_LAVA2) {
            event.setResult(Event.Result.DENY);
        }
    }
}