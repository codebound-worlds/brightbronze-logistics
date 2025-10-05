package com.codeboundworlds.bblogistics.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;

/**
 * A thin face-attached connector block that can be placed on any side of a supporting block.
 */
public class BrightbronzeConnector extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING; // all 6 directions

    // Centered 4x4 (6..10) 1px thick shapes per face.
    private static final VoxelShape NORTH = Block.box(6, 6, 15, 10, 10, 16);
    private static final VoxelShape SOUTH = Block.box(6, 6, 0, 10, 10, 1);
    private static final VoxelShape WEST = Block.box(15, 6, 6, 16, 10, 10);
    private static final VoxelShape EAST = Block.box(0, 6, 6, 1, 10, 10);
    private static final VoxelShape DOWN = Block.box(6, 15, 6, 10, 16, 10);
    private static final VoxelShape UP = Block.box(6, 0, 6, 10, 1, 10);

    public BrightbronzeConnector() {
        super(Block.Properties.of().strength(0.5f).requiresCorrectToolForDrops().noOcclusion());
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction face = ctx.getClickedFace();
        BlockPos placePos = ctx.getClickedPos();
        BlockPos supportPos = placePos.relative(face.getOpposite());
        BlockEntity be = ctx.getLevel().getBlockEntity(supportPos);
        if (be instanceof Container) {
            return defaultBlockState().setValue(FACING, face);
        }
        return null; // cancel placement if no inventory behind
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction attachDir = state.getValue(FACING);
        BlockPos supportPos = pos.relative(attachDir.getOpposite());
        BlockEntity be = level.getBlockEntity(supportPos);
        return be instanceof Container;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level,
            BlockPos pos, BlockPos neighborPos) {
        if (direction == state.getValue(FACING).getOpposite() && !canSurvive(state, (LevelReader) level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos,
            CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
            case UP -> UP;
            case DOWN -> DOWN;
        };
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
