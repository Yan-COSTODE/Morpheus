package com.noidern.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record UnlearnItemEvent(
        @Nonnull Ref<EntityStore> playerRef,
        String id
) implements IEvent<Void>
{
    public static void dispatch(Ref<EntityStore> _playerRef, String _id)
    {
        IEventDispatcher<UnlearnItemEvent, UnlearnItemEvent> dispatcher = HytaleServer.get().getEventBus().dispatchFor(UnlearnItemEvent.class);

        if (dispatcher.hasListener())
            dispatcher.dispatch(new UnlearnItemEvent(_playerRef, _id));
    }
}
