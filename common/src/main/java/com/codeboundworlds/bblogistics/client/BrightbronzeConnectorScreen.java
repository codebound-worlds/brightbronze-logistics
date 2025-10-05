package com.codeboundworlds.bblogistics.client;

import com.codeboundworlds.bblogistics.menu.BrightbronzeConnectorMenu;
import com.codeboundworlds.bblogistics.util.ChannelUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BrightbronzeConnectorScreen extends AbstractContainerScreen<BrightbronzeConnectorMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/hopper.png");
    
    private EditBox nameField;
    private Button channelButton;
    
    public BrightbronzeConnectorScreen(BrightbronzeConnectorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 133;
        this.imageWidth = 176;
    }
    
    @Override
    protected void init() {
        super.init();
        
        // Name input field
        this.nameField = new EditBox(this.font, this.leftPos + 8, this.topPos + 20, 160, 20, Component.literal("Name"));
        this.nameField.setMaxLength(50);
        if (this.menu.getConnectorEntity() != null) {
            this.nameField.setValue(this.menu.getConnectorEntity().getConnectorName());
        }
        this.addWidget(this.nameField);
        
        // Single channel cycling button
        this.channelButton = Button.builder(getCurrentChannelName(), button -> {
            // Trigger a special menu click to cycle channel on server
            if (this.minecraft != null && this.minecraft.gameMode != null) {
                this.minecraft.gameMode.handleInventoryMouseClick(this.menu.containerId, -999, 0, net.minecraft.world.inventory.ClickType.PICKUP, this.minecraft.player);
            }
            updateChannelButton();
        }).bounds(this.leftPos + 8, this.topPos + 50, 160, 20).build();
        
        this.addRenderableWidget(this.channelButton);
        updateChannelButton();
    }
    
    private Component getCurrentChannelName() {
        if (this.menu.getConnectorEntity() != null) {
            return ChannelUtils.getChannelName(this.menu.getConnectorEntity().getChannel());
        }
        return Component.literal("White");
    }
    
    private void updateChannelButton() {
        if (this.channelButton != null) {
            this.channelButton.setMessage(getCurrentChannelName());
        }
    }
    
    @Override
    public void onClose() {
        super.onClose();
        // Save the name when closing
        if (this.menu.getConnectorEntity() != null && this.nameField != null) {
            this.menu.getConnectorEntity().setConnectorName(this.nameField.getValue());
            this.menu.getConnectorEntity().setChanged(); // Mark as changed to save to NBT
        }
    }
    
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;
        guiGraphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        
        // Render the name field
        if (this.nameField != null) {
            this.nameField.render(guiGraphics, mouseX, mouseY, partialTick);
        }
        
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
    
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        
        // Channel label
        if (this.menu.getConnectorEntity() != null) {
            Component currentChannel = Component.literal("Channel: ").append(ChannelUtils.getChannelName(this.menu.getConnectorEntity().getChannel()));
            guiGraphics.drawString(this.font, currentChannel, 8, 110, 4210752, false);
        }
    }
}