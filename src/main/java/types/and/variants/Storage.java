package types.and.variants;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import types.and.variants.program.Common;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;

public class Storage
{
    public final LivingEntity mob;
    protected Common type = Common.invalid;
    protected Common variant = Common.invalid;
    protected boolean typeInit = false;
    protected boolean variantInit = false;
    protected void initType()
    {
        this.type = TypeUtil.instance.getCommon(this.mob.getRandom(), this.mob);
        if(!this.mob.level().isClientSide())
        {
            this.type.init(this.mob);
        }
        this.typeInit = true;
    }
    protected void initVariant()
    {
        this.variant = VariantUtil.instance.getCommon(this.mob.getRandom(), this.mob);
        if(!this.mob.level().isClientSide())
        {
            this.variant.init(this.mob);
        }
        this.variantInit = true;
    }
    public void addAdditionalSaveData(CompoundTag compoundTag)
    {
        compoundTag.putInt("type", this.type.id());
        compoundTag.putInt("variant", this.variant.id());
    }
    public void readAdditionalSaveData(CompoundTag compoundTag)
    {
        this.type = TypeUtil.instance.getCommon(compoundTag.getInt("type"));
        this.variant = VariantUtil.instance.getCommon(compoundTag.getInt("variant"));
        if(this.type == Common.invalid)
        {
            this.initType();
        }
        if(this.variant == Common.invalid)
        {
            this.initVariant();
        }
        this.typeInit = true;
        this.variantInit = true;
    }
    public void tick()
    {
        if(!this.typeInit)
        {
            this.initType();
        }
        if(!this.variantInit)
        {
            this.initVariant();
        }
        this.type.tick(this.mob);
        this.variant.tick(this.mob);
    }
    public Common type()
    {
        if(!this.typeInit)
        {
            this.initType();
        }
        return this.type;
    }
    public Common variant()
    {
        if(!this.variantInit)
        {
            this.initVariant();
        }
        return this.variant;
    }
    public Storage(final LivingEntity livingEntity)
    {
        this.mob = livingEntity;
    }
}
