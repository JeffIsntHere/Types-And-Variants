package types.and.variants.program;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import types.and.variants.TypesAndVariants;
import types.and.variants.parser.config.type.StringArrayType;

import java.util.ArrayList;

public abstract class CommonUtil
{
    public final ArrayList<Pair<Common, StringArrayType>> commons = new ArrayList<>();
    protected boolean checkIfAllowed(final LivingEntity livingEntity, final StringArrayType stringArrayType)
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
        if(all)
        {
            monster = !monster;
            neutral = !neutral;
            animal = !animal;
        }
        if(monster && livingEntity instanceof Monster)
        {
            found = !found;
        }
        else if(neutral && livingEntity instanceof NeutralMob)
        {
            found = !found;
        }
        else if(animal && livingEntity instanceof Animal)
        {
            found = !found;
        }
        return found;
    }
    public abstract void setTypes();
    public void addCommon(final Common common, final StringArrayType stringArrayType)
    {
        this.commons.add(new Pair<Common, StringArrayType>(common,stringArrayType));
    }
    public ArrayList<Common> getValidCommons(final LivingEntity livingEntity)
    {
        final ArrayList<Common> common = new ArrayList<>();
        common.add(Normal.instance);
        for(Pair<Common, StringArrayType> pair : this.commons)
        {
            if(this.checkIfAllowed(livingEntity, pair.getSecond()))
            {
                common.add(pair.getFirst());
            }
        }
        return common;
    }
    public void init()
    {
        if(commons.isEmpty())
        {
            this.setTypes();
        }
    }
    public Common getCommon(final RandomSource randomSource, final LivingEntity livingEntity)
    {
        this.init();
        float sum = 0.0f;
        final ArrayList<Common> commons = this.getValidCommons(livingEntity);
        for(Common common : commons)
        {
            sum += common.chance();
        }
        float randomNumber = randomSource.nextFloat();
        float runningSum = 0.0f;
        for(Common common : commons)
        {
            runningSum += (common.chance() / sum);
            if(runningSum >= randomNumber)
            {
                return common.create();
            }
        }
        return Common.invalid;
    }
    public Common getCommon(final int id)
    {
        this.init();
        if(id == -1)
        {
            return Normal.instance;
        }
        if(id != 0)
        {
            for(Pair<Common, StringArrayType> pair : this.commons)
            {
                final Common common = pair.getFirst();
                if(common.id() == id)
                {
                    TypesAndVariants.logger.debug("found!: " + common);
                    TypesAndVariants.logger.debug("id: " + common.id());
                    TypesAndVariants.logger.debug("create: " + common.create());
                    TypesAndVariants.logger.debug("id from create: " + common.create().id());
                    return common.create();
                }
            }
        }
        return Common.invalid;
    }
}
