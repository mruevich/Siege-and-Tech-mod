package net.mruevich.siege_evolved.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.entity.ModEntities;
import net.mruevich.siege_evolved.entity.custom.InfTestEntity;

@Mod.EventBusSubscriber(modid = SiegeEvolved.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.INF_TEST.get(), InfTestEntity.createAttributes().build());
    }
}
