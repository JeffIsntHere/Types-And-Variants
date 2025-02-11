package types.and.variants.program.variant;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import types.and.variants.program.Common;

public class Slender extends Common
{
    public static final Slender instance = new Slender();
    @Override
    public float chance()
    {
        return 0.05f;
    }
    @Override
    public int id()
    {
        return 6;
    }
    @Override
    public void damage(LivingDamageEvent livingDamageEvent)
    {
        if(livingDamageEvent.getEntity().getRandom().nextBoolean())
        {
            livingDamageEvent.setCanceled(true);
        }
    }
}
