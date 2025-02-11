package types.and.variants.program.variant;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import types.and.variants.program.Common;

public class Frail extends Common
{
    public static final Frail instance = new Frail();
    @Override
    public float chance()
    {
        return 0.05f;
    }
    @Override
    public int id()
    {
        return 4;
    }
    @Override
    public void init(LivingEntity livingEntity)
    {
        final AttributeMap attributeMap = livingEntity.getAttributes();
        final AttributeInstance healthAttribute = attributeMap.getInstance(Attributes.MAX_HEALTH);
        if(healthAttribute != null)
        {
            healthAttribute.setBaseValue(healthAttribute.getBaseValue() * 0.7d);
        }
    }
}
