package net.ochibo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import net.ochibo.MaximumGohan;
import net.ochibo.block.entity.ModBlockEntities;
import net.ochibo.block.entity.custom.CampfireModifierRackEntity;
import net.ochibo.block.entity.custom.CampfireModifierSmokerRackEntity;
import net.ochibo.recipe.CampfireSmokingRecipe;
import net.ochibo.recipe.ModRecipes;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CampfireModifierSmokerRack extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE =
            CampfireModifierSmokerRack.createCuboidShape(1,2,1,15,4,15);
    public static final EnumProperty<Direction> FACING;

    public static final MapCodec<CampfireModifierSmokerRack> CODEC = CampfireModifierSmokerRack.createCodec(CampfireModifierSmokerRack::new);

    public CampfireModifierSmokerRack(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CampfireModifierRackEntity(pos,state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                         PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof CampfireModifierRackEntity campfireModifierRackEntity) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (true) {
                if (world instanceof ServerWorld serverWorld) {
                    if (campfireModifierRackEntity.addItem(serverWorld, player, itemStack)) {
                        player.incrementStat(Stats.INTERACT_WITH_CAMPFIRE);
                        return ActionResult.SUCCESS_SERVER;
                    }
                }

                return ActionResult.CONSUME;
            }
        }
        return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
    }

    private boolean hasRecipe(World world, ItemStack stack){
        Optional<RecipeEntry<CampfireSmokingRecipe>> recipe = ((ServerWorld) world).getRecipeManager().getFirstMatch(ModRecipes.CAMPFIRE_SMOKING_TYPE,new SingleStackRecipeInput(stack), world);
        MaximumGohan.LOGGER.info("{}",recipe.isPresent());
        return true;
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(Blocks.CAMPFIRE);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING,ctx.getWorld().getBlockState(ctx.getBlockPos().down()).isOf(Blocks.CAMPFIRE) ? ctx.getWorld().getBlockState(ctx.getBlockPos().down()).get(FACING) : Direction.NORTH);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (!world.getBlockState(pos.down()).isOf(Blocks.CAMPFIRE)){
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public static void spawnSmokeParticle(World world, BlockPos pos, boolean isSignal, boolean lotsOfSmoke) {
        Random random = world.getRandom();
        SimpleParticleType simpleParticleType = isSignal ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        world.addImportantParticleClient(simpleParticleType, true, (double)pos.getX() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), (double)0.0F, 0.07, (double)0.0F);
        if (lotsOfSmoke) {
            world.addParticleClient(ParticleTypes.SMOKE, (double)pos.getX() + (double)0.5F + random.nextDouble() / (double)4.0F * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4, (double)pos.getZ() + (double)0.5F + random.nextDouble() / (double)4.0F * (double)(random.nextBoolean() ? 1 : -1), (double)0.0F, 0.005, (double)0.0F);
        }

    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world instanceof ServerWorld serverWorld) {
                ServerRecipeManager.MatchGetter<SingleStackRecipeInput, CampfireSmokingRecipe> matchGetter = ServerRecipeManager.createCachedMatchGetter(ModRecipes.CAMPFIRE_SMOKING_TYPE);
                return validateTicker(type, ModBlockEntities.SMOKER_RACK_BE, (world1, pos, state1, blockEntity) -> CampfireModifierSmokerRackEntity.serverTick(serverWorld, pos, state1, blockEntity, matchGetter));
        } else {
            return validateTicker(type, ModBlockEntities.SMOKER_RACK_BE, CampfireModifierSmokerRackEntity::clientTick);
        }
    }
    static{
        FACING = Properties.HORIZONTAL_FACING;
    }
}
