package com.codeboundworlds.bblogistics.client;

import com.codeboundworlds.bblogistics.menu.BrightbronzeTerminalMenu;
import com.codeboundworlds.bblogistics.util.ChannelUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BrightbronzeTerminalScreen extends AbstractContainerScreen<BrightbronzeTerminalMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/dispenser.png");
    
    private Button channelButton;
    
    public BrightbronzeTerminalScreen(BrightbronzeTerminalMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 184; // Height for terminal slots + player inventory + hotbar
        this.imageWidth = 176;
    }
    
    @Override
    protected void init() {
        super.init();
        
        // Single channel cycling button - positioned at the top
        this.channelButton = Button.builder(getCurrentChannelName(), button -> {
            // Trigger a special menu click to cycle channel on server
            if (this.minecraft != null && this.minecraft.gameMode != null) {
                this.minecraft.gameMode.handleInventoryMouseClick(this.menu.containerId, -999, 0, net.minecraft.world.inventory.ClickType.PICKUP, this.minecraft.player);
            }
            updateChannelButton();
        }).bounds(this.leftPos + 8, this.topPos + 6, 160, 20).build();
        
        this.addRenderableWidget(this.channelButton);
        updateChannelButton();
    }
    
    private Component getCurrentChannelName() {
        if (this.menu.getTerminalEntity() != null) {
            return ChannelUtils.getChannelName(this.menu.getTerminalEntity().getChannel());
        }
        return Component.literal("White");
    }
    
    private void updateChannelButton() {
        if (this.channelButton != null) {
            this.channelButton.setMessage(getCurrentChannelName());
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
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
    
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        
        // Channel label
        if (this.menu.getTerminalEntity() != null) {
            Component currentChannel = Component.literal("Channel: ").append(ChannelUtils.getChannelName(this.menu.getTerminalEntity().getChannel()));
            guiGraphics.drawString(this.font, currentChannel, 8, this.imageHeight - 94, 4210752, false);
        }
    }
}