package net.mruevich.siege_evolved.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mruevich.siege_evolved.SiegeEvolved;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SiegeEvolved.MODID);

    public static final RegistryObject<SoundEvent> SOUND_INF_AMBIENT = RegisterSoundEvents("sound_inf_ambient");
    public static final RegistryObject<SoundEvent> SOUND_INF_HURT = RegisterSoundEvents("sound_inf_hurt");
    public static final RegistryObject<SoundEvent> SOUND_INF_DEATH = RegisterSoundEvents("sound_inf_death");

    public static final RegistryObject<SoundEvent> SOUND_INF_EXTRA1 = RegisterSoundEvents("sound_inf_extra1");
    public static final RegistryObject<SoundEvent> SOUND_INF_EXTRA2 = RegisterSoundEvents("sound_inf_extra2");

    private static RegistryObject<SoundEvent> RegisterSoundEvents(String name){
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SiegeEvolved.MODID, name)));
    }
    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
