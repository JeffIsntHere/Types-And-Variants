package types.and.variants.program;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import types.and.variants.TypesAndVariants;
import types.and.variants.parser.config.type.StringArrayType;

import java.util.ArrayList;

public abstract class CommonUtil
{
    public ArrayList<Common> commons = new ArrayList<>();
    protected void addIfAllowed(final LivingEntity livingEntity, final StringArrayType stringArrayType)
    {
        final String name = livingEntity.getEncodeId();
        boolean all = false;
        boolean monster = false;
        boolean neutral = false;
        boolean animal = false;
        boolean found = false;
        for(String string : stringArrayType.value)
        {
            if(string.equals("all"))
            {
                all = !all;
            }
            else if(string.equals("monster"))
            {
                monster = !monster;
            }
            else if(string.equals("neutral"))
            {
                neutral = !neutral;
            }
            else if(string.equals("animal"))
            {
                animal = !animal;
            }
            else if(string.equals(name))
            {
                found = !found;
            }
        }
    }
    public abstract void setTypes();
    public void init()
    {
        if(commons.isEmpty())
        {
            this.commons.add(Normal.instance);
            this.setTypes();
        }
    }
    public Common getCommon(final RandomSource randomSource)
    {
        this.init();
        float sum = 0.0f;
        for(Common common : this.commons)
        {
            sum += common.chance();
        }
        float randomNumber = randomSource.nextFloat();
        float runningSum = 0.0f;
        int index = 0;
        for(Common common : this.commons)
        {
            runningSum += (common.chance() / sum);
            if(runningSum >= randomNumber)
            {
                TypesAndVariants.logger.debug(common.getClass().getCanonicalName() + " chance was! " + common.chance() + " id was! " + common.id());
                return common.create();
            }
        }
        return Common.invalid;
    }
    public Common getCommon(final int id)
    {
        this.init();
        if(id != 0)
        {
            for(Common common : this.commons)
            {
                if(common.id() == id)
                {
                    return common.create();
                }
            }
        }
        return Common.invalid;
    }
}
