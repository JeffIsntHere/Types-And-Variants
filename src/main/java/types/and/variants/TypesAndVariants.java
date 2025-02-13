package types.and.variants;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Pre;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Post;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import types.and.variants.parser.config.ConfigParser;
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.parser.config.type.TypeArgument;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;

import java.util.ArrayList;
import java.util.List;

@Mod(TypesAndVariants.modId)
public class TypesAndVariants
{
    public static final String modId = "types_and_variants";
    public static final Logger logger = LogUtils.getLogger();
    /*
    ALL = all living entities except the player.
    MONSTER = only monsters.
    NEUTRAL = only neutral mobs (bees, endermen, etc.)
    ANIMAL = only animals.
     */
    public TypesAndVariants(IEventBus iEventBus)
    {
        NeoForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void serverStartingEvent(ServerStartingEvent serverStartingEvent)
    {
        ConfigParser.instance.registerConfig("Giants",
                new TypeArgument(TypeUtil.enableGiant));
        ConfigParser.instance.registerConfig("Joe",
                new TypeArgument(TypeUtil.enableJoe));
        ConfigParser.instance.registerConfig("Tanky",
                new TypeArgument(VariantUtil.enableTanky));
        ConfigParser.instance.registerConfig("Fast",
                new TypeArgument(VariantUtil.enableFast));
        ConfigParser.instance.registerConfig("Weak",
                new TypeArgument(VariantUtil.enableWeak));
        ConfigParser.instance.registerConfig("Frail",
                new TypeArgument(VariantUtil.enableFrail));
        ConfigParser.instance.registerConfig("Slow",
                new TypeArgument(VariantUtil.enableSlow));
        ConfigParser.instance.registerConfig("Slender",
                new TypeArgument(VariantUtil.enableSlender));
        ConfigParser.instance.registerConfig("Strange",
                new TypeArgument(VariantUtil.enableStrange));
        ConfigParser.instance.registerConfig("Strong",
                new TypeArgument(VariantUtil.enableStrong));
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
