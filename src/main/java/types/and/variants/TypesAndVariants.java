package types.and.variants;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Pre;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent.Post;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import types.and.variants.parser.config.ConfigParser;
import types.and.variants.parser.config.type.TypeArgument;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;
import types.and.variants.program.type.Giant;
import types.and.variants.program.type.Joe;
import types.and.variants.program.variant.*;

@Mod(TypesAndVariants.modId)
public class TypesAndVariants
{
    public static final String modId = "types_and_variants";
    public static final Logger logger = LogUtils.getLogger();
    /*
    all = all living entities except the player.
    monster = only monsters.
    neutral = only neutral mobs (bees, endermen, etc.)
    animal = only animals.
     */
    public TypesAndVariants(IEventBus iEventBus)
    {
        NeoForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void registerCommandsEvent(RegisterCommandsEvent registerCommandsEvent)
    {
        Registry.registerCommands(registerCommandsEvent.getDispatcher());
    }
    @SubscribeEvent
    public void serverStartingEvent(ServerStartingEvent serverStartingEvent)
    {
        ConfigParser.instance.registerConfig("giant",
                new TypeArgument(TypeUtil.enableGiant),
                new TypeArgument(Giant.types),
                new TypeArgument(Giant.weight));
        ConfigParser.instance.registerConfig("joe",
                new TypeArgument(TypeUtil.enableJoe),
                new TypeArgument(Joe.types),
                new TypeArgument(Joe.weight));
        ConfigParser.instance.registerConfig("tanky",
                new TypeArgument(VariantUtil.enableTanky),
                new TypeArgument(Tanky.types),
                new TypeArgument(Tanky.weight));
        ConfigParser.instance.registerConfig("fast",
                new TypeArgument(VariantUtil.enableFast),
                new TypeArgument(Fast.types),
                new TypeArgument(Fast.weight));
        ConfigParser.instance.registerConfig("weak",
                new TypeArgument(VariantUtil.enableWeak),
                new TypeArgument(Weak.types),
                new TypeArgument(Weak.weight));
        ConfigParser.instance.registerConfig("frail",
                new TypeArgument(VariantUtil.enableFrail),
                new TypeArgument(Frail.types),
                new TypeArgument(Frail.weight));
        ConfigParser.instance.registerConfig("slow",
                new TypeArgument(VariantUtil.enableSlow),
                new TypeArgument(Slow.types),
                new TypeArgument(Slow.weight));
        ConfigParser.instance.registerConfig("slender",
                new TypeArgument(VariantUtil.enableSlender),
                new TypeArgument(Slender.types),
                new TypeArgument(Slender.weight));
        ConfigParser.instance.registerConfig("strange",
                new TypeArgument(VariantUtil.enableStrange),
                new TypeArgument(Strange.types),
                new TypeArgument(Strange.weight));
        ConfigParser.instance.registerConfig("strong",
                new TypeArgument(VariantUtil.enableStrong),
                new TypeArgument(Strong.types),
                new TypeArgument(Strong.weight));
        ConfigParser.instance.reload();
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
