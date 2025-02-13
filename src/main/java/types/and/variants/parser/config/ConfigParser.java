package types.and.variants.parser.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import types.and.variants.TypesAndVariants;
import types.and.variants.parser.File;
import types.and.variants.parser.Parser;
import types.and.variants.parser.config.type.TypeArgument;
import types.and.variants.program.TypeUtil;
import types.and.variants.program.VariantUtil;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ConfigParser extends Parser
{
    public static final ConfigParser instance = new ConfigParser();
    protected static final ArrayList<Config> configCache = new ArrayList<>();
    protected Config config = null;
    private ConfigParser(){}
    @Override
    protected void process()
    {
        if(super.parentCount == 0)
        {
            final String string = super.getKeyUntilOpen();
            this.config = this.getConfigFromCache(string);
            if(this.config == null)
            {
                this.config = new Config(string);
                if(!string.isEmpty())
                {
                    ConfigParser.configCache.add(this.config);
                }
            }
            this.config.clear();
            super.parentCount++;
        }
        else if(super.parentCount == 1)
        {
            final String left = super.getKeyUntilOpenOrClose();
            if(super.bufferedReaderWrapper.character == '}')
            {
                this.config = null;
                super.parentCount = 0;
                return;
            }
            final String right = super.parseValueToKey();
            this.config.add(new StringPair(left,right));
        }
    }
    public ArrayList<Config> getConfigCache()
    {
        return ConfigParser.configCache;
    }
    @Nullable
    protected Config getConfigFromCache(@NotNull final String name)
    {
        final char[] chars = name.toCharArray();
        ArrayList<Config> past;
        ArrayList<Config> present = ConfigParser.configCache;
        int currentIndex = 0;
        if(chars.length == 0)
        {
            return null;
        }
        TypesAndVariants.logger.debug("searching for config: " + name);
        do
        {
            past = present;
            present = new ArrayList<>();
            for(final Config config : past)
            {
                if(currentIndex >= config.name.length())
                {
                    continue;
                }
                if(config.name.charAt(currentIndex) == chars[currentIndex])
                {
                    present.add(config);
                }
            }
            currentIndex++;
        }
        while(!present.isEmpty() && (currentIndex < chars.length));
        return (present.isEmpty()) ? ((currentIndex < chars.length) ? null : past.getFirst()) : present.getFirst();
    }
    public void reload()
    {
        TypeUtil.instance.commons.clear();
        VariantUtil.instance.commons.clear();
        final File file = new File();
        final Reader reader = file.getFileReader("config.txt");
        super.parseFromInput(reader);
        File.closeReader(reader);
        for(final Config config : ConfigParser.configCache)
        {
            config.process();
        }
    }
    public void registerConfig(@NotNull final String name, @NotNull final TypeArgument... arguments)
    {
        final Config result = this.getConfigFromCache(name);
        final List<TypeArgument> typeArguments = List.of(arguments);
        if(result == null)
        {
            final Config deadConfig = new Config(name);
            deadConfig.typeArguments.addAll(typeArguments);
            ConfigParser.configCache.add(deadConfig);
            return;
        }
        result.typeArguments.addAll(typeArguments);
        result.process();
    }
}
