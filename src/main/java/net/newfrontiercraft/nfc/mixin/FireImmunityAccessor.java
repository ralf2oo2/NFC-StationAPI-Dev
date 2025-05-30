package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface FireImmunityAccessor {

    @Accessor("fireImmune")
    boolean getFireImmune();
}
