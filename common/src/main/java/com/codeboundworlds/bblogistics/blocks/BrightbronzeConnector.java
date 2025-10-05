package com.codeboundworlds.bblogistics.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.Container;
import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

/**
 * A thin face-attached connector block that can be placed on any side of a supporting block.
 */
public class BrightbronzeConnector extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING; // all 6 directions

    // Centered 4x4 (6..10) 1px thick shapes per face.
    private static final VoxelShape NORTH = Block.box(6, 6, 15, 10, 10, 16);
    private static final VoxelShape SOUTH = Block.box(6, 6, 0, 10, 10, 1);
    private static final VoxelShape WEST = Block.box(15, 6, 6, 16, 10, 10);
    private static final VoxelShape EAST = Block.box(0, 6, 6, 1, 10, 10);
    private static final VoxelShape DOWN = Block.box(6, 15, 6, 10, 16, 10);
    private static final VoxelShape UP = Block.box(6, 0, 6, 10, 1, 10);

    // Codec required by BaseEntityBlock in 1.21
    public static final MapCodec<BrightbronzeConnector> CODEC = MapCodec.unit(BrightbronzeConnector::new);

    public BrightbronzeConnector() {
        super(Block.Properties.of().strength(0.5f).requiresCorrectToolForDrops().noOcclusion());
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
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
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        if (level.isClientSide)
            return InteractionResult.SUCCESS;
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof BrightbronzeConnectorEntity connector) {
            player.openMenu(connector);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    // Fallback hooks for different mappings / loader dispatch patterns
    @SuppressWarnings("unused")
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hit) {
        return use(state, level, pos, player, InteractionHand.MAIN_HAND, hit);
    }

    @SuppressWarnings("unused")
    public InteractionResult onUse(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        return use(state, level, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrightbronzeConnectorEntity(pos, state);
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
