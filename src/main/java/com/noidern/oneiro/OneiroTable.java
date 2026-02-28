package com.noidern.oneiro;

import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.MaterialQuantity;
import com.hypixel.hytale.server.core.util.Config;
import com.noidern.MorpheusConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class OneiroTable
{
    private static HashMap<String, Long> ONEIRO_VALUE;
    private static Config<MorpheusConfig> config;

    private OneiroTable() {}

    public static void readConfig(Config<MorpheusConfig> _config)
    {
        config = _config;
        config.save();
        MorpheusConfig _data = config.get();
        ONEIRO_VALUE = _data.getOneiroValue();

        if (ONEIRO_VALUE == null)
            ONEIRO_VALUE = new HashMap<>();
    }

    public static void writeConfig()
    {
        MorpheusConfig _data = config.get();
        _data.setOneiroValue(ONEIRO_VALUE);
        config.save();
    }

    public static void setOneiroValue(String _id, long _amount)
    {
        ONEIRO_VALUE.put(_id, _amount);
        writeConfig();
    }

    public static Long getOneiroValue(String _id)
    {
        if (!ONEIRO_VALUE.containsKey(_id))
            return calculateOneiro(_id);

        return ONEIRO_VALUE.get(_id);
    }

    private static Long calculateOneiro(String _id)
    {
        Item _item = Item.getAssetMap().getAsset(_id);
        ArrayList<CraftingRecipe> _recipes = new ArrayList<>();

        if (_item == null)
            return 0L;

        _item.collectRecipesToGenerate(_recipes);
        _recipes.forEach(OneiroTable::calculateOneiroRecipe);

        if (!ONEIRO_VALUE.containsKey(_id))
            return 0L;

        return ONEIRO_VALUE.get(_id);
    }

    private static void calculateOneiroRecipe(CraftingRecipe _recipe)
    {
        MaterialQuantity[] _inputs = _recipe.getInput();
        MaterialQuantity[] _outputs = _recipe.getOutputs();

        long _totalOneiro = 0L;

        for (int i = 0; i < _inputs.length; i++)
        {
            MaterialQuantity _input = _inputs[0];
            Long _value = getOneiroValue(_input.getItemId());

            if (_value == 0L)
                return;

            _totalOneiro += _value * _input.getQuantity();
        }

        MaterialQuantity _output = _outputs[0];
        _totalOneiro /= _output.getQuantity();
        ONEIRO_VALUE.put(_output.getItemId(), _totalOneiro);
    }
}
