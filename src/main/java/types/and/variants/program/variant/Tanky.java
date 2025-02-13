package types.and.variants.program.variant;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Tanky extends Common
{
    public static final Tanky instance = new Tanky();
    public final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("ALL")));
    @Override
    public float chance()
    {
        return 0.05f;
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
        final AttributeInstance healthAttribute = attributeMap.getInstance(Attributes.MAX_HEALTH);
        if(healthAttribute != null)
        {
            healthAttribute.setBaseValue(healthAttribute.getBaseValue() * 1.5d);
        }
    }
}
