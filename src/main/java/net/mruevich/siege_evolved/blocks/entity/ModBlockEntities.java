package net.mruevich.siege_evolved.blocks.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mruevich.siege_evolved.SiegeEvolved;
import net.mruevich.siege_evolved.blocks.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SiegeEvolved.MODID);

    public static final RegistryObject<BlockEntityType<KettleBlockEntity>> KETTLE_BE =
            BLOCK_ENTITIES.register("kettle_be", () ->
                    BlockEntityType.Builder.of(KettleBlockEntity::new,
                            ModBlocks.Kettle.get()).build(null));

    public static void register (IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
