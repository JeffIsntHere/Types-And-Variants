package types.and.variants.program;

import net.minecraft.util.RandomSource;
import types.and.variants.TypesAndVariants;

import java.util.ArrayList;

public abstract class CommonUtil
{
    public ArrayList<Common> commons = new ArrayList<>();
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
