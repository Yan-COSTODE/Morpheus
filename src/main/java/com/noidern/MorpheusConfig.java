package com.noidern;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.map.MapCodec;

import java.util.HashMap;
import java.util.Map;

public class MorpheusConfig
{
    public static final BuilderCodec<MorpheusConfig> CODEC = BuilderCodec
            .builder(MorpheusConfig.class, MorpheusConfig::new)
            .append(new KeyedCodec<>("OneiroValue", new MapCodec<>(Codec.LONG, HashMap<String, Long>::new)),
                    MorpheusConfig::setValue,
                    MorpheusConfig::getValue)
            .add()
            .build();

    private HashMap<String, Long> OneiroValue;

    public MorpheusConfig()
    {
        OneiroValue = new HashMap<>();
    }

    private void setValue(Map<String, Long> _values)
    {
        OneiroValue = new HashMap<>(_values);
    }

    private Map<String, Long> getValue()
    {
        return OneiroValue;
    }

    public HashMap<String, Long> getOneiroValue()
    {
        return OneiroValue;
    }

    public void setOneiroValue(HashMap<String, Long> _oneiroValues)
    {
        OneiroValue = _oneiroValues;
    }
}
