package types.and.variants;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import types.and.variants.program.Common;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;

public class Storage
{
    public final LivingEntity mob;
    protected Common type;
    protected Common variant;
    protected boolean initialized;
    protected void init()
    {
        this.type = TypeUtil.instance.getCommon(this.mob.getRandom(), this.mob);
        this.type.init(this.mob);
        this.variant = VariantUtil.instance.getCommon(this.mob.getRandom(), this.mob);
        this.variant.init(this.mob);
        this.initialized = true;
    }
    public void addAdditionalSaveData(CompoundTag compoundTag)
    {
        compoundTag.putInt("type", this.type.id());
        compoundTag.putInt("variant", this.variant.id());
    }
    public void readAdditionalSaveData(CompoundTag compoundTag)
    {
        this.initialized = true;
        this.type = TypeUtil.instance.getCommon(compoundTag.getInt("type"));
        this.variant = VariantUtil.instance.getCommon(compoundTag.getInt("variant"));
        if(this.type == null || this.variant == null)
        {
            this.initialized = false;
        }
    }
    public void tick()
    {
        if(!this.initialized)
        {
            this.init();
        }
        this.type.tick(this.mob);
        this.variant.tick(this.mob);
    }
    public Common type()
    {
        if(!this.initialized)
        {
            this.init();
        }
        return this.type;
    }
    public Common variant()
    {
        if(!this.initialized)
        {
            this.init();
        }
        return this.variant;
    }
    public Storage(final LivingEntity livingEntity)
    {
        this.mob = livingEntity;
    }
}
