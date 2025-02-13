package types.and.variants.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import types.and.variants.GetTypeAndVariants;
import types.and.variants.program.Common;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity implements GetTypeAndVariants
{
    @Unique
    private Common type = Common.invalid;
    @Unique
    private Common variant = Common.invalid;

    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level)
    {
        super(entityType, level);
    }

    @Inject(method="tick",at=@At("HEAD"))
    public void additionalTick(CallbackInfo callbackInfo)
    {
        if(this.type == Common.invalid)
        {
            this.type = TypeUtil.instance.getCommon(super.random, this);
            this.type.init(this);
        }
        if(this.variant == Common.invalid)
        {
            this.variant = VariantUtil.instance.getCommon(super.random, this);
            this.variant.init(this);
        }
        this.type.tick(this);
        this.variant.tick(this);
    }
    @Inject(method="addAdditionalSaveData",at=@At("RETURN"))
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo callbackInfo)
    {
        compoundTag.putInt("type", this.type.id());
        compoundTag.putInt("variant", this.variant.id());
    }
    @Inject(method="readAdditionalSaveData",at=@At("RETURN"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo callbackInfo)
    {
        this.type = TypeUtil.instance.getCommon(compoundTag.getInt("type"));
        this.variant = VariantUtil.instance.getCommon(compoundTag.getInt("variant"));
    }
    @Override
    public Common type()
    {
        return this.type;
    }
    @Override
    public Common variant()
    {
        return this.variant;
    }
}
