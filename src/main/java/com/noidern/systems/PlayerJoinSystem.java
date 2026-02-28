package com.noidern.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.ModifyOneiroEvent;
import com.noidern.pages.MorpheusHUDPage;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class PlayerJoinSystem extends RefSystem<EntityStore>
{
    @Override
    public void onEntityAdded(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl AddReason addReason, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer)
    {
        if (addReason != AddReason.LOAD)
            return;

        var _playerRef = store.getComponent(ref, PlayerRef.getComponentType());

        if (_playerRef == null)
            return;

        var _oneiroType = PlayerOneiroComponent.getComponentType();
        var _oneiro = store.getComponent(ref, _oneiroType);

        if (_oneiro == null)
            commandBuffer.addComponent(ref, _oneiroType, new PlayerOneiroComponent());

        ModifyOneiroEvent.dispatch(ref);
    }

    @Override
    public void onEntityRemove(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl RemoveReason removeReason, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer)
    {
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery()
    {
        return PlayerRef.getComponentType();
    }
}
