package net.ochibo.util;

import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BlockGetterUtil {
    public static BlockPos getNearestBlockBelow(ServerWorld world, BlockPos startPos){
        for (int i = 1; i < 300; i++) {
            BlockPos pos = startPos.down(i);
            if (!world.getBlockState(pos).isAir()){
                return pos;
            }
        }
        return null;
    }
}
