package types.and.variants;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
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
import types.and.variants.parser.config.type.StringArrayType;
import types.and.variants.parser.config.type.TypeArgument;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;
import types.and.variants.program.type.Giant;
import types.and.variants.program.type.Joe;
import types.and.variants.program.variant.*;

import java.util.ArrayList;
import java.util.List;

@Mod(TypesAndVariants.modId)
public class TypesAndVariants
{
    public static final String modId = "types_and_variants";
    public static final Logger logger = LogUtils.getLogger();
    public static final StringArrayType types = new StringArrayType("types", new ArrayList<>());
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
        ConfigParser.instance.registerConfig("Common",
                new TypeArgument(TypesAndVariants.types));
        ConfigParser.instance.registerConfig("Giant",
                new TypeArgument(TypeUtil.enableGiant),
                new TypeArgument(Giant.types));
        ConfigParser.instance.registerConfig("Joe",
                new TypeArgument(TypeUtil.enableJoe),
                new TypeArgument(Joe.types));
        ConfigParser.instance.registerConfig("Tanky",
                new TypeArgument(VariantUtil.enableTanky),
                new TypeArgument(Tanky.types));
        ConfigParser.instance.registerConfig("Fast",
                new TypeArgument(VariantUtil.enableFast),
                new TypeArgument(Fast.types));
        ConfigParser.instance.registerConfig("Weak",
                new TypeArgument(VariantUtil.enableWeak),
                new TypeArgument(Weak.types));
        ConfigParser.instance.registerConfig("Frail",
                new TypeArgument(VariantUtil.enableFrail),
                new TypeArgument(Frail.types));
        ConfigParser.instance.registerConfig("Slow",
                new TypeArgument(VariantUtil.enableSlow),
                new TypeArgument(Slow.types));
        ConfigParser.instance.registerConfig("Slender",
                new TypeArgument(VariantUtil.enableSlender),
                new TypeArgument(Slender.types));
        ConfigParser.instance.registerConfig("Strange",
                new TypeArgument(VariantUtil.enableStrange),
                new TypeArgument(Strange.types));
        ConfigParser.instance.registerConfig("Strong",
                new TypeArgument(VariantUtil.enableStrong),
                new TypeArgument(Strong.types));
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
