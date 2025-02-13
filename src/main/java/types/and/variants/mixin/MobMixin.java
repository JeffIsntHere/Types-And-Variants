package types.and.variants.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import types.and.variants.GetTypeAndVariants;
import types.and.variants.TypesAndVariants;
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
        if(this.type.id() == Common.invalid.id())
        {
            TypesAndVariants.logger.debug("entity " + this.uuid);
            TypesAndVariants.logger.debug("type id! " + this.type.id());
            TypesAndVariants.logger.debug("invalid id! " + Common.invalid.id());
            TypesAndVariants.logger.debug("creating new type! " + this.type);
            this.type = TypeUtil.instance.getCommon(super.random, this);
            this.type.init(this);
        }
        if(this.variant.id() == Common.invalid.id())
        {
            TypesAndVariants.logger.debug("entity " + this.uuid);
            TypesAndVariants.logger.debug("variant id! " + this.variant.id());
            TypesAndVariants.logger.debug("invalid id! " + Common.invalid.id());
            TypesAndVariants.logger.debug("creating new variant! " + this.variant);
            this.variant = VariantUtil.instance.getCommon(super.random, this);
            this.variant.init(this);
        }
        this.type.tick(this);
        this.variant.tick(this);
    }
    @Inject(method="addAdditionalSaveData",at=@At("HEAD"))
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo callbackInfo)
    {
        TypesAndVariants.logger.debug("saving!" + this.uuid);
        compoundTag.putInt("type", this.type.id());
        compoundTag.putInt("variant", this.variant.id());
    }
    @Inject(method="readAdditionalSaveData",at=@At("HEAD"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo callbackInfo)
    {
        TypesAndVariants.logger.debug("reading! " + this.uuid);
        this.type = TypeUtil.instance.getCommon(compoundTag.getInt("type"));
        TypesAndVariants.logger.debug("found! type " + this.type);
        this.variant = VariantUtil.instance.getCommon(compoundTag.getInt("variant"));
        TypesAndVariants.logger.debug("found! variant " + this.variant);
        TypesAndVariants.logger.debug("stopped reading! " + this.uuid);
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
