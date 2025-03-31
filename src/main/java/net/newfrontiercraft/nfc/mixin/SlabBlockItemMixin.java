package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.util.hit.HitResult;
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

        HitResult hitResult = user.raycast(5, 0);
        double hitOffsetX = hitResult.pos.x - (float)hitResult.blockX;
        double hitOffsetY = hitResult.pos.y - (float)hitResult.blockY;
        double hitOffsetZ = hitResult.pos.z - (float)hitResult.blockZ;
        System.out.println(hitResult.pos.x - (float)hitResult.blockX);
        System.out.println(hitResult.pos.y - (float)hitResult.blockY);
        System.out.println(hitResult.pos.z - (float)hitResult.blockZ);

        System.out.println(hitResult.pos.x + " - " + hitResult.blockX);

        System.out.println(side);
        if(world.getBlockId(x, y, z) == Block.SLAB.id && (side == 0 || side == 1)){
            world.setBlock(x, y, z, Block.STONE.id);
            return true;
        }
        else {
            switch (side){
                case 0:
                    blockY--;
                    break;
                case 1:
                    blockY++;
                    break;
                case 2:
                    blockZ--;
                    break;
                case 3:
                    blockZ++;
                    break;
                case 4:
                    blockX--;
                    break;
                case 5:
                    blockX++;
                    break;
            }
            if(world.getBlockId(blockX, blockY, blockZ) == Block.SLAB.id){
                int meta = world.getBlockMeta(blockX, blockY, blockZ);
                if((meta & 8) == 0 && side == 1){
                    world.setBlock(blockX, blockY, blockZ, Block.DOUBLE_SLAB.id, 0);
                }
                else if(side == 0){
                    world.setBlock(blockX, blockY, blockZ, Block.DOUBLE_SLAB.id, 0);
                }
                return true;
            }
            if(world.isAir(blockX, blockY, blockZ)){
                if(hitOffsetY < 0.5f || side == 1){
                    world.setBlock(blockX, blockY, blockZ, Block.SLAB.id, 0);
                }
                else {
                    world.setBlock(blockX, blockY, blockZ, Block.SLAB.id, 8);
                }
                return true;
            }
        }
        return false;
    }
}
