package com.codeboundworlds.bblogistics.blocks;

import com.codeboundworlds.bblogistics.RegistryBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BrightbronzeConnectorEntity extends BlockEntity implements MenuProvider {
    private int channel = 0; // 0-15 for the 16 colors
    private String connectorName = "";

    public BrightbronzeConnectorEntity(BlockPos pos, BlockState state) {
        super(RegistryBlockEntities.BRIGHTBRONZE_CONNECTOR_ENTITY.get(), pos, state);
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

    // Name management
    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String name) {
        this.connectorName = name == null ? "" : name;
        setChanged();
    }

    // Serialization (1.21 uses saveAdditional/loadAdditional with HolderLookup)
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putInt("Channel", channel);
        tag.putString("ConnectorName", connectorName);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.channel = tag.getInt("Channel");
        this.connectorName = tag.getString("ConnectorName");
    }

    // MenuProvider
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.bblogistics.brightbronze_connector");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        // Return our custom connector menu
        return new com.codeboundworlds.bblogistics.menu.BrightbronzeConnectorMenu(id, inv, this);
    }
}