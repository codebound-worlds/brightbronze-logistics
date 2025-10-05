package com.codeboundworlds.bblogistics.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeConnectorEntity;

public class BrightbronzeConnectorMenu extends AbstractContainerMenu {
    private final BrightbronzeConnectorEntity connectorEntity;

    public BrightbronzeConnectorMenu(int id, Inventory playerInventory, BrightbronzeConnectorEntity connectorEntity) {
        super(com.codeboundworlds.bblogistics.RegistryMenuTypes.BRIGHTBRONZE_CONNECTOR_MENU.get(), id);
        this.connectorEntity = connectorEntity;
    }
    
    // Client-side constructor for menu opening
    public BrightbronzeConnectorMenu(int id, Inventory playerInventory) {
        super(com.codeboundworlds.bblogistics.RegistryMenuTypes.BRIGHTBRONZE_CONNECTOR_MENU.get(), id);
        this.connectorEntity = null; // Will be set by client
    }

    public BrightbronzeConnectorEntity getConnectorEntity() {
        return connectorEntity;
    }
    
    public void cycleChannel() {
        if (connectorEntity != null) {
            connectorEntity.cycleChannel();
            connectorEntity.setChanged();
        }
    }
    
    @Override
    public void clicked(int slotId, int button, net.minecraft.world.inventory.ClickType clickType, Player player) {
        // Handle special click for channel cycling (using an unused slot ID like -999)
        if (slotId == -999 && clickType == net.minecraft.world.inventory.ClickType.PICKUP) {
            cycleChannel();
            return;
        }
        super.clicked(slotId, button, clickType, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return connectorEntity != null && 
               player.distanceToSqr(connectorEntity.getBlockPos().getX() + 0.5, 
                                   connectorEntity.getBlockPos().getY() + 0.5,
                                   connectorEntity.getBlockPos().getZ() + 0.5) <= 64;
    }
}