package types.and.variants.program;

import types.and.variants.parser.config.type.BooleanType;
import types.and.variants.program.variant.*;

public class VariantUtil extends CommonUtil
{
    public static final VariantUtil instance = new VariantUtil();
    public static final BooleanType enableTanky = new BooleanType("enable", true);
    public static final BooleanType enableFast = new BooleanType("enable", true);
    public static final BooleanType enableWeak = new BooleanType("enable", true);
    public static final BooleanType enableFrail = new BooleanType("enable", true);
    public static final BooleanType enableSlow = new BooleanType("enable", true);
    public static final BooleanType enableSlender = new BooleanType("enable", true);
    public static final BooleanType enableStrange = new BooleanType("enable", true);
    public static final BooleanType enableStrong = new BooleanType("enable", true);
    private VariantUtil(){}
    public void setTypes()
    {
        if(enableTanky.value)
        {
            super.addCommon(Tanky.instance, Tanky.types);
        }
        if(enableFast.value)
        {
            super.addCommon(Fast.instance, Fast.types);
        }
        if(enableWeak.value)
        {
            super.addCommon(Weak.instance, Weak.types);
        }
        if(enableFrail.value)
        {
            super.addCommon(Frail.instance, Frail.types);
        }
        if(enableSlow.value)
        {
            super.addCommon(Slow.instance, Slow.types);
        }
        if(enableSlender.value)
        {
            super.addCommon(Slender.instance, Slender.types);
        }
        if(enableStrange.value)
        {
            super.addCommon(Strange.instance, Strange.types);
        }
        if(enableStrong.value)
        {
            super.addCommon(Strong.instance, Strong.types);
        }
    }
}
