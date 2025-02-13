package types.and.variants.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import types.and.variants.GetTypeAndVariants;
import types.and.variants.Storage;
import types.and.variants.program.Common;

import javax.annotation.Nullable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity implements GetTypeAndVariants
{
    @Shadow @Nullable public abstract <T extends Mob> T convertTo(EntityType<T> entityType, boolean transferInventory);

    @Unique
    public final Storage storage = new Storage(this);

    @Inject(method="tick",at=@At("HEAD"))
    public void additionalTick(CallbackInfo callbackInfo)
    {
        this.storage.tick();
    }
    @Inject(method="addAdditionalSaveData",at=@At("RETURN"))
    public void additionalAddAdditionalSaveData(CompoundTag compoundTag, CallbackInfo callbackInfo)
    {
        this.storage.addAdditionalSaveData(compoundTag);
    }
    @Inject(method="readAdditionalSaveData", at=@At("RETURN"))
    public void additionalReadAdditionalSaveData(CompoundTag compoundTag, CallbackInfo callbackInfo)
    {
        this.storage.readAdditionalSaveData(compoundTag);
    }
    @Override
    public Common type()
    {
        return this.storage.type();
    }
    @Override
    public Common variant()
    {
        return this.storage.variant();
    }
    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level)
    {
        super(entityType, level);
    }
}
