package com.codeboundworlds.bblogistics.blocks;

import com.codeboundworlds.bblogistics.RegistryBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BrightbronzeTerminalEntity extends BlockEntity implements Container, MenuProvider {
    private final NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private int channel = 0; // 0-15 for the 16 colors

    public BrightbronzeTerminalEntity(BlockPos pos, BlockState state) {
        super(RegistryBlockEntities.BRIGHTBRONZE_TERMINAL_ENTITY.get(), pos, state);
    }

    // Channel management
    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = Math.max(0, Math.min(15, channel));
        setChanged();
    }

    public void cycleChannel() {
        setChannel((channel + 1) % 16);
    }

    // Container implementation
    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack stack = ContainerHelper.removeItem(items, slot, amount);
        if (!stack.isEmpty())
            setChanged();
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize())
            stack.setCount(getMaxStackSize());
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (level == null || level.getBlockEntity(worldPosition) != this)
            return false;
        return player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5) <= 64;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    public NonNullList<ItemStack> items() {
        return items;
    }

    // Serialization (1.21 uses saveAdditional/loadAdditional with HolderLookup)
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        ContainerHelper.saveAllItems(tag, items, provider);
        tag.putInt("Channel", channel);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        ContainerHelper.loadAllItems(tag, items, provider);
        this.channel = tag.getInt("Channel");
    }

    // MenuProvider
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.bblogistics.brightbronze_terminal");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        // Return our custom terminal menu
        return new com.codeboundworlds.bblogistics.menu.BrightbronzeTerminalMenu(id, inv, this);
    }
}
