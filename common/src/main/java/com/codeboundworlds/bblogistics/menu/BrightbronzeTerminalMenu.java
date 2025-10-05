package com.codeboundworlds.bblogistics.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeTerminalEntity;

public class BrightbronzeTerminalMenu extends AbstractContainerMenu {
    private final BrightbronzeTerminalEntity terminalEntity;

    public BrightbronzeTerminalMenu(int id, Inventory playerInventory, BrightbronzeTerminalEntity terminalEntity) {
        super(com.codeboundworlds.bblogistics.RegistryMenuTypes.BRIGHTBRONZE_TERMINAL_MENU.get(), id);
        this.terminalEntity = terminalEntity;
        
        // Add a few empty slots at the top for future network display
        // This prevents the IndexOutOfBoundsException when Minecraft tries to access slot 0
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(terminalEntity, i, 8 + i * 18, 18));
        }
        
        // Add player inventory slots
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        
        // Add player hotbar slots
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }
    
    // Client-side constructor for menu opening
    public BrightbronzeTerminalMenu(int id, Inventory playerInventory) {
        super(com.codeboundworlds.bblogistics.RegistryMenuTypes.BRIGHTBRONZE_TERMINAL_MENU.get(), id);
        this.terminalEntity = null; // Will be set by client
        
        // Add empty slots to match server-side constructor
        // This prevents slot count mismatch between client and server
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, 0, 8 + i * 18, 18) {
                @Override
                public boolean mayPlace(net.minecraft.world.item.ItemStack stack) {
                    return false; // Don't allow placing items in terminal slots on client
                }
            });
        }
        
        // Add player inventory slots
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        
        // Add player hotbar slots
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public BrightbronzeTerminalEntity getTerminalEntity() {
        return terminalEntity;
    }
    
    public void cycleChannel() {
        if (terminalEntity != null) {
            terminalEntity.cycleChannel();
            terminalEntity.setChanged();
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
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            
            if (index < 54) { // Network slots
                // Try to move to player inventory
                if (!this.moveItemStackTo(itemstack1, 54, 90, true)) {
                    return ItemStack.EMPTY;
                }
            } else { // Player slots
                // Try to move to network (not supported yet)
                return ItemStack.EMPTY;
            }
            
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return terminalEntity != null && 
               player.distanceToSqr(terminalEntity.getBlockPos().getX() + 0.5, 
                                   terminalEntity.getBlockPos().getY() + 0.5,
                                   terminalEntity.getBlockPos().getZ() + 0.5) <= 64;
    }
}