package com.codeboundworlds.bblogistics.network;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeConnectorEntity;
import com.codeboundworlds.bblogistics.blocks.BrightbronzeConnector;

import java.util.ArrayList;
import java.util.List;

public class ChannelNetwork {
    
    public static List<Container> getConnectedInventories(Level level, int channel, BlockPos excludePos) {
        List<Container> inventories = new ArrayList<>();
        
        if (level == null) return inventories;
        
        // Search in a reasonable radius around the terminal (64 blocks)
        int searchRadius = 64;
        
        for (int x = excludePos.getX() - searchRadius; x <= excludePos.getX() + searchRadius; x++) {
            for (int y = excludePos.getY() - searchRadius; y <= excludePos.getY() + searchRadius; y++) {
                for (int z = excludePos.getZ() - searchRadius; z <= excludePos.getZ() + searchRadius; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    
                    // Skip if too far or the same position
                    if (pos.distSqr(excludePos) > searchRadius * searchRadius || pos.equals(excludePos)) {
                        continue;
                    }
                    
                    BlockEntity be = level.getBlockEntity(pos);
                    
                    // Check if this is a connector on the same channel
                    if (be instanceof BrightbronzeConnectorEntity connector) {
                        if (connector.getChannel() == channel) {
                            // Find the inventory this connector is attached to
                            BlockPos inventoryPos = pos.relative(
                                level.getBlockState(pos).getValue(BrightbronzeConnector.FACING).getOpposite()
                            );
                            BlockEntity inventoryBE = level.getBlockEntity(inventoryPos);
                            if (inventoryBE instanceof Container container) {
                                inventories.add(container);
                            }
                        }
                    }
                }
            }
        }
        
        return inventories;
    }
    
    public static List<ItemStack> getAllItems(List<Container> inventories) {
        List<ItemStack> allItems = new ArrayList<>();
        
        for (Container container : inventories) {
            for (int i = 0; i < container.getContainerSize(); i++) {
                ItemStack stack = container.getItem(i);
                if (!stack.isEmpty()) {
                    allItems.add(stack.copy());
                }
            }
        }
        
        return allItems;
    }
    
    public static boolean tryExtractItem(List<Container> inventories, ItemStack targetStack, int amount) {
        int remaining = amount;
        
        for (Container container : inventories) {
            for (int i = 0; i < container.getContainerSize() && remaining > 0; i++) {
                ItemStack stack = container.getItem(i);
                if (ItemStack.isSameItemSameComponents(stack, targetStack)) {
                    int toExtract = Math.min(remaining, stack.getCount());
                    container.removeItem(i, toExtract);
                    remaining -= toExtract;
                }
            }
        }
        
        return remaining == 0;
    }
}