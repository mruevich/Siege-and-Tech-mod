package net.mruevich.siege_evolved.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.mruevich.siege_evolved.items.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.basic.BasicComboBoxUI;

public class KettleBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 50;

    public KettleBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.KETTLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> KettleBlockEntity.this.progress;
                    case 1 -> KettleBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> KettleBlockEntity.this.progress = pValue;
                    case 1 -> KettleBlockEntity.this.maxProgress = pValue;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public boolean PlaceItem(Entity entity, ItemStack itemStack){
        if (!(entity instanceof Player)) { return false; }
        Player ply = (Player) entity;
        if (itemStack.getItem() != Items.AIR) {
            ItemStack stackToPut = itemStack.split(1);
            stackToPut.grow(this.itemHandler.getStackInSlot(INPUT_SLOT).getCount());
            this.itemHandler.setStackInSlot(INPUT_SLOT, stackToPut);
        } else if ( itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == Items.AIR) {
            ply.getInventory().add(itemHandler.getStackInSlot(INPUT_SLOT).split(1));
        } else if (itemStack.getItem() != Items.BROWN_MUSHROOM) {
            ply.getInventory().add(itemHandler.getStackInSlot(OUTPUT_SLOT).split(1));
        }

        else {
            return false;
        }
        return true;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i <= itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("kettle.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("kettle.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (hasRecipe()){
            level.playSound((Player) null, pPos, SoundEvents.ANVIL_STEP, SoundSource.BLOCKS);
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }
        }
        else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (output.getCount() == output.getMaxStackSize()){
            //return false;
        }
        boolean hasCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == Items.BROWN_MUSHROOM;
        return hasCraftingItem;
    }

    private void craftItem() {
        itemHandler.setStackInSlot(INPUT_SLOT, new ItemStack(Items.BROWN_MUSHROOM, itemHandler.getStackInSlot(INPUT_SLOT).getCount()-1));
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(ModItems.MushroomTea.get(), itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+4));
    }



}
