package types.and.variants.program.variant;

import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import types.and.variants.program.Common;

public class Strange extends Common
{
    public static final Strange instance = new Strange();
    @Override
    public float chance()
    {
        return 0.05f;
    }
    @Override
    public int id()
    {
        return 7;
    }
    @Override
    public void damage(LivingDamageEvent.Pre livingDamageEvent)
    {
        livingDamageEvent.setNewDamage(2.0f);
        livingDamageEvent.getContainer().setReduction(DamageContainer.Reduction.ARMOR, 0.0f);
    }
}
