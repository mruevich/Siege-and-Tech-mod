package net.mruevich.siege_evolved.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.items.ModFoods;
import net.mruevich.siege_evolved.items.custom.TeaItem;
import net.mruevich.siege_evolved.items.custom.TempeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, SiegeEvolved.MODID);

    public static final RegistryObject<Item> Bouncy = ITEMS.register("bouncy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MushroomTea = ITEMS.register("tea_mushroom",
            () -> new TeaItem(new Item.Properties().food(ModFoods.TEA_MUSHROOM)));

    public static final RegistryObject<Item> Tempe = ITEMS.register("tempe",
            () -> new TempeItem(new Item.Properties().food(ModFoods.TEMPE)));


    public static void register (IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
