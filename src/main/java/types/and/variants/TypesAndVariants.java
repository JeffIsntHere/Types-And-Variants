package types.and.variants;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Pre;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Post;
import org.slf4j.Logger;

@Mod(TypesAndVariants.modId)
public class TypesAndVariants
{
    public static final String modId = "types_and_variants";
    public static final Logger logger = LogUtils.getLogger();
    public TypesAndVariants(IEventBus iEventBus)
    {
        NeoForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void livingDamageEvent(Pre livingDamageEvent)
    {
        if(livingDamageEvent.getEntity() instanceof GetTypeAndVariants getTypeAndVariants)
        {
            getTypeAndVariants.type().damage(livingDamageEvent);
            getTypeAndVariants.variant().damage(livingDamageEvent);
        }
    }
    @SubscribeEvent
    public void livingDamageEvent(Post livingDamageEvent)
    {
        if(livingDamageEvent.getEntity() instanceof GetTypeAndVariants getTypeAndVariants)
        {
            getTypeAndVariants.type().damage(livingDamageEvent);
            getTypeAndVariants.variant().damage(livingDamageEvent);
        }
    }
}
