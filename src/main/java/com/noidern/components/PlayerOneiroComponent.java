package com.noidern.components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.array.ArrayCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerOneiroComponent implements Component<EntityStore>
{
    private static ComponentType<EntityStore, PlayerOneiroComponent> TYPE;

    public static void setComponentType(ComponentType<EntityStore, PlayerOneiroComponent> _type)
    {
        TYPE = _type;
    }

    public static ComponentType<EntityStore, PlayerOneiroComponent> getComponentType()
    {
        return TYPE;
    }

    public static final BuilderCodec<PlayerOneiroComponent> CODEC = BuilderCodec
            .builder(PlayerOneiroComponent.class, PlayerOneiroComponent::new)
            .append(new KeyedCodec<>("Oneiro", Codec.LONG),
                    (component, value) -> component.oneiro = value,
                    (component) -> component.oneiro)
            .add()
            .append(new KeyedCodec<>("Items", new ArrayCodec<>(Codec.STRING, String[]::new)),
                    PlayerOneiroComponent::setItemsArray,
                    PlayerOneiroComponent::getItemsArray)
            .add()
            .build();

    private long oneiro = 0;
    private ArrayList<String> items = new ArrayList<>();

    public PlayerOneiroComponent()
    {
    }

    public PlayerOneiroComponent(long _oneiro, ArrayList<String> _items)
    {
        oneiro = Math.max(0L, _oneiro);
        items = _items;
    }

    public void setOneiro(long _amount)
    {
        oneiro = _amount;
    }

    public long getOneiro()
    {
        return oneiro;
    }

    public String getOneiroDisplay()
    {
        if (oneiro >= 1000000000)
            return String.format("%.2fB", oneiro / 1000000000.0);
        if (oneiro >= 1000000)
            return String.format("%.2fM", oneiro / 1000000.0);
        if (oneiro >= 1000)
            return String.format("%.2fK", oneiro / 1000.0);
        else
            return String.valueOf(oneiro);
    }

    public ArrayList<String> getItems()
    {
        return items;
    }

    private void setItemsArray(String[] _items)
    {
        items.clear();
        items.addAll(Arrays.asList(_items));
    }

    private String[] getItemsArray()
    {
        if (items.isEmpty())
            return new String[0];

        return items.toArray(new String[0]);
    }

    public void setItems(ArrayList<String> _items)
    {
        items = _items;
    }

    public void addOneiro(long _amount)
    {
        oneiro += _amount;
    }

    public void learnItem(String _id)
    {
        if (items.contains(_id))
            return;

        items.add(_id);
    }

    public void unlearnItem(String _id)
    {
        if (!items.contains(_id))
            return;

        items.remove(_id);
    }

    @NullableDecl
    @Override
    public PlayerOneiroComponent clone()
    {
        return new PlayerOneiroComponent(oneiro, items);
    }

    @Override
    public String toString()
    {
        return "PlayerOneiroComponent{oneiro=" + getOneiro() +
                ", totalItems=" + getItems().size() + "}";
    }
}
