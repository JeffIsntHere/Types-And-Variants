package types.and.variants.parser.config.type;

import java.util.ArrayList;
import java.util.List;

public class StringArrayType extends BaseType
{
    public final ArrayList<String> value;
    @Override
    public void save(String string)
    {
        this.value.clear();
        this.value.addAll(List.of(string.split(",")));
    }

    public String toString()
    {
        if(this.value.isEmpty())
        {
            return super.toString() + " : Empty.";
        }
        StringBuilder stringBuilder = new StringBuilder(super.toString() + " : ");
        for(String string : this.value)
        {
            stringBuilder.append(string).append(" ");
        }
        return stringBuilder.toString();
    }

    public StringArrayType(String name, String desc, final ArrayList<String> value)
    {
        super(name, desc);
        this.value = value;
    }

    public StringArrayType(final String name, final ArrayList<String> value)
    {
        super(name);
        this.value = value;
    }
}
