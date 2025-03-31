package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlabBlock.class)
public abstract class SlabBlockMixin extends Block {

    @Shadow private boolean doubleSlab;

    public SlabBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        if(doubleSlab){
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else {
            if(world.getBlockMeta(x, y, z) / 8 == 0) {
                this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            } else {
                this.setBoundingBox(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }

        return super.getCollisionShape(world, x, y, z);
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        if(doubleSlab){
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else {
            if(blockView.getBlockMeta(x, y, z) / 8 == 0) {
                this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            } else {
                this.setBoundingBox(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
