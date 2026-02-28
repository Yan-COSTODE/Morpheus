package com.noidern.events;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.PlaceBlockEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.noidern.components.MorpheusBloomComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class MorpheusPlaceBlockEvent extends EntityEventSystem<EntityStore, PlaceBlockEvent>
{

    protected MorpheusPlaceBlockEvent() {
        super(PlaceBlockEvent.class);
    }

    @Override
    public void handle(int id, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer, @NonNull PlaceBlockEvent event)
    {
        var _ref = archetypeChunk.getReferenceTo(id);
        var _playerRef = store.getComponent(_ref, PlayerRef.getComponentType());

        if (_playerRef == null)
            return;

        var _player = store.getComponent(_ref, Player.getComponentType());
        World _world = _player.getWorld();
        var _blockType = _world.getBlockType(event.getTargetBlock());
        var _object = _blockType.getBlockEntity().getComponent(MorpheusBloomComponent.getComponentType());

        if (_object == null)
            return;

        _object.setPlayer(_playerRef.getUuid());
    }

    @Override
    public @Nullable Query<EntityStore> getQuery()
    {
        return PlayerRef.getComponentType();
    }
}
