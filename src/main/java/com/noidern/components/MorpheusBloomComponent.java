package com.noidern.components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MorpheusBloomComponent implements Component<ChunkStore>
{
    private static ComponentType<ChunkStore, MorpheusBloomComponent> TYPE;

    public static void setComponentType(ComponentType<ChunkStore, MorpheusBloomComponent> _type)
    {
        TYPE = _type;
    }

    public static ComponentType<ChunkStore, MorpheusBloomComponent> getComponentType()
    {
        return TYPE;
    }

    public static final BuilderCodec<MorpheusBloomComponent> CODEC = BuilderCodec
            .builder(MorpheusBloomComponent.class, MorpheusBloomComponent::new)
            .append(new KeyedCodec<>("Amount", Codec.LONG),
                    (component, value) -> component.amount = value,
                    (component) -> component.amount)
            .add()
            .append(new KeyedCodec<>("UUID", Codec.UUID_STRING),
                    (component, value) -> component.playerUUID = value,
                    (component) -> component.playerUUID)
            .add()
            .build();

    private long amount = 1080;
    private UUID playerUUID;
    private float timer = 0;

    public MorpheusBloomComponent()
    {
    }

    public MorpheusBloomComponent(MorpheusBloomComponent _other)
    {
        amount = _other.amount;
        playerUUID = _other.playerUUID;
        timer = _other.timer;
    }

    public void setPlayer(UUID _uuid)
    {
        playerUUID = _uuid;
    }

    public UUID getPlayerUUID()
    {
        return playerUUID;
    }

    public void giveOneiro(PlayerOneiroComponent _component, float _dt)
    {
        timer += _dt;

        if (timer < 1.0f)
            return;

        timer = 0.0f;
        _component.addOneiro(amount);
    }

    @Nullable
    public Component<ChunkStore> clone()
    {
        return new MorpheusBloomComponent(this);
    }
}
