package types.and.variants.program;

import types.and.variants.parser.config.type.BooleanType;
import types.and.variants.program.type.Giant;
import types.and.variants.program.type.Joe;

public class TypeUtil extends CommonUtil
{
    public static final TypeUtil instance = new TypeUtil();
    public static final BooleanType enableGiant = new BooleanType("enable", true);
    public static final BooleanType enableJoe = new BooleanType("enable", true);
    private TypeUtil(){}
    @Override
    public void setTypes()
    {
        if(enableGiant.value)
        {
            super.addCommon(Giant.instance, Giant.types);
        }
        if(enableJoe.value)
        {
            super.addCommon(Joe.instance, Joe.types);
        }
    }
}
