package types.and.variants.program.variant;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import types.and.variants.program.Common;

public class Fast extends Common
{
    public static final Fast instance = new Fast();
    @Override
    public float chance()
    {
        return 0.05f;
    }
    @Override
    public int id()
    {
        return 2;
    }
    @Override
    public void init(LivingEntity livingEntity)
    {
        final AttributeMap attributeMap = livingEntity.getAttributes();
        final AttributeInstance speedAttribute = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        if(speedAttribute != null)
        {
            speedAttribute.setBaseValue(speedAttribute.getBaseValue() * 1.5d);
        }
    }
}
