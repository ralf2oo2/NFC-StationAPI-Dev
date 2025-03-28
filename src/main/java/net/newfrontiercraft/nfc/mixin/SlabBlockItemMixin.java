package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlabBlockItem.class)
public abstract class SlabBlockItemMixin extends BlockItem {
    boolean isFullBlock = false;
    public SlabBlockItemMixin(int i) {
        super(i);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        int blockX = x;
        int blockY = y;
        int blockZ = z;

        System.out.println(side);
        if(world.getBlockId(x, y, z) == Block.SLAB.id && side == 0){
            world.setBlock(x, y, z, Block.DOUBLE_SLAB.id);
            return true;
        }
        else {
            switch (side){
                case 0:
                    blockY--;
                case 1:
                    blockY++;
                case 2:
                    blockZ--;
                case 3:
                    blockZ++;
                case 4:
                    blockX--;
                case 5:
                    blockX++;
            }
            System.out.println("====");
            System.out.println(blockX);
            System.out.println(blockY);
            System.out.println(blockZ);
            if(world.getBlockId(blockX, blockY, blockZ) == Block.SLAB.id){
                world.setBlock(blockX, blockY, blockZ, Block.DOUBLE_SLAB.id);
                return true;
            }
            if(world.isAir(blockX, blockY, blockZ)){
                world.setBlock(blockX, blockY, blockZ, Block.SLAB.id);
                return true;
            }
        }
        return false;
    }
}
