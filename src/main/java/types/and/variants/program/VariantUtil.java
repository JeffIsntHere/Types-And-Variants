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
            super.commons.add(Tanky.instance);
        }
        if(enableFast.value)
        {
            super.commons.add(Fast.instance);
        }
        if(enableWeak.value)
        {
            super.commons.add(Weak.instance);
        }
        if(enableFrail.value)
        {
            super.commons.add(Frail.instance);
        }
        if(enableSlow.value)
        {
            super.commons.add(Slow.instance);
        }
        if(enableSlender.value)
        {
            super.commons.add(Slender.instance);
        }
        if(enableStrange.value)
        {
            super.commons.add(Strange.instance);
        }
        if(enableStrong.value)
        {
            super.commons.add(Strong.instance);
        }
    }
}
