package types.and.variants.parser.config.type;

public class FloatType extends BaseType
{
    public float value;

    @Override
    public void save(String string)
    {
        final float oldValue = this.value;
        try
        {
            this.value = Float.valueOf(string);
        }
        catch (NumberFormatException e)
        {
            this.value = oldValue;
        }
    }

    public String toString()
    {
        return super.toString() + " : " + this.value;
    }

    public FloatType(final String name, final String desc, final float value)
    {
        super(name, desc);
        this.value = value;
    }

    public FloatType(final String name, final float value)
    {
        super(name);
        this.value = value;
    }
}
