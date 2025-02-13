package types.and.variants.program.variant;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Weak extends Common
{
    public static final Weak instance = new Weak();
    public final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("ALL")));
    @Override
    public float chance()
    {
        return 0.05f;
    }
    @Override
    public int id()
    {
        return 3;
    }
    @Override
    public void init(LivingEntity livingEntity)
    {
        final AttributeMap attributeMap = livingEntity.getAttributes();
        final AttributeInstance attackAttribute = attributeMap.getInstance(Attributes.ATTACK_DAMAGE);
        if(attackAttribute != null)
        {
            attackAttribute.setBaseValue(attackAttribute.getBaseValue() * 0.7d);
        }
    }
}
