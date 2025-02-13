package types.and.variants.program.variant;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import types.and.variants.parser.config.type.FloatType;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Frail extends Common
{
    public static final Frail instance = new Frail();
    public static final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("all")));
    public static final FloatType weight = new FloatType("weight", 0.1f);
    @Override
    public float chance()
    {
        return Frail.weight.value;
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
