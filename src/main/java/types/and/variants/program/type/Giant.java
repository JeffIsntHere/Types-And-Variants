package types.and.variants.program.type;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import types.and.variants.parser.config.type.FloatType;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Giant extends Common
{
    public static final Giant instance = new Giant();
    public static final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("all")));
    public static final FloatType weight = new FloatType("weight", 0.05f);
    @Override
    public float chance()
    {
        return Giant.weight.value;
    }
    @Override
    public int id()
    {
        return 1;
    }
    @Override
    public void init(LivingEntity livingEntity)
    {
        final AttributeMap attributeMap = livingEntity.getAttributes();
        final AttributeInstance scaleAttribute = attributeMap.getInstance(Attributes.SCALE);
        final AttributeInstance attackAttribute = attributeMap.getInstance(Attributes.ATTACK_DAMAGE);
        final AttributeInstance healthAttribute = attributeMap.getInstance(Attributes.MAX_HEALTH);
        final AttributeInstance speedAttribute = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        final AttributeInstance knockBackAttribute = attributeMap.getInstance(Attributes.ATTACK_KNOCKBACK);
        if(scaleAttribute != null)
        {
            scaleAttribute.setBaseValue(scaleAttribute.getBaseValue() * 1.5d);
        }
        if(attackAttribute != null)
        {
            attackAttribute.setBaseValue(attackAttribute.getBaseValue() * 2d);
        }
        if(healthAttribute != null)
        {
            healthAttribute.setBaseValue(healthAttribute.getBaseValue() * 4d);
        }
        if(speedAttribute != null)
        {
            speedAttribute.setBaseValue(speedAttribute.getBaseValue() * 1.5d);
        }
        if(knockBackAttribute != null)
        {
            knockBackAttribute.setBaseValue(1.0d);
        }
    }
}
