package types.and.variants.program.type;

import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.program.Common;

import java.util.ArrayList;
import java.util.List;

public class Joe extends Common
{
    public static final Joe instance = new Joe();
    public final StringArrayType types = new StringArrayType("types", new ArrayList<>(List.of("ALL")));
    @Override
    public float chance()
    {
        return 0.1f;
    }
    @Override
    public int id()
    {
        return 2;
    }
    @Override
    public Common create()
    {
        return new Joe();
    }
    public int cooldown = 1;
    @Override
    public void damage(LivingDamageEvent.Post livingDamageEvent)
    {
        cooldown--;
        if(cooldown != 0)
        {
            return;
        }
        cooldown = 200;
        final LivingEntity livingEntity = livingDamageEvent.getEntity();
        final AreaEffectCloud areaEffectCloud = new AreaEffectCloud(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        areaEffectCloud.setRadius(3.0F);
        areaEffectCloud.setRadiusOnUse(-0.5F);
        areaEffectCloud.setWaitTime(10);
        areaEffectCloud.setDuration(60);
        areaEffectCloud.setRadiusPerTick(-areaEffectCloud.getRadius() / (float)areaEffectCloud.getDuration());
        areaEffectCloud.setPotionContents(new PotionContents(Potions.POISON));
        livingEntity.level().addFreshEntity(areaEffectCloud);
    }
}
