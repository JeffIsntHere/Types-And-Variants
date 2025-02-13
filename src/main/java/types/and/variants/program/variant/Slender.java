package types.and.variants.program.variant;

import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import types.and.variants.parser.config.type.FloatType;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Slender extends Common
{
    public static final Slender instance = new Slender();
    public static final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("all")));
    public static final FloatType weight = new FloatType("weight", 0.05f);
    @Override
    public float chance()
    {
        return Slender.weight.value;
    }
    @Override
    public int id()
    {
        return 6;
    }
    @Override
    public void damage(LivingDamageEvent.Pre livingDamageEvent)
    {
        if(livingDamageEvent.getEntity().getRandom().nextBoolean())
        {
            livingDamageEvent.getContainer().setNewDamage(0.0f);
        }
    }
}
