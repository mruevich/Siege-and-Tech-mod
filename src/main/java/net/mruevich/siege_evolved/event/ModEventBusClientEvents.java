package net.mruevich.siege_evolved.event;

import com.google.common.eventbus.Subscribe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.entity.client.ModModelLayers;
import net.mruevich.siege_evolved.entity.client.inf_test;

@Mod.EventBusSubscriber(modid = SiegeEvolved.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void RegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.INF_TEST_LAYER, inf_test::createBodyLayer);
    }

}
