package types.and.variants.program.variant;

import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Strange extends Common
{
    public static final Strange instance = new Strange();
    public final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("ALL")));
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
