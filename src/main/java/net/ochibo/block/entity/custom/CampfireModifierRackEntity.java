package net.ochibo.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.ochibo.MaximumGohan;
import net.ochibo.block.entity.ImplementedInventory;
import net.ochibo.block.entity.ModBlockEntities;
import net.ochibo.recipe.CampfireSmokingRecipe;
import net.ochibo.recipe.ModRecipes;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public class CampfireModifierRackEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);
    private int[] cookingTimes = new int[4];
    private int[] requiredCookingTimes = new int[4];
    private float rotation = 0;

    public CampfireModifierRackEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RACK_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view, inventory);
        view.putIntArray("CookingTimes", this.cookingTimes);
        view.putIntArray("RequiredCookingTimes", this.requiredCookingTimes);
    }

    @Override
    protected void readData(ReadView view) {
        this.getItems().clear();
        super.readData(view);
        Inventories.readData(view, inventory);
        view.getOptionalIntArray("CookingTimes").ifPresentOrElse((is) -> System.arraycopy(is, 0, this.cookingTimes, 0, Math.min(this.requiredCookingTimes.length, is.length)), () -> Arrays.fill(this.cookingTimes, 0));
        view.getOptionalIntArray("RequiredCookingTimes").ifPresentOrElse((is) -> System.arraycopy(is, 0, this.requiredCookingTimes, 0, Math.min(this.requiredCookingTimes.length, is.length)), () -> Arrays.fill(this.requiredCookingTimes, 0));

    }

    public int getEmptySlot() {
        for (int i = 0; i < this.inventory.size(); i++) {
            if (this.getStack(i) == ItemStack.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }


    public static void serverTick(ServerWorld world, BlockPos pos, BlockState state, CampfireModifierRackEntity blockEntity, ServerRecipeManager.MatchGetter<SingleStackRecipeInput, CampfireCookingRecipe> recipeMatchGetter){
        boolean bl = false;

        for(int i = 0; i < blockEntity.inventory.size(); ++i) {
            ItemStack itemStack = blockEntity.inventory.get(i);
            if (!itemStack.isEmpty()) {
                bl = true;
                blockEntity.cookingTimes[i]++;
                if (blockEntity.cookingTimes[i] >= blockEntity.requiredCookingTimes[i]) {
                    SingleStackRecipeInput singleStackRecipeInput = new SingleStackRecipeInput(itemStack);
                    ItemStack itemStack2 = recipeMatchGetter.getFirstMatch(singleStackRecipeInput, world).map((recipe) -> recipe.value().craft(singleStackRecipeInput, world.getRegistryManager())).orElse(itemStack);
                    if (itemStack2.isItemEnabled(world.getEnabledFeatures())) {
                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), itemStack2);
                        blockEntity.inventory.set(i, ItemStack.EMPTY);
                        world.updateListeners(pos, state, state, 3);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
                    }
                }
            }
        }

        if (bl) {
            markDirty(world, pos, state);
        }
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, CampfireModifierRackEntity rack) {
    }

    public boolean addItem(ServerWorld world, @Nullable LivingEntity entity, ItemStack stack) {
        for(int i = 0; i < this.inventory.size(); ++i) {
            ItemStack itemStack = this.inventory.get(i);
            if (itemStack.isEmpty()) {
                Optional<RecipeEntry<CampfireSmokingRecipe>> optional = world.getRecipeManager().getFirstMatch(ModRecipes.CAMPFIRE_SMOKING_TYPE, new SingleStackRecipeInput(stack), world);
                if (optional.isEmpty()) {
                    return false;
                }

                this.requiredCookingTimes[i] = ((CampfireCookingRecipe)((RecipeEntry)optional.get()).value()).getCookingTime()*3/4;
                this.cookingTimes[i] = 0;
                this.inventory.set(i, stack.splitUnlessCreative(1, entity));
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(entity, this.getCachedState()));
                updateListeners();
                return true;
            }
        }

        return false;
    }

    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if(this.world != null) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }
}
