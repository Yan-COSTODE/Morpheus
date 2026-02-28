package com.noidern.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record SetOneiroEvent(
        @Nonnull Ref<EntityStore> playerRef,
        long amount
) implements IEvent<Void>
{
    public static void dispatch(Ref<EntityStore> _playerRef, long _amount)
    {
        IEventDispatcher<SetOneiroEvent, SetOneiroEvent> dispatcher = HytaleServer.get().getEventBus().dispatchFor(SetOneiroEvent.class);

        if (dispatcher.hasListener())
            dispatcher.dispatch(new SetOneiroEvent(_playerRef, _amount));
    }
}
