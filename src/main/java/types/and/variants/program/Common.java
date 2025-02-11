package types.and.variants.program;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class Common
{
    public static final Common invalid = new Common();
    public int id(){return 0;}
    public float chance(){return 1.0f;}
    public void init(LivingEntity livingEntity){}
    public void damage(LivingDamageEvent.Pre livingDamageEvent){}
    public void damage(LivingDamageEvent.Post livingDamageEvent){}
    public void tick(LivingEntity livingEntity){}
    //the create function is for those cases where you need to store
    //extra data on the entity.
    public Common create(){return this;}
}
