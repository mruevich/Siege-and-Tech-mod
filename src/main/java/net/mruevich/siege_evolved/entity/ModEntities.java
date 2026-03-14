package net.mruevich.siege_evolved.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.entity.custom.InfTestEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SiegeEvolved.MODID);

    public static final RegistryObject<EntityType<InfTestEntity>> INF_TEST =
            ENTITY_TYPES.register("inf_test", () -> EntityType.Builder.of(InfTestEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("inf_test"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
