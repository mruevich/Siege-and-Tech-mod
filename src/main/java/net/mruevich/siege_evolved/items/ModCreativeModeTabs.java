package net.mruevich.siege_evolved.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.blocks.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SiegeEvolved.MODID);

    public static final RegistryObject<CreativeModeTab> GENERAL_TAB = CREATIVE_MODE_TABS.register("general_tab",
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Bouncy.get()))
            .title(Component.translatable("creativetab.general_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.Bouncy.get());
                pOutput.accept(ModBlocks.OnyxPlushie.get());
            })
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
