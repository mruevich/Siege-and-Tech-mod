package net.mruevich.siege_evolved.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mruevich.siege_evolved.SiegeEvolved;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, SiegeEvolved.MODID);

    public static final RegistryObject<Item> Bouncy = ITEMS.register("bouncy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OnyxPlushie = ITEMS.register("onyx_plushie",
            () -> new Item(new Item.Properties()));

    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
